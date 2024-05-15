package cn.featherfly.common.db.dialect.ddl;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.dialect.AbstractDDLFeature;
import cn.featherfly.common.db.dialect.OracleDialect;
import cn.featherfly.common.exception.UnsupportedException;

/**
 * Oracle DDL feature.
 *
 * @author zhongj
 */
public class OracleDDLFeature extends AbstractDDLFeature<OracleDialect> {

    /**
     * Instantiates a new SQL server DDL feature.
     *
     * @param dialect the dialect
     */
    public OracleDDLFeature(OracleDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String autoIncrement(Column column) {
        // NOIMPL 未实现
        throw new UnsupportedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String primaryKey(Table table) {
        // NOIMPL 未实现
        throw new UnsupportedException();
    }

}
