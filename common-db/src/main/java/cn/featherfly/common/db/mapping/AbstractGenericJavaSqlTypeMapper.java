
package cn.featherfly.common.db.mapping;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.reflect.ClassType;

/**
 * AbstractGenericSqlTypeToJavaRegister.
 *
 * @author zhongj
 */
public abstract class AbstractGenericJavaSqlTypeMapper<E> extends AbstractJavaSqlTypeMapper<E> {

    /**
     */
    public AbstractGenericJavaSqlTypeMapper() {
        super();
        Class<E> javaType = ClassUtils.getSuperClassGenericType(this.getClass());
        setType(new ClassType<>(javaType));
    }
}
