package cn.featherfly.common.algorithm;

import java.util.zip.CRC32;

/**
 * CRC algorithm
 *
 * @author zhongj
 */
public abstract class CRC extends Algorithm {
    /**
     * algorithm name
     */
    public static final String NAME = "CRC";

    /**
     * generate crc32 long number
     *
     * @param bytes bytes
     * @return crc32 long number
     */
    public static long encode32(byte[] bytes) {
        CRC32 crc = new CRC32();
        crc.update(bytes);
        return crc.getValue();
    }
}
