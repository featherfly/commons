
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
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * UserProperties.
 *
 * @author zhongj
 */
public class UserAccessor {

    private BiConsumer<User, Object>[] setProperties;
    private Function<User, Object>[] getProperties;

    private Map<String, Integer> map = new HashMap<>();

    /**
     */
    @SuppressWarnings("unchecked")
    public UserAccessor() {
        setProperties = new BiConsumer[4];
        setProperties[0] = (u, o) -> u.setName((String) o);
        setProperties[1] = (u, o) -> u.setAge((Integer) o);
        setProperties[2] = (u, o) -> u.setUsername((String) o);
        setProperties[3] = (u, o) -> u.setGender((String) o);

        getProperties = new Function[4];
        getProperties[0] = (u) -> u.getName();
        getProperties[1] = (u) -> u.getAge();
        getProperties[2] = (u) -> u.getUsername();
        getProperties[3] = (u) -> u.getGender();

        map.put("name", 0);
        map.put("age", 1);
        map.put("username", 2);
        map.put("gender", 3);
    }

    public void setProperty(Object obj, int index, Object value) {
        setProperties[index].accept((User) obj, value);
    }

    public void setProperty(Object obj, String name, Object value) {
        setProperties[map.get(name)].accept((User) obj, value);
    }

    public Object getProperty(Object obj, int index) {
        return getProperties[index].apply((User) obj);
    }

    public Object getProperty(Object obj, String name) {
        return getProperties[map.get(name)].apply((User) obj);
    }

}
