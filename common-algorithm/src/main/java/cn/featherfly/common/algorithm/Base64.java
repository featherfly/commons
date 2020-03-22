package cn.featherfly.common.algorithm;

/**
 * Base64组件
 *
 * @author zhongj
 */
public abstract class Base64 extends Algorithm {

    /**
     * Base64 encde
     *
     * @param data 待编码数据
     * @return byte[] 编码数据
     */
    public static byte[] encode(byte[] data) {
        // 执行编码
        return java.util.Base64.getEncoder().encode(data);
    }

    /**
     * Base64编码
     *
     * @param data 待编码数据
     * @return byte[] 编码数据
     */
    public static byte[] encode(String data) {
        return encode(getBytes(data));
    }

    /**
     * Base64编码
     *
     * @param data 待编码数据
     * @return String 编码数据
     */
    public static String encodeToString(byte[] data) {
        return new String(encode(data), CHARSET);
    }

    /**
     * Base64编码
     *
     * @param data 待编码数据
     * @return String 编码数据
     */
    public static String encodeToString(String data) {
        return encodeToString(getBytes(data));
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @return byte[] 解码数据
     */
    public static byte[] decode(byte[] data) {
        // 执行解码
        return java.util.Base64.getDecoder().decode(data);
        //        return org.bouncycastle.util.encoders.Base64.decode(data);
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @return byte[] 解码数据
     */
    public static byte[] decode(String data) {
        return decode(getBytes(data));
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @return String 解码数据
     */
    public static String decodeToString(byte[] data) {
        return new String(decode(data), CHARSET);
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @return String 解码数据
     */
    public static String decodeToString(String data) {
        return decodeToString(getBytes(data));
    }
}
