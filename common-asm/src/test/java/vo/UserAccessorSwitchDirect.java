
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.exception.UnsupportedException;

/**
 * UserProperties.
 *
 * @author zhongj
 */
public class UserAccessorSwitchDirect extends AbstractPropertyAccessor<User> {

    /** The Constant INSTANCE. */
    public static final UserAccessorSwitchDirect INSTANCE = new UserAccessorSwitchDirect();

    @SuppressWarnings("rawtypes")
    private Map<String, Property> map = new HashMap<>();

    private UserNameProperty nameProperty;
    private UserAgeProperty ageProperty;
    private UserUsernameProperty usernameProperty;
    private UserGenderProperty genderProperty;

    /**
     * Instantiates a new user visitor 2.
     */
    public UserAccessorSwitchDirect() {

        nameProperty = new UserNameProperty();

        ageProperty = new UserAgeProperty();

        usernameProperty = new UserUsernameProperty();

        genderProperty = new UserGenderProperty();

        map.put("name", nameProperty);
        map.put("age", ageProperty);
        map.put("username", usernameProperty);
        map.put("gender", genderProperty);
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
                throw new UnsupportedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setPropertyValue(User obj, String name, Object value) {
        map.get(name).set(obj, value);
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
                throw new UnsupportedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object getPropertyValue(User obj, String name) {
        return map.get(name).get(obj);
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
                throw new UnsupportedException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<User, V> getProperty(String name) {
        return map.get(name);
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
    @SuppressWarnings("unchecked")
    @Override
    public Property<User, ?>[] getProperties() {
        return map.values().toArray(new Property[map.size()]);
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    protected <V> PropertyVisitor<V> getPropertyVisitor(int index) {
    //        switch (index) {
    //            default:
    //                throw new IllegalArgumentException("no property at index " + index);
    //        }
    //    }

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
}
