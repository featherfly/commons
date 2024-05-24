package impl;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.PropertyAccessException;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * The Class RolePropertyAccessor1WriteProperty.
 *
 * @author zhongj
 */
public class RolePropertyAccessor1WriteProperty extends AbstractProperty<Role, Integer> {

    /**
     * Instantiates a new role property accessor 1 write property.
     *
     * @param propertyVisitor the property visitor
     */
    public RolePropertyAccessor1WriteProperty(PropertyAccessor<Integer> propertyVisitor) {
        super(Role.class, Integer.TYPE, "write", 6, propertyVisitor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(Role object, Integer value) {
        object.setWrite(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer get(Role object) {
        throw PropertyAccessException.propertyNoGetter(instanceType, name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadable() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWritable() {
        return true;
    }

}
