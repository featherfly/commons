
package cn.featherfly.common.db.builder.dml.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.builder.model.ParamedColumnElement;
import cn.featherfly.common.db.builder.model.UpdateColumnElement;
import cn.featherfly.common.db.builder.model.UpdateColumnElement.SetType;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialect.Keyworld;
import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * <p>
 * SqlUpdateSetBasicBuilder
 * </p>
 * .
 *
 * @author zhongj
 */
public class SqlUpdateSetBasicBuilder implements SqlBuilder {

    private String tableName;

    private String alias;

    private List<ParamedColumnElement> params = new ArrayList<>();

    private Dialect dialect;

    private Predicate<Object> ignorePolicy;

    /**
     * Instantiates a new sql update set basic builder.
     *
     * @param dialect      the dialect
     * @param tableName    the table name
     * @param ignorePolicy the ignore policy
     */
    public SqlUpdateSetBasicBuilder(Dialect dialect, String tableName, Predicate<Object> ignorePolicy) {
        this(dialect, tableName, null, ignorePolicy);
    }

    /**
     * Instantiates a new sql update set basic builder.
     *
     * @param dialect      the dialect
     * @param tableName    the table name
     * @param alias        the alias
     * @param ignorePolicy the ignore policy
     */
    public SqlUpdateSetBasicBuilder(Dialect dialect, String tableName, String alias, Predicate<Object> ignorePolicy) {
        AssertIllegalArgument.isNotNull(ignorePolicy, "ignorePolicy");
        this.tableName = tableName;
        this.alias = alias;
        this.dialect = dialect;
        this.ignorePolicy = ignorePolicy;
    }

    /**
     * Sets the value.
     *
     * @param columnName the column name
     * @param value      the value
     * @return the sql update set basic builder
     */
    public SqlUpdateSetBasicBuilder setValue(String columnName, Object value) {
        params.add(new UpdateColumnElement(dialect, columnName, value, alias, ignorePolicy));
        return this;
    }

    /**
     * Sets the value.
     *
     * @param columnName the column name
     * @param value      the value
     * @param setType    the set type
     * @return the sql update set basic builder
     */
    public SqlUpdateSetBasicBuilder setValue(String columnName, Object value, SetType setType) {
        params.add(new UpdateColumnElement(dialect, columnName, value, alias, setType, ignorePolicy));
        return this;
    }

    // public SqlUpdateSetBasicBuilder setValue(String columnName, Object value,
    // String tableAlias) {
    // params.add(new ParamedColumnElement(dialect, columnName, tableAlias,
    // value));
    // return this;
    // }

    /**
     * Gets the params.
     *
     * @return the params
     */
    public List<Object> getParams() {
        return params.stream().map(ParamedColumnElement::getParam).collect(Collectors.toList());
    }

    /**
     * 设置tableName.
     *
     * @param tableName tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 返回tableName.
     *
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 返回alias.
     *
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置alias.
     *
     * @param alias alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder update = new StringBuilder();
        Keyworld keyworld = dialect.getKeywords();
        update.append(keyworld.update()).append(Chars.SPACE).append(dialect.buildTableSql(tableName, alias))
                .append(Chars.SPACE).append(keyworld.set());
        // TODO 判断tableName paramMap 为空 抛出异常
        params.forEach(c -> {
            update.append(Chars.SPACE).append(c.toSql()).append(Chars.COMMA);
        });
        update.deleteCharAt(update.length() - 1);
        return update.toString();
    }

    /**
     * get ignorePolicy value
     *
     * @return ignorePolicy
     */
    public Predicate<Object> getIgnorePolicy() {
        return ignorePolicy;
    }

    /**
     * set ignorePolicy value
     *
     * @param ignorePolicy ignorePolicy
     */
    public void setIgnorePolicy(Predicate<Object> ignorePolicy) {
        this.ignorePolicy = ignorePolicy;
    }

}
