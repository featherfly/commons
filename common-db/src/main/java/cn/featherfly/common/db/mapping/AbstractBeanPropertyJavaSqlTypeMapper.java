
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

import cn.featherfly.common.bean.BeanProperty;

/**
 * AbstractGenericSqlTypeToJavaRegister.
 *
 * @author zhongj
 * @param <E> the element type
 */
public abstract class AbstractBeanPropertyJavaSqlTypeMapper<T, E extends Serializable>
        extends AbstractJavaSqlTypeMapper<E> {

    /**
     * Instantiates a new abstract bean property java sql type mapper.
     *
     * @param beanProperty the bean property
     */
    public AbstractBeanPropertyJavaSqlTypeMapper(BeanProperty<T, E> beanProperty) {
        super(beanProperty);
    }
}
