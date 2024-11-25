
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.NoSuchPropertyException;
import cn.featherfly.common.bean.Property;

/**
 * UserProperties.
 *
 * @author zhongj
 */
public class UserAccessorSwitchDirect2 extends AbstractPropertyAccessor<User> {

    /** The Constant INSTANCE. */
    public static final UserAccessorSwitchDirect2 INSTANCE = new UserAccessorSwitchDirect2();

    private UserNameProperty nameProperty;
    private UserAgeProperty ageProperty;
    private UserUsernameProperty usernameProperty;
    private UserGenderProperty genderProperty;
    private UserLockedProperty lockedProperty;

    @SuppressWarnings("unchecked")
    private Property<User, ?>[] properties = new Property[4];

    /**
     * Instantiates a new user visitor 2.
     */
    public UserAccessorSwitchDirect2() {

        nameProperty = new UserNameProperty();

        ageProperty = new UserAgeProperty();

        usernameProperty = new UserUsernameProperty();

        genderProperty = new UserGenderProperty();

        lockedProperty = new UserLockedProperty();

        properties[0] = nameProperty;
        properties[1] = ageProperty;
        properties[2] = usernameProperty;
        properties[3] = genderProperty;
        properties[4] = lockedProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(User obj, int index, Object value) {
        switch (index) {
            case 0:
                obj.setName((String) value);
                return;
            case 1:
                obj.setAge((Integer) value);
                return;
            case 2:
                obj.setUsername((String) value);
                return;
            case 3:
                obj.setGender((String) value);
                return;
            default:
                throw new NoSuchPropertyException(User.class, index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(User obj, String name, Object value) {
        switch (name) {
            case "name":
                obj.setName((String) value);
                break;
            case "age":
                obj.setAge((Integer) value);
                break;
            case "username":
                obj.setUsername((String) value);
                return;
            case "gender":
                obj.setGender((String) value);
                return;
            case "locked":
                obj.setLocked(((Boolean) value).booleanValue());
                return;
            default:
                throw new NoSuchPropertyException(User.class, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(User obj, int index) {
        switch (index) {
            case 0:
                return obj.getName();
            case 1:
                return obj.getAge();
            case 2:
                return obj.getUsername();
            case 3:
                return obj.getGender();
            default:
                throw new NoSuchPropertyException(User.class, index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(User obj, String name) {
        switch (name) {
            case "name":
                return obj.getName();
            case "age":
                return obj.getAge();
            case "username":
                return obj.getUsername();
            case "gender":
                return obj.getGender();
            default:
                throw new NoSuchPropertyException(User.class, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<User, V> getProperty(int index) {
        switch (index) {
            case 0:
                return (Property<User, V>) nameProperty;
            case 1:
                return (Property<User, V>) ageProperty;
            case 2:
                return (Property<User, V>) usernameProperty;
            case 3:
                return (Property<User, V>) genderProperty;
            default:
                throw new NoSuchPropertyException(User.class, index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<User, V> getProperty(String name) {
        switch (name) {
            case "name":
                return (Property<User, V>) nameProperty;
            case "age":
                return (Property<User, V>) ageProperty;
            case "username":
                return (Property<User, V>) usernameProperty;
            case "gender":
                return (Property<User, V>) genderProperty;
            default:
                throw new NoSuchPropertyException(User.class, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User instantiate() {
        return new User();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<User> getType() {
        return User.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<User, ?>[] getProperties() {
        return properties.clone();
    }

    /**
     * The Class UserNameProperty.
     *
     * @author zhongj
     */
    public static class UserNameProperty extends AbstractReadWriteProperty<User, String> {

        /**
         * Instantiates a new user name property.
         */
        public UserNameProperty() {
            super(User.class, String.class, "name", 0, null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(User object, String value) {
            object.setName(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String get(User object) {
            return object.getName();
        }
    }

    /**
     * @author zhongj
     */
    public static class UserAgeProperty extends AbstractReadWriteProperty<User, Integer> {

        /**
         * Instantiates a new user age property.
         */
        public UserAgeProperty() {
            super(User.class, Integer.class, "age", 1, null);
        }

        @Override
        public void set(User object, Integer value) {
            object.setAge(value);
        }

        @Override
        public Integer get(User object) {
            return object.getAge();
        }
    }

    /**
     * @author zhongj
     */
    public static class UserUsernameProperty extends AbstractReadWriteProperty<User, String> {

        /**
         * Instantiates a new user name property.
         */
        public UserUsernameProperty() {
            super(User.class, String.class, "username", 2, null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(User object, String value) {
            object.setUsername(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String get(User object) {
            return object.getUsername();
        }
    }

    /**
     * @author zhongj
     */
    public static class UserGenderProperty extends AbstractReadWriteProperty<User, String> {

        /**
         * Instantiates a new user name property.
         */
        public UserGenderProperty() {
            super(User.class, String.class, "gender", 3, null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(User object, String value) {
            object.setGender(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String get(User object) {
            return object.getGender();
        }
    }

    public static class UserLockedProperty extends AbstractReadWriteProperty<User, Boolean> {

        /**
         * Instantiates a new user name property.
         */
        public UserLockedProperty() {
            super(User.class, boolean.class, "locked", 3, null);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(User object, Boolean value) {
            object.setLocked(value.booleanValue());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Boolean get(User object) {
            return Boolean.valueOf(object.isLocked());
        }
    }
}
