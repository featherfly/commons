
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 19:06:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.BytesClassLoader;
import vo.Employee;
import vo.User;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyAccessorFactoryPropertyAccessorCascadeTest {

    AsmPropertyAccessorFactory factory;
    PropertyAccessor<Employee> employeePropertyAccessor;

    Employee employee;
    User user;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyAccessorFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
        employeePropertyAccessor = factory.create(Employee.class);
        // after scan package and create which your wanted create, then invoke this
        factory.createPropertyAccessorCascade();
    }

    @BeforeMethod
    void b() {
        user = new User();
        user.setName("user_yi");
        user.setAge(18);

        employee = new Employee();
        employee.setId(123L);
        employee.setName("employee_yi");
        employee.setCreateUser(user);
    }

    @Test
    void getPropertyValueByIndex() throws Exception {
        assertEquals(employee.getId(), employeePropertyAccessor.getPropertyValue(employee, 0));

        assertEquals(employee.getCreateUser().getName(), employeePropertyAccessor.getPropertyValue(employee, 20, 0));
    }

    @Test
    void getPropertyValueByName() throws Exception {
        assertEquals(employee.getId(), employeePropertyAccessor.getPropertyValue(employee, "id"));

        assertEquals(employee.getCreateUser().getName(),
            employeePropertyAccessor.getPropertyValue(employee, "createUser", "name"));
    }

    @Test
    void setPropertyValueByIndex() throws Exception {
        final Long id = 22L;
        assertNotEquals(employee.getId(), id);

        employeePropertyAccessor.setPropertyValue(employee, 0, id);

        assertEquals(employee.getId(), id);

        final String name = "newName";

        assertNotEquals(employee.getCreateUser().getName(), name);

        employeePropertyAccessor.setPropertyValue(employee, new int[] { 20, 0 }, name);

        assertEquals(employee.getCreateUser().getName(), name);
    }

    @Test
    void setPropertyValueByIndexAutoCreate() throws Exception {

        employee.setCreateUser(null);

        assertNull(employee.getCreateUser());

        final String name = "newName";

        employeePropertyAccessor.setPropertyValue(employee, new int[] { 20, 0 }, name);

        assertEquals(employee.getCreateUser().getName(), name);
    }

    @Test
    void setPropertyValueByName() throws Exception {
        final Long id = 22L;
        assertNotEquals(employee.getId(), id);

        employeePropertyAccessor.setPropertyValue(employee, "id", id);

        assertEquals(employee.getId(), id);

        final String name = "newName";

        assertNotEquals(employee.getCreateUser().getName(), name);

        employeePropertyAccessor.setPropertyValue(employee, new String[] { "createUser", "name" }, name);

        assertEquals(employee.getCreateUser().getName(), name);
    }

    @Test
    void setPropertyValueByNameAutoCreate() throws Exception {

        employee.setCreateUser(null);

        assertNull(employee.getCreateUser());

        final String name = "newName";

        employeePropertyAccessor.setPropertyValue(employee, new String[] { "createUser", "name" }, name);

        assertEquals(employee.getCreateUser().getName(), name);
    }
}
