
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.reflect.GenericClass;

/**
 * <p>
 * AbstractGenericSqlTypeToJavaRegister
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractGenericJavaSqlTypeMapper<E extends Serializable> extends AbstractJavaSqlTypeMapper<E> {

    /**
     */
    public AbstractGenericJavaSqlTypeMapper() {
        super();
        Class<E> javaType = ClassUtils.getSuperClassGenricType(this.getClass());
        setGenericType(new GenericClass<>(javaType));
    }
}
