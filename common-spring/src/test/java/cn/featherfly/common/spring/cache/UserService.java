
package cn.featherfly.common.spring.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * UserService
 * </p>
 * <p>
 * 2019-08-08
 * </p>
 *
 * @author zhongj
 */
@Service
public class UserService {

    private Map<Integer, User> users = new HashMap<>();

    @CachePut(value = "userCache", key = "'user:id:' + #user.id")
    public User save(User user) {
        System.out.println("save " + user);
        users.put(user.getId(), user);
        return user;
    }

    @Cacheable(value = "userCache", key = "'user:id:' + #id")
    public User get(Integer id) {
        System.out.println("get " + id);
        return users.get(id);
    }

    @CacheEvict(value = "userCache", key = "'user:id:' + #user.id")
    public void delete(User user) {
        System.out.println("delete");
        users.remove(user.getId());
    }

    @Cacheable(value = "userCache", key = "'user:username:' + #username")
    public User getByUsername(String username) {
        System.out.println("getByUsername " + username);
        return users.values().stream().filter(v -> {
            return username.equals(v.getUsername());
        }).findFirst().orElseGet(() -> null);
    }

    public void p() {
        System.out.println(users);
    }
}
