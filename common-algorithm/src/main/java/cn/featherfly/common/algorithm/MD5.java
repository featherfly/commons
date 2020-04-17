package cn.featherfly.common.algorithm;

/**
 * MD5 algorithm
 *
 * @author zhongj
 */
public abstract class MD5 extends Algorithm {

    /**
     * MD5 encrypt
     *
     * @param data data to encrypt
     * @return encryptd result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.MD5);
    }

    /**
     * MD5 encrypt
     *
     * @param data data to encrypt
     * @return encryptd result with string
     * @throws AlgorithmException
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(getBytes(data)));
    }
}
