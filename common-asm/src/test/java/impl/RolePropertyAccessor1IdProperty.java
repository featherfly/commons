package impl;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * The Class RoleIdProperty.
 */
public class RolePropertyAccessor1IdProperty extends AbstractProperty<Role, Integer> {

    /**
     * Instantiates a new role id property.
     */
    public RolePropertyAccessor1IdProperty(PropertyAccessor<Integer> propertyVisitor) {
        super(Role.class, Integer.class, "id", 0, propertyVisitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(Role object, Integer value) {
        object.setId(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer get(Role object) {
        return object.getId();
    }

}
