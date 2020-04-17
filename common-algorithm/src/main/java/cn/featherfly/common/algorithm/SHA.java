package cn.featherfly.common.algorithm;

/**
 * <p>
 * SHA algorithm
 * </p>
 *
 * @author zhongj
 */
public abstract class SHA extends Algorithm {

    /**
     * SHA-1 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA);
    }

    /**
     * SHA-1 encrypt
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(getBytes(data)));
    }

    /**
     * SHA-224 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt224(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA224);
    }

    /**
     * SHA-224 encrypt
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException
     */
    public static String encrypt224(String data) {
        return toHexString(encrypt224(getBytes(data)));
    }

    /**
     * SHA-256 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt256(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA256);
    }

    /**
     * SHA-256 encrypt
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException
     */
    public static String encrypt256(String data) {
        return toHexString(encrypt256(getBytes(data)));
    }

    /**
     * SHA-384 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt384(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA384);
    }

    /**
     * SHA-384 encrypt
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException
     */
    public static String encrypt384(String data) {
        return toHexString(encrypt384(getBytes(data)));
    }

    /**
     * SHA-512 encrypt
     *
     * @param data data to encrypt
     * @return encrypted result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt512(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA512);
    }

    /**
     * SHA-512 encrypt
     *
     * @param data data to encrypt
     * @return encrypt result with String
     * @throws AlgorithmException
     */
    public static String encrypt512(String data) {
        return toHexString(encrypt512(getBytes(data)));
    }
}
