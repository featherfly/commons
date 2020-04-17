package cn.featherfly.common.algorithm;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * RSA algorithm
 *
 * @author zhongj
 */
public abstract class RSA extends Algorithm {

    /**
     * 非对称加密密钥算法
     */
    public static final String ALGORITHM_NAME = "RSA";

    /**
     * 数字签名 签名/验证算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * RSA密钥长度 默认1024位，密钥长度必须是64的倍数，范围在512至65536位之间。
     */
    private static final int KEY_SIZE = 1024;

    /**
     * decrypt with private key
     *
     * @param data data to decrypt
     * @param key  private Key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decrypt(byte[] data, PrivateKey key) {
        try {
            Cipher cipher = getChipher();
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * decrypt with private key
     *
     * @param data data to decrypt
     * @param key  private Key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decrypt(String data, PrivateKey key) {
        return decrypt(getBytes(data), key);
    }

    /**
     * decrypt with private key
     *
     * @param data data to decrypt
     * @param key  private Key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) {
        try {
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            // 生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            return decrypt(data, privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * decrypt with private key
     *
     * @param data data to decrypt
     * @param key  private Key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decryptByPrivateKey(String data, byte[] key) {
        return decryptByPrivateKey(encryptResultToBytes(data), key);
    }

    /**
     * decrypt with private key
     *
     * @param data data to decrypt
     * @param key  private Key
     * @return decrypted String
     * @throws AlgorithmException
     */
    public static String decryptToString(byte[] data, PrivateKey key) {
        return decryptByPrivateKeyToString(data, key.getEncoded());
    }

    /**
     * decrypt with private key
     *
     * @param data data to decrypt
     * @param key  private Key
     * @return decrypted String
     * @throws AlgorithmException
     */
    public static String decryptToString(String data, PrivateKey key) {
        return decryptByPrivateKeyToString(data, key.getEncoded());
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPrivateKeyToString(byte[] data, byte[] key) {
        return new String(decryptByPrivateKey(data, key), CHARSET);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密数据
     * @param key  私钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPrivateKeyToString(String data, byte[] key) {
        return decryptByPrivateKeyToString(encryptResultToBytes(data), key);
    }

    /**
     * decrypt with public key
     *
     * @param data data to decrypt
     * @param key  public Key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decrypt(byte[] data, PublicKey key) {
        try {
            Cipher cipher = getChipher();
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * decrypt with public key
     *
     * @param data data to decrypt
     * @param key  public Key
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decrypt(String data, PublicKey key) {
        return decrypt(getBytes(data), key);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) {
        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            // 生成公钥
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            return decrypt(data, publicKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return decrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] decryptByPublicKey(String data, byte[] key) {
        return decryptByPublicKey(encryptResultToBytes(data), key);
    }

    /**
     * decrypt with public key
     *
     * @param data data to decrypt
     * @param key  public Key
     * @return decrypted String
     * @throws AlgorithmException
     */
    public static String decryptToString(byte[] data, PublicKey key) {
        return decryptByPublicKeyToString(data, key.getEncoded());
    }

    /**
     * decrypt with public key
     *
     * @param data data to decrypt
     * @param key  public Key
     * @return decrypted String
     * @throws AlgorithmException
     */
    public static String decryptToString(String data, PublicKey key) {
        return decryptByPublicKeyToString(data, key.getEncoded());
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPublicKeyToString(byte[] data, byte[] key) {
        return new String(decryptByPublicKey(data, key), CHARSET);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密数据
     * @param key  公钥
     * @return String 解密数据
     * @throws AlgorithmException
     */
    public static String decryptByPublicKeyToString(String data, byte[] key) {
        return decryptByPublicKeyToString(encryptResultToBytes(data), key);
    }

    /**
     * encrypt with private key
     *
     * @param data data to encrypt
     * @param key  private Key
     * @return encrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data, PrivateKey key) {
        // 对数据加密
        try {
            Cipher cipher = getChipher();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * encrypt with private key
     *
     * @param data data to encrypt
     * @param key  private Key
     * @return encrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(String data, PrivateKey key) {
        return encrypt(getBytes(data), key);
    }

    /**
     * encrypt with public key
     *
     * @param data data to encrypt
     * @param key  public Key
     * @return encrypted byte[]
     * @throws AlgorithmException
     */
    public static byte[] encrypt(byte[] data, PublicKey key) {
        // 对数据加密
        try {
            Cipher cipher = getChipher();
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * encrypt with public key
     *
     * @param data data to encrypt
     * @param key  public Key
     * @return encrypted String
     * @throws AlgorithmException
     */
    public static byte[] encrypt(String data, PublicKey key) {
        return encrypt(getBytes(data), key);
    }

    /**
     * encrypt with private key
     *
     * @param data data to encrypt
     * @param key  private Key
     * @return encrypted String
     * @throws AlgorithmException
     */
    public static String encryptToString(byte[] data, PrivateKey key) {
        return encryptByPrivateKeyToString(data, key.getEncoded());
    }

    /**
     * encrypt with private key
     *
     * @param data data to encrypt
     * @param key  private Key
     * @return encrypted String
     * @throws AlgorithmException
     */
    public static String encryptToString(String data, PrivateKey key) {
        return encryptByPrivateKeyToString(data, key.getEncoded());
    }

    /**
     * encrypt with public key
     *
     * @param data data to encrypt
     * @param key  public Key
     * @return encrypted String
     * @throws AlgorithmException
     */
    public static String encryptToString(byte[] data, PublicKey key) {
        return encryptByPublicKeyToString(data, key.getEncoded());
    }

    /**
     * encrypt with public key
     *
     * @param data data to encrypt
     * @param key  public Key
     * @return encrypted String
     * @throws AlgorithmException
     */
    public static String encryptToString(String data, PublicKey key) {
        return encryptByPublicKeyToString(data, key.getEncoded());
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) {
        try {
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            return encrypt(data, publicKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPublicKey(String data, byte[] key) {
        return encryptByPublicKey(getBytes(data), key);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPublicKeyToString(byte[] data, byte[] key) {
        return encryptResultToString(encryptByPublicKey(data, key));
    }

    /**
     * 公钥加密
     *
     * @param data 待加密数据
     * @param key  公钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPublicKeyToString(String data, byte[] key) {
        return encryptByPublicKeyToString(getBytes(data), key);
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) {
        try {
            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            // 生成私钥
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 对数据加密
            return encrypt(data, privateKey);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return byte[] 加密数据
     * @throws AlgorithmException
     */
    public static byte[] encryptByPrivateKey(String data, byte[] key) {
        return encryptByPrivateKey(getBytes(data), key);
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPrivateKeyToString(byte[] data, byte[] key) {
        return encryptResultToString(encryptByPrivateKey(data, key));
    }

    /**
     * 私钥加密
     *
     * @param data 待加密数据
     * @param key  私钥
     * @return String 加密数据
     * @throws AlgorithmException
     */
    public static String encryptByPrivateKeyToString(String data, byte[] key) {
        return encryptByPrivateKeyToString(getBytes(data), key);
    }

    /**
     * generate KeyPair
     *
     * @param keySize key size
     * @return KeyPair KeyPair
     * @throws AlgorithmException
     */
    public static KeyPair generateKeyPair(int keySize) {
        try {
            // 实例化密钥对生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_NAME);
            // 初始化密钥对生成器
            keyPairGen.initialize(KEY_SIZE);
            // 生成密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * generate KeyPair
     *
     * @param seed seed for SecureRandom
     * @return KeyPair KeyPair
     * @throws AlgorithmException
     */
    public static KeyPair generateKeyPair(byte... seed) {
        try {
            // 实例化密钥对生成器
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_NAME);
            SecureRandom secureRandom = null;
            if (seed != null && seed.length > 0) {
                secureRandom = new SecureRandom(seed);
            } else {
                secureRandom = new SecureRandom();
            }
            // 初始化密钥对生成器
            keyPairGen.initialize(KEY_SIZE, secureRandom);
            // 生成密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    // ********************************************************************
    //	数字签名
    // ********************************************************************

    /**
     * 签名. {@link #sign(byte[], PrivateKey)}
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws AlgorithmException
     */
    public static String signToString(byte[] data, PrivateKey privateKey) {
        return encryptResultToString(sign(data, privateKey));
    }

    /**
     * 签名. {@link #sign(String, PrivateKey)}
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws AlgorithmException
     */
    public static String signToString(String data, PrivateKey privateKey) {
        return signToString(getBytes(data), privateKey);
    }

    /**
     * 签名. {@link #sign(byte[], PrivateKey)}
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws AlgorithmException
     */
    public static byte[] sign(String data, PrivateKey privateKey) {
        return sign(getBytes(data), privateKey);
    }

    /**
     * 签名. {@link #sign(byte[], byte[])}
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws AlgorithmException
     */
    public static byte[] sign(String data, byte[] privateKey) {
        return sign(getBytes(data), privateKey);
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws AlgorithmException
     */
    public static byte[] sign(byte[] data, PrivateKey privateKey) {
        try {
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initSign(privateKey);
            // 更新
            signature.update(data);
            // 签名
            return signature.sign();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return byte[] 数字签名
     * @throws AlgorithmException
     */
    public static byte[] sign(byte[] data, byte[] privateKey) {
        try {
            // 转换私钥材料
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            // 取私钥匙对象
            PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initSign(priKey);
            // 更新
            signature.update(data);
            // 签名
            return signature.sign();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 校验. {@link #verify(byte[], byte[], PublicKey)}
     *
     * @param data      待校验数据
     * @param sign      数字签名
     * @param publicKey 公钥
     * @return boolean 校验成功返回true 失败返回false
     * @throws AlgorithmException
     */
    public static boolean verify(String data, String sign, PublicKey publicKey) {
        return verify(getBytes(data), encryptResultToBytes(sign), publicKey);
    }

    /**
     * 校验. {@link #verify(byte[], byte[], byte[])}
     *
     * @param data      待校验数据
     * @param sign      数字签名
     * @param publicKey 公钥
     * @return boolean 校验成功返回true 失败返回false
     * @throws AlgorithmException
     */
    public static boolean verify(String data, String sign, byte[] publicKey) {
        return verify(getBytes(data), encryptResultToBytes(sign), publicKey);
    }

    /**
     * 校验
     *
     * @param data      待校验数据
     * @param sign      数字签名
     * @param publicKey 公钥
     * @return boolean 校验成功返回true 失败返回false
     * @throws AlgorithmException
     */
    public static boolean verify(byte[] data, byte[] sign, PublicKey publicKey) {
        try {
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initVerify(publicKey);
            // 更新
            signature.update(data);
            // 验证
            return signature.verify(sign);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * 校验
     *
     * @param data      待校验数据
     * @param sign      数字签名
     * @param publicKey 公钥
     * @return boolean 校验成功返回true 失败返回false
     * @throws AlgorithmException
     */
    public static boolean verify(byte[] data, byte[] sign, byte[] publicKey) {
        try {
            // 转换公钥材料
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
            // 实例化密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
            // 生成公钥
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            // 实例化Signature
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            // 初始化Signature
            signature.initVerify(pubKey);
            // 更新
            signature.update(data);
            // 验证
            return signature.verify(sign);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    private static Cipher getChipher() throws Exception {
        // 取得私钥
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);
        // 对数据解密
        return Cipher.getInstance(keyFactory.getAlgorithm());
    }
}
