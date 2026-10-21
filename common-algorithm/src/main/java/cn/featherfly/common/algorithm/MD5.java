package cn.featherfly.common.algorithm;

import java.io.InputStream;

/**
 * MD5 algorithm.
 *
 * @author zhongj
 */
public abstract class MD5 extends Algorithm {

    /**
     * MD5 encrypt.
     *
     * @param is the input stream
     * @param chunkSize the chunk size
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt(InputStream is, int chunkSize) {
        return messageDigestEncode(is, chunkSize, MessageDigestAlgorithms.MD5);
    }

    /**
     * MD5 encrypt.
     *
     * @param data data to encrypt
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.MD5);
    }

    /**
     * MD5 encrypt.
     *
     * @param data data to encrypt
     * @return encryptd result with string
     * @throws AlgorithmException the algorithm exception
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(getBytes(data)));
    }
}
