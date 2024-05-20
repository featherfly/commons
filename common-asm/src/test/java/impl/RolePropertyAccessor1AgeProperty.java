package impl;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * The Class RolePropertyVisitor1AgeProperty.
 *
 * @author zhongj
 */
public class RolePropertyAccessor1AgeProperty extends AbstractProperty<Role, Integer> {

    /**
     * Instantiates a new role property visitor 1 age property.
     *
     * @param propertyVisitor the property visitor
     */
    public RolePropertyAccessor1AgeProperty(PropertyAccessor<Integer> propertyVisitor) {
        super(Role.class, Integer.TYPE, "id", 3, propertyVisitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(Role object, Integer value) {
        object.setAge(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer get(Role object) {
        return object.getAge();
    }

}
