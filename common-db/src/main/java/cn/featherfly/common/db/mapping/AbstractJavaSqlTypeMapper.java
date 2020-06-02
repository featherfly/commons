
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.GenericType;

/**
 * <p>
 * AbstractGenericSqlTypeToJavaRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> the element type
 */
public abstract class AbstractJavaSqlTypeMapper<E extends Serializable> implements JavaSqlTypeMapper<E> {

    private GenericType<E> genericType;

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
     * {@inheritDoc}
     */
    @Override
    public boolean support(GenericType<E> type) {
        return this.genericType.equals(type);
    }
}
