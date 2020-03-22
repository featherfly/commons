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
}
