
/*
 * Thgk-Component-Commons created on May 20, 2010 2:58:26 PM
 */
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.featherfly.common.lang.Randoms.CharType;

/**
 * <p>
 * 随机生成工具类
 * </p>
 *
 * @author zhongj
 * @since 1.0
 * @version 1.0
 * @deprecated use {@link Randoms} instead
 */
@Deprecated
public final class RandomUtils {

    private RandomUtils() {
    }

    private static final int LETTER_NUMBER = 25;

    private static final int LOWERCASE_START = 97;

    private static final int UPPERCASE_START = 65;

    private static final int NUMBER = 10;

    private static final int NUMBER_START = 48;

    private static Random random = new Random();

    /**
     * 使用单个 long 种子设置此随机数生成器的种子
     *
     * @param seed 设置此随机数生成器的种子
     */
    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    /**
     * 随机返回一个字母(随机大小写）
     *
     * @return 一个随机字母(随机大小写）
     */
    public static char getRandomLetter() {
        if (getRandomBoolean()) {
            return getRandomLetterLowerCase();
        } else {
            return getRandomLetterUpperCase();
        }
    }

    /**
     * 随机返回一个大写字母
     *
     * @return 一个随机大写字母
     */
    public static char getRandomLetterUpperCase() {
        return (char) (random.nextInt(LETTER_NUMBER) + UPPERCASE_START);
    }

    /**
     * 随机返回一个小写字母
     *
     * @return 一个随机小写字母
     */
    public static char getRandomLetterLowerCase() {
        return (char) (random.nextInt(LETTER_NUMBER) + LOWERCASE_START);
    }

    /**
     * 随机返回一个数字，（0-9）
     *
     * @return 一个随机数字，（0-9）
     */
    public static char getRandomNumber() {
        return (char) (random.nextInt(NUMBER) + NUMBER_START);
    }

    /**
     * 随机返回一个字符（大小写字母，数字0-9)
     *
     * @return 一个随机字符（大小写字母，数字0-9)
     */
    public static char getRandomWord() {
        final int type = 3;
        int sign = random.nextInt(type);
        if (sign == 0) {
            return getRandomNumber();
        } else if (sign == 1) {
            return getRandomLetterLowerCase();
        } else {
            return getRandomLetterUpperCase();
        }
    }

    /**
     * 随机返回一个布尔值
     *
     * @return 一个随机布尔值
     */
    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    /**
     * 随机返回一个整数
     *
     * @return 一个随机整数
     */
    public static int getRandomInt() {
        return random.nextInt();
    }

    /**
     * 返回一个不大于max的随机整数
     *
     * @param max 返回随机整数的最大边界值（大于返回的数）
     * @return 整数，0&lt;=返回值&lt;max
     */
    public static int getRandomInt(int max) {
        return random.nextInt(max);
    }

    /**
     * 返回一个随机double
     *
     * @return 一个随机double
     */
    public static double getRandomDouble() {
        return random.nextDouble();
    }

    /**
     * 返回一个随机Float
     *
     * @return 一个随机float
     */
    public static double getRandomFloat() {
        return random.nextFloat();
    }

    /**
     * 返回一个随机Long
     *
     * @return 一个随机long
     */
    public static double getRandomLong() {
        return random.nextLong();
    }

    /**
     * 返回一个随机Double
     *
     * @see java.util.Random#nextGaussian()
     * @return 一个随机duble
     */
    public static double getRandomGaussian() {
        return random.nextGaussian();
    }

    /**
     * 随机返回一个字符串（大小写字母，数字0-9）
     *
     * @param length 返回字符串的长度
     * @return 一个给定长度的随机字符串
     */
    public static String getRandomString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(getRandomWord());
        }
        return result.toString();
    }

    /**
     * 随机返回一个字符串，包含大写，小写还是数字又传入参数charTypes确定
     *
     * @param length 返回字符串的长度
     * @param charTypes 确定字符的类型（大写，小写，数字）
     * @return 一个给定长度的随机字符串
     */
    public static String getRandomString(int length, CharType... charTypes) {
        StringBuilder result = new StringBuilder();
        boolean[] signTemp = new boolean[] { false, false, false };
        for (int i = 0; charTypes != null && i < charTypes.length; i++) {
            CharType type = charTypes[i];
            if (type == CharType.LOWER_CASE) {
                signTemp[0] = true;
            } else if (type == CharType.UPPER_CASE) {
                signTemp[1] = true;
            } else if (type == CharType.NUMBER_CASE) {
                signTemp[2] = true;
            }
        }
        List<Integer> signList = new ArrayList<>();
        for (int i = 0; i < signTemp.length; i++) {
            if (signTemp[i]) {
                signList.add(i);
            }
        }
        // 传入了字符类型，随机按传入的类型返回
        if (signList.size() > 0) {
            for (int i = 0; i < length; i++) {
                int signIndex = random.nextInt(signList.size());
                int index = signList.get(signIndex);
                if (index == 0) {
                    result.append(getRandomLetterLowerCase());
                } else if (index == 1) {
                    result.append(getRandomLetterUpperCase());
                } else {
                    result.append(getRandomNumber());
                }
            }
        } else {
            // 没有传入任何类型，返回大小写以及数字
            for (int i = 0; i < length; i++) {
                result.append(getRandomWord());
            }
        }
        return result.toString();
    }

    /**
     * <p>
     * 以给定的列表为源，随机抽取其中一个元素返回
     * </p>
     *
     * @param <T> 集合内的对象类型
     * @param source 随机对象选取的源对象
     * @return 随机选择的对象
     */
    public static <T> T getRandom(List<T> source) {
        checkSource(source);
        int index = getRandomInt(source.size());
        return source.get(index);
    }

    /**
     * <p>
     * 以给定的列表为源，随机抽取其中一个元素返回
     * </p>
     *
     * @param <T> 集合内的对象类型
     * @param source 随机对象选取的源对象
     * @param length 返回字符串的长度
     * @return 随机选择的对象
     */
    public static <T> String getRandomString(List<T> source, int length) {
        checkSource(source);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = getRandomInt(source.size());
            sb.append(source.get(index));
        }
        return sb.toString();
    }

    /**
     * 把传入总数分解为随机的传入份数
     *
     * @param total 总数
     * @param num 分数
     * @return 随机数组
     */
    public static double[] getRandomFromTotal(double total, int num) {
        double[] results = new double[num];
        LeftMoneyPackage leftMoneyPackage = new LeftMoneyPackage(total, num);
        int i = 0;
        while (leftMoneyPackage.remainSize > 0) {
            results[i] = getRandomMoney(leftMoneyPackage);
            i++;
        }
        return results;
    }

    private static double getRandomMoney(LeftMoneyPackage _leftMoneyPackage) {
        if (_leftMoneyPackage.remainSize == 1) {
            _leftMoneyPackage.remainSize--;
            return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
        }
        Random r = new Random();
        double min = 0.01;
        double max = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        _leftMoneyPackage.remainSize--;
        _leftMoneyPackage.remainMoney -= money;
        return money;
    }

    private static class LeftMoneyPackage {
        // remainSize 剩余的红包数量
        private Integer remainSize;
        // remainMoney 剩余的钱
        private Double remainMoney;

        public LeftMoneyPackage(Double remainMoney, Integer remainSize) {
            this.remainSize = remainSize;
            this.remainMoney = remainMoney;
        }
    }

    // ********************************************************************
    //    private method
    // ********************************************************************

    //检查源
    private static <T> void checkSource(List<T> source) {
        AssertIllegalArgument.isNotEmpty(source, "List<T> source");
    }
}
