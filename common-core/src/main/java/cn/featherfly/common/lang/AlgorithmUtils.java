
package cn.featherfly.common.lang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import org.apache.commons.io.IOUtils;

/**
 * <p>
 * AlgorithmUtils
 * </p>
 *
 * @author zhongj
 */
public class AlgorithmUtils {

    /**
     * generate crc32 long number
     *
     * @param inputStream inputStream
     * @return crc32 long number
     * @throws IOException
     */
    public static long crc32(InputStream inputStream) throws IOException {
        return crc32(IOUtils.toByteArray(inputStream));
    }

    /**
     * generate crc32 long number
     *
     * @param bytes bytes
     * @return crc32 long number
     */
    public static long crc32(byte[] bytes) {
        CRC32 crc = new CRC32();
        crc.update(bytes);
        return crc.getValue();
    }

    /**
     * 使用CheckedInputStream计算CRC
     */
    public static long getCRC32(String filepath) throws IOException {
        CRC32 crc32 = new CRC32();
        FileInputStream fileinputstream = new FileInputStream(new File(filepath));
        CheckedInputStream checkedinputstream = new CheckedInputStream(fileinputstream, crc32);
        while (checkedinputstream.read() != -1) {
        }
        checkedinputstream.close();
        return crc32.getValue();
    }
}
