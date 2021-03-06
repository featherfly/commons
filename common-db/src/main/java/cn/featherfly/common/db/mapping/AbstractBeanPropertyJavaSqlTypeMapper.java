
package cn.featherfly.common.db.mapping;

import cn.featherfly.common.bean.BeanProperty;

/**
 * <p>
 * AbstractGenericSqlTypeToJavaRegister
 * </p>
 * .
 *
 * @author zhongj
 * @param <E> the element type
 */
public abstract class AbstractBeanPropertyJavaSqlTypeMapper<E extends Object> extends AbstractJavaSqlTypeMapper<E> {

    /**
     * Instantiates a new abstract bean property java sql type mapper.
     *
     * @param beanProperty the bean property
     */
    public AbstractBeanPropertyJavaSqlTypeMapper(BeanProperty<E> beanProperty) {
        super(beanProperty);
    }
}
