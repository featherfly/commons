package cn.featherfly.common.algorithm;

/**
 * MD3 algorithm
 *
 * @author zhongj
 */
public abstract class SM3 extends Algorithm {

    /**
     * sm3 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SM3);
    }

    /**
     * sm3 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with String
     * @throws AlgorithmException
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(getBytes(data)));
    }

    /**
     * verify data and encrypted data
     *
     * @param data          data to encrypt
     * @param encryptedData encrypted data
     * @return equals
     * @throws AlgorithmException
     */
    public static boolean verify(String data, String encryptedData) {
        return encrypt(data).equals(encryptedData);
    }
}
