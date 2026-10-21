package cn.featherfly.common.algorithm;

import java.io.InputStream;

/**
 * SHA algorithm.
 *
 * @author zhongj
 */
public abstract class SHA extends Algorithm {

    /**
     * SHA-1 encrypt.
     *
     * @param is the input stream
     * @param chunkSize the chunk size
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt(InputStream is, int chunkSize) {
        return messageDigestEncode(is, chunkSize, MessageDigestAlgorithms.SHA);
    }

    /**
     * SHA-1 encrypt.
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA);
    }

    /**
     * SHA-1 encrypt.
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException the algorithm exception
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(getBytes(data)));
    }

    /**
     * SHA-224 encrypt.
     *
     * @param is the input stream
     * @param chunkSize the chunk size
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt224(InputStream is, int chunkSize) {
        return messageDigestEncode(is, chunkSize, MessageDigestAlgorithms.SHA224);
    }

    /**
     * SHA-224 encrypt.
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt224(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA224);
    }

    /**
     * SHA-224 encrypt.
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException the algorithm exception
     */
    public static String encrypt224(String data) {
        return toHexString(encrypt224(getBytes(data)));
    }

    /**
     * SHA-256 encrypt.
     *
     * @param is the input stream
     * @param chunkSize the chunk size
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt256(InputStream is, int chunkSize) {
        return messageDigestEncode(is, chunkSize, MessageDigestAlgorithms.SHA256);
    }

    /**
     * SHA-256 encrypt.
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt256(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA256);
    }

    /**
     * SHA-256 encrypt.
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException the algorithm exception
     */
    public static String encrypt256(String data) {
        return toHexString(encrypt256(getBytes(data)));
    }

    /**
     * SHA-384 encrypt.
     *
     * @param is the input stream
     * @param chunkSize the chunk size
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt384(InputStream is, int chunkSize) {
        return messageDigestEncode(is, chunkSize, MessageDigestAlgorithms.SHA384);
    }

    /**
     * SHA-384 encrypt.
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt384(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA384);
    }

    /**
     * SHA-384 encrypt.
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException the algorithm exception
     */
    public static String encrypt384(String data) {
        return toHexString(encrypt384(getBytes(data)));
    }

    /**
     * SHA-512 encrypt.
     *
     * @param is the input stream
     * @param chunkSize the chunk size
     * @return encryptd result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt512(InputStream is, int chunkSize) {
        return messageDigestEncode(is, chunkSize, MessageDigestAlgorithms.SHA512);
    }

    /**
     * SHA-512 encrypt.
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException the algorithm exception
     */
    public static byte[] encrypt512(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA512);
    }

    /**
     * SHA-512 encrypt.
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException the algorithm exception
     */
    public static String encrypt512(String data) {
        return toHexString(encrypt512(getBytes(data)));
    }
}
