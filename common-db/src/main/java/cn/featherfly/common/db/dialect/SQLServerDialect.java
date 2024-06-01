package cn.featherfly.common.db.dialect;

import cn.featherfly.common.db.dialect.ddl.SQLServerDDLFeature;
import cn.featherfly.common.db.dialect.dml.SQLServerDMLFeature;
import cn.featherfly.common.exception.NotImplementedException;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.repository.id.IdGenerator;

/**
 * SQLServer Dialect.
 *
 * @author zhongj
 */
public class SQLServerDialect extends AbstractDialect {

    private final SQLServerDDLFeature ddlFeature;

    private final SQLServerDMLFeature dmlFeature;

    /**
     * Instantiates a new my SQL
     */
    public SQLServerDialect() {
        super();
        ddlFeature = new SQLServerDDLFeature(this);
        dmlFeature = new SQLServerDMLFeature(this);
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DDLFeature ddl() {
        return ddlFeature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DMLFeature dml() {
        return dmlFeature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPaginationSql(String sql, int start, int limit) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamedParamPaginationSql(String sql, int start, int limit) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamedParamPaginationSql(String sql, int start, int limit, char startSymbol) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String valueToSql(Object value, int sqlType) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlHeader() {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInitSqlFooter() {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getWrapSymbol() {
        throw new UnsupportedException("user getLeftWrapSymbol() and getRightWrapSymbol() instead");
    }

    @Override
    public String getLeftWrapSymbol() {
        return "[";
    }

    @Override
    public String getRightWrapSymbol() {
        return "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdGenerator getIdGenerator(String table, String column) {
        return DEFAULT_ID_GENERATOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDatabaseName() {
        return "SQLServer";
    }

}