package impl;

import java.util.HashMap;

import cn.featherfly.common.bean.AbstractProperty;
import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.NoSuchPropertyException;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * The Class RolePropertyVisitor2.
 *
 * @author zhongj
 */
public class RolePropertyAccessor2 extends AbstractPropertyAccessor<Role> {

    public static final RolePropertyAccessor2 INSTANCE = new RolePropertyAccessor2();

    @SuppressWarnings("rawtypes")
    private final HashMap<String, Property> map;

    private final RoleIdProperty id;

    private final RoleNameProperty name;

    public RolePropertyAccessor2() {

        map = new HashMap<>();

        id = new RoleIdProperty(null);
        map.put("id", id);

        name = new RoleNameProperty(null);
        map.put("name", name);
    }

    /**
     * The Class RoleIdProperty.
     */
    public static class RoleIdProperty extends AbstractProperty<Role, Integer> {

        /**
         * Instantiates a new role id property.
         */
        public RoleIdProperty(PropertyAccessor<Integer> propertyVisitor) {
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

    /**
     * The Class RoleNameProperty.
     *
     * @author zhongj
     */
    public static class RoleNameProperty extends AbstractProperty<Role, String> {

        /**
         * Instantiates a new role name property.
         *
         * @param propertyVisitor the property visitor
         */
        public RoleNameProperty(PropertyAccessor<String> propertyVisitor) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Role obj, int index) {
        switch (index) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getName();
            default:
                throw new NoSuchPropertyException(getType(), index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Role bean, String name) {
        return getProperty(name).get(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Role obj, int index, Object value) {
        switch (index) {
            case 0:
                obj.setId((Integer) value);
                return;
            case 1:
                obj.setName((String) value);
                return;
            default:
                throw new NoSuchPropertyException(getType(), index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Role bean, String name, Object value) {
        getProperty(name).set(bean, value);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Role, V> getProperty(int index) {
        switch (index) {
            case 0:
                return (Property<Role, V>) id;
            case 1:
                return (Property<Role, V>) name;
            default:
                throw new NoSuchPropertyException(getType(), index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Role, V> getProperty(String name) {
        return map.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role instantiate() {
        return new Role();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Role> getType() {
        return Role.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Role, ?>[] getProperties() {
        // YUFEI_TODO Auto-generated method stub
        return null;
    }
}