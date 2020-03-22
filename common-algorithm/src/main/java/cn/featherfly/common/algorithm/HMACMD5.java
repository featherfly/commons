package cn.featherfly.common.algorithm;

/**
 * MD5 algorithm
 *
 * @author zhongj
 */
public abstract class HMACMD5 extends Algorithm {

    /**
     * use a random key, every encoded result is different with same data.
     *
     * @param data data to encode
     * @return encoded result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encode(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACMD5);
    }

    /**
     * {@link #encode(byte[])}
     *
     * @param data data to encode
     * @return encoded result with string
     * @throws AlgorithmException
     */
    public static String encode(String data) {
        return toHexString(encode(data.getBytes(CHARSET)));
    }

    /**
     * use gived key to encde.
     *
     * @param data data to encode
     * @param key  key
     * @return encoded result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encode(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACMD5);
    }

    /**
     * use gived key to encde.
     *
     * @param data data to encode
     * @param key  key
     * @return encoded result with string
     * @throws AlgorithmException
     */
    public static String encode(String data, String key) {
        return toHexString(encode(getBytes(data), getBytes(key)));
    }
}
