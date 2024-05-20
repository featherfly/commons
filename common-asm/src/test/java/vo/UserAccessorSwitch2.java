
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
import vo.UserAccessor0.UserAgeProperty;
import vo.UserAccessor0.UserGenderProperty;
import vo.UserAccessor0.UserNameProperty;
import vo.UserAccessor0.UserUsernameProperty;

/**
 * UserProperties.
 *
 * @author zhongj
 */
public class UserAccessorSwitch2 extends AbstractPropertyAccessor<User> {

    /** The Constant INSTANCE. */
    public static final UserAccessorSwitch2 INSTANCE = new UserAccessorSwitch2();

    @SuppressWarnings("rawtypes")
    private Map<String, Property> map = new HashMap<>();

    private UserNameProperty nameProperty;
    private UserAgeProperty ageProperty;
    private UserUsernameProperty usernameProperty;
    private UserGenderProperty genderProperty;

    /**
     * Instantiates a new user visitor 2.
     */
    public UserAccessorSwitch2() {

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
        getProperty(index).set(obj, value);
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
        return getProperty(index).get(obj);
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
    //    @SuppressWarnings("unchecked")
    //    @Override
    //    protected <V> PropertyVisitor<V> getPropertyVisitor(int index) {
    //        return (PropertyVisitor<V>) getProperty(index).getPropertyVisitor();
    //    }

}
