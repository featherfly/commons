package cn.featherfly.common.algorithm;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES algorithm
 *
 * @author zhongj
 */
public abstract class DES extends Algorithm {

    /**
     * 密钥算法 <br>
     */
    public static final String ALGORITHM_NAME = "DES";

    /**
     * DES encrypt/解密算法 / 工作模式 / 填充方式
     */
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

    /**
     * DES decrypt
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密数据
     * @throws AlgorithmException
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        // 还原密钥
        try {
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
     * DES decrypt
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
     * DES decrypt
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
     * DES decrypt
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
     * DES encrypt
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        // 还原密钥
        try {
            Key k = toKey(key);
            // 实例化
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
     * DES encrypt
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
     * DES encrypt
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
     * DES encrypt
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
        return generateKey(ALGORITHM_NAME, 56);
    }

    // ********************************************************************
    //	private method
    // ********************************************************************

    private static Key toKey(byte[] key) throws Exception {
        // 实例化DES密钥材料
        DESKeySpec dks = new DESKeySpec(key);
        // 实例化秘密密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_NAME);
        // 生成秘密密钥
        SecretKey secretKey = keyFactory.generateSecret(dks);
        return secretKey;
    }
}
