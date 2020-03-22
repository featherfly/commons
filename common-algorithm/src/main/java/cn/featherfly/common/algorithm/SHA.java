package cn.featherfly.common.algorithm;

/**
 * <p>
 * SHA安全编码组件
 * </p>
 *
 * @author zhongj
 */
public abstract class SHA extends Algorithm {

    /**
     * SHA-1编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA);
    }

    /**
     * SHA-1编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode(String data) {
        return toHexString(encode(getBytes(data)));
    }

    /**
     * SHA-224编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode224(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA224);
    }

    /**
     * SHA-224编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode224(String data) {
        return toHexString(encode224(getBytes(data)));
    }

    /**
     * SHA-256编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode256(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA256);
    }

    /**
     * SHA-256编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode256(String data) {
        return toHexString(encode256(getBytes(data)));
    }

    /**
     * SHA-384编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode384(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA384);
    }

    /**
     * SHA-384编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode384(String data) {
        return toHexString(encode384(getBytes(data)));
    }

    /**
     * SHA-512编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode512(byte[] data) {
        return messageDigestEncode(data, MessageDigestAlgorithms.SHA512);
    }

    /**
     * SHA-512编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode512(String data) {
        return toHexString(encode512(getBytes(data)));
    }
}
