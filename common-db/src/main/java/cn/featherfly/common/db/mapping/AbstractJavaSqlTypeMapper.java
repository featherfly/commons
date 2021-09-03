
package cn.featherfly.common.db.mapping;

import java.sql.SQLType;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.GenericType;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.policy.WhiteBlackListPolicy;

/**
 * <p>
 * AbstractGenericSqlTypeToJavaRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> the element type
 */
public abstract class AbstractJavaSqlTypeMapper<E extends Object> implements JavaSqlTypeMapper<E> {

    private GenericType<E> genericType;

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
     * @param genericType the generic type
     */
    protected AbstractJavaSqlTypeMapper(GenericType<E> genericType) {
        AssertIllegalArgument.isNotNull(genericType, "genericType");
        this.genericType = genericType;
    }

    /**
     * 设置genericType.
     *
     * @param genericType genericType
     */
    protected void setGenericType(GenericType<E> genericType) {
        this.genericType = genericType;
    }

    /**
     * 返回genericType.
     *
     * @return genericType
     */
    public GenericType<E> getGenericType() {
        return genericType;
    }

    /**
     * Gets the java type.
     *
     * @return the java type
     */
    public Class<E> getJavaType() {
        return genericType.getType();
    }

    /**
     * Gets the java type.
     *
     * @param sqlType the sql type
     * @return the java type
     */
    @Override
    public Class<E> getJavaType(SQLType sqlType) {
        return getJavaType();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean support(GenericType<E> type) {
        return this.genericType.equals(type);
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
}
