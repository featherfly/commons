
package cn.featherfly.common.algorithm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.security.KeyPair;

import org.testng.annotations.Test;

/**
 * <p>
 * Base64Test
 * </p>
 *
 * @author zhongj
 */
@Test
public class BasicTest {

    /*   public static void main(String[] args)  {
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{"123".getBytes("UTF-8")});
        System.out.println(retObj);
        System.out.println(new sun.misc.BASE64Encoder().encode("123".getBytes("UTF-8")));
    }*/

    String data = "123";

    @Test
    public void testBase64() {
        String src = "123";
        String target = "MTIz";
        assertEquals(Base64.encodeToString(src), target);
        assertEquals(Base64.decodeToString(target), src);
    }

    @Test
    public void testCRC() {
        Long result32 = 2286445522l;
        Long result64 = 3138064834360563487l;

        assertEquals(new Long(CRC.crc32(data)), result32);
        assertEquals(new Long(CRC.crc64(data)), result64);
    }

    @Test
    public void testMD5() {
        String src = "123";
        String target = "202cb962ac59075b964b07152d234b70";
        assertEquals(MD5.encode(src), target);
        assertEquals(MD5.encode(src).length(), 32);
    }

    @Test
    public void testHMACMD5() {
        String src = "123";
        String result1 = HMACMD5.encode(src);
        String result2 = HMACMD5.encode(src);

        assertNotEquals(result1, result2);

        String key = "key";
        String result = "52851cb05258c8d98da1672d95729e53";
        result1 = HMACMD5.encode(src, key);
        result2 = HMACMD5.encode(src, key);
        assertEquals(result1, result2);
        assertEquals(result1, result);
    }

    @Test
    public void testSHA() {
        String src = "123";
        String target = "40bd001563085fc35165329ea1ff5c5ecbdbbeef";
        assertEquals(SHA.encode(src), target);
        assertEquals(SHA.encode(src).length(), 40);

        target = "78d8045d684abd2eece923758f3cd781489df3a48e1278982466017f";
        assertEquals(SHA.encode224(src), target);
        assertEquals(SHA.encode224(src).length(), 56);

        target = "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3";
        assertEquals(SHA.encode256(src), target);
        assertEquals(SHA.encode256(src).length(), 64);

        target = "9a0a82f0c0cf31470d7affede3406cc9aa8410671520b727044eda15b4c25532a9b5cd8aaf9cec4919d76255b6bfb00f";
        assertEquals(SHA.encode384(src), target);
        assertEquals(SHA.encode384(src).length(), 96);

        target = "3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2";
        assertEquals(SHA.encode512(src), target);
        assertEquals(SHA.encode512(src).length(), 128);
    }

    @Test
    public void testHMACSHA() {
        String src = "123";
        String key = "key";

        String result;
        String result1;
        String result2;

        result1 = HMACSHA.encode(src);
        result2 = HMACSHA.encode(src);
        assertNotEquals(result1, result2);

        result = "d4a5b6721d75a5ac15ec698818c77fe1f6e40187";
        result1 = HMACSHA.encode(src, key);
        result2 = HMACSHA.encode(src, key);
        assertEquals(result1, result2);
        assertEquals(result1, result);

        result1 = HMACSHA.encode224(src);
        result2 = HMACSHA.encode224(src);
        assertNotEquals(result1, result2);

        result = "0036623d2876383f1bf426d7ca8f46884f0d811f664118b2f93c774b";
        result1 = HMACSHA.encode224(src, key);
        result2 = HMACSHA.encode224(src, key);
        assertEquals(result1, result2);
        assertEquals(result1, result);

        result1 = HMACSHA.encode256(src);
        result2 = HMACSHA.encode256(src);
        assertNotEquals(result1, result2);

        result = "a7f7739b1dc5b4e922b1226c9fcbdc83498dee375382caee08fd52a13eb7cfe2";
        result1 = HMACSHA.encode256(src, key);
        result2 = HMACSHA.encode256(src, key);
        assertEquals(result1, result2);
        assertEquals(result1, result);

        result1 = HMACSHA.encode384(src);
        result2 = HMACSHA.encode384(src);
        assertNotEquals(result1, result2);

        result = "a94c9966bd530d65b5b09fd226479926bef037705e2090a0b24ab11922d821a7076c0a8bc120a9b49e41cd38428ec7ec";
        result1 = HMACSHA.encode384(src, key);
        result2 = HMACSHA.encode384(src, key);
        assertEquals(result1, result2);
        assertEquals(result1, result);

        result1 = HMACSHA.encode512(src);
        result2 = HMACSHA.encode512(src);
        assertNotEquals(result1, result2);

        result = "2ea823c645b1baf845ef76096a6d7fa9e568304ba9f7910bd52f01c03eec39cdfeec54e50b86b62ef5bfb9e6ce5c0be747ec13b3a199f9d235e99a36de369a84";
        result1 = HMACSHA.encode512(src, key);
        result2 = HMACSHA.encode512(src, key);
        assertEquals(result1, result2);
        assertEquals(result1, result);
    }

    @Test
    public void testDES() {
        String src = "DES";
        byte[] key = DES.initKey();
        String target = DES.encryptToString(src, key);
        assertEquals(DES.decryptToString(target, key), src);
    }

    @Test
    public void testAES() {
        String src = "AES";
        byte[] key = AES.initKey();
        String target = AES.encryptToString(src, key);
        assertEquals(AES.decryptToString(target, key), src);
    }

    @Test
    public void testRSA() {
        String src = "RSA";
        KeyPair kp = RSA.initKeyPair();
        byte[] privateKey = kp.getPrivate().getEncoded();
        byte[] publicKey = kp.getPublic().getEncoded();
        byte[] srcBytes = src.getBytes(Algorithm.CHARSET);

        //		System.out.println("private : " + Base64.encodeToString(kp.getPrivate().getEncoded()));
        //		System.out.println("public : " + Base64.encodeToString(kp.getPublic().getEncoded()));

        String target = RSA.encryptByPrivateKeyToString(src, privateKey);
        byte[] targetBytes = RSA.encryptByPrivateKey(srcBytes, privateKey);
        //System.out.println("private key encrypt, public key decrypt");

        assertEquals(RSA.decryptByPublicKeyToString(target, publicKey), src);
        assertEquals(RSA.decryptByPublicKey(targetBytes, publicKey), srcBytes);

        //		System.out.println("public key encrypt, private key decrypt");

        target = RSA.encryptByPublicKeyToString(src, publicKey);
        targetBytes = RSA.encryptByPublicKey(srcBytes, publicKey);

        assertEquals(RSA.decryptByPrivateKeyToString(target, privateKey), src);
        assertEquals(RSA.decryptByPrivateKey(targetBytes, privateKey), srcBytes);

        byte[] sign = RSA.sign(srcBytes, privateKey);

        assertTrue(RSA.verify(srcBytes, publicKey, sign));

    }

}
