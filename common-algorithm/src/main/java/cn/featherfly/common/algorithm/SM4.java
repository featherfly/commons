package cn.featherfly.common.algorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * SM4 algorithm
 *
 * @author zhongj
 */
public class SM4 extends Algorithm {

    /**
     * 密钥算法.
     */
    public static final String ALGORITHM_NAME = "SM4";

    // 加密算法/分组加密模式/分组填充方式
    // PKCS5Padding-以8个字节为一组进行分组加密
    // 定义分组加密模式使用：PKCS5Padding
    /**
     * 加密/解密算法 / 工作模式 / 填充方式.
     */
    public static final String CIPHER_ALGORITHM = "SM4/ECB/PKCS5Padding";

    /**
     * SM4 encrypt
     *
     * @param data data to encrypt
     * @param key  key
     * @return encrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = generateEcbCipher(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * SM4 encrypt
     *
     * @param data data to encrypt
     * @param key  key
     * @return encrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(String data, byte[] key) {
        return encrypt(getBytes(data), key);
    }

    /**
     * SM4 encrypt
     *
     * @param data data to encrypt
     * @param key  key
     * @return encrypted String
     * @throws AlgorithmException
     */
    public static String encryptToString(byte[] data, byte[] key) {
        return encryptResultToString(encrypt(data, key));
    }

    /**
     * SM4 encrypt
     *
     * @param data data to encrypt
     * @param key  key
     * @return encrypted String
     * @throws AlgorithmException
     */
    public static String encryptToString(String data, byte[] key) {
        return encryptToString(getBytes(data), key);
    }

    /**
     * SM4 decrypt
     *
     * @param data data to decrypt
     * @param key  key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        // 还原密钥
        try {
            Cipher cipher = generateEcbCipher(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * SM4 decrypt
     *
     * @param data data to decrypt，使用encryptToString加密的字符串
     * @param key  key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decrypt(String data, byte[] key) {
        return decrypt(encryptResultToBytes(data), key);
    }

    /**
     * SM4 decrypt
     *
     * @param data data to decrypt
     * @param key  key
     * @return decrypted String
     * @throws AlgorithmException
     */
    public static String decryptToString(byte[] data, byte[] key) {
        return new String(decrypt(data, key), CHARSET);
    }

    /**
     * SM4 decrypt
     *
     * @param data data to decrypt，the string encrypt with encryptToString
     *             method
     * @param key  key
     * @return decrypted String
     * @throws AlgorithmException
     */
    public static String decryptToString(String data, byte[] key) {
        return decryptToString(encryptResultToBytes(data), key);
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

    /**
     * verify.
     *
     * @param data      data to encrypted
     * @param encrypted encrypted data
     * @param key       encrypt key
     * @return equals
     * @throws AlgorithmException
     */
    public static boolean verify(String data, String encrypted, byte[] key) {
        return encryptToString(data, key).equals(encrypted);
    }

    private static Cipher generateEcbCipher(int mode, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(mode, new SecretKeySpec(key, ALGORITHM_NAME));
        return cipher;
    }
}
