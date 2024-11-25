package impl;

import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.NoSuchPropertyException;
import cn.featherfly.common.bean.Property;

public class RolePropertyAccessor1 extends AbstractPropertyAccessor<Role> {

    private final RolePropertyAccessor1IdProperty id;

    private final RolePropertyAccessor1NameProperty name;

    private final RolePropertyAccessor1GroupProperty group;

    private final RolePropertyAccessor1AgeProperty age;

    private final RolePropertyAccessor1AvailableProperty available;

    private final Property<Role, ?>[] properties;

    /**
     */
    @SuppressWarnings("unchecked")
    public RolePropertyAccessor1() {

        properties = new Property[5];

        id = new RolePropertyAccessor1IdProperty(null);
        properties[0] = id;

        name = new RolePropertyAccessor1NameProperty(null);
        properties[1] = name;

        group = new RolePropertyAccessor1GroupProperty(null);
        properties[2] = group;

        age = new RolePropertyAccessor1AgeProperty(null);
        properties[3] = age;

        available = new RolePropertyAccessor1AvailableProperty(null);
        properties[4] = available;
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
                throw new NoSuchPropertyException(Role.class, index);
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
        switch (name) {
            case "id":
                return id.get(bean);
            case "name":
                return this.name.get(bean);
            case "group":
                return group.get(bean);
            case "age":
                return age.get(bean);
            case "available":
                return available.get(bean);
            default:
                throw new NoSuchPropertyException(Role.class, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Role, V> getProperty(String name) {
        switch (name) {
            case "id":
                return (Property<Role, V>) id;
            case "name":
                return (Property<Role, V>) this.name;
            case "group":
                return (Property<Role, V>) group;
            case "age":
                return (Property<Role, V>) age;
            case "available":
                return (Property<Role, V>) available;
            default:
                throw new NoSuchPropertyException(Role.class, name);
        }
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
    @Override
    public Property<Role, ?>[] getProperties() {
        return properties.clone();
    }
}