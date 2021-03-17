
package cn.featherfly.common.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.number.Radix;

/**
 * <p>
 * NumberUtils
 * </p>
 * .
 *
 * @author zhongj
 */
public final class NumberUtils {

    /** The Constant LETTERS62. */
    private static final char[] LETTERS62;

    /** The Constant LETTERS64. */
    private static final char[] LETTERS64;

    /** The Constant LETTERS128 */
    private static final char[] LETTERS128;

    /** The Constant NUMBER_TEN. */
    private static final int NUMBER_TEN = 10;

    /** The Constant NUMBER_THREETY_SIX. */
    private static final int NUMBER_THREETY_SIX = 36;

    /** The Constant LETTERS64_DIGITS_MAP. */
    private static final Map<Character, Integer> LETTERS128_DIGITS_MAP = new HashMap<>();

    static {
        LETTERS62 = new char[Radix.RADIX62.value()];
        LETTERS64 = new char[Radix.RADIX64.value()];
        LETTERS128 = new char[Radix.RADIX128.value()];

        for (int i = 0; i < NUMBER_TEN; i++) {
            LETTERS62[i] = (char) ('0' + i);
            LETTERS64[i] = (char) ('0' + i);
        }
        for (int i = NUMBER_TEN; i < NUMBER_THREETY_SIX; i++) {
            LETTERS62[i] = (char) ('a' + i - NUMBER_TEN);
            LETTERS64[i] = (char) ('a' + i - NUMBER_TEN);
        }
        for (int i = NUMBER_THREETY_SIX; i < Radix.RADIX62.value(); i++) {
            LETTERS62[i] = (char) ('A' + i - NUMBER_THREETY_SIX);
            LETTERS64[i] = (char) ('A' + i - NUMBER_THREETY_SIX);
        }
        LETTERS64[Radix.RADIX62.value()] = '_';
        LETTERS64[Radix.RADIX62.value() + 1] = '~';

        int max = 0;
        for (char c : LETTERS64) {
            if (c > max) {
                max = c;
            }
        }
        //        for (int i = 0; i < LETTERS64.length; i++) {
        //            LETTERS128_DIGITS_MAP.put(LETTERS64[i], i);
        //
        //            LETTERS128[i] = LETTERS64[i];
        //        }

        ArrayUtils.fillAll(LETTERS128, LETTERS64);
        ArrayUtils.fillAll(LETTERS128, Radix.RADIX64.value(),
                new char[] { '©', 'ß', '¿', '£', '¤', '¥', '¦', '§', 'µ', '¶', 'À', 'Á', 'Â', 'Ä', 'Å', 'Æ', 'È', 'É',
                        'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'à',
                        'á', 'â', 'ä', 'å', 'æ', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ',
                        'ö', 'ù', 'ú', 'û', 'ü', 'ý', '¯', '®' });

        for (int i = 0; i < LETTERS128.length; i++) {
            LETTERS128_DIGITS_MAP.put(LETTERS128[i], i);
        }
    }

    /**
     * Instantiates a new number utils.
     */
    private NumberUtils() {
    }

    /**
     * <p>
     * 转换数字对象到指定数字类型
     * </p>
     * .
     *
     * @param <T>         转换后的类型
     * @param number      数字
     * @param targetClass 转换目标数字类型
     * @return 转换后的数字对象
     */
    public static <T extends Number> T convert(Number number, Class<T> targetClass) {
        return value(number, targetClass);
    }

    /**
     * Compare.
     *
     * @param <T>     the generic type
     * @param number1 the number 1
     * @param number2 the number 2
     * @return the int
     */
    public static <T extends Number> int compare(T number1, T number2) {
        if (number1 == null && number2 == null) {
            return 0;
        } else if (number1 == null) {
            return 1;
        } else if (number2 == null) {
            return -1;
        } else {
            if (number1 instanceof Byte) {
                return Byte.compare(number1.byteValue(), number2.byteValue());
            } else if (number1 instanceof Short) {
                return Short.compare(number1.shortValue(), number2.shortValue());
            } else if (number1 instanceof Integer) {
                return Integer.compare(number1.intValue(), number2.intValue());
            } else if (number1 instanceof Long) {
                return Long.compare(number1.longValue(), number2.longValue());
            } else if (number1 instanceof Float) {
                return Float.compare(number1.floatValue(), number2.floatValue());
            } else if (number1 instanceof Double) {
                return Double.compare(number1.doubleValue(), number2.doubleValue());
            } else if (number1 instanceof BigInteger) {
                return ((BigInteger) number1).compareTo((BigInteger) number2);
            } else if (number1 instanceof BigDecimal) {
                return ((BigDecimal) number1).compareTo((BigDecimal) number2);
            } else {
                throw new UnsupportedException("unsupported for " + number1.getClass().getName());
            }
        }
    }

    /**
     * <p>
     * 将Number转换为指定数字类型，如果传入数字是null，则返回null
     * </p>
     * .
     *
     * @param <T>    目标类型泛型
     * @param number 数字源
     * @param toType 转换目标类型
     * @return 数字
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T value(Number number, Class<T> toType) {
        if (number == null) {
            return null;
        }
        T value = null;
        if (toType == Integer.class || toType == Integer.TYPE) {
            value = (T) new Integer(number.intValue());
        } else if (toType == Long.class || toType == Long.TYPE) {
            value = (T) new Long(number.longValue());
        } else if (toType == Double.class || toType == Double.TYPE) {
            value = (T) new Double(number.doubleValue());
        } else if (toType == Float.class || toType == Float.TYPE) {
            value = (T) new Float(number.floatValue());
        } else if (toType == BigInteger.class) {
            value = (T) new BigInteger(number.toString());
        } else if (toType == BigDecimal.class) {
            value = (T) new BigDecimal(number.toString());
        } else if (toType == Byte.class || toType == Byte.TYPE) {
            value = (T) new Byte(number.byteValue());
        } else if (toType == Short.class || toType == Short.TYPE) {
            value = (T) new Short(number.shortValue());
        }
        if (value != null) {
            return value;
        }
        throw new IllegalArgumentException("不支持的目标类型：" + toType.getName());
    }

    /**
     * <p>
     * 将字符串转换为对应的类型，如果传入字符串是null或者空字符串，则返回null
     * </p>
     * .
     *
     * @param <T>    目标类型泛型
     * @param source 转换源字符串
     * @param toType 转换目标类型
     * @return 数字
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T parse(String source, Class<T> toType) {
        if (Lang.isEmpty(source)) {
            return null;
        }
        T value = null;
        if (toType == Integer.class || toType == Integer.TYPE) {
            value = (T) new Integer(Integer.parseInt(source));
        } else if (toType == Long.class || toType == Long.TYPE) {
            value = (T) new Long(Long.parseLong(source));
        } else if (toType == Double.class || toType == Double.TYPE) {
            value = (T) new Double(Double.parseDouble(source));
        } else if (toType == Float.class || toType == Float.TYPE) {
            value = (T) new Float(Float.parseFloat(source));
        } else if (toType == BigInteger.class) {
            value = (T) new BigInteger(source);
        } else if (toType == BigDecimal.class) {
            value = (T) new BigDecimal(source);
        } else if (toType == Byte.class || toType == Byte.TYPE) {
            value = (T) new Byte(Byte.parseByte(source));
        } else if (toType == Short.class || toType == Short.TYPE) {
            value = (T) new Short(Short.parseShort(source));
        }
        if (value != null) {
            return value;
        }
        throw new IllegalArgumentException("不支持的目标类型：" + toType.getName());
    }

    /**
     * <p>
     * 将传入字符串转换为Byte，如果转换不了则返回传入的默认值
     * </p>
     * .
     *
     * @param target       target
     * @param defaultValue defaultValue
     * @return Byte
     */
    public static Byte parse(String target, Byte defaultValue) {
        try {
            return Byte.parseByte(target);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * <p>
     * 将传入字符串转换为Integer，如果转换不了则返回传入的默认值
     * </p>
     * .
     *
     * @param target       target
     * @param defaultValue defaultValue
     * @return Integer
     */
    public static Integer parse(String target, Integer defaultValue) {
        try {
            return Integer.parseInt(target);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * <p>
     * 将传入字符串转换为Long，如果转换不了则返回传入的默认值
     * </p>
     * .
     *
     * @param target       target
     * @param defaultValue defaultValue
     * @return Long
     */
    public static Long parse(String target, Long defaultValue) {
        try {
            return Long.parseLong(target);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * <p>
     * 将传入字符串转换为Double，如果转换不了则返回传入的默认值
     * </p>
     * .
     *
     * @param target       target
     * @param defaultValue defaultValue
     * @return Double
     */
    public static Double parse(String target, Double defaultValue) {
        try {
            return Double.parseDouble(target);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * <p>
     * 将传入字符串转换为Float，如果转换不了则返回传入的默认值
     * </p>
     * .
     *
     * @param target       target
     * @param defaultValue defaultValue
     * @return Float
     */
    public static Float parse(String target, Float defaultValue) {
        try {
            return Float.parseFloat(target);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * <p>
     * 传入数字长度低于传入最小长度则使用传入字符补全在开头使长度达到最小长度
     * </p>
     * .
     *
     * @param <N>       泛型数字类型
     * @param number    数字
     * @param minLength 最小长度
     * @param sign      补全使用的字符
     * @return 补全后的字符串
     */
    public static <N extends Number> String fillingAtStart(N number, int minLength, char sign) {
        AssertIllegalArgument.isNotNull(number, "N number");
        int requiredLength = minLength - number.toString().length();
        if (requiredLength > 0) {
            StringBuilder pattern = new StringBuilder("'");
            for (int i = 0; i < requiredLength; i++) {
                pattern.append(sign);
            }
            pattern.append("'#");
            return new DecimalFormat(pattern.toString()).format(number);
        } else {
            return number.toString();
        }
    }

    /**
     * 将传入的long转换为长度为8的字节数组.
     *
     * @param longSource longSource
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

    /**
     * 将传入的字节数组转换为long.
     *
     * @param bytes bytes
     * @return int
     */
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

    /**
     * <p>
     * 将传入的int转换为长度为4的字节数组
     * </p>
     * .
     *
     * @param intSource intSource
     * @return byte[]
     */
    public static byte[] toByteArray(int intSource) {
        byte[] bLocalArr = new byte[Integer.BYTES];
        for (int i = 0; i < 4 && i < Integer.BYTES; i++) {
            bLocalArr[i] = (byte) (intSource >> 8 * i & 0xFF);
        }
        return bLocalArr;
    }

    /**
     * <p>
     * 将传入的字节数组转换为int
     * </p>
     * .
     *
     * @param bytes bytes
     * @return int
     */
    public static int toInt(byte... bytes) {
        int iOutcome = 0;
        byte bLoop;
        for (int i = 0; i < bytes.length; i++) {
            bLoop = bytes[i];
            iOutcome += (bLoop & 0xFF) << 8 * i;
        }
        return iOutcome;
    }

    /**
     * <p>
     * 将传入的short转换为长度为2的字节数组
     * </p>
     * .
     *
     * @param shortSource shortSource
     * @return byte[]
     */
    public static byte[] toByteArray(short shortSource) {
        byte[] bLocalArr = new byte[Short.BYTES];
        for (int i = 0; i < 4 && i < Short.BYTES; i++) {
            bLocalArr[i] = (byte) (shortSource >> 8 * i & 0xFF);
        }
        return bLocalArr;
    }

    /**
     * <p>
     * 将传入的字节数组转换为short
     * </p>
     * .
     *
     * @param bytes bytes
     * @return short
     */
    public static short toShort(byte... bytes) {
        short iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bytes.length; i++) {
            bLoop = bytes[i];
            iOutcome += (bLoop & 0xFF) << 8 * i;
        }
        return iOutcome;
    }

    /**
     * To string.
     *
     * @param number the value
     * @param radix  the radix
     * @return the string
     */
    public static String toString(byte number, Radix radix) {
        return toString(BigInteger.valueOf(number), radix);
    }

    /**
     * To string.
     *
     * @param number the value
     * @param radix  the radix
     * @return the string
     */
    public static String toString(short number, Radix radix) {
        return toString(BigInteger.valueOf(number), radix);
    }

    /**
     * To string.
     *
     * @param number the value
     * @param radix  the radix
     * @return the string
     */
    public static String toString(int number, Radix radix) {
        return toString(BigInteger.valueOf(number), radix);
    }

    /**
     * To string.
     *
     * @param number the value
     * @param radix  the radix
     * @return the string
     */
    public static String toString(long number, Radix radix) {
        return toString(BigInteger.valueOf(number), radix);
    }

    /**
     * To string.
     *
     * @param number the value
     * @param radix  the radix
     * @return the string
     */
    public static String toString(BigInteger number, Radix radix) {
        switch (radix) {
            case RADIX62:
                return toString62Unit(number);
            case RADIX64:
                return toString64Unit(number);
            default:
                return number.toString(radix.value());
        }
    }

    /**
     * To string 62 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString62Unit(byte number) {
        return toString62Unit((long) number);
    }

    /**
     * To string 62 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString62Unit(short number) {
        return toString62Unit((long) number);
    }

    /**
     * To string 62 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString62Unit(int number) {
        return toString62Unit((long) number);
    }

    /**
     * To string 62 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString62Unit(long number) {
        boolean reverse = false;
        if (number < 0) {
            reverse = true;
            number = 0 - number;
        } else if (number == 0) {
            return Chars.ZERO_STR;
        }
        StringBuilder buf = new StringBuilder();
        while (number != 0) {
            buf.append(LETTERS62[(int) (number % Radix.RADIX62.value())]);
            number /= Radix.RADIX62.value();
        }
        if (reverse) {
            buf.append(Chars.MINUS_CHAR);
        }
        return buf.reverse().toString();
    }

    /**
     * To string 62 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString62Unit(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            return Chars.ZERO_STR;
        }
        boolean reverse = false;
        BigInteger abs = number.abs();
        reverse = !abs.equals(number);
        if (reverse) {
            number = abs;
        }
        StringBuilder buf = new StringBuilder();
        while (!number.equals(BigInteger.ZERO)) {
            buf.append(LETTERS62[number.mod(BigInteger.valueOf(Radix.RADIX62.value())).intValue()]);
            number = number.divide(BigInteger.valueOf(Radix.RADIX62.value()));
        }
        if (reverse) {
            buf.append(Chars.MINUS_CHAR);
        }
        return buf.reverse().toString();
    }

    /**
     * To string 64 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString64Unit(byte number) {
        return toString64Unit((long) number);
    }

    /**
     * To string 64 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString64Unit(short number) {
        return toString64Unit((long) number);
    }

    /**
     * To string 64 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString64Unit(int number) {
        return toString64Unit((long) number);
    }

    /**
     * To string 64 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString64Unit(long number) {
        boolean reverse = false;
        if (number < 0) {
            reverse = true;
            number = 0 - number;
        } else if (number == 0) {
            return Chars.ZERO_STR;
        }
        StringBuilder buf = new StringBuilder();
        while (number != 0) {
            buf.append(LETTERS64[(int) (number % Radix.RADIX64.value())]);
            number /= Radix.RADIX64.value();
        }
        if (reverse) {
            buf.append(Chars.MINUS_CHAR);
        }
        return buf.reverse().toString();
    }

    /**
     * To string 64 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString64Unit(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            return Chars.ZERO_STR;
        }
        boolean reverse = false;
        BigInteger abs = number.abs();
        reverse = !abs.equals(number);
        if (reverse) {
            number = abs;
        }
        StringBuilder buf = new StringBuilder();
        while (!number.equals(BigInteger.ZERO)) {
            buf.append(LETTERS64[number.mod(BigInteger.valueOf(Radix.RADIX64.value())).intValue()]);
            number = number.divide(BigInteger.valueOf(Radix.RADIX64.value()));
        }
        if (reverse) {
            buf.append(Chars.MINUS_CHAR);
        }
        return buf.reverse().toString();
    }

    /**
     * To string 128 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString128Unit(byte number) {
        return toString128Unit((long) number);
    }

    /**
     * To string 128 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString128Unit(short number) {
        return toString128Unit((long) number);
    }

    /**
     * To string 128 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString128Unit(int number) {
        return toString128Unit((long) number);
    }

    /**
     * To string 128 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString128Unit(long number) {
        boolean reverse = false;
        if (number < 0) {
            reverse = true;
            number = 0 - number;
        } else if (number == 0) {
            return Chars.ZERO_STR;
        }
        StringBuilder buf = new StringBuilder();
        while (number != 0) {
            buf.append(LETTERS128[(int) (number % Radix.RADIX128.value())]);
            number /= Radix.RADIX128.value();
        }
        if (reverse) {
            buf.append(Chars.MINUS_CHAR);
        }
        return buf.reverse().toString();
    }

    /**
     * To string 128 unit.
     *
     * @param number the number
     * @return the string
     */
    public static String toString128Unit(BigInteger number) {
        if (number.equals(BigInteger.ZERO)) {
            return Chars.ZERO_STR;
        }
        boolean reverse = false;
        BigInteger abs = number.abs();
        reverse = !abs.equals(number);
        if (reverse) {
            number = abs;
        }
        StringBuilder buf = new StringBuilder();
        while (!number.equals(BigInteger.ZERO)) {
            buf.append(LETTERS128[number.mod(BigInteger.valueOf(Radix.RADIX128.value())).intValue()]);
            number = number.divide(BigInteger.valueOf(Radix.RADIX128.value()));
        }
        if (reverse) {
            buf.append(Chars.MINUS_CHAR);
        }
        return buf.reverse().toString();
    }

    /**
     * Parses the.
     *
     * @param numberStr the number str
     * @param radix     the radix
     * @return the big integer
     */
    public static BigInteger parse(String numberStr, Radix radix) {
        switch (radix) {
            case RADIX62:
                return parse62Unit(numberStr);
            case RADIX64:
                return parse64Unit(numberStr);
            case RADIX128:
                return parse128Unit(numberStr);
            default:
                return new BigInteger(numberStr, radix.value());
        }
    }

    /**
     * Parses the 62 unit to int.
     *
     * @param numberStr the number str
     * @return the int
     */
    public static int parse62UnitToInt(String numberStr) {
        return parse62Unit(numberStr).intValue();
    }

    /**
     * Parses the 62 unit to long.
     *
     * @param numberStr the number str
     * @return the long
     */
    public static long parse62UnitToLong(String numberStr) {
        boolean reverse = false;
        if (numberStr.charAt(0) == Chars.MINUS_CHAR) {
            reverse = true;
            numberStr = numberStr.substring(1);
        }
        long result = 0L;
        long multiplier = 1;
        for (int pos = numberStr.length() - 1; pos >= 0; pos--) {
            int index = getIndex(numberStr, pos, Radix.RADIX62);
            result += index * multiplier;
            multiplier *= Radix.RADIX62.value();
        }
        if (reverse) {
            return 0 - result;
        } else {
            return result;
        }
    }

    /**
     * Parses the 62 unit.
     *
     * @param numberStr the number str
     * @return the big integer
     */
    public static BigInteger parse62Unit(String numberStr) {
        boolean reverse = false;
        if (numberStr.charAt(0) == Chars.MINUS_CHAR) {
            reverse = true;
            numberStr = numberStr.substring(1);
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger multiplier = BigInteger.ONE;
        for (int pos = numberStr.length() - 1; pos >= 0; pos--) {
            int index = getIndex(numberStr, pos, Radix.RADIX62);
            result = result.add(multiplier.multiply(BigInteger.valueOf(index)));
            multiplier = multiplier.multiply(BigInteger.valueOf(Radix.RADIX62.value()));
        }
        if (reverse) {
            return BigInteger.ZERO.subtract(result);
        } else {
            return result;
        }
    }

    /**
     * Parses the 64 unit to int.
     *
     * @param numberStr the number str
     * @return the int
     */
    public static int parse64UnitToInt(String numberStr) {
        return parse64Unit(numberStr).intValue();
    }

    /**
     * Parses the 64 unit to long.
     *
     * @param numberStr the number str
     * @return the long
     */
    public static long parse64UnitToLong(String numberStr) {
        boolean reverse = false;
        if (numberStr.charAt(0) == Chars.MINUS_CHAR) {
            reverse = true;
            numberStr = numberStr.substring(1);
        }
        long result = 0L;
        long multiplier = 1;
        for (int pos = numberStr.length() - 1; pos >= 0; pos--) {
            int index = getIndex(numberStr, pos, Radix.RADIX64);
            result += index * multiplier;
            multiplier *= Radix.RADIX64.value();
        }
        if (reverse) {
            return 0 - result;
        } else {
            return result;
        }
    }

    /**
     * Parses the 64 unit.
     *
     * @param numberStr the number str
     * @return the big integer
     */
    public static BigInteger parse64Unit(String numberStr) {
        boolean reverse = false;
        if (numberStr.charAt(0) == Chars.MINUS_CHAR) {
            reverse = true;
            numberStr = numberStr.substring(1);
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger multiplier = BigInteger.ONE;
        for (int pos = numberStr.length() - 1; pos >= 0; pos--) {
            int index = getIndex(numberStr, pos, Radix.RADIX64);
            result = result.add(multiplier.multiply(BigInteger.valueOf(index)));
            multiplier = multiplier.multiply(BigInteger.valueOf(Radix.RADIX64.value()));
        }
        if (reverse) {
            return BigInteger.ZERO.subtract(result);
        } else {
            return result;
        }
    }

    /**
     * Parses the 128 unit to int.
     *
     * @param numberStr the number str
     * @return the int
     */
    public static int parse128UnitToInt(String numberStr) {
        return parse128Unit(numberStr).intValue();
    }

    /**
     * Parses the 128 unit to long.
     *
     * @param numberStr the number str
     * @return the long
     */
    public static long parse128UnitToLong(String numberStr) {
        return parse128Unit(numberStr).longValue();
    }

    /**
     * Parses the 128 unit.
     *
     * @param numberStr the number str
     * @return the big integer
     */
    public static BigInteger parse128Unit(String numberStr) {
        boolean reverse = false;
        if (numberStr.charAt(0) == Chars.MINUS_CHAR) {
            reverse = true;
            numberStr = numberStr.substring(1);
        }
        BigInteger result = BigInteger.ZERO;
        BigInteger multiplier = BigInteger.ONE;
        for (int pos = numberStr.length() - 1; pos >= 0; pos--) {
            int index = getIndex(numberStr, pos, Radix.RADIX128);
            result = result.add(multiplier.multiply(BigInteger.valueOf(index)));
            multiplier = multiplier.multiply(BigInteger.valueOf(Radix.RADIX128.value()));
        }
        if (reverse) {
            return BigInteger.ZERO.subtract(result);
        } else {
            return result;
        }
    }

    /**
     * Gets the index.
     *
     * @param s     the s
     * @param pos   the pos
     * @param radix the radix
     * @return the index
     */
    private static int getIndex(String s, int pos, Radix radix) {
        char c = s.charAt(pos);
        Integer value = LETTERS128_DIGITS_MAP.get(c);
        switch (radix) {
            case RADIX62:
                if (value == null) {
                    throw new IllegalArgumentException("Unknow character for unit62 " + c);
                }
                break;
            case RADIX64:
                if (value == null) {
                    throw new IllegalArgumentException("Unknow character for unit64 " + c);
                }
                break;
            case RADIX128:
                if (value == null) {
                    throw new IllegalArgumentException("Unknow character for unit128 " + c);
                }
                break;
            default:
                throw new IllegalArgumentException("not support for the radix -> " + radix);
        }
        return value;
    }

}
