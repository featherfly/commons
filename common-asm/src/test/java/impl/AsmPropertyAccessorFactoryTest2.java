
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 19:06:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package impl;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.bean.AsmPropertyAccessorFactory;
import cn.featherfly.common.bean.NoSuchPropertyException;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.bean.PropertyAccessor;
import cn.featherfly.common.lang.BytesClassLoader;
import vo.Employee;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyAccessorFactoryTest2 {

    AsmPropertyAccessorFactory factory;

    PropertyAccessor<Employee> visitor;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyAccessorFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
        visitor = factory.create(Employee.class);
    }

    @Test
    void getPropertyValueByIndex() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        role.setId(123);

        PropertyAccessor<Role> visitor = factory.create(Role.class);

        assertNull(role.getName());

        final String name = "admin";

        role.setName(name);

        assertEquals(role.getName(), name);

        assertEquals(visitor.getPropertyValue(role, 1), name);
    }

    @Test
    void getPropertyValueByName() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        role.setId(123);

        PropertyAccessor<Role> visitor = factory.create(Role.class);

        assertNull(role.getName());

        final String name = "admin";

        role.setName(name);

        assertEquals(role.getName(), name);

        assertEquals(visitor.getPropertyValue(role, "name"), name);
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyValueByIndexNoSuchPropertyException() throws Exception {
        Role role = new Role();
        PropertyAccessor<Role> visitor = factory.create(Role.class);
        visitor.getPropertyValue(role, 100);
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyValueByNameNoSuchPropertyException() throws Exception {
        Role role = new Role();
        PropertyAccessor<Role> visitor = factory.create(Role.class);
        visitor.getPropertyValue(role, "notExistsProperty");
    }

    @Test
    void setPropertyValueByIndex() throws Exception {
        Role role = new Role();

        final int id = 123;

        assertNull(role.getId());

        PropertyAccessor<Role> visitor = factory.create(Role.class);

        visitor.setPropertyValue(role, 0, id);

        assertEquals(role.getId(), new Integer(id));

        final String name = "admin";

        assertNull(role.getName());

        visitor.setPropertyValue(role, 1, name);

        assertEquals(role.getName(), name);
    }

    @Test
    void setPropertyValueByName() throws Exception {
        Role role = new Role();

        final int id = 123;

        assertNull(role.getId());

        PropertyAccessor<Role> visitor = factory.create(Role.class);

        visitor.setPropertyValue(role, "id", id);

        assertEquals(role.getId(), new Integer(id));

        final String name = "admin";

        assertNull(role.getName());

        visitor.setPropertyValue(role, "name", name);

        assertEquals(role.getName(), name);
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void setPropertyValueByIndexNoSuchPropertyException() throws Exception {
        Role role = new Role();
        PropertyAccessor<Role> visitor = factory.create(Role.class);
        visitor.setPropertyValue(role, 100, "");
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void setPropertyValueByNameNoSuchPropertyException() throws Exception {
        Role role = new Role();
        PropertyAccessor<Role> visitor = factory.create(Role.class);
        visitor.setPropertyValue(role, "notExistsProperty", "");
    }

    @Test
    void geProperty() {
        Role role = new Role();
        role.setId(123);

        PropertyAccessor<Role> visitor = factory.create(Role.class);

        Property<Role, Integer> id = visitor.getProperty(0);

        assertEquals(id.get(role), role.getId());
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyByIndexNoSuchPropertyException() {
        Role role = new Role();
        role.setId(123);

        PropertyAccessor<Role> visitor = factory.create(Role.class);

        Property<Role, Integer> id = visitor.getProperty(100);

        assertEquals(id.get(role), role.getId());
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyByNameNoSuchPropertyException() {
        Role role = new Role();
        role.setId(123);

        PropertyAccessor<Role> visitor = factory.create(Role.class);

        Property<Role, Integer> id = visitor.getProperty("notExistsProperty");

        assertEquals(id.get(role), role.getId());
    }

}
