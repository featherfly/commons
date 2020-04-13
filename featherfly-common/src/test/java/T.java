import cn.featherfly.common.lang.ArrayUtils;

/**
 * <p>
 * T
 * </p>
 * <p>
 * 2020-03-20
 * </p>
 *
 * @author zhongj
 */
public class T {
    static long crc16(long[] buf, int Len) {
        int IX;
        long IY, CRC16;
        CRC16 = 0xFFFF;
        if (Len <= 0) {
            return 0;
        }
        for (IX = 0; IX < Len; IX++) {
            CRC16 = CRC16 ^ buf[IX];
        }
        for (IY = 0; IY < 8; IY++) {
            if ((CRC16 & 1) != 0) {
                CRC16 = CRC16 >> 1 ^ 0xA001;
            } else {
                CRC16 = CRC16 >> 1;
            }
        }
        return CRC16;
    }

    public static void main(String[] args) {
        System.out.println(
                Long.toHexString(crc16(new long[] { 0xFF, 0x0C, 0x00, 0xA3, 0x0B, 0x01, 0x01, 0x01, 0x03 }, 9)));

        System.out.println(ArrayUtils.toString(new long[] { 0xFF, 0x0C, 0x00, 0xA3, 0x0B, 0x01, 0x01, 0x01, 0x03 }));

    }
}
