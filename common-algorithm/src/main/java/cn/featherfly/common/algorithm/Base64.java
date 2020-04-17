package cn.featherfly.common.algorithm;

/**
 * Base64 algorithm
 *
 * @author zhongj
 */
public abstract class Base64 extends Algorithm {

    /**
     * Base64 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     */
    public static byte[] encrypt(byte[] data) {
        // 执行编码
        return java.util.Base64.getEncoder().encode(data);
    }

    /**
     * Base64 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     */
    public static byte[] encrypt(String data) {
        return encrypt(getBytes(data));
    }

    /**
     * Base64 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with String
     */
    public static String encryptToString(byte[] data) {
        return new String(encrypt(data), CHARSET);
    }

    /**
     * Base64 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with String
     */
    public static String encryptToString(String data) {
        return encryptToString(getBytes(data));
    }

    /**
     * Base64 decrypt
     *
     * @param data data to decrypt
     * @return decrypted result with byte[]
     */
    public static byte[] decrypt(byte[] data) {
        // 执行解码
        return java.util.Base64.getDecoder().decode(data);
        //        return org.bouncycastle.util.encoders.Base64.decode(data);
    }

    /**
     * Base64 decrypt
     *
     * @param data data to decrypt
     * @return decrypted result with byte[]
     */
    public static byte[] decrypt(String data) {
        return decrypt(getBytes(data));
    }

    /**
     * Base64 decrypt
     *
     * @param data data to decrypt
     * @return decrypted result with String
     */
    public static String decryptToString(byte[] data) {
        return new String(decrypt(data), CHARSET);
    }

    /**
     * Base64 decrypt
     *
     * @param data data to decrypt
     * @return decrypted result with String
     */
    public static String decryptToString(String data) {
        return decryptToString(getBytes(data));
    }
}
