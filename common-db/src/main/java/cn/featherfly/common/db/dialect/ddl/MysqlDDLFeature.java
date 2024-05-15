
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
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.AbstractDDLFeature;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.db.dialect.MySQLDialect;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * mysql DDL feature.
 *
 * @author zhongj
 */
public class MysqlDDLFeature extends AbstractDDLFeature<MySQLDialect> {

    /**
     * Instantiates a new mysql DDL feature.
     *
     * @param dialect the dialect
     */
    public MysqlDDLFeature(MySQLDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, Column... columns) {
        StringBuilder ddl = new StringBuilder(alterTable(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(dropColumn(columns)).append(Chars.SEMI).toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, String... columnNames) {
        StringBuilder ddl = new StringBuilder(alterTable(schema, tableName));
        return ddl.append(Chars.NEW_LINE).append(dropColumn(columnNames)).append(Chars.SEMI).toString();
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
        if (ifExists) {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.INDEX),
                    dialect.getKeyword(Keywords.IF), dialect.getKeyword(Keywords.EXISTS), in,
                    dialect.getKeyword(Keywords.ON), tn) + Chars.SEMI;
        } else {
            return BuilderUtils.link(dialect.getKeyword(Keywords.DROP), dialect.getKeyword(Keywords.INDEX), in,
                    dialect.getKeyword(Keywords.ON), tn) + Chars.SEMI;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String autoIncrement(Column column) {
        if (column.isAutoincrement()) {
            return "AUTO_INCREMENT";
        } else {
            return "";
        }
    }

}
