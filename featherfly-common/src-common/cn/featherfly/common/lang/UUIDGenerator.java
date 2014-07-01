package cn.featherfly.common.lang;
import java.util.UUID;

/**
 * <p>
 * UUID生成工具类.<br/>
 * 支持生成长度为22,32,36的UUID.<br/>
 * 生成性能依次是36>32>22.
 * </p>
 * @author 钟冀
 */
public final class UUIDGenerator {

	private UUIDGenerator() {
	}

	/**
	 * <p>
	 * 生成长度为36的UUID
	 * </p>
	 * @return 长度为36的UUID
	 */
	public static String generateUUID36() {
		return generate();
	}

	/**
	 * <p>
	 * 生成长度为32的UUID
	 * </p>
	 * @return 长度为32的UUID
	 */
	public static String generateUUID32() {
		return StringUtils.replace(generate().toString(), "-", "");
	}

	/**
	 *
	 * <p>
	 * 生成长度为22的UUID.包含字符（ a到z,A到Z,0到9,-_ ）.<br/>
	 * 可以用在URL中，但是需要区分大小写，不建议作为数据库主键。
	 * </p>
	 * @return 长度为22的UUID
	 */
	public static String generateUUID22Letters() {
		String uuid = generateUUID32();
		uuid = "0" + uuid;
		return hexTo64Letters(uuid);
	}

	/**
	 *
	 * <p>
	 * 生成长度为22的UUID.包含字符（ a到z,`1234567890-=~!@#$%^&*()_+[]{}\\|;;,./? ）<br/>
	 * 包含URL特殊字符，不能在URL中使用，不包含大小写，可以用作数据库主键（优点：短，加速查询；缺点：人眼难识别）
	 * </p>
	 * @return 长度为22的UUID
	 */
	public static String generateUUID22Chars() {
		String uuid = generateUUID32();
		uuid = "0" + uuid;
		return hexTo64CHARS(uuid);
	}
	/**
	 * <p>
	 * 返回默认的UUID字符串（长度32位）
	 * </p>
	 * @return 默认的UUID字符串（长度32位）
	 */
	public static String generateUUID() {
		return generateUUID32();
	}

	// ********************************************************************
	//	private method
	// ********************************************************************

	private static String generate() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	// hexTo64 LETTERS <<<

	private static final char[] LETTERS_MAP;
	static {
		LETTERS_MAP = new char[64];
		for (int i = 0; i < 10; i++) {
			LETTERS_MAP[i] = (char) ('0' + i);
		}
		for (int i = 10; i < 36; i++) {
			LETTERS_MAP[i] = (char) ('a' + i - 10);
		}
		for (int i = 36; i < 62; i++) {
			LETTERS_MAP[i] = (char) ('A' + i - 36);
		}
		LETTERS_MAP[62] = '_';
		LETTERS_MAP[63] = '-';
	}

	private static String hexTo64Letters(String hex) {
		StringBuffer r = new StringBuffer();
		int index = 0;
		int[] buff = new int[3];
		int l = hex.length();
		for (int i = 0; i < l; i++) {
			index = i % 3;
			buff[index] = Integer.parseInt("" + hex.charAt(i), 16);
			if (index == 2) {
				r.append(LETTERS_MAP[buff[0] << 2 | buff[1] >>> 2]);
				r.append(LETTERS_MAP[(buff[1] & 3) << 4 | buff[2]]);
			}
		}
		return r.toString();
	}

    // hexTo64 LETTERS >>>

    // hexTo64 CHARS <<<

	private static final char[] CHARS_MAP;
	static {
		CHARS_MAP = new char[64];
		for (int i = 0; i < 63; i++) {
			CHARS_MAP[i] = (char) ('!' + i);
			if (CHARS_MAP[i] == '"') {
				CHARS_MAP[i] = '{';
			} else if (CHARS_MAP[i] == '\'') {
				CHARS_MAP[i] = '}';
			}
		}
		CHARS_MAP[63] = '|';
	}

	private static String hexTo64CHARS(String hex) {
		StringBuffer r = new StringBuffer();
		int index = 0;
		int[] buff = new int[3];
		int l = hex.length();
		for (int i = 0; i < l; i++) {
			index = i % 3;
			buff[index] = Integer.parseInt("" + hex.charAt(i), 16);
			if (index == 2) {
				r.append(CHARS_MAP[buff[0] << 2 | buff[1] >>> 2]);
				r.append(CHARS_MAP[(buff[1] & 3) << 4 | buff[2]]);
			}
		}
		return r.toString();
	}
    // hexTo64 CHARS >>>
}
