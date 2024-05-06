package cn.featherfly.common.lang;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.constant.Unit;
import cn.featherfly.common.lang.string.StringFormatter;

/**
 * 字符串的工具类 .
 *
 * @author zhongj
 * @since  1.8.6
 */
public final class Strings extends org.apache.commons.lang3.StringUtils {

    private static final Pattern UNICODE_PATTERN = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

    private static final StringFormatter FORMATTER = new StringFormatter(Chars.CURLY_L_CHAR, Chars.CURLY_R_CHAR, true);

    // 全角空格 String s = "　"
    private static final String FULL_WIDTH_SPACE = "\u3000";

    private Strings() {
    }

    /**
     * null的字符串表示.
     */
    public static final String NULL_STRING = "null";

    /**
     * 如果第一个为有效的字符串(不是null，空串），则返回，否则返回第二个.
     *
     * @param  target        目标字符串
     * @param  defaultTarget 默认字符串
     * @return               第一个为有效的字符串(null，空串），则返回，否则返回第二个
     */
    public static String pickNotEmpty(String target, String defaultTarget) {
        return isEmpty(target) ? defaultTarget : target;
    }

    /**
     * 如果第一个为有效的字符串(不是null，空串，全空白字符组成的字符串），则返回，否则返回第二个.
     *
     * @param  target        目标字符串
     * @param  defaultTarget 默认字符串
     * @return               第一个为有效的字符串(null，空串，全空白字符组成的字符串），则返回，否则返回第二个
     */
    public static String pickNotBlank(String target, String defaultTarget) {
        return isBlank(target) ? defaultTarget : target;
    }

    /**
     * 返回第一个有效的字符串（不是null,不是空字符串,不是由全空白字符组成的字符串）.
     *
     * @param  pickedItems 需要选择的字符串
     * @return             返回第一个有效的字符串
     */
    public static String pickFirst(String... pickedItems) {
        if (pickedItems != null) {
            for (String s : pickedItems) {
                if (isNotBlank(s)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * 返回最后一个有效字符串（不是null,不是空字符串,不是由全空白字符组成的字符串） .
     *
     * @param  pickedItems 需要选择的字符串
     * @return             返回最后一个有效字符串
     */
    public static String pickLast(String... pickedItems) {
        if (pickedItems != null) {
            for (int i = pickedItems.length - 1; i >= 0; i--) {
                String s = pickedItems[i];
                if (isNotBlank(s)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * 如果为null,返回空字符串，否则返回传入字符串.
     *
     * @param  str 需要判断的字符串
     * @return     传入字符串或者空字符串
     */
    public static String getString(String str) {
        return getString(str, Chars.EMPTY_STR);
    }

    /**
     * 如果为null,空字符串，返回第二个参数，否则返回第一个参数.
     *
     * @param  str      需要判断的字符串
     * @param  defValue 默认值
     * @return          第一个参数或第二个参数
     */
    public static String getString(String str, String defValue) {
        return getString(str, defValue, true);
    }

    /**
     * 如果为null,空字符串，（当ignoreCaseNullStr为false，还要判断字符串"null"和"NULL"）
     * 返回第二个参数，否则返回第一个参数.
     *
     * @param  str               需要判断的字符串
     * @param  defValue          默认值
     * @param  ignoreCaseNullStr 是否忽略字符串形式的null和NULL
     * @return                   第一个参数或第二个参数
     */
    public static String getString(String str, String defValue, boolean ignoreCaseNullStr) {
        if (str == null || "".equals(str)) {
            return defValue;
        } else if (!ignoreCaseNullStr && (NULL_STRING.equals(str) || NULL_STRING.toUpperCase().equals(str))) {
            return defValue;
        } else {
            return str;
        }
    }

    /**
     * 判断传入字符串数组是否为null,0长度或者全部是空字符串（即""和null）. 只要有一个不是空字符串即返回真.
     *
     * @param  strs 需要判断的字符串数组
     * @return      传入是否是一个空字符串数组
     */
    public static boolean isEmpty(String[] strs) {
        for (String str : strs) {
            if (Lang.isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断传入字符串集合是否为null,0长度或者全部是空字符串（即""和null）. 只要有一个不是空字符串即返回真.
     *
     * @param  strs 需要判断的字符串集合
     * @return      传入是否是一个空字符串集合
     */
    public static boolean isEmpty(Collection<String> strs) {
        for (String str : strs) {
            if (Lang.isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断传入字符串是否是空字符串（即""和null） .
     *
     * @param  str 需要判断的字符串
     * @return     传入字符串是否是空字符串
     */
    public static boolean isEmpty(String str) {
        return Lang.isEmpty(str);
    }

    /**
     * 判断传入字符串是否不是空字符串（即""和null以外的） .
     *
     * @param  str 需要判断的字符串
     * @return     传入字符串是否不是空字符串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断传入字符串是否不是空白字符串（即包含空白字符以外的字符） .
     *
     * @param  str 需要判断的字符串
     * @return     传入字符串是否不是空白字符串
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断传入字符串是否为空以及是否为字符串null（不区分大小写）.
     *
     * @param  str 需要判断的字符串
     * @return     是否为空以及是否为null字符串
     */
    public static boolean isNull(String str) {
        return isEmpty(str) || "NULL".equalsIgnoreCase(str.trim());
    }

    /**
     * 去除字符串开始和结尾的空白字符. 如果传入的对象为null，直接返回，不会出现NullPointerException.
     *
     * @param  str 需要处理的字符串
     * @return     去除开始和结尾空白字符后的字符串
     */
    public static String trim(String str) {
        if (str == null) {
            return str;
        }
        return str.trim();
    }

    /**
     * 去除字符串开始和结尾的空白字符, 如果传入的对象为null，则返回defaultValue.
     *
     * @param  str          需要处理的字符串
     * @param  defaultValue 默认值
     * @return              去除开始和结尾空白字符后的字符串
     */
    public static String trim(String str, String defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        return str.trim();
    }

    /**
     * 去除字符串开始的空白字符 .
     *
     * @param  str 需要处理的字符串
     * @return     去除开始空白字符后的字符串
     */
    public static String trimBegin(String str) {
        if (isBlank(str)) {
            return "";
        }
        char[] c = str.toCharArray();
        int offset = 0;
        for (int i = 0; i < c.length; i++) {
            if (!Character.isWhitespace(c[i])) {
                offset = i;
                break;
            }
        }
        return new String(c, offset, c.length - offset);
    }

    /**
     * 去除字符串开始的空白字符 .
     *
     * @param      str 需要处理的字符串
     * @return         去除开始空白字符后的字符串
     * @deprecated     {@link #trimBegin(String)}
     */
    @Deprecated
    public static String trimStart(String str) {
        if (isBlank(str)) {
            return "";
        }
        char[] c = str.toCharArray();
        int offset = 0;
        for (int i = 0; i < c.length; i++) {
            if (!Character.isWhitespace(c[i])) {
                offset = i;
                break;
            }
        }
        return new String(c, offset, c.length - offset);
    }

    /**
     * 去除字符串结尾的空白字符 .
     *
     * @param  str 需要处理的字符串
     * @return     去除结尾空白字符的字符串
     */
    public static String trimEnd(String str) {
        if (isBlank(str)) {
            return "";
        }
        char[] c = str.toCharArray();
        int end = 0;
        for (int i = c.length; i > 0; i--) {
            if (!Character.isWhitespace(c[i - 1])) {
                end = i;
                break;
            }
        }
        return new String(c, 0, end);
    }

    /**
     * 去掉起始字符.
     *
     * @param  str 源字符串
     * @param  ts  需要去除的字符
     * @return     去掉起始字符后的字符串
     */
    public static String trimBegin(String str, String ts) {
        if (str == null) {
            return "";
        }
        while (str.startsWith(ts)) {
            str = str.substring(ts.length(), str.length());
        }
        return str;
    }

    /**
     * 去掉起始字符.
     *
     * @param      str 源字符串
     * @param      ts  需要去除的字符
     * @return         去掉起始字符后的字符串
     * @deprecated     {@link #trimBegin(String,String)}
     */
    @Deprecated
    public static String trimStart(String str, String ts) {
        if (str == null) {
            return "";
        }
        while (str.startsWith(ts)) {
            str = str.substring(ts.length(), str.length());
        }
        return str;
    }

    /**
     * 去掉尾部字符.
     *
     * @param  str 源字符串
     * @param  ts  需要去除的字符
     * @return     去掉尾部字符后的字符串
     */
    public static String trimEnd(String str, String ts) {
        if (str == null) {
            return "";
        }
        while (str.endsWith(ts)) {
            str = str.substring(0, str.length() - ts.length());
        }
        return str;
    }

    /**
     * 去掉首尾字符.
     *
     * @param      str 源字符串
     * @param      ts  需要去除的字符
     * @return         去掉首尾字符后的字符串
     * @deprecated     {@link #trimBeginEnd(String,String)}
     */
    @Deprecated
    public static String trimStartEnd(String str, String ts) {
        str = trimStart(str, ts);
        str = trimEnd(str, ts);
        return str;
    }

    /**
     * 去掉首尾空格，包括全角，半角.
     *
     * @param      str 源字符串
     * @return         去掉首尾空格后的字符串
     * @deprecated     {@link #trimBeginEndBlank(String)}
     */
    @Deprecated
    public static String trimStartEndBlank(String str) {
        if (str == null) {
            return "";
        }
        str = str.trim();
        //        str = trimStartEnd(str, " ");
        str = trimStartEnd(str, FULL_WIDTH_SPACE); // 全角空格
        return str;
    }

    /**
     * 去掉首尾字符.
     *
     * @param  str 源字符串
     * @param  ts  需要去除的字符
     * @return     去掉首尾字符后的字符串
     */
    public static String trimBeginEnd(String str, String ts) {
        str = trimBegin(str, ts);
        return trimEnd(str, ts);
    }

    /**
     * 去掉首尾空格，包括全角，半角.
     *
     * @param  str 源字符串
     * @return     去掉首尾空格后的字符串
     */
    public static String trimBeginEndBlank(String str) {
        if (str == null) {
            return "";
        }
        str = str.trim();
        str = trimBeginEnd(str, FULL_WIDTH_SPACE); // 全角空格
        return str;

    }

    /**
     * 判断传入的字符串是否为整数（使用10进制判断） .
     *
     * @param  str 需要判断的字符串
     * @return     传入的字符串是否为整数
     */
    public static boolean isInteger(String str) {
        final int numberUnit = 10;
        return isInteger(str, numberUnit);
    }

    /**
     * 判断传入的字符串是否为整数（使用指定进制判断） .
     *
     * @param  str   需要判断的字符串
     * @param  radio 使用的进制
     * @return       传入的字符串是否为整数
     */
    public static boolean isInteger(String str, int radio) {
        str = trim(str);
        try {
            Integer.parseInt(str, radio);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 将传入字符串以大写形式返回 .
     *
     * @param  str 需要转换的字符串
     * @return     传入字符串的大写形式
     */
    public static String toUpperCase(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            return str.toUpperCase();
        }
    }

    /**
     * 将传入字符串以小写形式返回 .
     *
     * @param  str 需要转换的字符串
     * @return     传入字符串的小写形式
     */
    public static String toLowerCase(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            return str.toLowerCase();
        }
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is {0}", "yufei");
     * </pre>
     *
     * </blockquote>
     *
     * @param  str  format string
     * @param  argu format argu
     * @return      formated str
     */
    public static String format(String str, Object argu) {
        return FORMATTER.format(str, new Object[] { argu });
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is {0}, i am {1} years old", new Object[] { "yufei", 18 });
     * </pre>
     *
     * </blockquote>
     *
     * @param  str  format string
     * @param  args format args
     * @return      formated str
     */
    public static String format(String str, Object... args) {
        return FORMATTER.format(str, args);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is $0$, i am $1$ years old", '$', new Object[] { "yufei", 18 });
     * </pre>
     *
     * </blockquote>
     *
     * @param  content the content
     * @param  args    format args
     * @param  symbol  the symbol
     * @return         formated str
     */
    public static String format(String content, char symbol, Object... args) {
        return format(content, symbol, symbol, args);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is {0}, i am {1} years old", '{', '}', new Object[] { "yufei", 18 });
     * </pre>
     *
     * </blockquote>
     *
     * @param  content     the content
     * @param  args        format args
     * @param  startSymbol the start symbol
     * @param  endSymbol   the end symbol
     * @return             formated str
     */
    public static String format(String content, char startSymbol, char endSymbol, Object... args) {
        return format(content, Lang.toMapStringKey(args), startSymbol, endSymbol);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is $0$, i am $1$ years old", '$', new Object[] { "yufei", 18 });
     * </pre>
     *
     * </blockquote>
     *
     * @param      content the content
     * @param      args    format args
     * @param      symbol  the symbol
     * @return             formated str
     * @deprecated         use {@link #format(String, char, Object...)} instead
     */
    @Deprecated
    public static String format(String content, Object[] args, char symbol) {
        return format(content, args, symbol, symbol);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is {0}, i am {1} years old", '{', '}', new Object[] { "yufei", 18 });
     * </pre>
     *
     * </blockquote>
     *
     * @param      content     the content
     * @param      args        format args
     * @param      startSymbol the start symbol
     * @param      endSymbol   the end symbol
     * @return                 formated str
     * @deprecated             use {@link #format(String, char,char, Object...)} instead
     */
    @Deprecated
    public static String format(String content, Object[] args, char startSymbol, char endSymbol) {
        return format(content, Lang.toMapStringKey(args), startSymbol, endSymbol);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is：{name}, i am {age} years old",
     *     new HashChainMap&lt;String, String&gt;().putChain("name", "yufei").putChain("age", "18"));
     * </pre>
     *
     * </blockquote>
     *
     * @param  content str content
     * @param  args    arg map
     * @return         formated str
     */
    public static String format(String content, Map<String, Object> args) {
        return FORMATTER.format(content, args);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is：$name$, i am $age$ years old",
     *     new HashChainMap&lt;String, String&gt;().putChain("name", "yufei").putChain("age", "18"), '$');
     * </pre>
     *
     * </blockquote>
     *
     * @param  content the content
     * @param  args    the args
     * @param  symbol  the symbol
     * @return         the string
     */
    public static String format(String content, Map<String, Object> args, char symbol) {
        return format(content, args, symbol, symbol);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * Strings.format("my name is：{name}, i am {age} years old",
     *     new HashChainMap&lt;String, String&gt;().putChain("name", "yufei").putChain("age", "18"), '{', '}');
     * </pre>
     *
     * </blockquote>
     *
     * @param  content     the content
     * @param  args        the args
     * @param  startSymbol the start symbol
     * @param  endSymbol   the end symbol
     * @return             the string
     */
    public static String format(String content, Map<String, Object> args, char startSymbol, char endSymbol) {
        return new StringFormatter(startSymbol, endSymbol).format(content, args);
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * User user = new User();
     * user.setName("yufei");
     * user.setAge(18);
     * StringFormatter formatter = new StringFormatter('{', '}');
     * formatter.format("my name is {name}, i am {age} years old", user);
     * </pre>
     *
     * </blockquote>
     *
     * @param  content the content
     * @param  args    format args
     * @return         formated str
     */
    public static String format2(String content, Object args) {
        return FORMATTER.format(content, args);
    }

    /**
     * format str. <code>
     * User user = new User();
     * user.setName("yufei");
     * user.setAge(18);
     * Strings.format("my name is：$name$, i am $age$ years old", user , '$');
     * </code>
     *
     * @param  content the content
     * @param  args    the args
     * @param  symbol  the symbol
     * @return         the string
     */
    public static String format(String content, Object args, char symbol) {
        return format(content, args, symbol, symbol);
    }

    /**
     * format str. <code>
     * User user = new User();
     * user.setName("yufei");
     * user.setAge(18);
     * Strings.format("my name is：{name}, i am {age} years old", user, '{', '}');
     * </code>
     *
     * @param  content     the content
     * @param  args        the args
     * @param  startSymbol the start symbol
     * @param  endSymbol   the end symbol
     * @return             the string
     */
    public static String format(String content, Object args, char startSymbol, char endSymbol) {
        return new StringFormatter(startSymbol, endSymbol).format(content, args);
    }

    /**
     * <p>
     * 文本替换
     * </p>
     * .
     *
     * @param  text         搜索的源
     * @param  searchString 搜索的内容
     * @param  replacement  替换的内容
     * @return              替换完成的文本, 如果传入null返回null
     */
    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    /**
     * null安全的判断两个字符串是否相等，如果两个都为null,返回true.
     *
     * @param  str1 字符串1
     * @param  str2 字符串2
     * @return      两个字符串是否相等
     */
    public static boolean isEquals(String str1, String str2) {
        if (str1 != null) {
            return str1.equals(str2);
        }
        if (str2 != null) {
            return str2.equals(str1);
        }
        return true;
    }

    /**
     * null安全的判断两个字符串是否相等，忽略大小写，如果两个都为null,返回true.
     *
     * @param  str1 字符串1
     * @param  str2 字符串2
     * @return      两个字符串是否相等
     */
    public static boolean isEqualsIgnoreCase(String str1, String str2) {
        if (str1 != null) {
            return str1.equalsIgnoreCase(str2);
        }
        if (str2 != null) {
            return str2.equalsIgnoreCase(str1);
        }
        return true;
    }

    /**
     * 使用给定的连接符连接传入的字符串, 如果传入的字符串为null或长度为0，则返回"".
     *
     * @param  str 需要连接的字符串
     * @param  num 连接次数
     * @return     String 形式为：str＋str＋...
     */
    public static String join(String str, int num) {
        return join(str, num, null);
    }

    /**
     * 使用给定的连接符连接传入的字符串, 如果传入的字符串为null或长度为0，则返回"".
     *
     * @param  str   需要连接的字符串
     * @param  num   连接次数
     * @param  delim 集合中的元素的分隔符。(null表示直接连接集合中的元素，不加入分割符)
     * @return       String 形式为：str＋delim＋str＋delim＋...
     */
    public static String join(String str, int num, String delim) {
        if (Lang.isEmpty(str)) {
            return EMPTY;
        }
        if (delim == null) {
            delim = EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(str);
            if (i < num - 1) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * 连接集合中的所有元素以创建一个String，其中集合中的元素间以指定的delim来分隔, 如果 集合为null或长度为0，则返回"".
     *
     * @param  collection 需要连接的String为元素的集合
     * @return            String 形式为：list的元素＋list的元素＋...
     */
    public static String join(Collection<String> collection) {
        return join(collection, EMPTY);
    }

    /**
     * 连接集合中的所有元素以创建一个String，其中集合中的元素间以指定的delim来分隔, 如果 集合为null或长度为0，则返回"".
     *
     * @param  collection 需要连接的String为元素的集合
     * @param  delim      集合中的元素的分隔符。(null表示直接连接集合中的元素，不加入分割符)
     * @return            String 形式为：list的元素＋delim＋list的元素＋delim＋...
     */
    public static String join(Collection<String> collection, String delim) {
        if (Lang.isEmpty(collection)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> iter = collection.iterator();
        while (iter.hasNext()) {
            String strTemp = pickFirst(iter.next(), Chars.EMPTY_STR);
            sb.append(strTemp);
            if (iter.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串. 如果数组为null或长度为0，则返回"".
     *
     * @param  array 字符串数组
     * @return       合并后的字符串
     */
    public static String join(String[] array) {
        return join(array, EMPTY);
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串. 如果数组为null或长度为0，则返回"".
     *
     * @param  array 字符串数组
     * @param  delim 分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return       合并后的字符串
     */
    public static String join(String[] array, String delim) {
        if (Lang.isEmpty(array)) {
            return "";
        }
        int length = array.length - 1;
        if (delim == null) {
            delim = "";
        }
        StringBuilder result = new StringBuilder(length * Unit.TEN);
        for (int i = 0; i < length; i++) {
            result.append(array[i]);
            result.append(delim);
        }
        result.append(array[length]);
        return result.toString();
    }

    /**
     * 以指定的delim分隔符分隔String，并将分隔的每一个String作为List的一个元素.
     *
     * @param  source 需要分隔的string
     * @param  delim  分隔String的符合 (null表示以空格作为分隔符)
     * @return        存储了分隔的子串的List
     */
    public static List<String> splitToList(String source, String delim) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, split(source, delim));
        return list;
    }

    /**
     * 此方法将给出的字符串source使用delim划分为单词数组.
     *
     * @param  source 需要进行划分的原字符串
     * @param  delim  单词的分隔字符串
     * @return        划分以后的数组，如果source或delim为null的时候返回以source为唯一元素的数组.
     */
    public static String[] split(String source, String delim) {
        String[] wordLists;
        if (source == null || delim == null) {
            wordLists = new String[1];
            wordLists[0] = source;
            return wordLists;
        }
        StringTokenizer st = new StringTokenizer(source, delim);
        int total = st.countTokens();
        wordLists = new String[total];
        for (int i = 0; i < total; i++) {
            wordLists[i] = st.nextToken();
        }
        return wordLists;
    }

    /**
     * 此方法将给出的字符串source使用逗号划分为单词数组.
     *
     * @param  source 需要进行划分的原字符串
     * @return        划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
     */
    public static String[] split(String source) {
        return split(source, Chars.COMMA);
    }

    /**
     * 将数转换成字符串数组,使用对象的toString方法.
     *
     * @param  array 数组
     * @return       转换后的字符串数组
     */
    public static String[] objectArrayToStringArray(Object[] array) {
        String[] strArray = null;
        if (array != null) {
            if (array.length < 1) {
                strArray = new String[0];
            } else if (array instanceof String[]) {
                strArray = (String[]) array;
            } else {
                strArray = new String[array.length];
                for (int i = 0; i < array.length; i++) {
                    Object object = array[i];
                    if (object == null) {
                        strArray[i] = null;
                    } else {
                        strArray[i] = object.toString();
                    }

                }
            }
        }
        return strArray;
    }

    /**
     * 删除String中的所有空格，并返回删除后的String.
     *
     * @param  str 需要进行删除操作的String
     * @return     String 删除空格后的String
     */
    public static String removeSpaces(String str) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                newString.append(str.charAt(i));
            }
        }
        return newString.toString();
    }

    /**
     * 判断一个String里是不是包含另一个String.
     *
     * @param  text         源文本
     * @param  searchString 包含的字符串
     * @return              是否包含
     */
    public static boolean contains(String text, String searchString) {
        return text.indexOf(searchString) != -1;
    }

    /**
     * 字符串数组中是否包含指定的字符串.大小写敏感
     *
     * @param  texts        源文本数组
     * @param  searchString 包含的字符串
     * @return              不包含，返回-1，如果包含，返回数组的下标数
     */
    public static int contains(String[] texts, String searchString) {
        return contains(texts, searchString, true);
    }

    /**
     * 不区分大小写判定字符串数组中是否包含指定的字符串。.
     *
     * @param  texts        源文本数组
     * @param  searchString 包含的字符串
     * @return              不包含，返回-1，如果包含，返回数组的下标数
     */
    public static int containsIgnoreCase(String[] texts, String searchString) {
        return contains(texts, searchString, false);
    }

    /*
     * 字符串数组中是否包含指定的字符串。
     * @param texts 源文本数组
     * @param searchString 包含的字符串
     * @param caseSensitive 是否大小写敏感
     * @return 不包含，返回-1，如果包含，返回数组的小标数
     */
    private static int contains(String[] texts, String searchString, boolean caseSensitive) {
        for (int i = 0; i < texts.length; i++) {
            if (caseSensitive) {
                if (texts[i].equals(searchString)) {
                    return i;
                }
            } else {
                if (texts[i].equalsIgnoreCase(searchString)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * use ISO8859-1 as the input str charset, encode it to UTF-8.
     *
     * @param  str encoding str
     * @return     UTF-8 str
     */
    public static String encode(String str) {
        return encode(str, StandardCharsets.UTF_8);
    }

    /**
     * 将一个字符串进行转码处理,使用系统默认字符集[{@link Charset#defaultCharset()}]解码并使用传入字符集进行编码.
     *
     * @param  str     输入字符串
     * @param  charset 输出字符转编码时使用的字符集
     * @return         输出转换后的字符串.
     */
    public static String encode(String str, Charset charset) {
        return encode(str, Charset.defaultCharset(), charset);
    }

    /**
     * 将一个字符串进行转码处理.
     *
     * @param  str                 输入字符串
     * @param  sourceDecodeCharset 输入字符串解码时使用的字符集
     * @param  encodeToCharset     输出字符转编码时使用的字符集
     * @return                     输出转换后的字符串.
     */
    public static String encode(String str, Charset sourceDecodeCharset, Charset encodeToCharset) {
        if (isEmpty(str)) {
            return str;
        }
        AssertIllegalArgument.isNotNull(sourceDecodeCharset, "sourceDecodeCharset");
        AssertIllegalArgument.isNotNull(encodeToCharset, "encodeToCharset");
        sourceDecodeCharset = Lang.ifNull(sourceDecodeCharset, StandardCharsets.UTF_8);
        encodeToCharset = Lang.ifNull(encodeToCharset, StandardCharsets.UTF_8);
        if (sourceDecodeCharset.equals(encodeToCharset)) {
            return str;
        }
        return new String(str.getBytes(sourceDecodeCharset), encodeToCharset);
    }

    /**
     * 将一个字符串进行转码处理.
     *
     * @param  str         输入字符串
     * @param  charsetName 输出字符转编码时使用的字符集
     * @return             输出转换后的字符串.
     */
    public static String encode(String str, String charsetName) {
        AssertIllegalArgument.isNotEmpty(charsetName, "charsetName");
        return encode(str, Charset.forName(charsetName));
    }

    /**
     * 将一个字符串进行转码处理.
     *
     * @param  str                     输入字符串
     * @param  sourceDecodeCharsetName 输入字符串解码时使用的字符集
     * @param  encodeToCharsetName     输出字符转编码时使用的字符集
     * @return                         输出转换后的字符串.
     */
    public static String encode(String str, String sourceDecodeCharsetName, String encodeToCharsetName) {
        AssertIllegalArgument.isNotNull(sourceDecodeCharsetName, "sourceDecodeCharsetName");
        AssertIllegalArgument.isNotNull(encodeToCharsetName, "encodeToCharsetName");
        return encode(str, Charset.forName(sourceDecodeCharsetName), Charset.forName(encodeToCharsetName));
    }

    /**
     * string2Unicode.
     *
     * @param  string string
     * @return        unicode_string
     */
    public static String stringToUnicode(String string) {
        StringBuilder unicode = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * unicode2String.
     *
     * @param  unicode unicode_string
     * @return         string
     */
    public static String unicodeToString(String unicode) {
        Matcher matcher = UNICODE_PATTERN.matcher(unicode);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), Unit.SIXTEEN);
            unicode = unicode.replace(matcher.group(1), ch + "");
        }
        return unicode;
    }

    /**
     * UTF-8编码.
     *
     * @param  str 输入字符串
     * @return     输出转换后的字符串.
     */
    public static String toUTF8(String str) {
        return encode(str, StandardCharsets.UTF_8);
    }

    /**
     * UTF-8编码.
     *
     * @param  str        输入字符串
     * @param  fromEncode 输入字符串解码时使用的字符集
     * @return            输出转换后的字符串.
     */
    public static String toUTF8(String str, String fromEncode) {
        return encode(str, fromEncode, StandardCharsets.UTF_8.displayName());
    }

    /**
     * 将字符串转换为整型数.
     *
     * @param  str          输入的字符串
     * @param  defaultValue 如果转换失败后的默认值
     * @return              字符串转换为的整数
     */
    public static int toInt(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为长整型数.
     *
     * @param  str          输入的字符串
     * @param  defaultValue 如果转换失败后的默认值
     * @return              字符串转换为的长整型数
     */
    public static long toLong(String str, long defaultValue) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为双精度数.
     *
     * @param  str          输入的字符串
     * @param  defaultValue 如果转换失败后的默认值
     * @return              字符串转换为的双精度数
     */
    public static double toDouble(String str, double defaultValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为单精度数.
     *
     * @param  str          输入的字符串
     * @param  defaultValue 如果转换失败后的默认值
     * @return              字符串转换为的单精度数
     */
    public static float toFloat(String str, float defaultValue) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取传入字符串的长度，null返回0.
     *
     * @param  str 字符串
     * @return     传入字符串的长度，null返回0
     */
    public static int getLength(String str) {
        return getLength(str, false);
    }

    /**
     * 获取传入字符串的长度，null返回0.
     *
     * @param  str             字符串
     * @param  needDeleteSpace 是否删除传入字符串的空字符串
     * @return                 传入字符串的长度，null返回0
     */
    public static int getLength(String str, boolean needDeleteSpace) {
        if (str == null) {
            return Chars.ZERO;
        }
        if (needDeleteSpace) {
            str = removeSpaces(str);
            return str.length();
        } else {
            return str.length();
        }
    }

    /**
     * 取字符串的前X个字符,X为传入参数.
     *
     * @param  str   字符串
     * @param  index 序号
     * @return       子串.
     */
    public static String substringBefore(String str, int index) {
        if (isEmpty(str)) {
            return str;
        }
        if (str.length() <= index) {
            return str;
        }
        return str.substring(0, index);
    }

    /**
     * 取字符串从x开始后的字符,X为传入参数.
     *
     * @param  str   字符串
     * @param  index 序号
     * @return       子串.
     */
    public static String substringAfter(String str, int index) {
        if (isEmpty(str)) {
            return str;
        }
        int length = str.length();
        if (length <= index) {
            return str;
        }
        return str.substring(index + 1, length);
    }

    /**
     * 取字符串的后X个字符,X为传入参数.
     *
     * @param  str    字符串
     * @param  length 长度
     * @return        子串.
     */
    public static String substringLast(String str, int length) {
        if (isEmpty(str)) {
            return str;
        }
        int len = str.length();
        if (len <= length) {
            return str;
        }
        return str.substring(len - length);
    }

    /**
     * 判断字符串是否以指定字符串开始,如果需要判断的源文本为null,返回false.
     *
     * @param  sourceString 源文本
     * @param  keyString    判断是否是起始的串
     * @return              字符串是否以指定字符串开始
     */
    public static boolean startsWith(String sourceString, String keyString) {
        if (sourceString == null) {
            return false;
        }
        return sourceString.startsWith(keyString);
    }

    /**
     * 判断字符串是否以指定字符串结束,如果需要判断的源文本为null,返回false.
     *
     * @param  sourceString 源文本
     * @param  keyString    判断是否是起始的串
     * @return              字符串是否以指定字符串结束
     */
    public static boolean endWith(String sourceString, String keyString) {
        if (sourceString == null) {
            return false;
        }
        return sourceString.endsWith(keyString);
    }

    /**
     * 追加字符串，如果字符串为null,忽略. 如果两个字符串都为null,返回null.
     *
     * @param  str1 原字符串
     * @param  str2 要追加的字符串
     * @return      追加后的字符串
     */
    public static String append(String str1, String str2) {
        if (str1 == null) {
            return str2;
        }
        if (str2 == null) {
            return str1;
        }
        return str1 + str2;
    }

    /**
     * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
     * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。.
     *
     * @param  source 带参数的原字符串
     * @param  values 替换用的字符串数组
     * @return        替换后的字符串
     */
    public static String getReplaceString(String source, String... values) {
        return getReplaceString(Chars.MOD, source, values);
    }

    /**
     * 将字符串中的变量使用values数组中的内容进行替换。 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。.
     *
     * @param  prefix 变量前缀字符串
     * @param  source 带参数的原字符串
     * @param  values 替换用的字符串数组
     * @return        替换后的字符串。 如果前缀为null则使用“%”作为前缀；
     *                如果source或者values为null或者values的长度为0则返回source；
     *                如果values的长度大于参数的个数，多余的值将被忽略；
     *                如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
     */
    public static String getReplaceString(String prefix, String source, String[] values) {
        String result = source;
        if (source == null || values == null || values.length < 1) {
            return source;
        }
        if (prefix == null) {
            prefix = Chars.MOD;
        }
        for (int i = 0; i < values.length; i++) {
            String argument = prefix + Integer.toString(i + 1);
            int index = result.indexOf(argument);
            if (index != -1) {
                String temp = result.substring(0, index);
                if (i < values.length) {
                    temp += values[i];
                } else {
                    temp += values[values.length - 1];
                }
                temp += result.substring(index + 2);
                result = temp;
            }
        }
        return result;
    }

    /**
     * 过滤&lt;, &gt;, " 等html字符的方法。.
     *
     * @param  str 需要过滤的字符串
     * @return     完成过滤以后的字符串
     */
    public static String filterHtml(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        StringBuilder html = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '&':
                    html.append("&amp;");
                    break;
                case '<':
                    html.append("&lt;");
                    break;
                case '>':
                    html.append("&gt;");
                    break;
                case '\'':
                    html.append("&#39;");
                    break;
                case '\"':
                    html.append("&quot;");
                    break;
                default:
                    html.append(c);
                    break;
            }
        }
        return html.toString();
    }

    /**
     * 还原成html.
     *
     * @param  str 需要转换的字符串
     * @return     完成转换的html
     */
    public static String toHtml(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return str;
        }
        return str.replace("&amp;", "&") //
            .replace("&lt;", "<") //
            .replace("&gt;", ">") //
            .replace("&#39;", "'") //
            .replace("&quot;", "\"");
    }

    /**
     * 为传入URI附加请求参数 .
     *
     * @param  uri   uri
     * @param  name  name
     * @param  value value
     * @return       附加请求参数后的URI
     */
    // TODO 是否应该移动到web
    public static String appendRequestParams(String uri, String name, String value) {
        if (Lang.isNotEmpty(uri)) {
            if (uri.contains(Chars.QUESTION)) {
                uri += Chars.AMP;
            } else {
                uri += Chars.QUESTION;
            }
            uri += name + "=" + value;
        }
        return uri;
    }
}
