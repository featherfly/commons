
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 17:40:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.asm.instantiator;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.AsmInstantiatorFactory;
import cn.featherfly.common.bean.Instantiator;

/**
 * ClassLoaderProxyTest.
 *
 * @author zhongj
 */
public class InstantiatorFactorTest {

    private static final int loops = 1000000;

    AsmInstantiatorFactory factor;

    @BeforeClass
    void before() {
        factor = new AsmInstantiatorFactory(Thread.currentThread().getContextClassLoader());
    }

    @Test
    void test1() throws Exception {
        Instantiator<User> userInstantiator = factor.create(User.class);

        User user = userInstantiator.instantiate();
        System.out.println(user.getDescp());

        Instantiator<Role> roleInstantiator = factor.create(Role.class);
        Role role = roleInstantiator.instantiate();

        System.out.println(role.getDescp());
    }

    @Test
    void performanceWithInstantiator() throws Exception {
        Instantiator<User> userInstantiator = factor.create(User.class);
        for (int i = 0; i < loops; i++) {
            userInstantiator.instantiate();
        }
    }

    @Test
    void performanceWithReflection() throws Exception {
        for (int i = 0; i < loops; i++) {
            User.class.newInstance();
        }
    }

    @Test
    void performanceWithNew() throws Exception {
        for (int i = 0; i < loops; i++) {
            new User();
        }
    }

}
