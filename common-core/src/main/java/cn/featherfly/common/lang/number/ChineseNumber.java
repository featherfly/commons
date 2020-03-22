package cn.featherfly.common.lang.number;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.LangUtils;

public class ChineseNumber implements LocaleNumber {

    private boolean simple = false;

    private static final String[] GROUP_UNITS = { "", "万", "亿", "兆", "京", "垓", "杼", "穰", "沟", "涧", "正", "载", "极" };

    private static final String[] SIMPLE_CHINESE_UNITS = { "", "十", "百", "千" };

    private static final String[] TRADITIONAL_CHINESE_UNITS = { "", "拾", "佰", "仟" };

    private static final String[] SIMPLE_CHINESE_NUMBERS = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
    private static final String[] TRADITIONAL_CHINESE_NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

    private static final String YUAN = "圆";

    private static final String JIAO = "角";

    private static final String FEN = "分";

    private static final String ZHENG = "整";

    /**
     */
    public ChineseNumber() {
        this(false);
    }

    /**
     * @param simple
     */
    public ChineseNumber(boolean simple) {
        super();
        this.simple = simple;
    }

    public String getGroupUnit(int groupPosition) {
        AssertIllegalArgument.isLt(groupPosition, GROUP_UNITS.length, "groupPosition");
        return GROUP_UNITS[groupPosition];
    }

    public String getUnit(int position) {
        AssertIllegalArgument.isLt(position, SIMPLE_CHINESE_UNITS.length, "position");
        if (simple) {
            return SIMPLE_CHINESE_UNITS[position];
        } else {
            return TRADITIONAL_CHINESE_UNITS[position];
        }
    }

    public String getChineseNumber(int number) {
        AssertIllegalArgument.isLt(number, SIMPLE_CHINESE_NUMBERS.length, "number");
        if (simple) {
            return SIMPLE_CHINESE_NUMBERS[number];
        } else {
            return TRADITIONAL_CHINESE_NUMBERS[number];
        }
    }

    private String toChineseNumber(String number) {
        if (LangUtils.isEmpty(number)) {
            return "";
        }
        String result = "";

        int groupSize = 4;
        int groupAmount = (number.length() - 1) / groupSize + 1;
        int start = -1;
        int end = -1;
        for (int groupNo = 0; groupNo < groupAmount; groupNo++) {
            String groupUnit = getGroupUnit(groupAmount - groupNo - 1);
            if (start == -1) {
                start = 0;
            }
            if (end == -1) {
                end = number.length() - groupSize * (groupAmount - groupNo - 1);
            }
            int pre = -1;
            for (; start < end; start++) {
                String unit = getUnit(end - start - 1);
                int num = number.charAt(start) - '0';
                if ((start + 1) % groupSize == 0 && groupNo == groupAmount - 1 && num == 0) {

                } else if (num == 0 && pre == 0) {
                } else {
                    result += getChineseNumber(num);
                }
                if (num != 0) {
                    result += unit;
                }
                pre = num;
            }
            while (result.length() > 1 && result.endsWith(getChineseNumber(0))) {
                result = result.substring(0, result.length() - 1);
            }
            result += groupUnit;

            start = end;
            end = start + groupSize;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toNumberWord(int number) {
        // YUFEI_TODO Auto-generated method stub
        return toChineseNumber(String.valueOf(number));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toNumberWord(long number) {
        return toChineseNumber(String.valueOf(number));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toMoneyNumberWord(double number) {
        BigDecimal value = BigDecimal.valueOf(number);
        String money = toNumberWord(value.longValue());
        money += YUAN;
        if (value.scale() == 0) {

        } else if (value.scale() == 1) {
            String scaleValue = StringUtils.substringAfter(value.toString(), ".");
            if (Integer.valueOf(scaleValue) != 0) {
                money += toChineseNumber(scaleValue) + JIAO;
            }
        } else if (value.scale() == 2) {
            String scaleValue = StringUtils.substringAfter(value.toString(), ".");
            money += toChineseNumber(scaleValue.substring(0, 1)) + JIAO;
            money += toChineseNumber(scaleValue.substring(1, 2)) + FEN;
        }
        return money += ZHENG;
    }
}