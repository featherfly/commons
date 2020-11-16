package cn.featherfly.common.lang;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Unit;

/**
 * <p>
 * 日期的帮助类
 * </p>
 * .
 *
 * @author zhongj
 * @since 1.8.7
 */
public final class Dates {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dates.class);

    private Dates() {
    }

    // 默认格式化参数
    private static final String FORMART_DATE = "yyyy-MM-dd";

    private static final String FORMART_TIME = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern(FORMART_DATE);

    private static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern(FORMART_TIME);

    // 信息
    private static final String MSG_START_AFTER_END = "开始日期startDate不能晚于结束日期endDate";

    /**
     * 方法 {@link #getDayOfWeek(Date date)}的返回值 星期一.
     */
    public static final int MONDAY = 1;

    /**
     * 方法 {@link #getDayOfWeek(Date date)}的返回值 星期二.
     */
    public static final int TUESDAY = 2;

    /**
     * 方法 {@link #getDayOfWeek(Date date)}的返回值 星期三.
     */
    public static final int WEDNESDAY = 3;

    /**
     * 方法 {@link #getDayOfWeek(Date date)}的返回值 星期四.
     */
    public static final int THURSDAY = 4;

    /**
     * 方法 {@link #getDayOfWeek(Date date)}的返回值 星期五.
     */
    public static final int FRIDAY = 5;
    /**
     * 方法 {@link #getDayOfWeek(Date date)}的返回值 星期六.
     */
    public static final int SATURDAY = 6;
    /**
     * 方法 {@link #getDayOfWeek(Date date)}的返回值 星期日.
     */
    public static final int SUNDAY = 7;
    /**
     * 日期比较小于的常量.
     */
    public static final int COMPARE_LT = -1;
    /**
     * 日期比较等于的常量.
     */
    public static final int COMPARE_EQ = 0;
    /**
     * 日期比较大于的常量.
     */
    public static final int COMPARE_GT = 1;

    /**
     * <p>
     * 时间类型
     * </p>
     * millisecond 毫秒 second 秒 minute 分钟 hour 小时 day 日.
     */
    public enum TimeType {
        /** The millisecond. */
        millisecond,
        /** The second. */
        second,
        /** The minute. */
        minute,
        /** The hour. */
        hour,
        /** The day. */
        day
    }

    /**
     * Gets the time.
     *
     * @param localDateTime the local date time
     * @return the time
     */
    public static long getTime(LocalDateTime localDateTime) {
        return getTime(localDateTime, ZoneId.systemDefault());
    }

    /**
     * Gets the time.
     *
     * @param localDateTime the local date time
     * @param zoneId        the zone id
     * @return the time
     */
    public static long getTime(LocalDateTime localDateTime, ZoneId zoneId) {
        if (localDateTime == null) {
            return 0;
        }
        if (zoneId == null) {
            zoneId = ZoneId.systemDefault();
        }
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * To local date time.
     *
     * @param date the date
     * @return the local date time
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * To local date.
     *
     * @param date the date
     * @return the local date
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * To local time.
     *
     * @param date the date
     * @return the local time
     */
    public static LocalTime toLocalTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * To date.
     *
     * @param localDateTime the local date time
     * @return the date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * To date.
     *
     * @param localDate the local date
     * @return the date
     */
    public static Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 使用yyyy-MM-dd进行格式化.
     *
     * @param localDateTime the local date time
     * @return 如果传入的日期不为null,则按yyyy-MM-dd的格式返回一个格式化的字符串 如果传入的日期为null则返回空字符串（""）
     */
    public static String formatDate(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER_DATE);
    }

    /**
     * 使用yyyy-MM-dd hh:mm:ss进行格式化.
     *
     * @param localDateTime the local date time
     * @return 如果传入的日期不为null,则按yyyy-MM-dd hh:mm:ss的格式返回一个格式化的字符串
     *         如果传入的日期为null则返回空字符串（""）
     */
    public static String formatTime(LocalDateTime localDateTime) {
        return format(localDateTime, FORMATTER_TIME);
    }

    /**
     * 使用传入的格式化参数进行格式化.
     *
     * @param localDateTime the local date time
     * @param format        格式化参数
     * @return 如果传入的日期不会null,则按传入的格式返回一个格式化的字符串 如果传入的日期为null则返回空字符串（""）
     */
    public static String format(LocalDateTime localDateTime, String format) {
        LOGGER.debug("formart : formart={} ||| localDateTime ={}", new Object[] { format, localDateTime });
        return format(localDateTime, DateTimeFormatter.ofPattern(format));
    }

    private static String format(LocalDateTime localDateTime, DateTimeFormatter formartter) {
        if (localDateTime != null) {
            return localDateTime.format(formartter);
        }
        return "";
    }

    /**
     * 使用yyyy-MM-dd进行格式化.
     *
     * @param date 日期对象
     * @return 如果传入的日期不为null,则按yyyy-MM-dd的格式返回一个格式化的字符串 如果传入的日期为null则返回空字符串（""）
     */
    public static String formatDate(Date date) {
        return format(date, FORMART_DATE);
    }

    /**
     * 使用yyyy-MM-dd hh:mm:ss进行格式化.
     *
     * @param date 日期对象
     * @return 如果传入的日期不为null,则按yyyy-MM-dd hh:mm:ss的格式返回一个格式化的字符串
     *         如果传入的日期为null则返回空字符串（""）
     */
    public static String formatTime(Date date) {
        return format(date, FORMART_TIME);
    }

    /**
     * 使用传入的格式化参数进行格式化.
     *
     * @param date   日期
     * @param format 格式化参数
     * @return 如果传入的日期不会null,则按传入的格式返回一个格式化的字符串 如果传入的日期为null则返回空字符串（""）
     */
    public static String format(Date date, String format) {
        LOGGER.debug("formartDate: formart={} ||| date={}", new Object[] { format, date });
        if (date != null) {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
        return "";
    }

    /**
     * <p>
     * 将传入的参数以yyyy-MM-dd的格式进行日期转换
     * </p>
     * .
     *
     * @param strDate 日期的字符串表示
     * @return 转换后的日期
     */
    public static Date parseDate(String strDate) {
        return parse(strDate, FORMART_DATE);
    }

    /**
     * <p>
     * 将传入的参数以yyyy-MM-dd hh:mm:ss的格式进行日期转换
     * </p>
     * .
     *
     * @param strDate 日期的字符串表示
     * @return 转换后的日期
     */
    public static Date parseTime(String strDate) {
        return parse(strDate, FORMART_TIME);
    }

    /**
     * <p>
     * 将传入的参数（第一个）以传入的格式（第二个）进行日期转换
     * </p>
     * .
     *
     * @param strDate 日期的字符串表示
     * @param formart 格式
     * @return 转换后的日期
     */
    public static Date parse(String strDate, String formart) {
        LOGGER.debug("parse: formart={} ||| strDate={}", new Object[] { formart, strDate });
        AssertIllegalArgument.isNotBlank(strDate, "String strDate");
        AssertIllegalArgument.isNotBlank(formart, "String formart");
        DateFormat format = new SimpleDateFormat(formart);
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 根据指定的 year,month,day 返回Date实例.
     *
     * @param year  年
     * @param month 月 1-12
     * @param day   日 start 1
     * @return 指定year,month,day的Date实例
     */
    public static Date getDate(int year, int month, int day) {
        return toDate(LocalDate.of(year, month, day));
    }

    /**
     * 根据指定的 year,month,day,hour,minute,second 返回Date实例.
     *
     * @param year   年
     * @param month  月 1-12
     * @param day    日 start 1
     * @param hour   小时 0-23
     * @param minute 分钟 0-59
     * @param second 秒 0-59
     * @return 指定year,month,day,hour,minute,second的Date实例
     */
    public static Date getTime(int year, int month, int day, int hour, int minute, int second) {
        return toDate(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    /**
     * 返回当前年.
     *
     * @return 当前年
     */
    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 返回当前月，与Date和Calendar的0-11不同，返回的是1-12.
     *
     * @return 当前月
     */
    public static int getCurrentMonth() {
        return LocalDate.now().getMonthValue();
    }

    /**
     * 返回当前日期（一月中的哪天）.
     *
     * @return 当前日期（一月中的哪天）
     */
    public static int getCurrentDayOfMonth() {
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * 返回当前是星期几. 1 - 7.
     *
     * @return 当前是星期几
     */
    public static int getCurrentDayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * 返回当前小时（24小时制）.
     *
     * @return 当前小时（24小时制）
     */
    public static int getCurrentHour() {
        return LocalTime.now().getHour();
    }

    /**
     * 返回当前分钟.
     *
     * @return 当前分钟
     */
    public static int getCurrentMinute() {
        return LocalTime.now().getMinute();
    }

    /**
     * 返回当前秒.
     *
     * @return 当前秒
     */
    public static int getCurrentSecond() {
        return LocalTime.now().getSecond();
    }

    /**
     * 返回传入日期的年份.
     *
     * @param date 日期
     * @return 传入日期的年份
     */
    public static int getYear(Date date) {
        return toLocalDate(date).getYear();
    }

    /**
     * 返回传入日期的月份，与Date和Calendar的0-11不同，返回的是1-12.
     *
     * @param date 日期
     * @return 传入日期的月份
     */
    public static int getMonth(Date date) {
        return toLocalDate(date).getMonthValue();
    }

    /**
     * 返回传入日期的日（一月中的哪天）.
     *
     * @param date 日期
     * @return 传入日期的日（一月中的哪天）
     */
    public static int getDayOfMonth(Date date) {
        return toLocalDate(date).getDayOfMonth();
    }

    /**
     * 返回传入日期是星期几，星期一是1，星期日是7.
     *
     * @param date 日期
     * @return 传入日期是星期几
     */
    public static int getDayOfWeek(Date date) {
        return toLocalDate(date).getDayOfWeek().getValue();
    }

    /**
     * 返回传入日期是当年的第几天.
     *
     * @param date 日期
     * @return 传入日期是当年的第几天
     */
    public static int getDayOfYear(Date date) {
        return toLocalDate(date).getDayOfYear();
    }

    /**
     * 返回传入日期是当年的第几个星期.以星期一为一个星期的开始时间
     *
     * @param date 日期
     * @return 传入日期是当年的第几个星期
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 返回传入日期小时（24小时制）.
     *
     * @param date 日期
     * @return 传入日期小时（24小时制）
     */
    public static int getHour(Date date) {
        return toLocalTime(date).getHour();
    }

    /**
     * 返回传入日期分钟.
     *
     * @param date 日期
     * @return 传入日期分钟
     */
    public static int getMinute(Date date) {
        return toLocalTime(date).getMinute();
    }

    /**
     * 返回传入日期秒.
     *
     * @param date 日期
     * @return 传入日期秒
     */
    public static int getSecond(Date date) {
        return toLocalTime(date).getSecond();
    }

    /**
     * 返回给定日期按照指定单位的一个数字表示值.
     *
     * @param date 日期
     * @param type 指定一个单位（如second,hour）
     * @return 指定单位的一个数字表示值
     */
    public static long getTime(Date date, TimeType type) {
        long result = 0;
        if (date != null) {
            switch (type) {
                case millisecond:
                    result = date.getTime();
                    break;
                case second:
                    result = date.getTime() / Unit.KILO;
                    break;
                case minute:
                    result = date.getTime() / Unit.KILO / Unit.SIXTY;
                    break;
                case hour:
                    result = date.getTime() / Unit.KILO / Unit.SIXTY / Unit.SIXTY;
                    break;
                case day:
                    result = date.getTime() / Unit.KILO / Unit.SIXTY / Unit.SIXTY / Unit.TWENTYFOUR;
                    break;
                default:
                    result = date.getTime();
            }
        }
        return result;
    }

    /**
     * 以年为最小单位比较日期大小. 如果第一个日期小于第二个日期，返回 -1 如果第一个日期等于第二个日期，返回 0 如果第一个日期大于第二个日期，返回
     * 1
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 日期比较后的常量
     */
    public static int compareYear(Date firstDate, Date secondDate) {
        final String formart = "yyyy";
        return compare(firstDate, secondDate, formart);
    }

    /**
     * 以月为最小单位比较日期大小. 如果第一个日期小于第二个日期，返回 -1 如果第一个日期等于第二个日期，返回 0 如果第一个日期大于第二个日期，返回
     * 1
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 日期比较后的常量
     */
    public static int compareMonth(Date firstDate, Date secondDate) {
        final String formart = "yyyyMM";
        return compare(firstDate, secondDate, formart);
    }

    /**
     * 以日为最小单位比较日期大小. 如果第一个日期小于第二个日期，返回 -1 如果第一个日期等于第二个日期，返回 0 如果第一个日期大于第二个日期，返回
     * 1
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 日期比较后的常量
     */
    public static int compareDay(Date firstDate, Date secondDate) {
        final String formart = "yyyyMMdd";
        return compare(firstDate, secondDate, formart);
    }

    /**
     * 以小时为最小单位比较日期大小. 如果第一个日期小于第二个日期，返回 -1 如果第一个日期等于第二个日期，返回 0
     * 如果第一个日期大于第二个日期，返回 1
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 日期比较后的常量
     */
    public static int compareHour(Date firstDate, Date secondDate) {
        final String formart = "yyyyMMddHH";
        return compare(firstDate, secondDate, formart);
    }

    /**
     * 以分钟为最小单位比较日期大小. 如果第一个日期小于第二个日期，返回 -1 如果第一个日期等于第二个日期，返回 0
     * 如果第一个日期大于第二个日期，返回 1
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 日期比较后的常量
     */
    public static int compareMinute(Date firstDate, Date secondDate) {
        final String formart = "yyyyMMddHHmm";
        return compare(firstDate, secondDate, formart);
    }

    /**
     * 以秒为最小单位比较日期大小. 如果第一个日期小于第二个日期，返回 -1 如果第一个日期等于第二个日期，返回 0 如果第一个日期大于第二个日期，返回
     * 1
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 日期比较后的常量
     */
    public static int compareSecond(Date firstDate, Date secondDate) {
        final String formart = "yyyyMMddHHmmss";
        return compare(firstDate, secondDate, formart);
    }

    /**
     * <p>
     * 以年为最小单位判断第一个日期是否早于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否早于第二个日期
     */
    public static boolean beforeYear(Date firstDate, Date secondDate) {
        return COMPARE_LT == compareYear(firstDate, secondDate);
    }

    /**
     * <p>
     * 以年为最小单位判断第一个日期是否等于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean equalsYear(Date firstDate, Date secondDate) {
        return COMPARE_EQ == compareYear(firstDate, secondDate);
    }

    /**
     * <p>
     * 以年为最小单位判断第一个日期是否晚于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean afterYear(Date firstDate, Date secondDate) {
        return COMPARE_GT == compareYear(firstDate, secondDate);
    }

    /**
     * <p>
     * 以月为最小单位判断第一个日期是否早于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否早于第二个日期
     */
    public static boolean beforeMonth(Date firstDate, Date secondDate) {
        return COMPARE_LT == compareMonth(firstDate, secondDate);
    }

    /**
     * <p>
     * 以月为最小单位判断第一个日期是否等于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean equalsMonth(Date firstDate, Date secondDate) {
        return COMPARE_EQ == compareMonth(firstDate, secondDate);
    }

    /**
     * <p>
     * 以月为最小单位判断第一个日期是否晚于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean afterMonth(Date firstDate, Date secondDate) {
        return COMPARE_GT == compareMonth(firstDate, secondDate);
    }

    /**
     * <p>
     * 以日为最小单位判断第一个日期是否早于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否早于第二个日期
     */
    public static boolean beforeDay(Date firstDate, Date secondDate) {
        return COMPARE_LT == compareDay(firstDate, secondDate);
    }

    /**
     * <p>
     * 以日为最小单位判断第一个日期是否等于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean equalsDay(Date firstDate, Date secondDate) {
        return COMPARE_EQ == compareDay(firstDate, secondDate);
    }

    /**
     * <p>
     * 以日为最小单位判断第一个日期是否晚于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean afterDay(Date firstDate, Date secondDate) {
        return COMPARE_GT == compareDay(firstDate, secondDate);
    }

    /**
     * <p>
     * 以小时为最小单位判断第一个日期是否早于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否早于第二个日期
     */
    public static boolean beforeHour(Date firstDate, Date secondDate) {
        return COMPARE_LT == compareHour(firstDate, secondDate);
    }

    /**
     * <p>
     * 以小时为最小单位判断第一个日期是否等于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean equalsHour(Date firstDate, Date secondDate) {
        return COMPARE_EQ == compareHour(firstDate, secondDate);
    }

    /**
     * <p>
     * 以小时为最小单位判断第一个日期是否晚于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean afterHour(Date firstDate, Date secondDate) {
        return COMPARE_GT == compareHour(firstDate, secondDate);
    }

    /**
     * <p>
     * 以分钟为最小单位判断第一个日期是否早于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否早于第二个日期
     */
    public static boolean beforeMinute(Date firstDate, Date secondDate) {
        return COMPARE_LT == compareMinute(firstDate, secondDate);
    }

    /**
     * <p>
     * 以分钟为最小单位判断第一个日期是否等于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean equalsMinute(Date firstDate, Date secondDate) {
        return COMPARE_EQ == compareMinute(firstDate, secondDate);
    }

    /**
     * <p>
     * 以分钟为最小单位判断第一个日期是否晚于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean afterMinute(Date firstDate, Date secondDate) {
        return COMPARE_GT == compareMinute(firstDate, secondDate);
    }

    /**
     * <p>
     * 以秒为最小单位判断第一个日期是否早于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否早于第二个日期
     */
    public static boolean beforeSecond(Date firstDate, Date secondDate) {
        return COMPARE_LT == compareSecond(firstDate, secondDate);
    }

    /**
     * <p>
     * 以秒为最小单位判断第一个日期是否等于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean equalsSecond(Date firstDate, Date secondDate) {
        return COMPARE_EQ == compareSecond(firstDate, secondDate);
    }

    /**
     * <p>
     * 以秒为最小单位判断第一个日期是否晚于第二个日期
     * </p>
     * .
     *
     * @param firstDate  第一个日期
     * @param secondDate 第二个日期
     * @return 第一个日期是否是早于第二个日期
     */
    public static boolean afterSecond(Date firstDate, Date secondDate) {
        return COMPARE_GT == compareSecond(firstDate, secondDate);
    }

    /**
     * <p>
     * 返回某年某月的最大天数
     * </p>
     * .
     *
     * @param date 日期
     * @return 最大天数
     */
    public static int getMaxDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * <p>
     * 返回某年某月的最大天数
     * </p>
     * .
     *
     * @param year  某年
     * @param month 某月
     * @return 最大天数
     */
    public static int getMaxDayOfMonth(int year, int month) {
        final int lastMonth = 12;
        AssertIllegalArgument.isInRange(month, 1, lastMonth, "month");
        //        if (month < 1 || month > lastMonth) {
        //            throw new IllegalArgumentException("month 必须是 1-12 的整数 ");
        //        }
        return getMaxDayOfMonth(getDate(year, month, 1));
    }

    /**
     * 获取两个日期中间的年分数（包含月份与天数计算）.
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 两个日期中间的月份数
     */
    public static int getYearNumber(Date startDate, Date endDate) {
        return getYearNumber(startDate, endDate, true, true);
    }

    /**
     * 获取两个日期中间的年分数.
     *
     * @param startDate    开始日期
     * @param endDate      结束日期
     * @param computeMonth 计算月份
     * @param computeDay   计算天数（当computeMonth为true时有效）
     * @return 两个日期中间的月份数
     */
    public static int getYearNumber(Date startDate, Date endDate, boolean computeMonth, boolean computeDay) {
        checkNull(startDate, endDate);
        int startYear = getYear(startDate);
        int endYear = getYear(endDate);
        isTrue(startYear <= endYear, MSG_START_AFTER_END);
        int years = endYear - startYear;
        if (computeMonth) {
            int startMonth = getMonth(startDate);
            int endMonth = getMonth(endDate);
            if (startMonth > endMonth) {
                years -= 1;
            } else if (startMonth == endMonth && computeDay) {
                int startDay = getDayOfMonth(startDate);
                int endDay = getDayOfMonth(endDate);
                if (startDay > endDay) {
                    years -= 1;
                }
            }
        }
        return years;

    }

    /**
     * 获取两个日期中间的月份数（包含天数计算）.
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 两个日期中间的月份数
     */
    public static int getMonthNumber(Date startDate, Date endDate) {
        return getMonthNumber(startDate, endDate, true);
    }

    /**
     * 获取两个日期中间的月份数.
     *
     * @param startDate  开始日期
     * @param endDate    结束日期
     * @param computeDay 是否计算天数
     * @return 两个日期中间的月份数
     */
    public static int getMonthNumber(Date startDate, Date endDate, boolean computeDay) {
        checkNull(startDate, endDate);
        int startYear = getYear(startDate);
        int startMonth = getMonth(startDate);
        int endYear = getYear(endDate);
        int endMonth = getMonth(endDate);
        isTrue(startYear <= endYear, MSG_START_AFTER_END);
        int months = 0;
        for (int i = startYear; i <= endYear; i++) {
            if (i == startYear) {
                months += 12 - startMonth;
            } else if (i == endYear) {
                months += endMonth;
            } else {
                months += 12;
            }
        }
        int startDay = getDayOfMonth(startDate);
        int endDay = getDayOfMonth(endDate);
        if (computeDay && startDay > endDay) {
            months -= 1;
        }
        return months;
    }

    /**
     * 获取两个日期中间的天数.
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 两个日期中间的天数
     */
    public static int getDayNumber(Date startDate, Date endDate) {
        checkNull(startDate, endDate);
        int startYear = getYear(startDate);
        int endYear = getYear(endDate);
        isTrue(startYear <= endYear, MSG_START_AFTER_END);
        int days = 0;
        for (int i = 0; i < endYear - startYear; i++) {
            int maxDayOfMonth = getMaxDayOfMonth(startYear, 2);
            if (maxDayOfMonth == 28) {
                days += 365;
            } else {
                days += 366;
            }
        }
        int startDay = getDayOfYear(startDate);
        int endDay = getDayOfYear(endDate);
        isTrue(startDay <= endDay, MSG_START_AFTER_END);
        return days + endDay - startDay + 1;
    }

    /**
     * 获取两个日期中间的工作日天数. 不包含周六周日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 两个日期中间的工作日天数
     */
    public static int getWorkDayNumber(Date startDate, Date endDate) {
        checkNull(startDate, endDate);
        int startDay = getDayOfYear(startDate);
        int endDay = getDayOfYear(endDate);
        isTrue(startDay <= endDay, MSG_START_AFTER_END);
        int days = 0;
        Calendar cal = null;
        while (startDate.before(endDate) || startDate.equals(endDate)) {
            cal = Calendar.getInstance();
            //设置日期
            cal.setTime(startDate);
            if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                //进行比较，如果日期不等于周六也不等于周日，工作日+1
                days++;
            }
            //日期加1
            cal.add(Calendar.DAY_OF_MONTH, 1);
            startDate = cal.getTime();
        }
        return days;
    }

    /**
     * <p>
     * 使用系统当前日期与传入出生日期比较获得年龄
     * </p>
     * .
     *
     * @param birthday 生日
     * @return 年龄
     */
    public static int getAge(Date birthday) {
        AssertIllegalArgument.isNotNull(birthday, "Date birthday");
        return getAge(birthday, new Date());
    }

    /**
     * <p>
     * 使用传入比较日期与传入出生日期比较获得年龄.参数只要有一个为null，则返回null
     * </p>
     *
     * @param birthday   生日
     * @param compareDay 比较日期
     * @return 年龄
     */
    public static int getAge(Date birthday, Date compareDay) {
        AssertIllegalArgument.isNotNull(birthday, "Date birthday");
        AssertIllegalArgument.isNotNull(compareDay, "Date compareDay");
        isTrue(beforeDay(birthday, compareDay) || equalsDay(birthday, compareDay), "birthday不能晚于compareDay");
        int age = getYear(compareDay) - getYear(birthday);
        if (compare(compareDay, birthday, "MMdd") == COMPARE_LT) {
            age -= 1;
        }
        return age;
    }

    // ********************************************************************
    // private method
    // ********************************************************************

    /*
     * 以传入的格式形比较日期大小. 如果第一个日期小于第二个日期，返回 -1 如果第一个日期等于第二个日期，返回 0 如果第一个日期大于第二个日期，返回
     * 1
     * @param firstDate 第一个日期
     * @param secondDate 第二个日期
     * @param formart 格式
     * @return 日期比较后的常量
     */
    private static int compare(Date firstDate, Date secondDate, String formart) {
        checkNull(firstDate, secondDate);
        long first = Long.parseLong(format(firstDate, formart));
        long second = Long.parseLong(format(secondDate, formart));
        if (first < second) {
            return COMPARE_LT;
        } else if (first == second) {
            return COMPARE_EQ;
        } else {
            return COMPARE_GT;
        }
    }

    private static void checkNull(Date firstDate, Date secondDate) {
        AssertIllegalArgument.isNotNull(firstDate, "Date firstDate");
        AssertIllegalArgument.isNotNull(secondDate, "Date secondDate");
    }

    private static void isTrue(boolean exp, String msg) {
        if (!exp) {
            throw new IllegalArgumentException(msg);
        }
    }
}