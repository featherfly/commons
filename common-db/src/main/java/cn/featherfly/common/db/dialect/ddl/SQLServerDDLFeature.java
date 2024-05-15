
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 01:23:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.ddl;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.AbstractDDLFeature;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.db.dialect.SQLServerDialect;
import cn.featherfly.common.exception.NotImplementedException;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * SQLServer DDL feature.
 *
 * @author zhongj
 */
public class SQLServerDDLFeature extends AbstractDDLFeature<SQLServerDialect> {

    /**
     * Instantiates a new SQL server DDL feature.
     *
     * @param dialect the dialect
     */
    public SQLServerDDLFeature(SQLServerDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createTable(Table table) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTable(String schema, String tableName, Column[] addColumns, Column[] modifyColumns,
            Column[] dropColumns) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableAddColumn(String schema, String tableName, Column... columns) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableModifyColumn(String schema, String tableName, Column... columns) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, Column... columns) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, String... columnNames) {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String dropIndex(String schema, String tableName, String indexName, boolean ifExists) {
        AssertIllegalArgument.isNotEmpty(tableName, "tableName");
        AssertIllegalArgument.isNotEmpty(indexName, "indexName");
        String in = Lang.isEmpty(schema) ? dialect.wrapName(indexName)
                : dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(indexName);
        String tn = Lang.isEmpty(schema) ? dialect.wrapName(tableName)
                : dialect.wrapName(schema) + Chars.DOT + dialect.wrapName(tableName);
        String drop = BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.INDEX), in,
                dialect.getKeyword(Keywords.ON), tn);
        if (ifExists) {
            // IF EXISTS (SELECT name FROM sys.indexes WHERE name = N'index_name') DROP INDEX [index_name] ON [table_name]
            return BuilderUtils.link(dialect.getKeyword(Keywords.IF), dialect.keywords().exists(), "(",
                    dialect.keywords().select(), "name", dialect.keywords().from(), "sys.indexes",
                    dialect.keywords().where(), "name = N'" + in + "')", drop);
        } else {
            return drop;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String autoIncrement(Column column) {
        if (column.isAutoincrement()) {
            return "IDENTITY";
        } else {
            return "";
        }
    }

}
