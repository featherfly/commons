
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

import org.junit.Test;

/**
 * BeanMapperUtilsTest.
 *
 * @author zhongj
 */
public class BeanMapperUtilsTest {

    int times = 10000;

    void time(Object d, Object s) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            BeanMappers.copy(d, s);
        }
        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - start));
    }

    void time2(Object d, Object s) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            //            BeanMappers.copyProperties(d, s);
        }
        long end = System.currentTimeMillis();
        System.out.println("time2 : " + (end - start));
    }

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

        //        System.out.println(BeanMappers.copyProperties(u, u3));

        //        time(u2, u3);

        //        time2(u, u3);

    }
}
