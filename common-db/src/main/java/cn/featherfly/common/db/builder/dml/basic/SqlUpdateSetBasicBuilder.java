package cn.featherfly.common.db.builder.dml.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.builder.model.ParamedColumnElement;
import cn.featherfly.common.db.builder.model.UpdateColumnElement;
import cn.featherfly.common.db.builder.model.UpdateColumnElement.SetType;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Dialect.Keyworld;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * SqlUpdateSetBasicBuilder .
 *
 * @author zhongj
 */
public class SqlUpdateSetBasicBuilder implements SqlBuilder {

    private String tableName;

    private String alias;

    private List<ParamedColumnElement> params = new ArrayList<>();

    private List<SqlJoinOnBuilder> sqlJoinOnBuilders;

    private Dialect dialect;

    private Predicate<?> ignoreStrategy;

    /**
     * Instantiates a new sql update set basic builder.
     *
     * @param dialect the dialect
     * @param tableName the table name
     * @param ignoreStrategy the ignore strategy
     */
    public SqlUpdateSetBasicBuilder(Dialect dialect, String tableName, Predicate<?> ignoreStrategy) {
        this(dialect, tableName, null, ignoreStrategy);
    }

    /**
     * Instantiates a new sql update set basic builder.
     *
     * @param dialect the dialect
     * @param tableName the table name
     * @param alias the alias
     * @param ignoreStrategy the ignore strategy
     */
    public SqlUpdateSetBasicBuilder(Dialect dialect, String tableName, String alias, Predicate<?> ignoreStrategy) {
        AssertIllegalArgument.isNotNull(ignoreStrategy, "ignoreStrategy");
        this.tableName = tableName;
        this.alias = alias;
        this.dialect = dialect;
        setIgnoreStrategy(ignoreStrategy);
        sqlJoinOnBuilders = new ArrayList<>(0);
    }

    /**
     * Adds the sql join on builder.
     *
     * @param sqlJoinOnBuilder the sql join on builder
     * @return the sql update set basic builder
     */
    public SqlUpdateSetBasicBuilder join(SqlJoinOnBuilder sqlJoinOnBuilder) {
        sqlJoinOnBuilders.add(sqlJoinOnBuilder);
        return this;
    }

    /**
     * Sets the value.
     *
     * @param columnName the column name
     * @param value the value
     * @return the sql update set basic builder
     */
    public SqlUpdateSetBasicBuilder setValue(String columnName, Object value) {
        return setValue(columnName, value, ignoreStrategy);
    }

    /**
     * Sets the value.
     *
     * @param columnName the column name
     * @param value the value
     * @param setIgnoreStrategy the set ignore strategy
     * @return the sql update set basic builder
     */
    public SqlUpdateSetBasicBuilder setValue(String columnName, Object value, Predicate<?> setIgnoreStrategy) {
        params.add(new UpdateColumnElement(dialect, alias, columnName, value, setIgnoreStrategy));
        return this;
    }

    /**
     * Sets the value.
     *
     * @param columnName the column name
     * @param value the value
     * @param setType the set type
     * @return the sql update set basic builder
     */
    public SqlUpdateSetBasicBuilder setValue(String columnName, Object value, SetType setType) {
        return setValue(columnName, value, setType, ignoreStrategy);
    }

    /**
     * Sets the value.
     *
     * @param columnName the column name
     * @param value the value
     * @param setType the set type
     * @param setIgnoreStrategy the set ignore strategy
     * @return the sql update set basic builder
     */
    public SqlUpdateSetBasicBuilder setValue(String columnName, Object value, SetType setType,
        Predicate<?> setIgnoreStrategy) {
        params.add(new UpdateColumnElement(dialect, alias, columnName, value, setType, setIgnoreStrategy));
        return this;
    }

    // public SqlUpdateSetBasicBuilder setValue(String columnName, Object value,
    // String tableAlias) {
    // params.add(new ParamedColumnElement(dialect, columnName, tableAlias,
    // value));
    // return this;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder update = new StringBuilder();
        Keyworld keyworld = dialect.getKeywords();
        update.append(keyworld.update()).append(Chars.SPACE).append(dialect.dml().table(tableName, alias));
        // TODO 判断tableName paramMap 为空 抛出异常
        if (!sqlJoinOnBuilders.isEmpty()) {
            for (SqlJoinOnBuilder sqlJoinOnBuilder : sqlJoinOnBuilders) {
                update.append(Chars.SPACE).append(sqlJoinOnBuilder.build());
            }
        }
        update.append(Chars.SPACE).append(keyworld.set());
        int len = update.length();
        params.forEach(c -> {
            Lang.ifNotEmpty(c.toSql(),
                (Consumer<String>) setValue -> update.append(Chars.SPACE).append(setValue).append(Chars.COMMA));
            //            String setValue = c.toSql();
            //            if (Lang.isNotEmpty(setValue)) {
            //                update.append(Chars.SPACE).append(setValue).append(Chars.COMMA);
            //            }
        });
        if (len == update.length()) {
            throw new JdbcException("no value supply for set");
        }
        update.deleteCharAt(update.length() - 1);
        return update.toString();
    }

    /**
     * Gets the params.
     *
     * @return the params
     */
    public List<Serializable> getParams() {
        return params.stream().map(o -> (Serializable) o.getParam()).collect(Collectors.toList());
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
        for (ParamedColumnElement params : params) {
            params.setTableAlias(alias);
        }
    }

    /**
     * get ignoreStrategy value.
     *
     * @return ignoreStrategy
     */
    public Predicate<?> getIgnoreStrategy() {
        return ignoreStrategy;
    }

    /**
     * set ignoreStrategy value.
     *
     * @param ignoreStrategy ignoreStrategy
     */
    public void setIgnoreStrategy(Predicate<?> ignoreStrategy) {
        this.ignoreStrategy = ignoreStrategy;
    }
}
