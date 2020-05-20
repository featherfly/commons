
package cn.featherfly.common.spring.cache;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * <p>
 * MulitiUniqueKeyCacheTest
 * </p>
 * <p>
 * 2019-08-08
 * </p>
 *
 * @author zhongj
 */
@ContextConfiguration(locations = { "classpath*:app.xml" })
public class MulitiUniqueKeyCacheTest extends AbstractTestNGSpringContextTests {

    java.util.List<User> users = new ArrayList<>();

    @Resource
    UserService userService;

    @BeforeClass
    void setUp() {
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setId(i);
            user.setName("name_" + i);
            user.setUsername("username_" + i);
            user.setDescp("descp_" + i);
            user.setEmail("email_" + i);
            user.setToken("token_" + i);
            users.add(user);
        }
        System.out.println(users.size());
    }

    @Test
    public void test() throws InterruptedException {
        User u = userService.get(1);
        System.out.println(u);

        users.forEach(v -> {
            System.out.println(v);
            userService.save(v);
        });

        System.out.println("save ok");
        u = userService.get(1);
        System.out.println(u);
        u = userService.get(1);
        System.out.println(u);

        u = userService.getByUsername("username_" + 1);
        System.out.println(u);
        u = userService.getByUsername("username_" + 1);
        System.out.println(u);

        userService.p();
    }
}
