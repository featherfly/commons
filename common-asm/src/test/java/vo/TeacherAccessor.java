
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-07-02 15:41:02
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import cn.featherfly.common.bean.AbstractPropertyAccessor;
import cn.featherfly.common.bean.NoSuchPropertyException;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * Teacher.
 *
 * @author zhongj
 */
public class TeacherAccessor extends AbstractPropertyAccessor<Teacher> {

    /** The Constant INSTANCE. */
    public static final TeacherAccessor INSTANCE = new TeacherAccessor();

    private final TeacherIdProperty idProperty;
    private final TeacherNoProperty noProperty;
    private final TeacherUserProperty userProperty;

    private final Property<Teacher, ?>[] properties;

    /**
     * Instantiates a new user visitor 2.
     */
    @SuppressWarnings("unchecked")
    public TeacherAccessor() {
        properties = new Property[3];

        idProperty = new TeacherIdProperty(null);
        properties[0] = idProperty;

        noProperty = new TeacherNoProperty(null);
        properties[1] = noProperty;

        userProperty = new TeacherUserProperty(null);
        properties[2] = userProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Teacher obj, int index, Object value) {
        switch (index) {
            case 0:
                obj.setId((Integer) value);
                return;
            case 1:
                obj.setNo((String) value);
                return;
            case 2:
                obj.setUser((User) value);
                return;
            default:
                throw new NoSuchPropertyException(Teacher.class, index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPropertyValue(Teacher obj, String name, Object value) {
        switch (name) {
            case "id":
                obj.setId((Integer) value);
                return;
            case "no":
                obj.setNo((String) value);
                return;
            case "user":
                obj.setUser((User) value);
                return;
            default:
                throw new NoSuchPropertyException(Teacher.class, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Teacher obj, int index) {
        switch (index) {
            case 0:
                return obj.getId();
            case 1:
                return obj.getNo();
            case 2:
                return obj.getUser();
            default:
                throw new NoSuchPropertyException(Teacher.class, index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getPropertyValue(Teacher obj, String name) {
        switch (name) {
            case "id":
                return obj.getId();
            case "no":
                return obj.getNo();
            case "user":
                return obj.getUser();
            default:
                throw new NoSuchPropertyException(Teacher.class, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Teacher, V> getProperty(int index) {
        switch (index) {
            case 0:
                return (Property<Teacher, V>) idProperty;
            case 1:
                return (Property<Teacher, V>) noProperty;
            case 2:
                return (Property<Teacher, V>) userProperty;
            default:
                throw new NoSuchPropertyException(Teacher.class, index);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public <V> Property<Teacher, V> getProperty(String name) {
        switch (name) {
            case "id":
                return (Property<Teacher, V>) idProperty;
            case "no":
                return (Property<Teacher, V>) noProperty;
            case "user":
                return (Property<Teacher, V>) userProperty;
            default:
                throw new NoSuchPropertyException(Teacher.class, name);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Teacher instantiate() {
        return new Teacher();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<Teacher> getType() {
        return Teacher.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Property<Teacher, ?>[] getProperties() {
        return properties.clone();
    }

    /**
     * @author zhongj
     */
    public static class TeacherIdProperty extends AbstractReadWriteProperty<Teacher, Integer> {

        public TeacherIdProperty(PropertyAccessor<Integer> propertyVisitor) {
            super(Teacher.class, Integer.class, "id", 0, propertyVisitor);
        }

        @Override
        public void set(Teacher object, Integer value) {
            object.setId(value);
        }

        @Override
        public Integer get(Teacher object) {
            return object.getId();
        }
    }

    /**
     * @author zhongj
     */
    public static class TeacherNoProperty extends AbstractReadWriteProperty<Teacher, String> {

        public TeacherNoProperty(PropertyAccessor<String> propertyVisitor) {
            super(Teacher.class, String.class, "no", 1, propertyVisitor);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(Teacher object, String value) {
            object.setNo(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String get(Teacher object) {
            return object.getNo();
        }
    }

    /**
     * @author zhongj
     */
    public static class TeacherUserProperty extends AbstractReadWriteProperty<Teacher, User> {

        public TeacherUserProperty(PropertyAccessor<User> propertyVisitor) {
            super(Teacher.class, User.class, "user", 2, propertyVisitor);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(Teacher object, User value) {
            object.setUser(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public User get(Teacher object) {
            return object.getUser();
        }
    }
}
