package cn.featherfly.common.algorithm;

/**
 * <p>
 * SHA安全编码组件
 * </p>
 *
 * @author zhongj
 */
public abstract class HMACSHA extends Algorithm {

    /**
     * HMACSHA1编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA1);
    }

    /**
     * HMACSHA1编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode(String data) {
        return toHexString(encode(getBytes(data)));
    }

    /**
     * HMACSHA1编码
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA1);
    }

    /**
     * HMACSHA1编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode(String data, String key) {
        return toHexString(encode(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA224编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode224(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA224);
    }

    /**
     * HMACSHA224编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode224(String data) {
        return toHexString(encode224(getBytes(data)));
    }

    /**
     * HMACSHA224编码
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode224(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA224);
    }

    /**
     * HMACSHA224编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode224(String data, String key) {
        return toHexString(encode224(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA256编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode256(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA256);
    }

    /**
     * HMACSHA256编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode256(String data) {
        return toHexString(encode256(getBytes(data)));
    }

    /**
     * HMACSHA256编码
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode256(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA256);
    }

    /**
     * HMACSHA256编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode256(String data, String key) {
        return toHexString(encode256(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA384编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode384(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA384);
    }

    /**
     * HMACSHA384编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode384(String data) {
        return toHexString(encode384(getBytes(data)));
    }

    /**
     * HMACSHA384编码
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode384(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA384);
    }

    /**
     * HMACSHA384编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode384(String data, String key) {
        return toHexString(encode384(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA512编码
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode512(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA512);
    }

    /**
     * HMACSHA512编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode512(String data) {
        return toHexString(encode512(getBytes(data)));
    }

    /**
     * HMACSHA512编码
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encode512(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA512);
    }

    /**
     * HMACSHA512编码
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encode512(String data, String key) {
        return toHexString(encode512(getBytes(data), getBytes(key)));
    }
}
