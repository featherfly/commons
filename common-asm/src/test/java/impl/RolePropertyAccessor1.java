package impl;

import java.util.LinkedHashMap;

import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.NoSuchPropertyException;
import cn.featherfly.common.bean.Property;

public class RolePropertyAccessor1 extends AbstractPropertyAccessor<Role> {

    @SuppressWarnings("rawtypes")
    private final LinkedHashMap<String, Property> propertyMap;

    private final RolePropertyAccessor1IdProperty id;

    private final RolePropertyAccessor1NameProperty name;

    private final RolePropertyAccessor1GroupProperty group;

    private final RolePropertyAccessor1AgeProperty age;

    private final RolePropertyAccessor1AvailableProperty available;

    /**
     */
    public RolePropertyAccessor1() {

        propertyMap = new LinkedHashMap<>();

        id = new RolePropertyAccessor1IdProperty(null);
        propertyMap.put("id", id);

        name = new RolePropertyAccessor1NameProperty(null);
        propertyMap.put("name", name);

        group = new RolePropertyAccessor1GroupProperty(null);
        propertyMap.put("group", group);

        age = new RolePropertyAccessor1AgeProperty(null);
        propertyMap.put("age", age);

        available = new RolePropertyAccessor1AvailableProperty(null);
        propertyMap.put("available", available);
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
            case 2:
                return obj.getGroup();
            case 3:
                return obj.getAge();
            case 4:
                return obj.isAvailable();
            default:
                //                int i = index;
                //                Class<Role> type = Role.class;
                //                throw new NoSuchPropertyException(type, i);
                throw new NoSuchPropertyException(Role.class, index);
            //                            return noSuchProperty(obj.getClass(), index);
            //                return null;
        }
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
            case 2:
                return (Property<Role, V>) group;
            case 3:
                return (Property<Role, V>) age;
            case 4:
                return (Property<Role, V>) available;
            default:
                throw new NoSuchPropertyException(Role.class, index);
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
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Role, V> getProperty(String name) {
        Property<Role, V> property = propertyMap.get(name);
        if (property == null) {
            throw new NoSuchPropertyException(Role.class, name);
        }
        return property;
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
    public void setPropertyValue(Role obj, int index, Object value) {
        switch (index) {
            case 0:
                obj.setId((Integer) value);
                return;
            case 1:
                obj.setName((String) value);
                return;
            case 2:
                obj.setGroup((String) value);
            case 3:
                obj.setAge((Integer) value);
                return;
            case 4:
                obj.setAvailable((Boolean) value);
                return;
            default:
                throw new NoSuchPropertyException(Role.class, index);
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
    public Property<Role, ?>[] getProperties() {
        return propertyMap.values().toArray(new Property[propertyMap.size()]);
    }
}