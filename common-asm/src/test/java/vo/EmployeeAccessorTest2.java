
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 22:49:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.BeanDescriptor;
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.bean.PropertyAccessorHolder;
import cn.featherfly.common.lang.Console;

/**
 * UserVisitorTest.
 *
 * @author zhongj
 */
public class EmployeeAccessorTest2 {

    static final int COUNT = 3;

    int total = 10000000;

    EmployeeAccessor ev = new EmployeeAccessor();
    EmployeeAccessorSwitch evSwitch = new EmployeeAccessorSwitch();
    EmployeeAccessorSwitchDirect evSwitchDirect = EmployeeAccessorSwitchDirect.INSTANCE;
    Employee e = new Employee();

    LocalDate date = LocalDate.now();

    BeanDescriptor<Employee> bd = BeanDescriptor.getBeanDescriptor(Employee.class);

    @BeforeClass
    void b() {
    }

    @AfterMethod
    void am() {
    }

    @Test()
    public void setVisitor() {

    }

    @Test(invocationCount = COUNT)
    public void getVisitor() {
        Employee employee = new Employee();
        assertNull(ev.getPropertyValue(employee, 20, 0));
        assertNull(ev.getPropertyValue(employee, 20, 1));
        assertNull(ev.getPropertyValue(employee, 20, 2));
        assertNull(ev.getPropertyValue(employee, 20, 3));
        User user = new User("yufei", 18);
        user.setUsername("yi");
        user.setGender("MALE");
        employee.setCreateUser(user);
        Console.log("0 = {}, 1 = {}, 2 = {}, 3 = {}", ev.getPropertyValue(employee, 20, 0),
            ev.getPropertyValue(employee, 20, 1), ev.getPropertyValue(employee, 20, 2),
            ev.getPropertyValue(employee, 20, 3));

        for (int i = 0; i < total; i++) {
            assertEquals(ev.getPropertyValue(employee, 20, 0), user.getName());
            assertEquals(ev.getPropertyValue(employee, 20, 1), user.getAge());
            assertEquals(ev.getPropertyValue(employee, 20, 2), user.getUsername());
            assertEquals(ev.getPropertyValue(employee, 20, 3), user.getGender());
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getVisitorException() {
        Employee employee = new Employee();
        employee.setId(1L);

        assertNull(ev.getPropertyValue(employee, 0, 1));
    }

    @Test(invocationCount = COUNT)
    public void getVisitorSwitch() {
        Employee employee = new Employee();
        assertNull(evSwitch.getPropertyValue(employee, 20, 0));
        assertNull(evSwitch.getPropertyValue(employee, 20, 1));
        assertNull(evSwitch.getPropertyValue(employee, 20, 2));
        assertNull(evSwitch.getPropertyValue(employee, 20, 3));
        User user = new User("yufei", 18);
        user.setUsername("yi");
        user.setGender("MALE");
        employee.setCreateUser(user);
        Console.log("0 = {}, 1 = {}, 2 = {}, 3 = {}", evSwitch.getPropertyValue(employee, 20, 0),
            evSwitch.getPropertyValue(employee, 20, 1), evSwitch.getPropertyValue(employee, 20, 2),
            evSwitch.getPropertyValue(employee, 20, 3));

        for (int i = 0; i < total; i++) {
            assertEquals(evSwitch.getPropertyValue(employee, 20, 0), user.getName());
            assertEquals(evSwitch.getPropertyValue(employee, 20, 1), user.getAge());
            assertEquals(evSwitch.getPropertyValue(employee, 20, 2), user.getUsername());
            assertEquals(evSwitch.getPropertyValue(employee, 20, 3), user.getGender());
        }
    }

    @Test(invocationCount = COUNT)
    public void getVisitorSwitchDirect() {
        Employee employee = new Employee();
        assertNull(evSwitchDirect.getPropertyValue(employee, 20, 0));
        assertNull(evSwitchDirect.getPropertyValue(employee, 20, 1));
        assertNull(evSwitchDirect.getPropertyValue(employee, 20, 2));
        assertNull(evSwitchDirect.getPropertyValue(employee, 20, 3));
        User user = new User("yufei", 18);
        user.setUsername("yi");
        user.setGender("MALE");
        employee.setCreateUser(user);
        Console.log("0 = {}, 1 = {}, 2 = {}, 3 = {}", evSwitchDirect.getPropertyValue(employee, 20, 0),
            evSwitchDirect.getPropertyValue(employee, 20, 1), evSwitchDirect.getPropertyValue(employee, 20, 2),
            evSwitchDirect.getPropertyValue(employee, 20, 3));

        for (int i = 0; i < total; i++) {
            assertEquals(evSwitchDirect.getPropertyValue(employee, 20, 0), user.getName());
            assertEquals(evSwitchDirect.getPropertyValue(employee, 20, 1), user.getAge());
            assertEquals(evSwitchDirect.getPropertyValue(employee, 20, 2), user.getUsername());
            assertEquals(evSwitchDirect.getPropertyValue(employee, 20, 3), user.getGender());
        }
    }

    @Test(priority = 100, invocationCount = COUNT)
    public void getVisitorByName() {
        Employee employee = new Employee();
        assertNull(ev.getPropertyValue(employee, "createUser", "name"));
        assertNull(ev.getPropertyValue(employee, "createUser", "age"));
        assertNull(ev.getPropertyValue(employee, "createUser", "username"));
        assertNull(ev.getPropertyValue(employee, "createUser", "gender"));

        User user = new User("yufei", 18);
        user.setUsername("yi");
        user.setGender("MALE");
        employee.setCreateUser(user);

        Console.log("0 = {}, 1 = {}, 2 = {}, 3 = {}", ev.getPropertyValue(employee, "createUser", "name"),
            ev.getPropertyValue(employee, "createUser", "age"), ev.getPropertyValue(employee, "createUser", "username"),
            ev.getPropertyValue(employee, "createUser", "gender"));

        for (int i = 0; i < total; i++) {
            assertEquals(ev.getPropertyValue(employee, "createUser", "name"), user.getName());
            assertEquals(ev.getPropertyValue(employee, "createUser", "age"), user.getAge());
            assertEquals(ev.getPropertyValue(employee, "createUser", "username"), user.getUsername());
            assertEquals(ev.getPropertyValue(employee, "createUser", "gender"), user.getGender());
        }
    }

    @Test(invocationCount = COUNT)
    public void getVisitorLookUpIndeByName() {
        Employee employee = new Employee();
        int[] nameIndex = ev.getPropertyIndexes("createUser", "name");
        int[] ageIndex = ev.getPropertyIndexes("createUser", "age");
        int[] usernameIndex = ev.getPropertyIndexes("createUser", "username");
        int[] genderIndex = ev.getPropertyIndexes("createUser", "gender");

        assertNull(ev.getPropertyValue(employee, nameIndex));
        assertNull(ev.getPropertyValue(employee, ageIndex));
        assertNull(ev.getPropertyValue(employee, usernameIndex));
        assertNull(ev.getPropertyValue(employee, genderIndex));

        User user = new User("yufei", 18);
        user.setUsername("yi");
        user.setGender("MALE");
        employee.setCreateUser(user);

        Console.log("0 = {}, 1 = {}, 2 = {}, 3 = {}", ev.getPropertyValue(employee, nameIndex),
            ev.getPropertyValue(employee, ageIndex), ev.getPropertyValue(employee, usernameIndex),
            ev.getPropertyValue(employee, genderIndex));

        for (int i = 0; i < total; i++) {
            assertEquals(ev.getPropertyValue(employee, nameIndex), user.getName());
            assertEquals(ev.getPropertyValue(employee, ageIndex), user.getAge());
            assertEquals(ev.getPropertyValue(employee, usernameIndex), user.getUsername());
            assertEquals(ev.getPropertyValue(employee, genderIndex), user.getGender());
        }
    }

    @Test(invocationCount = COUNT)
    public void getVisitorSwitchLookUpIndeByName() {
        Employee employee = new Employee();
        int[] nameIndex = evSwitch.getPropertyIndexes("createUser", "name");
        int[] ageIndex = evSwitch.getPropertyIndexes("createUser", "age");
        int[] usernameIndex = evSwitch.getPropertyIndexes("createUser", "username");
        int[] genderIndex = evSwitch.getPropertyIndexes("createUser", "gender");

        assertNull(evSwitch.getPropertyValue(employee, nameIndex));
        assertNull(evSwitch.getPropertyValue(employee, ageIndex));
        assertNull(evSwitch.getPropertyValue(employee, usernameIndex));
        assertNull(evSwitch.getPropertyValue(employee, genderIndex));

        User user = new User("yufei", 18);
        user.setUsername("yi");
        user.setGender("MALE");
        employee.setCreateUser(user);

        Console.log("0 = {}, 1 = {}, 2 = {}, 3 = {}", evSwitch.getPropertyValue(employee, nameIndex),
            evSwitch.getPropertyValue(employee, ageIndex), evSwitch.getPropertyValue(employee, usernameIndex),
            evSwitch.getPropertyValue(employee, genderIndex));

        for (int i = 0; i < total; i++) {
            assertEquals(evSwitch.getPropertyValue(employee, nameIndex), user.getName());
            assertEquals(evSwitch.getPropertyValue(employee, ageIndex), user.getAge());
            assertEquals(evSwitch.getPropertyValue(employee, usernameIndex), user.getUsername());
            assertEquals(evSwitch.getPropertyValue(employee, genderIndex), user.getGender());
        }
    }

    @Test(invocationCount = COUNT)
    public void getVisitorSwitchDirectLookUpIndeByName() {
        Employee employee = new Employee();
        int[] nameIndex = evSwitchDirect.getPropertyIndexes("createUser", "name");
        int[] ageIndex = evSwitchDirect.getPropertyIndexes("createUser", "age");
        int[] usernameIndex = evSwitchDirect.getPropertyIndexes("createUser", "username");
        int[] genderIndex = evSwitchDirect.getPropertyIndexes("createUser", "gender");

        assertNull(evSwitchDirect.getPropertyValue(employee, nameIndex));
        assertNull(evSwitchDirect.getPropertyValue(employee, ageIndex));
        assertNull(evSwitchDirect.getPropertyValue(employee, usernameIndex));
        assertNull(evSwitchDirect.getPropertyValue(employee, genderIndex));

        User user = new User("yufei", 18);
        user.setUsername("yi");
        user.setGender("MALE");
        employee.setCreateUser(user);

        Console.log("0 = {}, 1 = {}, 2 = {}, 3 = {}", evSwitchDirect.getPropertyValue(employee, nameIndex),
            evSwitchDirect.getPropertyValue(employee, ageIndex),
            evSwitchDirect.getPropertyValue(employee, usernameIndex),
            evSwitchDirect.getPropertyValue(employee, genderIndex));

        for (int i = 0; i < total; i++) {
            assertEquals(evSwitchDirect.getPropertyValue(employee, nameIndex), user.getName());
            assertEquals(evSwitchDirect.getPropertyValue(employee, ageIndex), user.getAge());
            assertEquals(evSwitchDirect.getPropertyValue(employee, usernameIndex), user.getUsername());
            assertEquals(evSwitchDirect.getPropertyValue(employee, genderIndex), user.getGender());
        }
    }

    @Test
    public void visitorHolder() {
        Employee employee = new Employee();

        Object obj = employee;

        if (employee instanceof PropertyAccessorHolder) {
            @SuppressWarnings("unchecked")
            PropertyAccessor<Object> visitor = ((PropertyAccessorHolder<Object>) obj).getHoldingPropertyAccessor();

            assertNull(visitor.getPropertyValue(obj, 1));

            String name = "setname";
            visitor.setPropertyValue(obj, 1, name);
            assertEquals(visitor.getPropertyValue(obj, 1), name);

        }

    }
}
