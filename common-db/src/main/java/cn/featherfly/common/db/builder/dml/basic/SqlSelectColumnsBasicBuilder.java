package cn.featherfly.common.db.builder.dml.basic;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.model.SelectColumnElement;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.builder.AliasManager;

/**
 * sql select basic builder. columns with given table.
 *
 * @author zhongj
 */
public class SqlSelectColumnsBasicBuilder extends AbstractSqlSelectColumnsBuilder<SqlSelectColumnsBasicBuilder> {

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect dialect
     */
    public SqlSelectColumnsBasicBuilder(Dialect dialect) {
        super(dialect);
    }

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect    dialect
     * @param tableAlias table name alias
     */
    public SqlSelectColumnsBasicBuilder(Dialect dialect, String tableAlias) {
        super(dialect, tableAlias);
    }

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect      dialect
     * @param classMapping classMapping
     */
    public SqlSelectColumnsBasicBuilder(Dialect dialect, JdbcClassMapping<?> classMapping) {
        this(dialect, AliasManager.generateAlias(classMapping.getRepositoryName()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder columnsBuilder = new StringBuilder();
        if (columns.isEmpty()) {
            if (Lang.isEmpty(tableAlias)) {
                columnsBuilder.append(Chars.STAR);
            } else {
                columnsBuilder.append(tableAlias).append(Chars.DOT).append(Chars.STAR);
            }
        } else {
            for (SelectColumnElement column : columns) {
                // 基础构建器一个实例对应一个table
                column.setTableAlias(tableAlias);
                columnsBuilder.append(column).append(Chars.COMMA).append(Chars.SPACE);
            }
            columnsBuilder.delete(columnsBuilder.length() - 2, columnsBuilder.length());
        }
        return columnsBuilder.toString();
    }
}
