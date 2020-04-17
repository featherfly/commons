package cn.featherfly.common.algorithm;

/**
 * HMACMD5 algorithm
 *
 * @author zhongj
 */
public abstract class HMACMD5 extends Algorithm {

    /**
     * HMACMD5 encrypt. use a random key, every encoded result is different with
     * same data.
     *
     * @param data data to encode
     * @return encoded result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data) {
        return macEncode(data, MacAlgorithms.HMACMD5);
    }

    /**
     * {@link #encrypt(byte[])}
     *
     * @param data data to encode
     * @return encoded result with string
     * @throws AlgorithmException
     */
    public static String encrypt(String data) {
        return toHexString(encrypt(data.getBytes(CHARSET)));
    }

    /**
     * HMACMD5 encrypt.use gived key to encrypt.
     *
     * @param data data to encrypt
     * @param key  key
     * @return encoded result with byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return macEncode(data, key, MacAlgorithms.HMACMD5);
    }

    /**
     * {@link #encrypt(byte[], byte[])}
     *
     * @param data data to encrypt
     * @param key  key
     * @return encoded result with string
     * @throws AlgorithmException
     */
    public static String encrypt(String data, String key) {
        return toHexString(encrypt(getBytes(data), getBytes(key)));
    }
}
