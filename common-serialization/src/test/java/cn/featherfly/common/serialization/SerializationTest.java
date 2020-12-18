
package cn.featherfly.common.serialization;

import org.testng.annotations.Test;

import cn.featherfly.common.serialization.po.User;

/**
 * SerializationTest.
 *
 * @author zhongj
 */
public class SerializationTest {

    User user;

    /**
     */
    public SerializationTest() {
        user = new User();
        user.setId(1L);
        user.setName("yufei");
        user.setAge(18);
    }

    @Test(expectedExceptions = SerializationException.class)
    public void test1() {
        Serialization.serialize(new Object(), "application/xml");
    }

    @Test
    public void testJson() {
        System.out.println(new String(Serialization.serialize(user)));
    }
}
