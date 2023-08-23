package cn.featherfly.common.db.builder.dml.basic;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.model.SelectColumnElement;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.mapping.ClassMappingUtils;
import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * sql select basic builder. columns with given table.
 *
 * @author zhongj
 */
public class SqlSelectColumnsClassMappingBuilder
        extends AbstractSqlSelectColumnsBuilder<SqlSelectColumnsClassMappingBuilder> {

    /** The class mapping. */
    protected JdbcClassMapping<?> classMapping;

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect      dialect
     * @param classMapping classMapping
     */
    public SqlSelectColumnsClassMappingBuilder(Dialect dialect, JdbcClassMapping<?> classMapping) {
        this(dialect, classMapping, null);
    }

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect      dialect
     * @param classMapping classMapping
     * @param tableAlias   table name alias
     */
    public SqlSelectColumnsClassMappingBuilder(Dialect dialect, JdbcClassMapping<?> classMapping, String tableAlias) {
        this(dialect, classMapping, tableAlias, null);
    }

    /**
     * Instantiates a new sql select columns basic builder.
     *
     * @param dialect      dialect
     * @param classMapping classMapping
     * @param tableAlias   table name alias
     */
    public SqlSelectColumnsClassMappingBuilder(Dialect dialect, JdbcClassMapping<?> classMapping, String tableAlias,
            String aliasPrefix) {
        super(dialect, tableAlias, aliasPrefix);
        AssertIllegalArgument.isNotNull(classMapping, "classMapping");
        this.classMapping = classMapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder columnsBuilder = new StringBuilder();
        if (columns.isEmpty()) {
            StringBuilder prefix = new StringBuilder(columnAliasPrefixTableAlias ? tableAlias : "");
            if (Lang.isNotEmpty(columnAliasPrefix)) {
                if (columnAliasPrefixTableAlias) {
                    prefix.append(Chars.DOT_CHAR);
                }
                prefix.append(columnAliasPrefix);
            }
            columnsBuilder.append(
                    ClassMappingUtils.getSelectColumnsSql(classMapping, tableAlias, prefix.toString(), dialect));
        } else {
            // addColumn的时候判断不判断String column是column还是property
            // 因为此类基本是内部使用，所以在使用时直接addColumn(propertyMapping.getFieldName(), propertyMapping.getName())
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
