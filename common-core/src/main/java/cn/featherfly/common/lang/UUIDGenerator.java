package cn.featherfly.common.lang;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * UUID生成工具类.<br>
 * 支持生成长度为22,32,36的UUID.<br>
 * 生成性能依次是36&gt;32&gt;22.
 *
 * @author zhongj
 */
public final class UUIDGenerator {

    /**
     * The Enum Type.
     *
     * @author zhongj
     */
    public enum Type {
        /**
         * 生成长度为36的UUID.
         */
        UUID36,
        /**
         * 生成长度为32的UUID.
         */
        UUID32,
        /**
         * 生成长度为22的UUID.包含字符（ a到z,A到Z,0到9,-_ ）.<br>
         * 可以用在URL中，但是需要区分大小写，不建议作为数据库主键.
         */
        UUID22_LETTERS,
        /**
         * 生成长度为22的UUID.包含字符（ a到z,`1234567890-=~!@#$%^&amp;*()_+[]{}\\|;;,./? ）<br>
         * 包含URL特殊字符，不能在URL中使用，不包含大小写，可以用作数据库主键（优点：短，加速查询；缺点：人眼难识别）.
         */
        UUID22_CHARS
    }

    private UUIDGenerator() {
    }

    /**
     * 生成长度为36的UUID.
     *
     * @return 长度为36的UUID
     */
    public static String generateUUID36() {
        return generate();
    }

    /**
     * 生成长度为32的UUID.
     *
     * @return 长度为32的UUID
     */
    public static String generateUUID32() {
        return Strings.replace(generate().toString(), "-", "");
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
        return hexTo64Letters(uuid);
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
        return hexTo64CHARS(uuid);
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

    // ********************************************************************
    //    private method
    // ********************************************************************

    private static String generate() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        UUID uuid = new UUID(random.nextInt(), random.nextInt());
        return uuid.toString();
    }

    // hexTo64 LETTERS <<<

    private static final char[] LETTERS_MAP;
    private static final int NUMBER_THREE = 3;
    private static final int NUMBER_FOUR = 4;
    private static final int NUMBER_TEN = 10;
    private static final int NUMBER_SIXTEEN = 16;
    private static final int NUMBER_SIXTY_TWO = 62;
    private static final int NUMBER_SIXTY_FOUR = 64;
    private static final int NUMBER_THREETY_SIX = 36;
    static {
        LETTERS_MAP = new char[NUMBER_SIXTY_FOUR];
        for (int i = 0; i < NUMBER_TEN; i++) {
            LETTERS_MAP[i] = (char) ('0' + i);
        }
        for (int i = NUMBER_TEN; i < NUMBER_THREETY_SIX; i++) {
            LETTERS_MAP[i] = (char) ('a' + i - NUMBER_TEN);
        }
        for (int i = NUMBER_THREETY_SIX; i < NUMBER_SIXTY_TWO; i++) {
            LETTERS_MAP[i] = (char) ('A' + i - NUMBER_THREETY_SIX);
        }
        LETTERS_MAP[NUMBER_SIXTY_TWO] = '_';
        LETTERS_MAP[NUMBER_SIXTY_TWO + 1] = '-';
    }

    static String hexTo64Letters(String hex) {
        StringBuffer r = new StringBuffer();
        int index = 0;
        int[] buff = new int[NUMBER_THREE];
        int l = hex.length();
        for (int i = 0; i < l; i++) {
            index = i % NUMBER_THREE;
            buff[index] = Integer.parseInt("" + hex.charAt(i), NUMBER_SIXTEEN);
            if (index == 2) {
                r.append(LETTERS_MAP[buff[0] << 2 | buff[1] >>> 2]);
                r.append(LETTERS_MAP[(buff[1] & NUMBER_THREE) << NUMBER_FOUR | buff[2]]);
            }
        }
        return r.toString();
    }

    // hexTo64 LETTERS >>>

    // hexTo64 CHARS <<<

    private static final char[] CHARS_MAP;
    static {
        int last = NUMBER_SIXTY_FOUR - 1;
        CHARS_MAP = new char[NUMBER_SIXTY_FOUR];
        for (int i = 0; i < last; i++) {
            CHARS_MAP[i] = (char) ('!' + i);
            if (CHARS_MAP[i] == '"') {
                CHARS_MAP[i] = '{';
            } else if (CHARS_MAP[i] == '\'') {
                CHARS_MAP[i] = '}';
            }
        }
        CHARS_MAP[last] = '|';
    }

    static String hexTo64CHARS(String hex) {
        StringBuffer r = new StringBuffer();
        int index = 0;
        int[] buff = new int[NUMBER_THREE];
        int l = hex.length();
        for (int i = 0; i < l; i++) {
            index = i % NUMBER_THREE;
            buff[index] = Integer.parseInt("" + hex.charAt(i), NUMBER_SIXTEEN);
            if (index == 2) {
                r.append(CHARS_MAP[buff[0] << 2 | buff[1] >>> 2]);
                r.append(CHARS_MAP[(buff[1] & NUMBER_THREE) << NUMBER_FOUR | buff[2]]);
            }
        }
        return r.toString();
    }
    // hexTo64 CHARS >>>
}
