
package cn.featherfly.common.lang.string;

import java.util.Map;
import java.util.function.Function;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.lang.Lang;

/**
 * StringFormatter.
 *
 * @author zhongj
 */
public class StringFormatter {

    private char startSymbol;

    private char endSymbol;

    private boolean placeholderAutoIndex; //

    /**
     * Instantiates a new custom string formatter.
     *
     * @param startSymbol the start symbol
     * @param endSymbol   the end symbol
     */
    public StringFormatter(char startSymbol, char endSymbol) {
        this(startSymbol, endSymbol, false);
    }

    /**
     * Instantiates a new custom string formatter.
     *
     * @param startSymbol          the start symbol
     * @param endSymbol            the end symbol
     * @param placeholderAutoIndex the placeholder auto index <br/>
     *                             if true auto add index to params placeholder,
     *                             such as: <br/>
     *                             "hello {} at {} from [{1}] at {}" <br/>
     *                             "hello {0} at {1} from [{1}] at {2}" <br/>
     */
    public StringFormatter(char startSymbol, char endSymbol, boolean placeholderAutoIndex) {
        super();
        this.startSymbol = startSymbol;
        this.endSymbol = endSymbol;
        this.placeholderAutoIndex = placeholderAutoIndex;
    }

    /**
     * 返回startSymbol.
     *
     * @return startSymbol
     */
    public char getStartSymbol() {
        return startSymbol;
    }

    /**
     * 返回endSymbol.
     *
     * @return endSymbol
     */
    public char getEndSymbol() {
        return endSymbol;
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * StringFormatter formatter = new StringFormatter('{', '}');
     * formatter.format("my name is {0}, i am {1} years old", new Object[] { "yufei", 18 });
     * </pre>
     *
     * </blockquote>
     *
     * @param str  format string
     * @param args format args
     * @return formated str
     */
    public String format(String str, Object... args) {
        return format(str, startSymbol, endSymbol, Lang.toMapStringKey(args));
    }

    /**
     * format str. <blockquote>
     *
     * <pre>
     * StringFormatter formatter = new StringFormatter('{', '}');
     * formatter.format("my name is {name}, i am {age} years old",
     *         new HashChainMap&lt;String, Object&gt;().putChain("name", "yufei").putChain("age", 18));
     * </pre>
     *
     * </blockquote>
     *
     * @param str  format string
     * @param args format args
     * @return formated str
     */
    public String format(String str, Map<String, Object> args) {
        return format(str, startSymbol, endSymbol, args);
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
     * @param <O>  the generic type
     * @param str  format string
     * @param args format args
     * @return formated str
     */
    public <O extends Object> String format(String str, O args) {
        return format(str, startSymbol, endSymbol, args);
    }

    private String format(String str, char startSymbol, char endSymbol, Object args) {
        if (Lang.isEmpty(str)) {
            return str;
        }
        int autoIndex = 0;
        Function<String, Object> getParam;
        if (args instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> argsMap = (Map<String, Object>) args;
            getParam = (name) -> argsMap.get(name);
        } else {
            getParam = (name) -> BeanUtils.getProperty(args, name);
        }

        StringBuilder sb = new StringBuilder(str);
        int nameStartIndex = -1;
        int nameEndIndex = -1;
        boolean isEnd = false;
        boolean sameStartEnd = startSymbol == endSymbol;
        for (int index = 0; index < sb.length(); index++) {
            char c = sb.charAt(index);
            isEnd = index == sb.length() - 1;
            if (nameStartIndex == -1) {
                if (startSymbol == c) {
                    if (!isEnd && sb.charAt(index + 1) == startSymbol) {
                        sb.deleteCharAt(index);
                    } else {
                        nameStartIndex = index;
                    }
                    continue;
                }
                if (c == endSymbol) {
                    if (!isEnd && sb.charAt(index + 1) == endSymbol) {
                        sb.deleteCharAt(index);
                    }
                }
            }
            if (nameStartIndex >= 0) {
                if (startSymbol == c && !sameStartEnd) {
                    if (!isEnd && sb.charAt(index + 1) == startSymbol) {
                        sb.deleteCharAt(index);
                    } else {
                        nameStartIndex = index;
                    }
                    continue;
                }
                if (c == endSymbol || isEnd) {
                    nameEndIndex = index;
                    String name = sb.substring(nameStartIndex + 1, nameEndIndex);
                    if (placeholderAutoIndex && Lang.isEmpty(name)) {
                        name = autoIndex + "";
                        autoIndex++;
                    }
                    nameEndIndex++;
                    sb.insert(nameEndIndex, getParam.apply(name));
                    sb.delete(nameStartIndex, nameEndIndex);
                    index -= nameEndIndex - nameStartIndex - 1;
                    // 查找name完成，start index 重置
                    nameStartIndex = -1;
                    nameEndIndex = -1;
                }
            }
        }
        return sb.toString();
    }
}
