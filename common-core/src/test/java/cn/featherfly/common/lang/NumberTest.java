
/*
 * All rights Reserved, Designed By zhongj
 * @Title: T.java
 * @Package cn.featherfly.hammer.sqldb
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-03-17 10:57:17
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * T.
 *
 * @author zhongj
 */
public class NumberTest {

    private static final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

    //byte 数组与 long 的相互转换
    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    /**
     * <p>
     * 将传入的int转换为长度为4的字节数组
     * </p>
     * .
     *
     * @param intSource intSource
     * @return byte[]
     */
    public static byte[] toByteArray(long longSource) {
        byte[] byteArray = new byte[Long.BYTES];
        int j = Long.BYTES - 1;
        for (int i = 0; i < Long.BYTES; i++, j--) {
            byteArray[i] = (byte) (longSource >> 8 * j);
        }
        return byteArray;
    }

    public static byte[] toByteArray2(long longSource) {
        byte[] byteArray = new byte[Long.BYTES];
        byteArray[0] = (byte) (longSource >> 56);
        byteArray[1] = (byte) (longSource >> 48);
        byteArray[2] = (byte) (longSource >> 40);
        byteArray[3] = (byte) (longSource >> 32);
        byteArray[4] = (byte) (longSource >> 24);
        byteArray[5] = (byte) (longSource >> 16);
        byteArray[6] = (byte) (longSource >> 8);
        byteArray[7] = (byte) (longSource >> 0);
        return byteArray;
    }

    public static long toLong(byte... bytes) {
        long iOutcome = 0;
        byte bLoop;
        int j = Long.BYTES - 1;
        long oxff = 0xFFL;
        for (int i = 0; i < bytes.length; i++, j--) {
            bLoop = bytes[i];
            iOutcome += (bLoop & oxff) << 8 * j;
        }
        return iOutcome;
    }

    public static int byteToUnsignedInt(byte b) {
        return b & 0xff;
    }

    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);

        byte[] bs = longToBytes(Long.MAX_VALUE);
        System.out.println(Arrays.toString(bs));
        System.out.println(bytesToLong(bs));

        bs = toByteArray(Long.MAX_VALUE);
        System.out.println(Arrays.toString(bs));
        System.out.println(toLong(bs));

        bs = longToBytes(Long.MIN_VALUE);
        System.out.println(Arrays.toString(bs));
        System.out.println(Arrays.toString(toByteArray(Long.MIN_VALUE)));

        System.out.println(Long.BYTES);

        System.out.println("bytesToLong " + byteToUnsignedInt((byte) -1));
        System.out.println("bytesToLong " + Byte.toUnsignedInt((byte) -1));

        Long l2 = new Long(1);

        BigInteger bi = BigInteger.valueOf(Long.MAX_VALUE);

        bi = bi.multiply(BigInteger.valueOf(10000));

        System.out.println(bi.toString());
        bs = bi.toByteArray();
        System.out.println(Arrays.toString(bs));
        System.out.println(new BigInteger(bs).toString());

        byte b = (byte) 255 & (byte) 0xFF;
        System.out.println(Arrays.toString(BigInteger.valueOf(b).toByteArray()));
        System.out.println(Arrays.toString(BigInteger.valueOf(Byte.MIN_VALUE).toByteArray()));
        System.out.println(Arrays.toString(BigInteger.valueOf(Byte.MAX_VALUE).toByteArray()));
        System.out.println(Arrays.toString(BigInteger.valueOf(Short.MAX_VALUE).toByteArray()));
        System.out.println(Arrays.toString(BigInteger.valueOf(Integer.MAX_VALUE).toByteArray()));
        System.out.println(Arrays.toString(BigInteger.valueOf(Long.MAX_VALUE).toByteArray()));

    }

}
