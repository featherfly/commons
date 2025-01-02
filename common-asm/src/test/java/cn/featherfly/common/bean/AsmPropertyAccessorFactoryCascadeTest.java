
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 19:06:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.BytesClassLoader;
import impl.Role;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyAccessorFactoryCascadeTest {

    AsmPropertyAccessorFactory factory;
    PropertyAccessor<Role> accessor;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyAccessorFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
        accessor = factory.create(Role.class);
    }

    @Test
    void getPropertyValueByIndex() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        role.setId(123);

        assertNull(role.getName());

        final String name = "admin";

        role.setName(name);

        assertEquals(role.getName(), name);

        assertEquals(accessor.getPropertyValue(role, 1), name);
    }

    @Test
    void getPropertyValueByName() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        role.setId(123);

        assertNull(role.getName());

        final String name = "admin";

        role.setName(name);

        assertEquals(role.getName(), name);

        assertEquals(accessor.getPropertyValue(role, "name"), name);
    }

    @Test
    void getPropertyValuePrimitive() throws Exception {
        Role role = new Role();
        role.setAge(18);
        role.setAvailable(true);
        assertEquals(accessor.getPropertyValue(role, "age"), 18);

        assertEquals(accessor.getPropertyValue(role, "available"), true);
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyValueByIndexNoSuchPropertyException() throws Exception {
        Role role = new Role();
        PropertyAccessor<Role> visitor = factory.create(Role.class);
        visitor.getPropertyValue(role, 100);
    }

    @Test(expectedExceptions = PropertyAccessException.class)
    void gePropertyValueByIndexPropertyAccessException() throws Exception {
        Role role = new Role();
        role.setId(1);
        PropertyAccessor<Role> visitor = factory.create(Role.class);
        visitor.getPropertyValue(role, 0, 0);
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyValueByNameNoSuchPropertyException() throws Exception {
        Role role = new Role();
        accessor.getPropertyValue(role, "notExistsProperty");
    }

    @Test(expectedExceptions = PropertyAccessException.class)
    void gePropertyValueByNamePropertyAccessException() throws Exception {
        Role role = new Role();
        role.setId(1);
        PropertyAccessor<Role> visitor = factory.create(Role.class);
        visitor.getPropertyValue(role, "id", "name");
    }

    @Test
    void setPropertyValueByIndex() throws Exception {
        Role role = new Role();

        final int id = 123;

        assertNull(role.getId());

        accessor.setPropertyValue(role, 0, id);

        assertEquals(role.getId(), new Integer(id));

        final String name = "admin";

        assertNull(role.getName());

        accessor.setPropertyValue(role, 1, name);

        assertEquals(role.getName(), name);
    }

    @Test
    void setPropertyValueByName() throws Exception {
        Role role = new Role();

        final int id = 123;

        assertNull(role.getId());

        accessor.setPropertyValue(role, "id", id);

        assertEquals(role.getId(), new Integer(id));

        final String name = "admin";

        assertNull(role.getName());

        accessor.setPropertyValue(role, "name", name);

        assertEquals(role.getName(), name);
    }

    @Test
    void setPropertyValuePrimitive() throws Exception {
        Role role = new Role();
        final int age = 18;
        final boolean available = true;
        accessor.setPropertyValue(role, "age", 18);
        accessor.setPropertyValue(role, "available", true);

        assertEquals(role.getAge(), age);
        assertEquals(role.isAvailable(), available);

    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void setPropertyValueByIndexNoSuchPropertyException() throws Exception {
        Role role = new Role();
        accessor.setPropertyValue(role, 100, "");
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void setPropertyValueByNameNoSuchPropertyException() throws Exception {
        Role role = new Role();
        accessor.setPropertyValue(role, "notExistsProperty", "");
    }

    @Test
    void getProperties() {
        for (int i = 0; i < accessor.getProperties().length; i++) {
            assertTrue(accessor.getProperties()[i].getIndex() == i);
        }
    }

    @Test
    void geProperty() {
        Role role = new Role();
        role.setId(123);

        Property<Role, Integer> id = accessor.getProperty(0);

        assertEquals(id.get(role), role.getId());
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyByIndexNoSuchPropertyException() {
        Role role = new Role();
        role.setId(123);

        Property<Role, Integer> id = accessor.getProperty(100);

        assertEquals(id.get(role), role.getId());
    }

    @Test(expectedExceptions = NoSuchPropertyException.class)
    void gePropertyByNameNoSuchPropertyException() {
        Role role = new Role();
        role.setId(123);

        Property<Role, Integer> id = accessor.getProperty("notExistsProperty");

        assertEquals(id.get(role), role.getId());
    }

}
