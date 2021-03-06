
package cn.featherfly.common.algorithm;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.LoggerFactory;

/**
 * <p>
 * 加解密算法
 * </p>
 *
 * @author zhongj
 */
public abstract class Algorithm {

    static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Algorithm.class.getPackage().getName());

    static {
        // 添加BouncyCastle实现
        try {
            if (Security.getProvider("BC") == null) {
                // 使用反射可以处理没有添加BouncyCastleProvider实现JAR包时，这里出错的问题
                Provider provider = (Provider) Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider")
                        .newInstance();
                Security.addProvider(provider);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            LOGGER.info("org.bouncycastle.jce.provider.BouncyCastleProvider not found, use jdk default Provider");
        }
    }

    /**
     * 字符编码
     */
    public final static Charset CHARSET = StandardCharsets.UTF_8;

    //  128-32位16进制；256-64位16进制
    /**
     * default key size
     */
    public static final int DEFAULT_KEY_SIZE = 128;

    /**
     * <p>
     * 转换为16进制的字符串
     * </p>
     *
     * @param bytes 字节数组
     * @return 16进制的字符串
     */
    public static String toHexString(byte bytes[]) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < bytes.length; j++) {
            sb.append(Integer.toHexString(bytes[j] >> 4 & 0x0f));
            sb.append(Integer.toHexString(bytes[j] & 0x0f));
        }
        return sb.toString();
    }

    /**
     * convert HexString to byte[]
     *
     * @param hexStr hex string
     * @return byte[]
     */
    public static byte[] hexStringToByte(String hexStr) {
        /*对输入值进行规范化整理*/
        hexStr = hexStr.trim();
        //处理值初始化
        int m = 0, n = 0;
        int iLen = hexStr.length() / 2; //计算长度
        byte[] ret = new byte[iLen]; //分配存储空间
        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + hexStr.substring(i * 2, m) + hexStr.substring(m, n)) & 0xFF);
        }
        return ret;
    }

    /**
     * generate key with DEFAULT_KEY_SIZE {@link #DEFAULT_KEY_SIZE}.
     *
     * @param algorithmName algorithm name
     * @return byte[] key
     * @throws AlgorithmException
     */
    static byte[] generateKey(String algorithmName) {
        return generateKey(algorithmName, DEFAULT_KEY_SIZE);
    }

    /**
     * generate key.
     *
     * @param algorithmName algorithm name
     * @param keySize       key size
     * @return byte[] key
     * @throws AlgorithmException
     */
    static byte[] generateKey(String algorithmName, int keySize) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithmName);
            kg.init(keySize, new SecureRandom());
            return kg.generateKey().getEncoded();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * generate key.
     *
     * @param algorithmName algorithm name
     * @param seed          seed for SecureRandom
     * @return byte[] key
     * @throws AlgorithmException
     */
    static byte[] generateKey(String algorithmName, byte... seed) {
        // 实例化密钥生成器
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithmName);
            SecureRandom secureRandom = null;
            if (seed != null && seed.length > 0) {
                secureRandom = new SecureRandom(seed);
            } else {
                secureRandom = new SecureRandom();
            }
            // 初始化密钥生成器
            kg.init(secureRandom);
            // 生成秘密密钥
            SecretKey secretKey = kg.generateKey();
            // 获得密钥的二进制编码形式
            return secretKey.getEncoded();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * get bytes from string data
     *
     * @param data string data
     * @return byte[]
     */
    static byte[] getBytes(String data) {
        return data.getBytes(CHARSET);
    }

    /**
     * encrypted result byte[] to String
     *
     * @param encryptResult encrypted result
     * @return String
     */
    static String encryptResultToString(byte[] encryptResult) {
        return Base64.encryptToString(encryptResult);
    }

    /**
     * encrypted result String to byte[]
     *
     * @param encryptResult encrypted result
     * @return byte[]
     */
    static byte[] encryptResultToBytes(String encryptResult) {
        return Base64.decrypt(encryptResult);
    }

    /**
     * 消息摘要编码
     *
     * @param data      待编码的数据
     * @param algorithm 消息摘要算法
     * @return 编码的结果
     */
    static byte[] messageDigestEncode(byte[] data, MessageDigestAlgorithm algorithm) {
        try {
            // 初始化MessageDigest
            MessageDigest md = MessageDigest.getInstance(algorithm.getName());
            // 执行消息摘要
            return md.digest(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * mac编码,使用随机key进行编码，注意：对同一个data进行编码的结果每次都不一样.
     *
     * @param data      待编码的数据
     * @param algorithm mac算法
     * @return 编码的结果
     */
    static byte[] macEncode(byte[] data, MacAlgorithms algorithm) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(algorithm.getName());
            SecretKey key = keyGen.generateKey();
            Mac mac = Mac.getInstance(algorithm.getName());
            mac.init(key);
            mac.update(data);
            return mac.doFinal();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * mac编码
     *
     * @param data      待编码的数据
     * @param key       编码使用的key
     * @param algorithm mac算法
     * @return 编码的结果
     */
    static byte[] macEncode(byte[] data, byte[] key, MacAlgorithms algorithm) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, algorithm.getName());
            Mac mac = Mac.getInstance(algorithm.getName());
            mac.init(secretKey);
            return mac.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    public enum MessageDigestAlgorithms implements MessageDigestAlgorithm {
        //        MD2("MD2"),
        MD5("MD5"),
        SHA("SHA"),
        SHA224("SHA-224"),
        SHA256("SHA-256"),
        SHA384("SHA-384"),
        SHA512("SHA-512"),
        SM3("SHA");

        private MessageDigestAlgorithms(String name) {
            this.name = name;
        }

        private String name;

        /**
         * 返回name
         *
         * @return name
         */
        @Override
        public String getName() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return getName();
        }
    }

    public enum MacAlgorithms implements MacAlgorithm {
        HMACMD5("HMACMD5"),
        HMACSHA1("HMACSHA1"),
        HMACSHA224("HMACSHA224"),
        HMACSHA256("HMACSHA256"),
        HMACSHA384("HMACSHA384"),
        HMACSHA512("HMACSHA512");

        private MacAlgorithms(String name) {
            this.name = name;
        }

        private String name;

        /**
         * 返回name
         *
         * @return name
         */
        @Override
        public String getName() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return getName();
        }
    }
}

interface MacAlgorithm {
    String getName();
}

interface MessageDigestAlgorithm {
    String getName();
}
