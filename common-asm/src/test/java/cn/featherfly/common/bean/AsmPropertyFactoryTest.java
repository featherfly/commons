
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 19:06:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.BytesClassLoader;
import impl.Role;
import impl.RolePropertyAccessor1;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyFactoryTest {

    AsmPropertyFactory factory;

    ClassLoader classLoader;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
        classLoader = Thread.currentThread().getContextClassLoader();
    }

    @Test
    void test() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        Property<Role,
            String> nameProperty = factory.newInstance(
                factory.create(RolePropertyAccessor1.class.getName(), Role.class, String.class, "name", 1, classLoader),
                new RolePropertyAccessor1());

        assertNull(role.getName());

        final String name = "admin";

        role.setName(name);

        assertEquals(role.getName(), name);

        assertEquals(nameProperty.get(role), name);

        final String name2 = "yufei";

        nameProperty.set(role, name2);

        assertEquals(role.getName(), name2);
    }

    //    // TODO 基本类型是否需要强制类型转换
    //    if (method.getReturnType() == Integer.TYPE) {
    //        methodNode.visitInsn(IRETURN);
    //    } else if (method.getReturnType() == Byte.TYPE) {
    //        methodNode.visitInsn(IRETURN);
    //    } else if (method.getReturnType() == Short.TYPE) {
    //        methodNode.visitInsn(IRETURN);
    //    } else if (method.getReturnType() == Character.TYPE) {
    //        methodNode.visitInsn(IRETURN);
    //    } else if (method.getReturnType() == Boolean.TYPE) {
    //        methodNode.visitInsn(IRETURN);
    //    } else if (method.getReturnType() == Long.TYPE) {
    //        methodNode.visitInsn(LRETURN);
    //    } else if (method.getReturnType() == Double.TYPE) {
    //        methodNode.visitInsn(DRETURN);
    //    } else if (method.getReturnType() == Float.TYPE) {
    //        methodNode.visitInsn(FRETURN);
    //    } else {
    //        methodNode.visitInsn(RETURN);
    //    }

    @Test
    void testPrimitiveType() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        role.setAge(18);
        Property<Role,
            Integer> ageProperty = factory.newInstance(
                factory.create(RolePropertyAccessor1.class.getName(), Role.class, int.class, "age", 3, classLoader),
                new RolePropertyAccessor1());

        assertEquals(ageProperty.get(role), Integer.valueOf(role.getAge()));
    }

    @Test
    void testPrimitiveBooleanType() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        role.setAvailable(false);
        Property<Role,
            Boolean> availableProperty = factory.newInstance(factory.create(RolePropertyAccessor1.class.getName(),
                Role.class, boolean.class, "available", 4, classLoader), new RolePropertyAccessor1());

        assertEquals(availableProperty.get(role), Boolean.valueOf(role.isAvailable()));
    }

    @Test(expectedExceptions = PropertyAccessException.class)
    void testPropertyNoSetter() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        Property<Role, Integer> readProperty = factory.newInstance(
            factory.create(RolePropertyAccessor1.class.getName(), Role.class, Integer.class, "read", 5, classLoader),
            new RolePropertyAccessor1());

        readProperty.set(role, 123);

        assertEquals(readProperty.get(role), role.getRead());
    }

    @Test(expectedExceptions = PropertyAccessException.class)
    void testPropertyNoGetter() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        Property<Role, Integer> writeProperty = factory.newInstance(
            factory.create(RolePropertyAccessor1.class.getName(), Role.class, Integer.class, "write", 6, classLoader),
            new RolePropertyAccessor1());

        writeProperty.get(role);
    }
}
