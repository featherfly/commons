
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-23 23:43:23
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.AsmPropertyAccessorFactory;
import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.bean.Property;

/**
 * MethodInvokePerfTest.
 *
 * @author zhongj
 */
public class MethodInvokePerfTest {

    int total = 100000000;

    BeanProperty<User, String> userNameRef;

    Property<User, String> userNameBc;

    MethodHandle userNameMhGet;
    MethodHandle userNameMhSet;

    User user;

    @BeforeClass
    void setup() throws NoSuchMethodException, IllegalAccessException {
        userNameRef = BeanDescriptor.getBeanDescriptor(User.class).getBeanProperty(User::getName);
        userNameBc = new AsmPropertyAccessorFactory(Thread.currentThread().getContextClassLoader()).create(User.class)
            .getProperty("name");

        Lookup lookup = MethodHandles.lookup();
        userNameMhGet = lookup.findVirtual(User.class, "getName", MethodType.methodType(String.class));
        userNameMhSet = lookup.findVirtual(User.class, "setName", MethodType.methodType(Void.TYPE, String.class));
    }

    @BeforeMethod
    void bm() {
        user = new User();
    }

    @Test(groups = "perfTestSet")
    void set() {
        for (int i = 0; i < total; i++) {
            user.setName("yi");
        }
    }

    @Test(dependsOnGroups = "perfTestSet")
    void get() {
        for (int i = 0; i < total; i++) {
            user.getName();
        }
    }

    @Test(groups = "perfTestSet")
    void setPropertyAccessor() {
        for (int i = 0; i < total; i++) {
            userNameBc.set(user, "yi");
        }
    }

    @Test(dependsOnGroups = "perfTestSet")
    void getPropertyAccessor() {
        for (int i = 0; i < total; i++) {
            userNameBc.get(user);
        }
    }

    @Test(groups = "perfTestSet")
    void setMethodHandle() throws Throwable {
        MethodHandle set = userNameMhSet.bindTo(user);
        for (int i = 0; i < total; i++) {
            set.invoke("yi");
        }
    }

    @Test(dependsOnGroups = "perfTestSet")
    void getMethodHandle() throws Throwable {
        MethodHandle get = userNameMhGet.bindTo(user);
        for (int i = 0; i < total; i++) {
            get.invoke();
        }
    }

    @Test(groups = "perfTestSet")
    void setRef() throws Throwable {
        for (int i = 0; i < total; i++) {
            userNameRef.set(user, "yi");
        }
    }

    @Test(dependsOnGroups = "perfTestSet")
    void getRef() throws Throwable {
        for (int i = 0; i < total; i++) {
            userNameRef.get(user);
        }
    }

}
