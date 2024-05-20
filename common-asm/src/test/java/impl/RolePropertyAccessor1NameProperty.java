package impl;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * The Class RoleNameProperty.
 *
 * @author zhongj
 */
public class RolePropertyAccessor1NameProperty extends AbstractProperty<Role, String> {

    /**
     * Instantiates a new role name property.
     *
     * @param propertyVisitor the property visitor
     */
    public RolePropertyAccessor1NameProperty(PropertyAccessor<String> propertyVisitor) {
        super(Role.class, String.class, "name", 1, propertyVisitor);
    }

    /**
     * Sets the.
     *
     * @param object the object
     * @return the string
     */
    @Override
    public String get(Role object) {
        return object.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(Role object, String value) {
        object.setName(value);
    }
}