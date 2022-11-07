
package cn.featherfly.common.serialization;

import static org.testng.Assert.assertEquals;

import java.nio.charset.StandardCharsets;

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
        user.setName("yufei 羽飞");
        user.setAge(18);
    }

    @Test
    public void testJson() {
        System.out.println(new String(Serialization.serialize(user), Serialization.getDefault().getCharset()));
        System.out.println(new String(Serialization.serialize(user), StandardCharsets.ISO_8859_1)); // 会乱码
    }

    @Test
    public void testXml() {
        System.out.println(new String(Serialization.serialize(user, Serialization.MIME_TYPE_XML)));
        System.out.println(
                new String(Serialization.serialize(user, Serialization.MIME_TYPE_XML), StandardCharsets.ISO_8859_1)); // 会乱码
    }

    @Test
    public void testKryo() {
        byte[] bs = Serialization.serialize(user, Serialization.MIME_TYPE_KRYO);
        System.out.println(bs.length);

        User u = Serialization.deserialize(bs, User.class, Serialization.MIME_TYPE_KRYO);
        System.out.println(u);
        assertEquals(u, user);

        System.out.println(
                new String(Serialization.serialize(user, Serialization.MIME_TYPE_KRYO), StandardCharsets.ISO_8859_1)); // 会乱码
    }

    @Test
    public void testProtobuff() {
        byte[] bs = Serialization.serialize(user, Serialization.MIME_TYPE_PROTOBUFF);
        System.out.println(bs.length);

        User u = Serialization.deserialize(bs, User.class, Serialization.MIME_TYPE_PROTOBUFF);
        System.out.println(u);
        assertEquals(u, user);

        System.out.println(new String(Serialization.serialize(user, Serialization.MIME_TYPE_PROTOBUFF),
                StandardCharsets.ISO_8859_1)); // 会乱码
    }
}
