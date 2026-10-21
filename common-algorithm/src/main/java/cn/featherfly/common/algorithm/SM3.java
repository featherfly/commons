package cn.featherfly.common.algorithm;

import java.io.InputStream;

/**
 * MD3 algorithm.
 *
 * @author zhongj
 */
public abstract class SM3 extends Algorithm {

    /**
     * sm3 encrypt.
     *
     * @param is the input stream
     * @param chunkSize the chunk size
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt(InputStream is, int chunkSize) {
        return messageDigestEncode(is, chunkSize, MessageDigestAlgorithms.SM3);
    }

    /**
     * sm3 encrypt.
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SM3);
    }

    /**
     * sm3 encrypt.
     *
     * @param data data to encrypt
     * @return encrypted result with String
     * @throws AlgorithmException the algorithm exception
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(getBytes(data)));
    }

    /**
     * verify data and encrypted data.
     *
     * @param data data to encrypt
     * @param encryptedData encrypted data
     * @return equals
     * @throws AlgorithmException the algorithm exception
     */
    public static boolean verify(String data, String encryptedData) {
        return encrypt(data).equals(encryptedData);
    }
}
