package cn.featherfly.common.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * CRC algorithm
 *
 * @author zhongj
 */
public abstract class CRC extends Algorithm {
    /**
     * algorithm name
     */
    public static final String NAME = "CRC32";

    /**
     * generate crc32 long number
     *
     * @param data data bytes
     * @return crc32 long number
     */
    public static long crc32(byte[] data) {
        CRC32 crc = new CRC32();
        crc.update(data);
        return crc.getValue();
    }

    /**
     * generate crc32 long number
     *
     * @param data data string
     * @return crc32 long number
     */
    public static long crc32(String data) {
        return crc32(getBytes(data));
    }

    /**
     * generate crc32 long number
     *
     * @param inputStream inputStream
     * @return crc32 long number
     * @throws IOException
     */
    public static long crc32(InputStream inputStream) throws IOException {
        CRC32 crc32 = new CRC32();
        CheckedInputStream checkedinputstream = new CheckedInputStream(inputStream, crc32);
        while (checkedinputstream.read() != -1) {
        }
        checkedinputstream.close();
        return crc32.getValue();
    }

    /**
     * generate crc32 long number
     *
     * @param filepath filepath
     * @return crc32 long number
     * @throws IOException
     */
    public static long crc32(File file) throws IOException {
        return crc32(new FileInputStream(file));
    }

    private static final long INITIALCRC = 0xFFFFFFFFFFFFFFFFL;
    private static long[] sCrcTable = new long[256];
    private static final long POLY64REV = 0x95AC9329AC4BC9B5L;

    static {
        // http://bioinf.cs.ucl.ac.uk/downloads/crc64/crc64.c
        long part;
        for (int i = 0; i < 256; i++) {
            part = i;
            for (int j = 0; j < 8; j++) {
                long x = ((int) part & 1) != 0 ? POLY64REV : 0;
                part = part >> 1 ^ x;
            }
            sCrcTable[i] = part;
        }
    }

    /**
     * generate crc64 long number
     *
     * @param data data byte[]
     * @return crc64 long number
     */
    public static final long crc64(byte[] bytes) {
        long crc = INITIALCRC;
        for (int k = 0, n = bytes.length; k < n; ++k) {
            crc = sCrcTable[((int) crc ^ bytes[k]) & 0xff] ^ crc >> 8;
        }
        return crc;
    }

    /**
     * generate crc64 long number
     *
     * @param data data string
     * @return crc64 long number
     */
    public static long crc64(String data) {
        return crc64(getBytes(data));
    }

    /**
     * CRC-16 (CCITT)：多项式x16+x12+x5+1（0x1021），初始值0x0000，低位在前，高位在后，结果与0x0000异或
     * 0x8408是0x1021按位颠倒后的结果。
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16Ccitt(byte[] data) {
        int wCRCin = 0x0000;
        int wCPoly = 0x8408;
        for (byte b : data) {
            wCRCin ^= b & 0x00ff;
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0x0000;

    }

    /**
     * CRC-16 (CCITT
     * REVERSE)：多项式x16+x12+x5+1（0x1021），初始值0xFFFF，低位在后，高位在前，结果与0x0000异或
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16CcittReverse(byte[] data) {
        int wCRCin = 0xffff;
        int wCPoly = 0x1021;
        for (byte b : data) {
            for (int i = 0; i < 8; i++) {
                boolean bit = (b >> 7 - i & 1) == 1;
                boolean c15 = (wCRCin >> 15 & 1) == 1;
                wCRCin <<= 1;
                if (c15 ^ bit) {
                    wCRCin ^= wCPoly;
                }
            }
        }
        wCRCin &= 0xffff;
        return wCRCin ^= 0x0000;
    }

    /**
     * CRC-16 (XModem)：多项式x16+x12+x5+1（0x1021），初始值0x0000，低位在后，高位在前，结果与0x0000异或
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16Xmodem(byte[] data) {
        int wCRCin = 0x0000; // initial value 65535
        int wCPoly = 0x1021; // 0001 0000 0010 0001 (0, 5, 12)
        for (byte b : data) {
            for (int i = 0; i < 8; i++) {
                boolean bit = (b >> 7 - i & 1) == 1;
                boolean c15 = (wCRCin >> 15 & 1) == 1;
                wCRCin <<= 1;
                if (c15 ^ bit) {
                    wCRCin ^= wCPoly;
                }
            }
        }
        wCRCin &= 0xffff;
        return wCRCin ^= 0x0000;
    }

    /**
     * CRC-16 (X25)：多项式x16+x12+x5+1（0x1021），初始值0xffff，低位在前，高位在后，结果与0xFFFF异或
     * 0x8408是0x1021按位颠倒后的结果。
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16X25(byte[] data) {
        int wCRCin = 0xffff;
        int wCPoly = 0x8408;
        for (byte b : data) {
            wCRCin ^= b & 0x00ff;
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0xffff;
    }

    /**
     * CRC-16 (Modbus)：多项式x16+x15+x2+1（0x8005），初始值0xFFFF，低位在前，高位在后，结果与0x0000异或
     * 0xA001是0x8005按位颠倒后的结果
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16Modbus(byte[] data) {
        int wCRCin = 0xffff;
        int POLYNOMIAL = 0xa001;
        for (byte b : data) {
            wCRCin ^= b & 0x00ff;
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= POLYNOMIAL;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0x0000;
    }

    /**
     * CRC-16 (IBM)：多项式x16+x15+x2+1（0x8005），初始值0x0000，低位在前，高位在后，结果与0x0000异或
     * 0xA001是0x8005按位颠倒后的结果
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16IBM(byte[] data) {
        int wCRCin = 0x0000;
        int wCPoly = 0xa001;
        for (byte b : data) {
            wCRCin ^= b & 0x00ff;
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0x0000;
    }

    /**
     * CRC-16 (MAXIM)：多项式x16+x15+x2+1（0x8005），初始值0x0000，低位在前，高位在后，结果与0xFFFF异或
     * 0xA001是0x8005按位颠倒后的结果
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16Maxim(byte[] data) {
        int wCRCin = 0x0000;
        int wCPoly = 0xa001;
        for (byte b : data) {
            wCRCin ^= b & 0x00ff;
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0xffff;
    }

    /**
     * CRC-16 (USB)：多项式x16+x15+x2+1（0x8005），初始值0xFFFF，低位在前，高位在后，结果与0xFFFF异或
     * 0xA001是0x8005按位颠倒后的结果
     *
     * @param data data byte[]
     * @return crc16 int number
     */
    public static int crc16USB(byte[] data) {
        int wCRCin = 0xFFFF;
        int wCPoly = 0xa001;
        for (byte b : data) {
            wCRCin ^= b & 0x00ff;
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0xffff;
    }

    /**
     * CRC-16
     * (DNP)：多项式x16+x13+x12+x11+x10+x8+x6+x5+x2+1（0x3D65），初始值0x0000，低位在前，高位在后，结果与0xFFFF异或
     * 0xA6BC是0x3D65按位颠倒后的结果
     *
     * @param data
     * @return crc16 int number
     */
    public static int crc16DNP(byte[] data) {
        int wCRCin = 0x0000;
        int wCPoly = 0xA6BC;
        for (byte b : data) {
            wCRCin ^= b & 0x00ff;
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x0001) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        return wCRCin ^= 0xffff;
    }

}
