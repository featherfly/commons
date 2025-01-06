
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 15:56:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import javax.sql.DataSource;

import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.dialect.creator.DerbyDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.DmDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.H2DialectURLCreator;
import cn.featherfly.common.db.dialect.creator.HSQLDBDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.KingbaseDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.MariaDBDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.MysqlDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.OracleDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.PostgreSQLDialectURLCreator;
import cn.featherfly.common.db.dialect.creator.SQLServerURLCreator;
import cn.featherfly.common.db.dialect.creator.SQLiteDialectURLCreator;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Str;

/**
 * JdbcDialectFactory.
 *
 * @author zhongj
 */
public class JdbcDialectFactory implements DialectFactory {

    private boolean exceptionWhenNoDialectCreate;

    private Set<Function<String, Dialect>> dialectCreators = new LinkedHashSet<>();

    /**
     * Instantiates a new jdbc dialect factory.
     */
    public JdbcDialectFactory() {
        this(new HashSet<>());
    }

    /**
     * Instantiates a new jdbc dialect factory.
     *
     * @param exceptionWhenNoDialectCreate the exception when no dialect create
     */
    public JdbcDialectFactory(boolean exceptionWhenNoDialectCreate) {
        this(exceptionWhenNoDialectCreate, new HashSet<>());
    }

    /**
     * Instantiates a new jdbc dialect factory.
     *
     * @param dialectCreators the dialect creators
     */
    public JdbcDialectFactory(Set<Function<String, Dialect>> dialectCreators) {
        this(true, dialectCreators);
    }

    /**
     * Instantiates a new jdbc dialect factory.
     *
     * @param exceptionWhenNoDialectCreate the exception when no dialect create
     * @param dialectCreators the dialect creators
     */
    public JdbcDialectFactory(boolean exceptionWhenNoDialectCreate, Set<Function<String, Dialect>> dialectCreators) {
        super();

        this.exceptionWhenNoDialectCreate = exceptionWhenNoDialectCreate;

        this.dialectCreators.addAll(dialectCreators);
        this.dialectCreators.add(new MysqlDialectURLCreator());
        this.dialectCreators.add(new PostgreSQLDialectURLCreator());
        this.dialectCreators.add(new SQLiteDialectURLCreator());
        this.dialectCreators.add(new OracleDialectURLCreator());

        this.dialectCreators.add(new SQLServerURLCreator());
        this.dialectCreators.add(new KingbaseDialectURLCreator());
        this.dialectCreators.add(new DmDialectURLCreator());
        this.dialectCreators.add(new MariaDBDialectURLCreator());
        this.dialectCreators.add(new H2DialectURLCreator());
        this.dialectCreators.add(new DerbyDialectURLCreator());
        this.dialectCreators.add(new HSQLDBDialectURLCreator());
    }

    /**
     * Gets the dialect creators.
     *
     * @return the dialect creators
     */
    public Set<Function<String, Dialect>> getDialectCreators() {
        return dialectCreators;
    }

    /**
     * Adds the dialect creator.
     *
     * @param dialectCreator the dialect creator
     * @return the jdbc dialect factory
     */
    public JdbcDialectFactory addDialectCreator(Function<String, Dialect> dialectCreator) {
        dialectCreators.add(dialectCreator);
        return this;
    }

    /**
     * Adds the dialect creator.
     *
     * @param dialectCreators the dialect creators
     * @return the jdbc dialect factory
     */
    public JdbcDialectFactory addDialectCreator(
        @SuppressWarnings("unchecked") Function<String, Dialect>... dialectCreators) {
        CollectionUtils.addAll(this.dialectCreators, dialectCreators);
        return this;
    }

    /**
     * Clear dialect creators.
     *
     * @return the jdbc dialect factory
     */
    public JdbcDialectFactory clearDialectCreators() {
        dialectCreators.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dialect create(DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            return create(conn);
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dialect create(Connection connection) {
        try {
            return create(connection.getMetaData());
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dialect create(DatabaseMetaData metadata) {
        try {
            String url = metadata.getURL();
            for (Function<String, Dialect> dialectCreator : dialectCreators) {
                Dialect dialect = dialectCreator.apply(url);
                if (dialect != null) {
                    return dialect;
                }
            }
            if (exceptionWhenNoDialectCreate) {
                throw new DialectException(Str.format("no dialect craete for datasource[{}]", url));
            }
        } catch (SQLException e) {
            throw new JdbcException(e);
        }
        return null;
    }
}
