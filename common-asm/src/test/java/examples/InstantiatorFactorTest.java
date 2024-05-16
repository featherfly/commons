
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 17:40:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.asm.InstantiatorFactor;
import cn.featherfly.common.bean.Instantiator;

/**
 * ClassLoaderProxyTest.
 *
 * @author zhongj
 */
public class InstantiatorFactorTest {

    InstantiatorFactor factor;

    @BeforeClass
    void before() {
        factor = new InstantiatorFactor();
    }

    @Test
    void test1() throws Exception {
        Instantiator<User> userInstantiator = factor.create(User.class, null);

        User user = userInstantiator.instantiate();
        System.out.println(user.getDescp());

        Instantiator<Role> roleInstantiator = factor.create(Role.class, null);
        Role role = roleInstantiator.instantiate();

        System.out.println(role.getDescp());
    }

}
