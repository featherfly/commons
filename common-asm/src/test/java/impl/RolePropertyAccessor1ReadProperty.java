package impl;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.PropertyAccessException;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * The Class RolePropertyAccessor1ReadProperty.
 *
 * @author zhongj
 */
public class RolePropertyAccessor1ReadProperty extends AbstractProperty<Role, Integer> {

    /**
     * Instantiates a new role property accessor 1 read property.
     *
     * @param propertyVisitor the property visitor
     */
    public RolePropertyAccessor1ReadProperty(PropertyAccessor<Integer> propertyVisitor) {
        super(Role.class, Integer.TYPE, "read", 5, propertyVisitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(Role object, Integer value) {
        throw PropertyAccessException.propertyNoSetter(instanceType, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer get(Role object) {
        return object.getRead();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWritable() {
        return false;
    }

}
