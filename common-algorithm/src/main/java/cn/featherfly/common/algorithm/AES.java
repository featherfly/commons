package cn.featherfly.common.algorithm;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES安全编码组件
 *
 * @author zhongj
 */
public abstract class AES extends Algorithm {

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "AES";

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
        return decrypt(Base64.decode(data), key);
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
        return decryptToString(Base64.decode(data), key);
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
        return Base64.encodeToString(encrypt(data, key));
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
     * 生成密钥 <br>
     *
     * @return byte[] 二进制密钥
     * @throws AlgorithmException
     */
    public static byte[] initKey() {
        try {
            // 实例化
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // AES 要求密钥长度为 128位、192位或 256位
            //		kg.init(256); 256为KEY没搞定
            kg.init(128);
            // 生成秘密密钥
            SecretKey secretKey = kg.generateKey();
            // 获得密钥的二进制编码形式
            return secretKey.getEncoded();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
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
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }
}
