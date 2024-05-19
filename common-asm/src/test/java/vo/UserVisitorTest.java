
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

import cn.featherfly.common.bean.BeanDescriptor;

/**
 * UserVisitorTest.
 *
 * @author zhongj
 */
public class UserVisitorTest {

    int max = 50000000;

    UserVisitor uv = new UserVisitor();
    UserVisitor2 uv2 = new UserVisitor2();
    User u = new User();
    BeanDescriptor<User> bd;

    @BeforeClass
    void b() {
        u.setName("yufei");
        u.setAge(18);
        bd = BeanDescriptor.getBeanDescriptor(User.class);
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
        for (int i = 0; i < max; i++) {
            u.setName(null);
            u.setAge(null);
            u.setUsername(null);
            u.setGender(null);
        }
    }

    @Test
    public void get() {
        for (int i = 0; i < max; i++) {
            u.getName();
            u.getAge();
            u.getUsername();
            u.getGender();
        }
    }

    @Test
    public void setVisitor() {
        for (int i = 0; i < max; i++) {
            uv.setProperty(u, 0, null);
            uv.setProperty(u, 1, null);
            uv.setProperty(u, 2, null);
            uv.setProperty(u, 3, null);
        }
    }

    @Test
    public void getVisitor() {
        for (int i = 0; i < max; i++) {
            uv.getProperty(u, 0);
            uv.getProperty(u, 1);
            uv.getProperty(u, 2);
            uv.getProperty(u, 3);
        }
    }

    @Test
    public void setVisitorByName() {
        for (int i = 0; i < max; i++) {
            uv.setProperty(u, "name", null);
            uv.setProperty(u, "age", null);
            uv.setProperty(u, "username", null);
            uv.setProperty(u, "gender", null);
        }
    }

    @Test
    public void getVisitorByName() {
        for (int i = 0; i < max; i++) {
            uv.getProperty(u, "name");
            uv.getProperty(u, "age");
            uv.getProperty(u, "username");
            uv.getProperty(u, "gender");
        }
    }

    @Test
    public void setVisitor2() {
        for (int i = 0; i < max; i++) {
            uv2.setProperty(u, 0, null);
            uv2.setProperty(u, 1, null);
            uv2.setProperty(u, 2, null);
            uv2.setProperty(u, 3, null);
        }
    }

    @Test
    public void getVisitor2() {
        for (int i = 0; i < max; i++) {
            uv2.getProperty(u, 0);
            uv2.getProperty(u, 1);
            uv2.getProperty(u, 2);
            uv2.getProperty(u, 3);
        }
    }

    @Test
    public void setVisitorByName2() {
        for (int i = 0; i < max; i++) {
            uv2.setProperty(u, "name", null);
            uv2.setProperty(u, "age", null);
            uv2.setProperty(u, "username", null);
            uv2.setProperty(u, "gender", null);
        }
    }

    @Test
    public void getVisitorByName2() {
        for (int i = 0; i < max; i++) {
            uv2.getProperty(u, "name");
            uv2.getProperty(u, "age");
            uv2.getProperty(u, "username");
            uv2.getProperty(u, "gender");
        }
    }

    @Test
    public void setReflection() {
        for (int i = 0; i < max; i++) {
            bd.getBeanProperty(0).setValue(u, null);
            bd.getBeanProperty(1).setValue(u, null);
            bd.getBeanProperty(2).setValue(u, null);
            bd.getBeanProperty(3).setValue(u, null);
        }
    }

    @Test
    public void getReflection() {
        for (int i = 0; i < max; i++) {
            bd.getBeanProperty(0).getValue(u);
            bd.getBeanProperty(1).getValue(u);
            bd.getBeanProperty(2).getValue(u);
            bd.getBeanProperty(3).getValue(u);
        }
    }

    @Test
    public void setReflectionByName() {
        for (int i = 0; i < max; i++) {
            bd.getBeanProperty("name").setValue(u, null);
            bd.getBeanProperty("age").setValue(u, null);
            bd.getBeanProperty("username").setValue(u, null);
            bd.getBeanProperty("gender").setValue(u, null);
        }
    }

    @Test
    public void getReflectionByName() {
        for (int i = 0; i < max; i++) {
            bd.getBeanProperty("name").getValue(u);
            bd.getBeanProperty("age").getValue(u);
            bd.getBeanProperty("username").getValue(u);
            bd.getBeanProperty("gender").getValue(u);
        }
    }
}
