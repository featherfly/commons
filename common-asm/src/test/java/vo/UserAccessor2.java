
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
import cn.featherfly.common.lang.AssertIllegalArgument;
import vo.UserAccessor0.UserAgeProperty;
import vo.UserAccessor0.UserGenderProperty;
import vo.UserAccessor0.UserNameProperty;
import vo.UserAccessor0.UserUsernameProperty;

/**
 * UserProperties.
 *
 * @author zhongj
 */
public class UserAccessor2 extends AbstractPropertyAccessor<User> {

    /** The Constant INSTANCE. */
    public static final UserAccessor2 INSTANCE = new UserAccessor2();

    //    public interface Property<T, V> {
    //        void set(T t, V v);
    //
    //        V get(T t);
    //    }

    @SuppressWarnings("rawtypes")
    private Property[] properties;

    @SuppressWarnings("rawtypes")
    private Map<String, Property> map = new HashMap<>();

    /**
     * Instantiates a new user visitor 2.
     */
    public UserAccessor2() {
        properties = new Property[4];

        properties[0] = new UserNameProperty();

        properties[1] = new UserAgeProperty();

        properties[2] = new UserUsernameProperty();

        properties[3] = new UserGenderProperty();

        map.put("name", properties[0]);
        map.put("age", properties[1]);
        map.put("username", properties[2]);
        map.put("gender", properties[3]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setPropertyValue(User obj, int index, Object value) {
        properties[index].set(obj, value);
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
    @SuppressWarnings("unchecked")
    public Object getPropertyValue(User obj, int index) {
        return properties[index].get(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(User obj, int... indexes) {
        AssertIllegalArgument.isNotEmpty(indexes, "indexes");
        if (indexes.length == 1) {
            return getPropertyValue(obj, indexes[0]);
        }
        throw new IllegalArgumentException("未实现");
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
    @Override
    public Object getPropertyValue(User obj, String... names) {
        AssertIllegalArgument.isNotEmpty(names, "names");
        if (names.length == 1) {
            return getPropertyValue(obj, names[0]);
        }
        throw new IllegalArgumentException("未实现");
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<User, V> getProperty(int index) {
        return properties[index];
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
    //    @SuppressWarnings("unchecked")
    //    @Override
    //    protected <V> PropertyVisitor<V> getPropertyVisitor(int index) {
    //        return (PropertyVisitor<V>) getProperty(index).getPropertyVisitor();
    //    }

    //    /**
    //     * The Class UserNameProperty.
    //     *
    //     * @author zhongj
    //     */
    //    public static class UserNameProperty extends AbstractProperty<User, String> {
    //
    //        /**
    //         * Instantiates a new user name property.
    //         */
    //        public UserNameProperty() {
    //            super(User.class, String.class, "name", 0, null);
    //        }
    //
    //        /**
    //         * {@inheritDoc}
    //         */
    //        @Override
    //        public void set(User object, String value) {
    //            object.setName(value);
    //        }
    //
    //        /**
    //         * {@inheritDoc}
    //         */
    //        @Override
    //        public String get(User object) {
    //            return object.getName();
    //        }
    //    }
    //
    //    /**
    //     * @author zhongj
    //     */
    //    public static class UserAgeProperty extends AbstractProperty<User, Integer> {
    //
    //        /**
    //         * Instantiates a new user age property.
    //         */
    //        public UserAgeProperty() {
    //            super(User.class, Integer.class, "age", 1, null);
    //        }
    //
    //        @Override
    //        public void set(User object, Integer value) {
    //            object.setAge(value);
    //        }
    //
    //        @Override
    //        public Integer get(User object) {
    //            return object.getAge();
    //        }
    //    }
    //
    //    /**
    //     * @author zhongj
    //     */
    //    public static class UserUsernameProperty extends AbstractProperty<User, String> {
    //
    //        /**
    //         * Instantiates a new user name property.
    //         */
    //        public UserUsernameProperty() {
    //            super(User.class, String.class, "username", 2, null);
    //        }
    //
    //        /**
    //         * {@inheritDoc}
    //         */
    //        @Override
    //        public void set(User object, String value) {
    //            object.setUsername(value);
    //        }
    //
    //        /**
    //         * {@inheritDoc}
    //         */
    //        @Override
    //        public String get(User object) {
    //            return object.getUsername();
    //        }
    //    }
    //
    //    /**
    //     * @author zhongj
    //     */
    //    public static class UserGenderProperty extends AbstractProperty<User, String> {
    //
    //        /**
    //         * Instantiates a new user name property.
    //         */
    //        public UserGenderProperty() {
    //            super(User.class, String.class, "gender", 3, null);
    //        }
    //
    //        /**
    //         * {@inheritDoc}
    //         */
    //        @Override
    //        public void set(User object, String value) {
    //            object.setGender(value);
    //        }
    //
    //        /**
    //         * {@inheritDoc}
    //         */
    //        @Override
    //        public String get(User object) {
    //            return object.getGender();
    //        }
    //    }
}
