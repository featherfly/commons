
package cn.featherfly.common.db.mapping;

import java.sql.CallableStatement;
import java.sql.SQLType;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.reflect.Type;
import cn.featherfly.common.policy.WhiteBlackListPolicy;

/**
 * AbstractGenericSqlTypeToJavaRegister.
 *
 * @author zhongj
 * @param <E> the element type
 */
public abstract class AbstractJavaSqlTypeMapper<E> implements JavaSqlTypeMapper<E> {

    private Type<E> type;

    private WhiteBlackListPolicy<String> columnAllowPolicy = new WhiteBlackListPolicy<String>() {

        @Override
        public boolean isEnableBlackList() {
            return !getBlackList().isEmpty();
        }

        @Override
        public boolean isEnableWhiteList() {
            return !getWhiteList().isEmpty();
        }

        @Override
        protected boolean isEquals(String target1, String target2) {
            return Lang.equals(target1, target2);
        }
    };

    /**
     * Instantiates a new abstract java sql type mapper.
     */
    AbstractJavaSqlTypeMapper() {
    }

    /**
     * Instantiates a new abstract java sql type mapper.
     *
     * @param type the type
     */
    protected AbstractJavaSqlTypeMapper(Type<E> type) {
        AssertIllegalArgument.isNotNull(type, "type");
        this.type = type;
    }

    /**
     * set type
     *
     * @param type Type
     */
    protected void setType(Type<E> type) {
        this.type = type;
    }

    /**
     * get type.
     *
     * @return genericType
     */
    public Type<E> getType() {
        return type;
    }

    /**
     * Gets the java type.
     *
     * @return the java type
     */
    public Class<E> getJavaType() {
        return type.getType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(Type<E> type) {
        return this.type.equals(type) || this.type.getType().equals(type.getType());
    }

    /**
     * Support.
     *
     * @param sqlType the sql type
     * @return true, if successful
     */
    protected abstract boolean support(SQLType sqlType);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(SQLType sqlType, String tableName, String columnName) {
        if (sqlType != null && support(sqlType)) {
            // ENHANCE 后续修改为两个允许策略
            return columnAllowPolicy.isAllow(toKey(tableName, columnName));
        }
        return false;
    }

    private String toKey(String tableName, String columnName) {
        return tableName + "." + columnName;
    }

    /**
     * Adds the allow.
     *
     * @param tableName  the table name
     * @param columnName the column name
     * @return the abstract java sql type mapper
     */
    public AbstractJavaSqlTypeMapper<E> addAllow(String tableName, String columnName) {
        columnAllowPolicy.addWhite(toKey(tableName, columnName));
        return this;
    }

    /**
     * Adds the deny.
     *
     * @param tableName  the table name
     * @param columnName the column name
     * @return the abstract java sql type mapper
     */
    public AbstractJavaSqlTypeMapper<E> addDeny(String tableName, String columnName) {
        columnAllowPolicy.addBlack(toKey(tableName, columnName));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getCallableParam(call, paramIndex, type.getType());
    }
}
