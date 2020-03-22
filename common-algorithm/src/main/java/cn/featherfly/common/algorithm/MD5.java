package cn.featherfly.common.algorithm;

/**
 * MD5 algorithm
 *
 * @author zhongj
 */
public abstract class MD5 extends Algorithm {

    /**
     * MD5 encode
     *
     * @param data data to encode
     * @return encoded result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encode(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.MD5);
    }

    /**
     * MD5 encode
     *
     * @param data data to encode
     * @return encoded result with string
     * @throws AlgorithmException
     */
    public static String encode(String data) {
        // 执行消息摘要
        return toHexString(encode(getBytes(data)));
    }
}
