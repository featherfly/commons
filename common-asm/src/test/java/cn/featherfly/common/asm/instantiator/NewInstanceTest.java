
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:27:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.asm.instantiator;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.AsmInstantiatorFactory;
import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.bean.Instantiator;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.ClassUtils;
import vo.User;

/**
 * NewInstanceTest.
 *
 * @author zhongj
 */
public class NewInstanceTest {

    int max = 100000000;

    Constructor<User> cons;

    Supplier<User> create;

    Instantiator<User> instantiator;

    @BeforeClass
    public void before() throws Exception {
        cons = User.class.getConstructor(ArrayUtils.EMPTY_CLASS_ARRAY);
        create = () -> new User();

        AsmInstantiatorFactory factor = new AsmInstantiatorFactory(
            () -> Thread.currentThread().getContextClassLoader());
        instantiator = factor.create(User.class);
    }

    @Test
    public void javaNew() {
        for (int i = 0; i < max; i++) {
            new User();
        }
    }

    @Test
    public void javaLambda() {
        for (int i = 0; i < max; i++) {
            create.get();
        }
    }

    @Test
    public void javaAsm() {
        for (int i = 0; i < max; i++) {
            instantiator.instantiate();
        }
    }

    @Test
    public void BeanUtils_instantiateClass() {
        for (int i = 0; i < max; i++) {
            BeanUtils.instantiateClass(User.class);
        }
    }

    @Test
    public void ClassUtils_newInstance() {
        for (int i = 0; i < max; i++) {
            ClassUtils.newInstance(User.class);
        }
    }

    @Test
    public void refelction_constructor() throws Exception {
        for (int i = 0; i < max; i++) {
            User.class.getConstructor(ArrayUtils.EMPTY_CLASS_ARRAY).newInstance(ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
    }

    @Test
    public void refelction_constructorWithCache() throws Exception {
        for (int i = 0; i < max; i++) {
            cons.newInstance(ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
    }

    @Test
    public void refelction_constructorWithCache2() throws Exception {
        cons.setAccessible(true);
        for (int i = 0; i < max; i++) {
            cons.newInstance(ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
    }

    @Test
    public void refelction_constructorWithCacheAndUncheck() throws Exception {
        cons.setAccessible(false); // 关闭访问检查
        for (int i = 0; i < max; i++) {
            cons.newInstance(ArrayUtils.EMPTY_OBJECT_ARRAY);
        }
    }

}
