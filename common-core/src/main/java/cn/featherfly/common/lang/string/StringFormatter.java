
package cn.featherfly.common.lang.string;

import java.util.Map;

import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * JdkStringFormatter
 * </p>
 * .
 *
 * @author zhongj
 */
public class StringFormatter {

    private char startSymbol;

    private char endSymbol;

    /**
     * Instantiates a new custom string formatter.
     *
     * @param startSymbol the start symbol
     * @param endSymbol   the end symbol
     */
    public StringFormatter(char startSymbol, char endSymbol) {
        super();
        this.startSymbol = startSymbol;
        this.endSymbol = endSymbol;
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
     * format str. <code>
     * StringFormatter formatter = new StringFormatter('{', '}');
     * formatter.format("my name is {0}, i am {1} years old", new Object[]{"yufei", 18});
     * </code>
     *
     * @param str  format string
     * @param args format args
     * @return formated str
     */
    public String format(String str, Object... args) {
        return format(str, startSymbol, endSymbol, Lang.toMapStringKey(args));
    }

    /**
     * format str. <code>
     * StringFormatter formatter = new StringFormatter('{', '}');
     * formatter.format("my name is {0}, i am {1} years old", new HashChainMap&lt;String,
     * Object&gt;().putChain("name", "yufei").putChain("age", 18));
     * </code>
     *
     * @param str  format string
     * @param args format args
     * @return formated str
     */
    public String format(String str, Map<String, Object> args) {
        return format(str, startSymbol, endSymbol, args);
    }

    private String format(String str, char startSymbol, char endSymbol, Map<String, Object> args) {
        if (Lang.isEmpty(str)) {
            return str;
        }
        StringBuilder sql = new StringBuilder(str);
        int nameStartIndex = -1;
        int nameEndIndex = -1;
        boolean isEnd = false;
        for (int index = 0; index < sql.length(); index++) {
            char c = sql.charAt(index);
            isEnd = index == sql.length() - 1;
            if (nameStartIndex == -1) {
                if (startSymbol == c) {
                    if (!isEnd && sql.charAt(index + 1) == startSymbol) {
                        sql.deleteCharAt(index);
                    } else {
                        nameStartIndex = index;
                    }
                    continue;
                }
                if (c == endSymbol) {
                    if (!isEnd && sql.charAt(index + 1) == endSymbol) {
                        sql.deleteCharAt(index);
                    }
                }
            }
            if (nameStartIndex > 0) {
                if (c == endSymbol || isEnd) {
                    nameEndIndex = index;
                    String name = sql.substring(nameStartIndex + 1, nameEndIndex);
                    nameEndIndex++;
                    sql.insert(nameEndIndex, args.get(name));
                    sql.delete(nameStartIndex, nameEndIndex);
                    index -= nameEndIndex - nameStartIndex - 1;
                    // 查找name完成，start index 重置
                    nameStartIndex = -1;
                    nameEndIndex = -1;
                }
            }
        }
        return sql.toString();
    }
}
