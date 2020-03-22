package cn.featherfly.common.algorithm;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * RSA安全编码组件
 *
 * @author zhongj
 */
public abstract class RSA extends Algorithm {

    /**
     * 非对称加密密钥算法
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 数字签名 签名/验证算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * RSA密钥长度 默认1024位，密钥长度必须是64的倍数，范围在512至65536位之间。
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws AlgorithmException
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) {
        try {
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return byte[] 解密数据
     * @throws AlgorithmException
     */
    public static byte[] decryptByPrivateKey(String data, byte[] key) {
        return decryptByPrivateKey(Base64.decode(data), key);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPrivateKeyToString(byte[] data, byte[] key) {
        return new String(decryptByPrivateKey(data, key), CHARSET);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPrivateKeyToString(String data, byte[] key) {
        return decryptByPrivateKeyToString(Base64.decode(data), key);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return byte[] 解密数据
     * @throws AlgorithmException
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) {
        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 生成公钥
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return byte[] 解密数据
     * @throws AlgorithmException
     */
    public static byte[] decryptByPublicKey(String data, byte[] key) {
        return decryptByPublicKey(Base64.decode(data), key);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPublicKeyToString(byte[] data, byte[] key) {
        return new String(decryptByPublicKey(data, key), CHARSET);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPublicKeyToString(String data, byte[] key) {
        return decryptByPublicKeyToString(Base64.decode(data), key);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) {
        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPublicKey(String data, byte[] key) {
        return encryptByPublicKey(getBytes(data), key);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPublicKeyToString(byte[] data, byte[] key) {
        return Base64.encodeToString(encryptByPublicKey(data, key));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPublicKeyToString(String data, byte[] key) {
        return encryptByPublicKeyToString(getBytes(data), key);
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) {
        try {
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPrivateKey(String data, byte[] key) {
        return encryptByPrivateKey(getBytes(data), key);
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPrivateKeyToString(byte[] data, byte[] key) {
        return Base64.encodeToString(encryptByPrivateKey(data, key));
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPrivateKeyToString(String data, byte[] key) {
        return encryptByPrivateKeyToString(getBytes(data), key);
    }

    /**
     * 初始化密钥
     *
     * @return KeyPair KeyPair
     * @throws AlgorithmException
     */
    public static KeyPair initKeyPair(byte... seed) {
        try {
            // 实例化密钥对生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = null;
            if (seed != null && seed.length > 0) {
                secureRandom = new SecureRandom(seed);
            } else {
                secureRandom = new SecureRandom();
            }
            // 初始化密钥对生成器
            keyPairGen.initialize(KEY_SIZE, secureRandom);
            // 生成密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    // ********************************************************************
    //	数字签名
    // ********************************************************************

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws AlgorithmException
     */
    public static byte[] sign(byte[] data, byte[] privateKey) {
        try {
            // 转换私钥材料
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initSign(priKey);
            // 更新
            signature.update(data);
            // 签名
            return signature.sign();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 校验
     *
     * @param data      待校验数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return boolean 校验成功返回true 失败返回false
     * @throws AlgorithmException
     */
    public static boolean verify(byte[] data, byte[] publicKey, byte[] sign) {
        try {
            // 转换公钥材料
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            // 生成公钥
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initVerify(pubKey);
            // 更新
            signature.update(data);
            // 验证
            return signature.verify(sign);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }
}
