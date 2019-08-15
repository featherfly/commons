
package cn.featherfly.common.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * <p>
 * NumberUtils
 * </p>
 *
 * @author zhongj
 */
public final class NumberUtils {

    /**
     */
    private NumberUtils() {
    }

    /**
     * <p>
     * 转换数字对象到指定数字类型
     * </p>
     *
     * @param number      数字
     * @param targetClass 转换目标数字类型
     * @param <T>         转换后的类型
     * @return 转换后的数字对象
     */
    public static <T extends Number> T convert(Number number, Class<T> targetClass) {
        return value(number, targetClass);
    }

    /**
     * <p>
     * 将Number转换为指定数字类型，如果传入数字是null，则返回null
     * </p>
     *
     * @param number 数字源
     * @param toType 转换目标类型
     * @param <T>    目标类型泛型
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
     *
     * @param source 转换源字符串
     * @param toType 转换目标类型
     * @param <T>    目标类型泛型
     * @return 数字
     */
    @SuppressWarnings("unchecked")
    public static <T extends Number> T parse(String source, Class<T> toType) {
        if (LangUtils.isEmpty(source)) {
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
     *
     * @param number    数字
     * @param minLength 最小长度
     * @param sign      补全使用的字符
     * @param <N>       泛型数字类型
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
     * <p>
     * 将传入的int转换为长度为4的字节数组
     * </p>
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
     *
     * @param bytes bytes
     * @return int
     */
    public static int toInt(byte[] bytes) {
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
     *
     * @param bytes bytes
     * @return short
     */
    public static short toShort(byte[] bytes) {
        short iOutcome = 0;
        byte bLoop;

        for (int i = 0; i < bytes.length; i++) {
            bLoop = bytes[i];
            iOutcome += (bLoop & 0xFF) << 8 * i;
        }
        return iOutcome;
    }
}
