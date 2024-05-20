
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:49:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.AsmPropertyAccessorFactory;
import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.PropertyAccessor;

/**
 * UserVisitorTest.
 *
 * @author zhongj
 */
public class UserAccessorTest extends TestBase {

    UserAccessor uv = new UserAccessor();
    UserAccessor2 uv2 = new UserAccessor2();
    UserAccessorSwitch uvSwitch = new UserAccessorSwitch();
    UserAccessorSwitch2 uvSwitch2 = new UserAccessorSwitch2();
    UserAccessorSwitchDirect uvSwitchDirect = new UserAccessorSwitchDirect();
    PropertyAccessor<User> pv;
    User u = new User();
    BeanDescriptor<User> bd;

    @BeforeClass
    void b() {
        u.setName("yufei");
        u.setAge(18);
        bd = BeanDescriptor.getBeanDescriptor(User.class);
        AsmPropertyAccessorFactory factory = new AsmPropertyAccessorFactory(
            Thread.currentThread().getContextClassLoader());
        pv = factory.create(User.class);
    }

    @AfterMethod
    void am() {
        System.out.println("name: " + u.getName());
        System.out.println("age: " + u.getAge());
        System.out.println("username: " + u.getUsername());
        System.out.println("gender: " + u.getGender());
    }

    @Test
    public void set() {
        for (int i = 0; i < total; i++) {
            u.setName("yi");
            u.setAge(18);
            u.setUsername("yufei");
            u.setGender("MALE");
        }
    }

    @Test
    public void get() {
        for (int i = 0; i < total; i++) {
            u.getName();
            u.getAge();
            u.getUsername();
            u.getGender();
        }
    }

    @Test
    public void setVisitor() {
        for (int i = 0; i < total; i++) {
            uv.setProperty(u, 0, "yi");
            uv.setProperty(u, 1, 18);
            uv.setProperty(u, 2, "yufei");
            uv.setProperty(u, 3, "MALE");
        }
    }

    @Test
    public void getVisitor() {
        for (int i = 0; i < total; i++) {
            uv.getProperty(u, 0);
            uv.getProperty(u, 1);
            uv.getProperty(u, 2);
            uv.getProperty(u, 3);
        }
    }

    @Test
    public void setVisitorByName() {
        for (int i = 0; i < total; i++) {
            uv.setProperty(u, "name", "yi");
            uv.setProperty(u, "age", 18);
            uv.setProperty(u, "username", "yufei");
            uv.setProperty(u, "gender", "MALE");
        }
    }

    @Test
    public void getVisitorByName() {
        for (int i = 0; i < total; i++) {
            uv.getProperty(u, "name");
            uv.getProperty(u, "age");
            uv.getProperty(u, "username");
            uv.getProperty(u, "gender");
        }
    }

    @Test
    public void setVisitor2() {
        for (int i = 0; i < total; i++) {
            uv2.setPropertyValue(u, 0, "yi");
            uv2.setPropertyValue(u, 1, 18);
            uv2.setPropertyValue(u, 2, "yufei");
            uv2.setPropertyValue(u, 3, "MALE");
        }
    }

    @Test
    public void getVisitor2() {
        for (int i = 0; i < total; i++) {
            uv2.getPropertyValue(u, 0);
            uv2.getPropertyValue(u, 1);
            uv2.getPropertyValue(u, 2);
            uv2.getPropertyValue(u, 3);
        }
    }

    @Test
    public void setVisitorByName2() {
        for (int i = 0; i < total; i++) {
            uv2.setPropertyValue(u, "name", "yi");
            uv2.setPropertyValue(u, "age", 18);
            uv2.setPropertyValue(u, "username", "yufei");
            uv2.setPropertyValue(u, "gender", "MALE");
        }
    }

    @Test
    public void getVisitorByName2() {
        for (int i = 0; i < total; i++) {
            uv2.getPropertyValue(u, "name");
            uv2.getPropertyValue(u, "age");
            uv2.getPropertyValue(u, "username");
            uv2.getPropertyValue(u, "gender");
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test
    public void setVisitorSwitch() {
        for (int i = 0; i < total; i++) {
            uvSwitch.setPropertyValue(u, 0, "yi");
            uvSwitch.setPropertyValue(u, 1, 18);
            uvSwitch.setPropertyValue(u, 2, "yufei");
            uvSwitch.setPropertyValue(u, 3, "MALE");
        }
    }

    @Test
    public void getVisitorSwitch() {
        for (int i = 0; i < total; i++) {
            uvSwitch.getPropertyValue(u, 0);
            uvSwitch.getPropertyValue(u, 1);
            uvSwitch.getPropertyValue(u, 2);
            uvSwitch.getPropertyValue(u, 3);
        }
    }

    //    @Test
    //    public void setVisitorByName3() {
    //        for (int i = 0; i < max; i++) {
    //            uv3.setPropertyValue(u, "name", null);
    //            uv3.setPropertyValue(u, "age", null);
    //            uv3.setPropertyValue(u, "username", null);
    //            uv3.setPropertyValue(u, "gender", null);
    //        }
    //    }

    //    @Test
    //    public void getVisitorByName3() {
    //        for (int i = 0; i < max; i++) {
    //            uv3.getPropertyValue(u, "name");
    //            uv3.getPropertyValue(u, "age");
    //            uv3.getPropertyValue(u, "username");
    //            uv3.getPropertyValue(u, "gender");
    //        }
    //    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test
    public void setVisitorSwitch2() {
        for (int i = 0; i < total; i++) {
            uvSwitch2.setPropertyValue(u, 0, "yi");
            uvSwitch2.setPropertyValue(u, 1, 18);
            uvSwitch2.setPropertyValue(u, 2, "yufei");
            uvSwitch2.setPropertyValue(u, 3, "MALE");
        }
    }

    @Test
    public void getVisitorSwitch2() {
        for (int i = 0; i < total; i++) {
            uvSwitch2.getPropertyValue(u, 0);
            uvSwitch2.getPropertyValue(u, 1);
            uvSwitch2.getPropertyValue(u, 2);
            uvSwitch2.getPropertyValue(u, 3);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test
    public void setVisitorSwitchDirect() {
        for (int i = 0; i < total; i++) {
            uvSwitchDirect.setPropertyValue(u, 0, "yi");
            uvSwitchDirect.setPropertyValue(u, 1, 18);
            uvSwitchDirect.setPropertyValue(u, 2, "yufei");
            uvSwitchDirect.setPropertyValue(u, 3, "MALE");
        }
    }

    @Test
    public void getVisitorSwitchDirect() {
        for (int i = 0; i < total; i++) {
            uvSwitchDirect.getPropertyValue(u, 0);
            uvSwitchDirect.getPropertyValue(u, 1);
            uvSwitchDirect.getPropertyValue(u, 2);
            uvSwitchDirect.getPropertyValue(u, 3);
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    @Test
    public void setVisitorByAmsFactory() {
        for (int i = 0; i < total; i++) {
            pv.setPropertyValue(u, 0, "yi");
            pv.setPropertyValue(u, 1, 18);
            pv.setPropertyValue(u, 2, "yufei");
            pv.setPropertyValue(u, 3, "MALE");
        }
    }

    @Test
    public void getVisitorByAmsFactory() {
        for (int i = 0; i < total; i++) {
            pv.getPropertyValue(u, 0);
            pv.getPropertyValue(u, 1);
            pv.getPropertyValue(u, 2);
            pv.getPropertyValue(u, 3);
        }
    }
}
