package cn.featherfly.common.algorithm;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES algorithm
 *
 * @author zhongj
 */
public abstract class AES extends Algorithm {

    /**
     * 密钥算法
     */
    public static final String ALGORITHM_NAME = "AES";

    /**
     * 加密/解密算法 / 工作模式 / 填充方式
     */
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     * @throws AlgorithmException
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            // 还原密钥
            Key k = toKey(key);
            // 实例化
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, k);
            // 执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密数据，使用encryptToString加密的字符串
     * @param key  密钥
     * @return byte[] 解密数据
     * @throws AlgorithmException
     */
    public static byte[] decrypt(String data, byte[] key) {
        return decrypt(encryptResultToBytes(data), key);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptToString(byte[] data, byte[] key) {
        return new String(decrypt(data, key), CHARSET);
    }

    /**
     * 解密
     *
     * @param data 待解密数据，使用encryptToString加密的字符串
     * @param key  密钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptToString(String data, byte[] key) {
        return decryptToString(encryptResultToBytes(data), key);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            // 还原密钥
            Key k = toKey(key);
            /*
             * 实例化
             */
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, k);
            // 执行操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encrypt(String data, byte[] key) {
        return encrypt(getBytes(data), key);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptToString(byte[] data, byte[] key) {
        return encryptResultToString(encrypt(data, key));
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptToString(String data, byte[] key) {
        return encryptToString(getBytes(data), key);
    }

    /**
     * generate key.
     *
     * @param seed seed for SecureRandom
     * @return byte[] key
     * @throws AlgorithmException
     */
    public static byte[] generateKey(byte... seed) {
        return generateKey(ALGORITHM_NAME, seed);
    }

    /**
     * generate key.
     *
     * @return byte[] key
     * @throws AlgorithmException
     */
    public static byte[] generateKey() {
        return generateKey(ALGORITHM_NAME);
    }

    /**
     * generate key.
     *
     * @param keySize key size
     * @return byte[] key
     * @throws AlgorithmException
     */
    public static byte[] generateKey(int keySize) {
        return generateKey(ALGORITHM_NAME, keySize);
    }

    // ********************************************************************
    //	private method
    // ********************************************************************

    /*
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     * @throws AlgorithmException
     */
    private static Key toKey(byte[] key) {
        // 实例化AES密钥材料
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM_NAME);
        return secretKey;
    }
}
