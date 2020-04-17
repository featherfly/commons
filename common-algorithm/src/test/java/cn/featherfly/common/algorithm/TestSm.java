
package cn.featherfly.common.algorithm;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.util.encoders.Hex;
import org.testng.annotations.Test;

/**
 * <p>
 * TestSm
 * </p>
 *
 * @author zhongj
 */
public class TestSm {

    @Test
    void testSm2() {
        String data = "hello sm2";

        KeyPair kp = SM2.generateKeyPair();

        byte[] bsData = data.getBytes();
        byte[] id = "userId".getBytes();
        byte[] sign = SM2.sign(bsData, id, kp.getPrivate());

        assertTrue(SM2.verify(bsData, id, sign, kp.getPublic()));

        byte[] rds = SM2.encrypt(bsData, kp.getPublic());
        System.out.println(rds);
        System.out.println(Algorithm.toHexString(rds));
        System.out.println(Base64.encryptToString(rds));

        byte[] od = SM2.decrypt(rds, kp.getPrivate());
        System.out.println(Arrays.equals(od, bsData));
        assertEquals(od, bsData);

        String e = SM2.encryptToString(data, kp.getPublic());
        System.out.println(e);
        String rd = SM2.decryptToString(e, kp.getPrivate());
        assertEquals(rd, data);

        String s = SM2.signToString(data, id, kp.getPrivate());

        assertTrue(SM2.verify(data, id, s, kp.getPublic()));
    }

    @Test
    void testSm3() {
        String data = "hello sm3";
        String r = "b673a85190098855103463dc908bc8c954b31460";
        String result = SM3.encrypt(data);
        System.out.println("不带密钥加密后的密文：" + result);
        assertTrue(SM3.verify(data, result));
        assertEquals(result, r);
    }

    @Test
    void testSm4() {
        String data = "hello sm4";
        byte[] key = SM4.generateKey();
        String encrypted = SM4.encryptToString(data, key);
        System.out.println("加密后的密文：" + encrypted);
        // 校验
        assertTrue(SM4.verify(data, encrypted, key));
        // 解密
        String decrypted = SM4.decryptToString(encrypted, key);
        System.out.println("解密后的明文：" + decrypted);
        assertEquals(decrypted, data);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPair kp = SM2.generateKeyPair();

        System.out.println(Hex.toHexString(kp.getPrivate().getEncoded()));
        System.out.println(Hex.toHexString(kp.getPublic().getEncoded()));

        System.out.println(kp.getPrivate().getAlgorithm());
        System.out.println(kp.getPublic().getAlgorithm());

        System.out.println(kp.getPrivate().getFormat());
        System.out.println(kp.getPublic().getFormat());

        System.out.println("private key d: " + ((BCECPrivateKey) kp.getPrivate()).getD());
        System.out.println("public key q:" + ((BCECPublicKey) kp.getPublic()).getQ()); //{x, y, zs...}

        byte[] msg = "message digest".getBytes();
        byte[] userId = "userId".getBytes();
        byte[] sig = SM2.sign(msg, userId, kp.getPrivate());
        System.out.println(Hex.toHexString(sig));
        System.out.println(SM2.verify(msg, userId, sig, kp.getPublic()));
    }
}
