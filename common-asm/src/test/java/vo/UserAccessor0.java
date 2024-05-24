
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:41:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

/**
 * UserProperties.
 *
 * @author zhongj
 */
public class UserAccessor0 {

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
