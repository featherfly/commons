
package cn.featherfly.common.db.migration;

import java.util.Set;

import javax.sql.DataSource;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.SqlExecutor;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.mapping.ClassMappingUtils;
import cn.featherfly.common.db.mapping.SqlTypeMappingManager;
import cn.featherfly.common.repository.mapping.ClassMapping;

/**
 * <p>
 * Migrator
 * </p>
 * .
 *
 * @author zhongj
 */
public class Migrator {

    /** The data source. */
    private DataSource dataSource;

    /** The dialect. */
    private Dialect dialect;

    /** The sql executor. */
    private SqlExecutor sqlExecutor;

    /** The sql type mapping manager. */
    private SqlTypeMappingManager sqlTypeMappingManager;

    /**
     * Instantiates a new migrator.
     *
     * @param dataSource            the data source
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    public Migrator(DataSource dataSource, Dialect dialect, SqlTypeMappingManager sqlTypeMappingManager) {
        super();
        this.dataSource = dataSource;
        this.dialect = dialect;
        this.sqlTypeMappingManager = sqlTypeMappingManager;
        sqlExecutor = new SqlExecutor(dataSource);
    }

    /**
     * Gets the inits the sql.
     *
     * @param classMappings the class mappings
     * @return the inits the sql
     */
    public String getInitSql(Set<ClassMapping<?>> classMappings) {
        StringBuilder sql = new StringBuilder();
        sql.append(dialect.getInitSqlHeader()).append(Chars.NEW_LINE);
        for (ClassMapping<?> classMapping : classMappings) {
            sql.append(createSql(classMapping, true)).append(Chars.SEMI).append(Chars.NEW_LINE);
        }
        sql.append(dialect.getInitSqlFooter());
        return sql.toString();
    }

    /**
     * Creates the sql.
     *
     * @param classMapping the class mapping
     * @return the string
     */
    public String createSql(ClassMapping<?> classMapping) {
        return createSql(classMapping, false);
    }

    /**
     * Creates the sql.
     *
     * @param classMapping the class mapping
     * @param dropIfExists the drop if exists
     * @return the string
     */
    public String createSql(ClassMapping<?> classMapping, boolean dropIfExists) {
        if (dropIfExists) {
            dialect.buildDropTableDDL(classMapping.getRepositoryName(), true);
        }
        return new StringBuilder().append(dialect
                .buildCreateTableDDL(ClassMappingUtils.createTable(classMapping, dialect, sqlTypeMappingManager)))
                .toString();
    }

    /**
     * Creates the.
     *
     * @param classMappings the class mappings
     */
    public void create(Set<ClassMapping<?>> classMappings) {
        sqlExecutor.execute(getInitSql(classMappings));
    }
}
