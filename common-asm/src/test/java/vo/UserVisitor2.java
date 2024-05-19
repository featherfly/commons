
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

import cn.featherfly.common.bean.Property;

/**
 * UserProperties.
 *
 * @author zhongj
 */
public class UserVisitor2 {

    //    public interface Property<T, V> {
    //        void set(T t, V v);
    //
    //        V get(T t);
    //    }

    private Property<User, Object>[] properties;

    private Map<String, Property<User, Object>> map = new HashMap<>();

    @SuppressWarnings("unchecked")
    public UserVisitor2() {
        properties = new Property[4];

        properties[0] = new Property<User, Object>() {
            @Override
            public void set(User object, Object value) {
                object.setName((String) value);
            }

            @Override
            public Object get(User object) {
                return object.getName();
            }
        };
        properties[1] = new Property<User, Object>() {
            @Override
            public void set(User object, Object value) {
                object.setAge((Integer) value);
            }

            @Override
            public Object get(User object) {
                return object.getAge();
            }
        };

        properties[2] = new Property<User, Object>() {
            @Override
            public void set(User object, Object value) {
                object.setUsername((String) value);
            }

            @Override
            public Object get(User object) {
                return object.getUsername();
            }
        };

        properties[3] = new Property<User, Object>() {
            @Override
            public void set(User object, Object value) {
                object.setGender((String) value);
            }

            @Override
            public String get(User object) {
                return object.getGender();
            }
        };

        map.put("name", properties[0]);
        map.put("age", properties[1]);
        map.put("username", properties[2]);
        map.put("gender", properties[3]);
    }

    public void setProperty(User obj, int index, Object value) {
        properties[index].set(obj, value);
    }

    public void setProperty(User obj, String name, Object value) {
        map.get(name).set(obj, value);
    }

    public Object getProperty(User obj, int index) {
        return properties[index].get(obj);
    }

    public Object getProperty(User obj, String name) {
        return map.get(name).get(obj);
    }

}
