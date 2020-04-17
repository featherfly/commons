//package cn.featherfly.common.algorithm;
//
//import java.security.MessageDigest;
//import java.util.Arrays;
//
//import org.bouncycastle.crypto.digests.SM3Digest;
//import org.bouncycastle.crypto.macs.HMac;
//import org.bouncycastle.crypto.params.KeyParameter;
//import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
//
///**
// * MD5 algorithm
// *
// * @author zhongj
// */
//public abstract class HMACSM3 extends Algorithm {
//
//    /**
//     * sm3 encrypt
//     *
//     * @param data data to encrypt
//     * @return encrypted result with byte[]
//     * @throws AlgorithmException
//     */
//    public static byte[] encrypt(byte[] data) {
//        return messageDigestEncode(data, MessageDigestAlgorithms.SM3);
//    }
//
//    /**
//     * sm3 encrypt
//     *
//     * @param data data to encrypt
//     * @return encrypted result with String
//     * @throws AlgorithmException
//     */
//    public static String encrypt(String data) {
//        return toHexString(encrypt(getBytes(data)));
//    }
//
//    /**
//     * sm3 encrypt
//     *
//     * @param data data to encrypt
//     * @param key  encrypt key
//     * @return encrypted result with byte[]
//     * @throws Exception
//     */
//    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
//        KeyParameter keyParameter = new KeyParameter(key);
//        SM3Digest sm3 = new SM3Digest();
//        HMac hMac = new HMac(sm3);
//        hMac.init(keyParameter);
//        hMac.update(data, 0, data.length);
//        byte[] result = new byte[hMac.getMacSize()];
//        hMac.doFinal(result, 0);
//        return result;
//    }
//
//    /**
//     * sm3 encrypt
//     *
//     * @param data data to encrypt
//     * @param key  encrypt key
//     * @return encrypted result with String
//     * @throws Exception
//     */
//    public static String encrypt(String data, String key) throws Exception {
//        return toHexString(encrypt(getBytes(data), getBytes(key)));
//
//    }
//
//    /**
//     * 校验源数据与加密数据是否一致
//     *
//     * @param src       源数据
//     * @param sm3HexStr 16进制的加密数据
//     * @return
//     * @throws Exception
//     */
//    public static boolean verify(String src, String sm3HexStr) throws Exception {
//        byte[] sm3HashCode = ByteUtils.fromHexString(sm3HexStr);
//        byte[] newHashCode = getEncryptBySrcByte(src.getBytes(ENCODING));
//        return Arrays.equals(newHashCode, sm3HashCode);
//    }
//
//    /**
//     * 利用源数据+密钥校验与密文是否一致
//     *
//     * @param src       源数据
//     * @param key       密钥
//     * @param sm3HexStr 密文
//     * @return
//     * @throws Exception
//     */
//    public static boolean verify(String src, String key, String sm3HexStr) throws Exception {
//        byte[] sm3HashCode = ByteUtils.fromHexString(sm3HexStr);
//        byte[] newHashCode = getEncryptByKey(src, key);
//        return Arrays.equals(newHashCode, sm3HashCode);
//    }
//
//    /**
//     * 测试
//     *
//     * @param args
//     * @throws Exception
//     */
//    public static void main(String[] args) throws Exception {
//        MessageDigest md = MessageDigest.getInstance("SM3");
//        System.out.println(md);
//        //        String srcStr = "春宵一刻值千金，花有清香月有阴；歌管楼台声细细，秋千院落夜沉沉！";
//        //        String key = "春宵";
//        //        // ******************************自定义密钥加密及校验*****************************************
//        //        String hexStrByKey = SM3.encrypt(srcStr, key);
//        //        System.out.println("        带密钥加密后的密文：" + hexStrByKey);
//        //
//        //        System.out.println("明文(带密钥)与密文校验结果：" + SM3.verify(srcStr, key, hexStrByKey));
//        //        System.out.println(
//        //                "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        //        // ******************************无密钥的加密及校验******************************************
//        //        String hexStrNoKey = SM3.encrypt(srcStr);
//        //        System.out.println("        不带密钥加密后的密文：" + hexStrNoKey);
//        //
//        //        System.out.println("明文(不带密钥)与密文校验结果：" + SM3.verify(srcStr, hexStrNoKey));
//
//    }
//
//}
