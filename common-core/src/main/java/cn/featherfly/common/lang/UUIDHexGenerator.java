package cn.featherfly.common.lang;

import java.net.InetAddress;

import cn.featherfly.common.lang.UUIDGenerator.Type;

/**
 * The Class UUIDHexGenerator.
 */
public final class UUIDHexGenerator {

    private static final UUIDHexGenerator UUID = new UUIDHexGenerator();

    //    private String sep = "";

    private final int ip;

    private String formatedIP = "";

    private final int JVM = (int) (System.currentTimeMillis() >>> 8);

    private String formatedJVM = "";

    private short counter = (short) 0;

    private UUIDHexGenerator() {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        ip = ipadd;
        formatedIP = format(getIP());
        formatedJVM = format(getJVM());
    }

    /**
     * 生成长度为36的UUID.
     *
     * @return 长度为36的UUID
     */
    public static String generateUUID36() {
        return generate(true);
    }

    /**
     * 生成长度为32的UUID.
     *
     * @return 长度为32的UUID
     */
    public static String generateUUID32() {
        return generate(false);
    }

    /**
     * 生成长度为22的UUID.包含字符（ a到z,A到Z,0到9,-_ ）.<br>
     * 可以用在URL中，但是需要区分大小写，不建议作为数据库主键。
     *
     * @return 长度为22的UUID
     */
    public static String generateUUID22Letters() {
        String uuid = generateUUID32();
        uuid = "0" + uuid;
        return UUIDGenerator.hexTo64Letters(uuid);
    }

    /**
     * 生成长度为22的UUID.包含字符（ a到z,`1234567890-=~!@#$%^&amp;*()_+[]{}\\|;;,./? ）<br>
     * 包含URL特殊字符，不能在URL中使用，不包含大小写，可以用作数据库主键（优点：短，加速查询；缺点：人眼难识别）
     *
     * @return 长度为22的UUID
     */
    public static String generateUUID22Chars() {
        String uuid = generateUUID32();
        uuid = "0" + uuid;
        return UUIDGenerator.hexTo64CHARS(uuid);
    }

    /**
     * 返回默认的UUID字符串（长度32位）.
     *
     * @return 默认的UUID字符串（长度32位）
     */
    public static String generateUUID() {
        return generateUUID32();
    }

    /**
     * 返回指定类型的UUID字符串.
     *
     * @param type the type
     * @return UUID字符串
     */
    public static String generateUUID(Type type) {
        switch (type) {
            case UUID32:
                return generateUUID32();
            case UUID36:
                return generateUUID36();
            case UUID22_LETTERS:
                return generateUUID22Letters();
            case UUID22_CHARS:
                return generateUUID22Chars();
            default:
                return generateUUID32();
        }
    }

    private static String generate(boolean sep) {
        if (sep) {
            return UUID.generateUUID("-");
        } else {
            return UUID.generateUUID("");
        }
    }

    private String generateUUID(String sep) {
        return formatedIP + sep + formatedJVM + sep + format(getHiTime()) + sep + format(getLoTime()) + sep
            + format(getCount());
    }

    private String format(int intValue) {
        String formatted = Integer.toHexString(intValue);
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private String format(short shortValue) {
        String formatted = Integer.toHexString(shortValue);
        StringBuilder buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    /**
     * Unique across JVMs on this machine (unless they load this class
     * in the same quater second - very unlikely)
     */
    private int getJVM() {
        return JVM;
    }

    /**
     * Unique in a millisecond for this JVM instance (unless there
     * are &gt; Short.MAX_VALUE instances created in a millisecond)
     *
     * @return the count
     */
    private short getCount() {
        synchronized (UUIDHexGenerator.class) {
            if (counter < 0) {
                counter = 0;
            }
            return counter++;
        }
    }

    /**
     * Unique in a local network
     */
    private int getIP() {
        return ip;
    }

    /**
     * Unique down to millisecond
     */
    private short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    private int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    /**
     * Custom algorithm used to generate an int from a series of bytes.
     * <p/>
     * NOTE : this is different than interpreting the incoming bytes as an int value!
     *
     * @param bytes The bytes to use in generating the int.
     * @return The generated int.
     */
    private int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + bytes[i];
        }
        return result;
    }
}