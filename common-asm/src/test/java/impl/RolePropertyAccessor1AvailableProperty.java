package impl;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * The Class RoleIdProperty.
 *
 * @author zhongj
 */
public class RolePropertyAccessor1AvailableProperty extends AbstractProperty<Role, Boolean> {

    /**
     * Instantiates a new role property visitor 1 available property.
     *
     * @param propertyVisitor the property visitor
     */
    public RolePropertyAccessor1AvailableProperty(PropertyAccessor<Boolean> propertyVisitor) {
        super(Role.class, boolean.class, "id", 4, propertyVisitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(Role object, Boolean value) {
        object.setAvailable(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean get(Role object) {
        return object.isAvailable();
    }

}
