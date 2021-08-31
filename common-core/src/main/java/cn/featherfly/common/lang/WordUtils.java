
package cn.featherfly.common.lang;

import java.math.BigDecimal;

import cn.featherfly.common.constant.Unit;
import cn.featherfly.common.lang.number.ChineseNumber;

/**
 * <p>
 * 一些单词的处理
 * </p>
 *
 * @author zhongj
 */
public final class WordUtils {
    private WordUtils() {
    }

    /**
     * 转换第一个字符为大写
     *
     * @param word 需要转换的字符串
     * @return 转换完成的字符串
     */
    public static String upperCaseFirst(String word) {
        if (org.apache.commons.lang3.StringUtils.isBlank(word)) {
            return word;
        }
        word = word.trim();
        if (word.length() == 1) {
            return Character.toUpperCase(word.charAt(0)) + "";
        } else {
            return Character.toUpperCase(word.charAt(0)) + word.substring(1);
        }
    }

    /**
     * 将传入字符串中含有相应符号后的首个字符转换为大写，并去符号
     *
     * @param word 需要转换的字符串
     * @param sign 符号
     * @return 转换完成的字符串
     */
    public static String parseToUpperFirst(String word, char sign) {
        return parseToUpperFirst(word, sign, true);
    }

    /**
     * 将传入字符串中含有相应符号后的首个字符转换为大写，并去符号
     *
     * @param word        需要转换的字符串
     * @param sign        符号
     * @param toLowerCase 不需要大写的字符都转换为小写
     * @return 转换完成的字符串
     */
    public static String parseToUpperFirst(String word, char sign, boolean toLowerCase) {
        if (Lang.isEmpty(word)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean isSign = false;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == sign) {
                isSign = true;
            } else {
                if (isSign) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    if (toLowerCase) {
                        sb.append(Character.toLowerCase(c));
                    } else {
                        sb.append(c);
                    }
                }
                isSign = false;
            }
        }
        if (sb.length() > 0) {
            sb.replace(0, 1, Character.toLowerCase(sb.charAt(0)) + "");
        }
        return sb.toString();
    }

    /**
     * 将驼峰的单词改为指定符号连接的单词. 如果upperToLower为true，则将大写转换为小写
     *
     * @param word         需要转换的字符串
     * @param sign         符号
     * @param upperToLower 是否转换大写为小写
     * @return 转换完成的字符串
     */
    public static String addSignBeforeUpper(String word, char sign, boolean upperToLower) {
        if (Lang.isEmpty(word)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append(sign);
                }
                if (upperToLower) {
                    c = Character.toLowerCase(c);
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * <p>
     * 将传入的数值转换为带单位（KB,MB,GB等）的字符串 如2032转换为1.98 KB，254855398转换为243.04 MB
     * </p>
     *
     * @param total 数值
     * @return 带单位（KB,MB,GB等）的字符串
     */
    public static String parseUnit(long total) {
        // TODO 加入一个转换到指定单位的实现
        String strUnit = "";
        final double unitSize = 1024.0;
        final long max = 1024;
        strUnit = total + " B";
        if (total >= max) {
            long mod = total % max;
            total = total / max;
            double dm = mod / unitSize;
            BigDecimal bd = new BigDecimal(dm);
            bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
            String dotNumber = bd.toString();
            strUnit = total + dotNumber.substring(1, dotNumber.length()) + " KB";
        }
        if (total >= max) {
            long mod = total % max;
            total = total / max;
            double dm = mod / unitSize;
            BigDecimal bd = new BigDecimal(dm);
            bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
            String dotNumber = bd.toString();
            strUnit = total + dotNumber.substring(1, dotNumber.length()) + " MB";
        }
        if (total >= max) {
            long mod = total % max;
            total = total / max;
            double dm = mod / unitSize;
            BigDecimal bd = new BigDecimal(dm);
            bd = bd.setScale(2, BigDecimal.ROUND_DOWN);
            String dotNumber = bd.toString();
            strUnit = total + dotNumber.substring(1, dotNumber.length()) + " GB";
        }
        return strUnit;
    }

    /**
     * <p>
     * 转换为百分比表示的字符串
     * </p>
     *
     * @param num       待转换的数值
     * @param precision 转换后保密几位小数精度
     * @return 转换后的百分比表示字符串
     */
    public static String parseToPercent(double num, int precision) {
        BigDecimal bd = BigDecimal.valueOf(num);
        bd = bd.multiply(BigDecimal.valueOf(Unit.HUNDRED));
        bd = bd.setScale(precision, BigDecimal.ROUND_DOWN);
        String result = "%" + bd.toString();
        return result;
    }

    /**
     * <p>
     * 转换为百分比表示的字符串，不保留小数精度.
     * </p>
     *
     * @param num 待转换的数值
     * @return 转换后的百分比表示字符串
     */
    public static String parseToPercent(double num) {
        return parseToPercent(num, 0);
    }

    /**
     * 将阿拉伯数字转换为中文大写的金额数字.
     *
     * @param value 阿拉伯数字的金额
     * @return 中文大写数字
     */
    public static String toChineseMoneyNumber(BigDecimal value) {
        return toChineseMoneyNumber(value.doubleValue());
    }

    /**
     * 将阿拉伯数字转换为中文大写的金额数字.
     *
     * @param value 阿拉伯数字的金额
     * @return 中文大写数字
     */
    public static String toChineseMoneyNumber(double value) {
        return new ChineseNumber(false).toMoneyNumberWord(value);
    }

    /**
     * 将阿拉伯数字转换为中文大写数字.
     *
     * @param number 阿拉伯数字
     * @return 中文大写数字
     */
    public static String toChineseNumber(long number) {
        return toChineseNumber(number, false);
    }

    /**
     * 将阿拉伯数字转换为中文数字.
     *
     * @param number 阿拉伯数字
     * @param simple 是否简写中文数字
     * @return 中文数字
     */
    public static String toChineseNumber(long number, boolean simple) {
        return new ChineseNumber(simple).toNumberWord(number);
    }

    /**
     * 将阿拉伯数字转换为中文大写数字.
     *
     * @param number 阿拉伯数字
     * @return 中文大写数字
     */
    public static String toChineseNumber(int number) {
        return toChineseNumber(number, false);
    }

    /**
     * 将阿拉伯数字转换为中文数字.
     *
     * @param number 阿拉伯数字
     * @param simple 是否简写中文数字
     * @return 中文数字
     */
    public static String toChineseNumber(int number, boolean simple) {
        return new ChineseNumber(simple).toNumberWord(number);
    }
}
