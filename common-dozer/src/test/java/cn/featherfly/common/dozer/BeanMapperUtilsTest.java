
/*
 * All rights Reserved, Designed By zhongj
 * @Title: BeanMapperUtilsTest.java
 * @Package cn.featherfly.common.dozer
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-03-12 13:49:12
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.dozer;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import org.testng.annotations.Test;

/**
 * BeanMapperUtilsTest.
 *
 * @author zhongj
 */
public class BeanMapperUtilsTest {

    @Test
    public void test() {

        User result = new User();
        User2 result2 = new User2();

        User u = new User();
        u.setId(1L);
        u.setName("yufei");
        u.setAge(18);

        User2 u2 = new User2();
        u2.setId(2L);
        u2.setName("yi");
        u2.setAge(20);

        result2 = BeanMappers.copy(User2.class, u);
        System.out.println(result2);
        assertEquals(result2.getId(), u.getId());
        assertEquals(result2.getName(), u.getName());
        assertEquals(result2.getAge(), u.getAge());

        result = BeanMappers.copy(User.class, u2);
        System.out.println(result);
        assertEquals(result.getId(), u2.getId());
        assertEquals(result.getName(), u2.getName());
        assertEquals(result.getAge(), u2.getAge());

        User2 u3 = new User2();
        u3.setId(3L);

        result2 = BeanMappers.copy(u2, u3);
        System.out.println(result2);
        assertEquals(u2.getId(), u3.getId());
        assertEquals(u2.getName(), u3.getName());
        assertEquals(u2.getAge(), u3.getAge());

        result = u;
        BeanMappers.IGNORE_NULL.copy(u, u3);
        System.out.println(result);
        assertEquals(u.getId(), u3.getId());
        assertEquals(u.getName(), result.getName());
        assertEquals(u.getAge(), result.getAge());
    }

    @Test
    public void test2() {

        User result = new User();
        User2 result2 = new User2();

        User u = new User();
        u.setId(1L);
        u.setName("yufei");
        u.setAge(18);
        u.setBirthday(new Date());

        User2 u2 = new User2();
        u2.setId(2L);
        u2.setName("yi");
        u2.setAge(20);
        u2.setBirthday(LocalDate.now().withYear(1999));

        System.out.println(BeanMappers.copy(u, u2));

        u.setBirthday(new Date());

        System.out.println(BeanMappers.copy(u2, u));
    }

    @Test
    public void testBuilder() {

        BeanMapper mapper = BeanMappers.builder().build();

        User result = new User();
        User2 result2 = new User2();

        User u = new User();
        u.setId(1L);
        u.setName("yufei");
        u.setAge(18);
        u.setBirthday(new Date());

        User2 u2 = new User2();
        u2.setId(2L);
        u2.setName("yi");
        u2.setAge(20);
        u2.setBirthday(LocalDate.now().withYear(1999));

        System.out.println(mapper.copy(u, u2));

        u.setBirthday(new Date());

        System.out.println(mapper.copy(u2, u));
    }
}
