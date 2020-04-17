package cn.featherfly.common.algorithm;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.util.encoders.Hex;

/**
 * need jars: bcpkix-jdk15on-160.jar bcprov-jdk15on-160.jar ref:
 * https://tools.ietf.org/html/draft-shen-sm2-ecdsa-02
 * http://gmssl.org/docs/oid.html http://www.jonllen.com/jonllen/work/164.aspx
 * 用BC的注意点：
 * 这个版本的BC对SM3withSM2的结果为asn1格式的r和s，如果需要直接拼接的r||s需要自己转换。下面rsAsn1ToPlainByteArray、rsPlainByteArrayToAsn1就在干这事。
 * 这个版本的BC对SM2的结果为C1||C2||C3，据说为旧标准，新标准为C1||C3||C2，用新标准的需要自己转换。下面changeC1C2C3ToC1C3C2、changeC1C3C2ToC1C2C3就在干这事。
 */
public class SM2 extends Algorithm {
    /**
     *
     */
    private static final String SIGNATURE = "SM3withSM2";
    private static final String EC = "EC";
    private static X9ECParameters x9ECParameters = GMNamedCurves.getByName("sm2p256v1");
    private static ECDomainParameters ecDomainParameters = new ECDomainParameters(x9ECParameters.getCurve(),
            x9ECParameters.getG(), x9ECParameters.getN());
    private static ECParameterSpec ecParameterSpec = new ECParameterSpec(x9ECParameters.getCurve(),
            x9ECParameters.getG(), x9ECParameters.getN());

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  public key
     * @return encrypted byte[]
     */
    public static byte[] encrypt(byte[] data, PublicKey key) {
        return changeC1C2C3ToC1C3C2(encryptOld(data, key));
    }

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  public key
     * @return encrypted byte[]
     */
    public static byte[] encrypt(String data, PublicKey key) {
        return encrypt(getBytes(data), key);
    }

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  public key
     * @return encrypted String
     */
    public static String encryptToString(byte[] data, PublicKey key) {
        return encryptResultToString(encrypt(data, key));
    }

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  public key
     * @return encrypted String
     */
    public static String encryptToString(String data, PublicKey key) {
        return encryptToString(getBytes(data), key);
    }

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  private key
     * @return encrypted byte[]
     */
    public static byte[] decrypt(byte[] data, PrivateKey key) {
        return decryptOld(changeC1C3C2ToC1C2C3(data), key);
    }

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  private key
     * @return encrypted byte[]
     */
    public static byte[] decrypt(String data, PrivateKey key) {
        return decrypt(encryptResultToBytes(data), key);
    }

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  private key
     * @return encrypted String
     */
    public static String decryptToString(byte[] data, PrivateKey key) {
        return new String(decrypt(data, key), CHARSET);
    }

    /**
     * c1||c3||c2
     *
     * @param data data to encrypt
     * @param key  private key
     * @return encrypted String
     */
    public static String decryptToString(String data, PrivateKey key) {
        return decryptToString(encryptResultToBytes(data), key);
    }

    /**
     * c1||c2||c3
     *
     * @param data data to encrypt
     * @param key  public key
     * @return encrypted byte[]
     */
    public static byte[] encryptOld(byte[] data, PublicKey key) {
        BCECPublicKey localECPublicKey = (BCECPublicKey) key;
        ECPublicKeyParameters ecPublicKeyParameters = new ECPublicKeyParameters(localECPublicKey.getQ(),
                ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));
        try {
            return sm2Engine.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * c1||c2||c3
     *
     * @param data data to decrypt
     * @param key  private key
     * @return decrypted byte[]
     */
    public static byte[] decryptOld(byte[] data, PrivateKey key) {
        BCECPrivateKey localECPrivateKey = (BCECPrivateKey) key;
        ECPrivateKeyParameters ecPrivateKeyParameters = new ECPrivateKeyParameters(localECPrivateKey.getD(),
                ecDomainParameters);
        SM2Engine sm2Engine = new SM2Engine();
        sm2Engine.init(false, ecPrivateKeyParameters);
        try {
            return sm2Engine.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * @param data       data to sign
     * @param id         id
     * @param privateKey private key
     * @return r||s，直接拼接byte数组的rs
     */
    public static byte[] sign(byte[] data, byte[] id, PrivateKey privateKey) {
        return rsAsn1ToPlainByteArray(signAsn1Rs(data, id, privateKey));
    }

    /**
     * {@link #sign(byte[], byte[], PrivateKey)}
     *
     * @param data       data to sign
     * @param id         id
     * @param privateKey private key
     * @return r||s，直接拼接byte数组的rs
     */
    public static byte[] sign(String data, byte[] id, PrivateKey privateKey) {
        return sign(getBytes(data), id, privateKey);
    }

    /**
     * {@link #sign(byte[], byte[], PrivateKey)}
     *
     * @param data       data to sign
     * @param id         id
     * @param privateKey private key
     * @return String
     */
    public static String signToString(byte[] data, byte[] id, PrivateKey privateKey) {
        return encryptResultToString(sign(data, id, privateKey));
    }

    /**
     * {@link #signToString(byte[], byte[], PrivateKey)}
     *
     * @param data       data to sign
     * @param id         id
     * @param privateKey private key
     * @return String
     */
    public static String signToString(String data, byte[] id, PrivateKey privateKey) {
        return signToString(getBytes(data), id, privateKey);
    }

    /**
     * {@link #signAsn1Rs(byte[], byte[], PrivateKey)}
     *
     * @param data       data to sign
     * @param id         id
     * @param privateKey private key
     * @return rs in <b>asn1 format</b>
     */
    public static String signAsn1RsToString(byte[] data, byte[] id, PrivateKey privateKey) {
        return encryptResultToString(signAsn1Rs(data, id, privateKey));
    }

    /**
     * @param data       data to sign
     * @param id         id
     * @param privateKey private key
     * @return rs in <b>asn1 format</b>
     */
    public static byte[] signAsn1Rs(byte[] data, byte[] id, PrivateKey privateKey) {
        try {
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(id);
            Signature signer = Signature.getInstance(SIGNATURE, BouncyCastleProvider.PROVIDER_NAME);
            signer.setParameter(parameterSpec);
            signer.initSign(privateKey, new SecureRandom());
            signer.update(data, 0, data.length);
            byte[] sig = signer.sign();
            return sig;
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * @param data
     * @param id
     * @param sign      r||s，直接拼接byte数组的rs
     * @param publicKey
     * @return
     */
    public static boolean verify(byte[] data, byte[] id, byte[] sign, PublicKey publicKey) {
        return verifyAsn1Rs(data, id, rsPlainByteArrayToAsn1(sign), publicKey);
    }

    /**
     * @param data
     * @param id
     * @param sign      r||s，直接拼接byte数组的rs
     * @param publicKey
     * @return
     */
    public static boolean verify(String data, byte[] id, String sign, PublicKey publicKey) {
        return verify(getBytes(data), id, encryptResultToBytes(sign), publicKey);
    }

    /**
     * @param msg
     * @param userId
     * @param rs        in <b>asn1 format</b>
     * @param publicKey
     * @return
     */
    public static boolean verifyAsn1Rs(byte[] msg, byte[] userId, byte[] rs, PublicKey publicKey) {
        try {
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
            Signature verifier = Signature.getInstance(SIGNATURE, BouncyCastleProvider.PROVIDER_NAME);
            verifier.setParameter(parameterSpec);
            verifier.initVerify(publicKey);
            verifier.update(msg, 0, msg.length);
            return verifier.verify(rs);
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * generate KeyPair
     *
     * @return KeyPair
     */
    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator kpGen = KeyPairGenerator.getInstance(EC, BouncyCastleProvider.PROVIDER_NAME);
            kpGen.initialize(ecParameterSpec, new SecureRandom());
            KeyPair kp = kpGen.generateKeyPair();
            return kp;
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    public static BCECPrivateKey getPrivatekeyFromD(BigInteger d) {
        ECPrivateKeySpec ecPrivateKeySpec = new ECPrivateKeySpec(d, ecParameterSpec);
        return new BCECPrivateKey(EC, ecPrivateKeySpec, BouncyCastleProvider.CONFIGURATION);
    }

    public static BCECPublicKey getPublickeyFromXY(BigInteger x, BigInteger y) {
        ECPublicKeySpec ecPublicKeySpec = new ECPublicKeySpec(x9ECParameters.getCurve().createPoint(x, y),
                ecParameterSpec);
        return new BCECPublicKey(EC, ecPublicKeySpec, BouncyCastleProvider.CONFIGURATION);
    }

    public static PublicKey getPublickeyFromX509File(InputStream in) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
            X509Certificate x509 = (X509Certificate) cf.generateCertificate(in);
            //            System.out.println(x509.getSerialNumber());
            return x509.getPublicKey();
        } catch (Exception e) {
            throw new AlgorithmException(e);
        }
    }

    /**
     * bc加解密使用旧标c1||c2||c3，此方法在加密后调用，将结果转化为c1||c3||c2
     *
     * @param c1c2c3
     * @return
     */
    private static byte[] changeC1C2C3ToC1C3C2(byte[] c1c2c3) {
        final int c1Len = (x9ECParameters.getCurve().getFieldSize() + 7) / 8 * 2 + 1; //sm2p256v1的这个固定65。可看GMNamedCurves、ECCurve代码。
        final int c3Len = 32; //new SM3Digest().getDigestSize();
        byte[] result = new byte[c1c2c3.length];
        System.arraycopy(c1c2c3, 0, result, 0, c1Len); //c1
        System.arraycopy(c1c2c3, c1c2c3.length - c3Len, result, c1Len, c3Len); //c3
        System.arraycopy(c1c2c3, c1Len, result, c1Len + c3Len, c1c2c3.length - c1Len - c3Len); //c2
        return result;
    }

    /**
     * bc加解密使用旧标c1||c3||c2，此方法在解密前调用，将密文转化为c1||c2||c3再去解密
     *
     * @param c1c3c2
     * @return
     */
    private static byte[] changeC1C3C2ToC1C2C3(byte[] c1c3c2) {
        final int c1Len = (x9ECParameters.getCurve().getFieldSize() + 7) / 8 * 2 + 1; //sm2p256v1的这个固定65。可看GMNamedCurves、ECCurve代码。
        final int c3Len = 32; //new SM3Digest().getDigestSize();
        byte[] result = new byte[c1c3c2.length];
        System.arraycopy(c1c3c2, 0, result, 0, c1Len); //c1: 0->65
        System.arraycopy(c1c3c2, c1Len + c3Len, result, c1Len, c1c3c2.length - c1Len - c3Len); //c2
        System.arraycopy(c1c3c2, c1Len, result, c1c3c2.length - c3Len, c3Len); //c3
        return result;
    }

    private final static int RS_LEN = 32;

    private static byte[] bigIntToFixexLengthBytes(BigInteger rOrS) {
        // for sm2p256v1, n is 00fffffffeffffffffffffffffffffffff7203df6b21c6052b53bbf40939d54123,
        // r and s are the result of mod n, so they should be less than n and have length<=32
        byte[] rs = rOrS.toByteArray();
        if (rs.length == RS_LEN) {
            return rs;
        } else if (rs.length == RS_LEN + 1 && rs[0] == 0) {
            return Arrays.copyOfRange(rs, 1, RS_LEN + 1);
        } else if (rs.length < RS_LEN) {
            byte[] result = new byte[RS_LEN];
            Arrays.fill(result, (byte) 0);
            System.arraycopy(rs, 0, result, RS_LEN - rs.length, rs.length);
            return result;
        } else {
            throw new RuntimeException("err rs: " + Hex.toHexString(rs));
        }
    }

    /**
     * BC的SM3withSM2签名得到的结果的rs是asn1格式的，这个方法转化成直接拼接r||s
     *
     * @param rsDer rs in asn1 format
     * @return sign result in plain byte array
     */
    private static byte[] rsAsn1ToPlainByteArray(byte[] rsDer) {
        ASN1Sequence seq = ASN1Sequence.getInstance(rsDer);
        byte[] r = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(0)).getValue());
        byte[] s = bigIntToFixexLengthBytes(ASN1Integer.getInstance(seq.getObjectAt(1)).getValue());
        byte[] result = new byte[RS_LEN * 2];
        System.arraycopy(r, 0, result, 0, r.length);
        System.arraycopy(s, 0, result, RS_LEN, s.length);
        return result;
    }

    /**
     * BC的SM3withSM2验签需要的rs是asn1格式的，这个方法将直接拼接r||s的字节数组转化成asn1格式
     *
     * @param sign in plain byte array
     * @return rs result in asn1 format
     */
    private static byte[] rsPlainByteArrayToAsn1(byte[] sign) {
        if (sign.length != RS_LEN * 2) {
            throw new RuntimeException("err rs. ");
        }
        BigInteger r = new BigInteger(1, Arrays.copyOfRange(sign, 0, RS_LEN));
        BigInteger s = new BigInteger(1, Arrays.copyOfRange(sign, RS_LEN, RS_LEN * 2));
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(r));
        v.add(new ASN1Integer(s));
        try {
            return new DERSequence(v).getEncoded("DER");
        } catch (IOException e) {
            throw new AlgorithmException(e);
        }
    }
}