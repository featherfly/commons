
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 19:06:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.BytesClassLoader;
import vo.Teacher;
import vo.User;

/**
 * AsmPropertyFactoryTest.
 *
 * @author zhongj
 */
public class AsmPropertyAccessorFactoryTest {

    AsmPropertyAccessorFactory factory;
    PropertyAccessor<Teacher> accessor;

    @BeforeClass
    void bc() {
        factory = new AsmPropertyAccessorFactory(new BytesClassLoader(Thread.currentThread().getContextClassLoader()));
        accessor = factory.create(Teacher.class);
    }

    @Test
    void getPropertyValueByIndex() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setNo("no:123");

        assertNull(teacher.getUser());

        User user = new User();
        user.setName("teacher_user_name");

        teacher.setUser(user);

        assertEquals(teacher.getUser().getName(), user.getName());

        assertEquals(accessor.getPropertyValue(teacher, 2, 0), user.getName());
    }

    @Test
    void getPropertyValueByName() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setNo("no:123");

        assertNull(teacher.getUser());

        User user = new User();
        user.setName("teacher_user_name");

        teacher.setUser(user);

        assertEquals(teacher.getUser().getName(), user.getName());

        assertEquals(accessor.getPropertyValue(teacher, "user", "name"), user.getName());
    }

    @Test
    void getPropertyValueByName2() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setNo("no:123");

        assertNull(teacher.getUser());

        User user = new User();
        user.setName("teacher_user_name");

        teacher.setUser(user);

        assertEquals(teacher.getUser().getName(), user.getName());

        assertEquals(accessor.getProperty("user", "name").get(user), user.getName());
    }

    @Test
    void setPropertyValueByIndex() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setNo("no:123");

        assertNull(teacher.getUser());

        final String name = "teacher_user_name";
        accessor.setPropertyValue(teacher, new int[] { 2, 0 }, name);

        assertEquals(teacher.getUser().getName(), name);
    }

    @Test
    void setPropertyValueByName() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setNo("no:123");

        assertNull(teacher.getUser());

        final String name = "teacher_user_name";
        accessor.setPropertyValue(teacher, new String[] { "user", "name" }, name);

        assertEquals(teacher.getUser().getName(), name);
    }

    @Test
    void setPropertyValueByName2() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setNo("no:123");

        String no = "no:111";
        accessor.getProperty("no").set(teacher, no);

        assertEquals(teacher.getNo(), no);
    }

    @Test
    void getProperties() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(123);
        teacher.setNo("no:123");

        Property<Teacher, ?>[] props1 = accessor.getProperties();
        Property<Teacher, ?>[] props2 = accessor.getProperties();

        assertEquals(props1.length, props2.length);
        assertEquals(props1.length, 3);
        assertEquals(props2.length, 3);

        assertNotSame(props1, props2);
        assertEquals(props1, props2);
    }
}
