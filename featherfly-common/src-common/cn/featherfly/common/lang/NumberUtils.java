
package cn.featherfly.common.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;

/**
 * <p>
 * NumberUtils
 * 类的说明放这里
 * </p>
 * 
 * @author 钟冀
 */
public final class NumberUtils {
    
    /**
     */
    private NumberUtils() {
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Number> T value(Number number, Class<T> targetType) {
        if (targetType == Integer.class
                || targetType == Integer.TYPE) {
            return (T) new Integer(number.intValue());
        } else if (targetType == Long.class
                || targetType == Long.TYPE) {
            return (T) new Long(number.longValue());
        } else if (targetType == Double.class
                || targetType == Double.TYPE) {
            return (T) new Double(number.doubleValue());
        } else if (targetType == Float.class
                || targetType == Float.TYPE) {
            return (T) new Float(number.floatValue());
        } else if (targetType == BigInteger.class) {
            return (T) new BigInteger(number.toString());
        } else if (targetType == BigDecimal.class) {
            return (T) new BigDecimal(number.toString());
        } else if (targetType == Byte.class
                || targetType == Byte.TYPE) {
            return (T) new Byte(number.byteValue());
        } else if (targetType == Short.class
                || targetType == Short.TYPE) {
            return (T) new Short(number.shortValue());
        }
        throw new IllegalArgumentException("不支持的目标类型：" + targetType.getName());
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Number> T parse(String target, Class<T> targetType) {
        if (targetType == Integer.class) {
            return (T) new Integer(Integer.parseInt(target));
        } else if (targetType == Long.class) {
            return (T) new Long(Long.parseLong(target));
        } else if (targetType == Double.class) {
            return (T) new Double(Double.parseDouble(target));
        } else if (targetType == Float.class) {
            return (T) new Float(Float.parseFloat(target));
        } else if (targetType == BigInteger.class) {
            return (T) new BigInteger(target);
        } else if (targetType == BigDecimal.class) {
            return (T) new BigDecimal(target);
        } else if (targetType == Byte.class) {
            return (T) new Byte(Byte.parseByte(target));
        } else if (targetType == Short.class) {
            return (T) new Short(Short.parseShort(target));
        }
        throw new IllegalArgumentException("不支持的目标类型：" + targetType.getName());
    }
    

    /**
     * <p>
     * 将传入字符串转换为Byte，如果转换不了则返回传入的默认值
     * </p>
     * @param target target
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
     * @param target target
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
     * @param target target
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
     * @param target target
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
     * @param target target
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
     * @param number 数字
     * @param minLength 最小长度
     * @param sign 补全使用的字符
     * @return 补全后的字符串
     */
    public static <N extends Number> String fillingAtStart(N number, int minLength, char sign) {
        AssertIllegalArgument.isNotNull(number);
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
}
