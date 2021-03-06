
package cn.featherfly.common.db.mapping;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.reflect.GenericClass;

/**
 * <p>
 * AbstractGenericSqlTypeToJavaRegister
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractGenericJavaSqlTypeMapper<E extends Object> extends AbstractJavaSqlTypeMapper<E> {

    /**
     */
    public AbstractGenericJavaSqlTypeMapper() {
        super();
        Class<E> javaType = ClassUtils.getSuperClassGenericType(this.getClass());
        setGenericType(new GenericClass<>(javaType));
    }
}
