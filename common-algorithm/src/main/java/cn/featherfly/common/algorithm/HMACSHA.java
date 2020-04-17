package cn.featherfly.common.algorithm;

/**
 * <p>
 * HMACSHA algorithm
 * </p>
 *
 * @author zhongj
 */
public abstract class HMACSHA extends Algorithm {

    /**
     * HMACSHA1 encrypt. use a random key, every encrypted result is different
     * with same data.
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA1);
    }

    /**
     * {@link #encrypt(byte[])}
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(getBytes(data)));
    }

    /**
     * HMACSHA1 encrypt.use gived key to encrypt.
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA1);
    }

    /**
     * {@link #encrypt(byte[],byte[])}
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt(String data, String key) {
        return toHexString(encrypt(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA224 encrypt. use a random key, every encrypted result is different
     * with same data.
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt224(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA224);
    }

    /**
     * {@link #encrypt224(byte[])}
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt224(String data) {
        return toHexString(encrypt224(getBytes(data)));
    }

    /**
     * HMACSHA224 encrypt.use gived key to encrypt.
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt224(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA224);
    }

    /**
     * {@link #encrypt224(byte[],byte[])}
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt224(String data, String key) {
        return toHexString(encrypt224(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA256 encrypt. use a random key, every encrypted result is different
     * with same data.
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt256(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA256);
    }

    /**
     * {@link #encrypt256(byte[])}
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt256(String data) {
        return toHexString(encrypt256(getBytes(data)));
    }

    /**
     * HMACSHA256 encrypt.use gived key to encrypt.
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt256(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA256);
    }

    /**
     * {@link #encrypt256(byte[],byte[])}
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt256(String data, String key) {
        return toHexString(encrypt256(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA384 encrypt. use a random key, every encrypted result is different
     * with same data.
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt384(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA384);
    }

    /**
     * {@link #encrypt384(byte[])}
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt384(String data) {
        return toHexString(encrypt384(getBytes(data)));
    }

    /**
     * HMACSHA384 encrypt.use gived key to encrypt.
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt384(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA384);
    }

    /**
     * {@link #encrypt384(byte[],byte[])}
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt384(String data, String key) {
        return toHexString(encrypt384(getBytes(data), getBytes(key)));
    }

    /**
     * HMACSHA512 encrypt. use a random key, every encrypted result is different
     * with same data.
     *
     * @param data 待编码数据
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt512(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACSHA512);
    }

    /**
     * {@link #encrypt512(byte[])}
     *
     * @param data 待编码数据
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt512(String data) {
        return toHexString(encrypt512(getBytes(data)));
    }

    /**
     * HMACSHA512 encrypt.use gived key to encrypt.
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return byte[] 消息摘要
     * @throws AlgorithmException
     */
    public static byte[] encrypt512(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACSHA512);
    }

    /**
     * {@link #encrypt512(byte[],byte[])}
     *
     * @param data 待编码数据
     * @param key  编码使用的key
     * @return String 消息摘要
     * @throws AlgorithmException
     */
    public static String encrypt512(String data, String key) {
        return toHexString(encrypt512(getBytes(data), getBytes(key)));
    }
}
