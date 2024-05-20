
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

import cn.featherfly.common.bean.AsmPropertyFactory;
import cn.featherfly.common.bean.Property;
import cn.featherfly.common.lang.BytesClassLoader;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyFactoryTest {

    AsmPropertyFactory factory;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
    }

    @Test
    void test() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        Property<Role,
            String> nameProperty = factory.newInstance(
                factory.create(RolePropertyAccessor1.class.getName(), Role.class, String.class, "name", 1),
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
                factory.create(RolePropertyAccessor1.class.getName(), Role.class, int.class, "age", 3),
                new RolePropertyAccessor1());

        assertEquals(ageProperty.get(role), Integer.valueOf(role.getAge()));
    }

    @Test
    void testPrimitiveBooleanType() throws Exception {
        //       String outerClassName, Class<T> instanceType, Class<V> propertyType, String propertyName, int propertyIndex
        Role role = new Role();
        role.setAvailable(false);
        Property<Role,
            Boolean> availableProperty = factory.newInstance(
                factory.create(RolePropertyAccessor1.class.getName(), Role.class, boolean.class, "available", 4),
                new RolePropertyAccessor1());

        assertEquals(availableProperty.get(role), Boolean.valueOf(role.isAvailable()));
    }
}
