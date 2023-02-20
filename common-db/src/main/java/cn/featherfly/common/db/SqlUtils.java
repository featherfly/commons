
package cn.featherfly.common.db;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Execution;
import cn.featherfly.common.repository.SimpleExecution;

/**
 * Sql的相关工具类.
 *
 * @author zhongj
 */
public final class SqlUtils {

    /** The Constant PARAM_NAME_START_SYMBOL. */
    public static final char PARAM_NAME_START_SYMBOL = Chars.COLON_CHAR;

    /** The Constant SELECT_PATTERN. */
    private static final Pattern SELECT_PATTERN = Pattern.compile("((select )(distinct [\\w-_.]+)?,?.+)(from .+)",
            Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

    //	private static final Pattern SELECT_PATTERN =
    //	        Pattern.compile("((select )(distinct [\\w-_.]+)?,?.+)(from +[\\w-_.]+(( as)? +[\\w-_.&&[^where]]+)?)( +where +[\\w-_.]+ ?\\W ?[\\w-_.]+)?( +order +by +[\\w-_.]+ (desc|asc))?.?"
    //	                , Pattern.CASE_INSENSITIVE);

    /**
     * Instantiates a new sql utils.
     */
    private SqlUtils() {
    }

    /**
     * 转换查询sql为统计sql.
     *
     * @param sql sql
     * @return 统计sql
     */
    public static String convertSelectToCount(String sql) {
        sql = sql.trim();
        Matcher m = SELECT_PATTERN.matcher(sql);
        if (!m.matches()) {
            throw new IllegalArgumentException("[" + sql + "] " + "不是查询SQL , 查询SQL应该是这样?[select xx from xxx ...]");
        }
        StringBuilder countSql = new StringBuilder("SELECT COUNT(");
        String fromSql = sql.substring(m.group(1).length());
        String distinctColumn = null;
        int groupThree = Chars.THREE;
        if (m.group(groupThree) != null) {
            distinctColumn = sql.substring(m.group(2).length(), m.group(2).length() + m.group(groupThree).length());
        }
        if (Lang.isEmpty(distinctColumn)) {
            countSql.append("*");
        } else {
            countSql.append(distinctColumn);
        }
        return countSql.append(") ").append(fromSql).toString();
    }

    /**
     * 将字符串转义.会将\ ' "转义为\\ \' \".
     *
     * @param str str
     * @return 转义后的字符串
     */
    public static String transferStringForSql(String str) {
        if (Lang.isEmpty(str)) {
            return str;
        }
        return str.replaceAll("[\\\\\'\"]", "\\\\$0");
    }

    /**
     * convert named param sql with {@link #PARAM_NAME_START_SYMBOL}.
     * <p>
     * transfer <code>select * from user where name = :user</code> to
     * <code>select * from user where name = ?</code>
     * <p>
     *
     * @param namedParamSql the named param sql
     * @param params        the params
     * @return the execution
     */
    public static Execution convertNamedParamSql(String namedParamSql, Map<String, Object> params) {
        return convertNamedParamSql(namedParamSql, params, PARAM_NAME_START_SYMBOL);
    }

    /**
     * convert named param sql.
     *
     * @param namedParamSql the named param sql
     * @param params        the params
     * @param startSymbol   the start symbol
     * @return the execution
     */
    public static Execution convertNamedParamSql(String namedParamSql, Map<String, Object> params, char startSymbol) {
        return convertNamedParamSql(namedParamSql, params, startSymbol, null);
    }

    /**
     * convert named param sql.
     *
     * @param namedParamSql the named param sql
     * @param params        the params
     * @param startSymbol   the start symbol
     * @param endSymbol     the end symbol
     * @return the execution
     */
    public static Execution convertNamedParamSql(String namedParamSql, Map<String, Object> params, char startSymbol,
            Character endSymbol) {
        AssertIllegalArgument.isNotNull(namedParamSql, "namedParamSql");
        AssertIllegalArgument.isNotEmpty(startSymbol, "startSymbol");

        StringBuilder sql = new StringBuilder();
        List<Object> paramList = new ArrayList<>();

        int nameStartIndex = -1;
        int nameEndIndex = -1;
        boolean emptySymbol = Lang.isEmpty(endSymbol);
        char end = emptySymbol ? Chars.SPACE_CHAR : endSymbol;
        boolean isEnd = false;
        boolean isEndSign = false;
        boolean isSplitSymbol = false;
        for (int index = 0; index < namedParamSql.length(); index++) {
            char c = namedParamSql.charAt(index);
            isSplitSymbol = isSqlWordSplitChar(c);

            if (startSymbol == c) {
                nameStartIndex = index;
            }
            if (nameStartIndex > 0) {
                isEnd = index == namedParamSql.length() - 1;

                isEndSign = c == end || isSplitSymbol;
                if (isEndSign || isEnd) {
                    nameEndIndex = index;
                    if (!isEndSign && isEnd && emptySymbol) {
                        nameEndIndex++;
                    }
                    String name = namedParamSql.substring(nameStartIndex + 1, nameEndIndex);
                    Object param = getNamedParam(params, name);

                    if (param == null) {
                        paramList.add(param);
                        sql.append(Chars.QUESTION_CHAR);
                    } else if (param instanceof Collection) {
                        paramList.addAll((Collection<?>) param);
                        setParams(sql, ((Collection<?>) param).size(), namedParamSql, nameStartIndex);
                    } else if (param.getClass().isArray()) {
                        int length = Array.getLength(param);
                        for (int i = 0; i < length; i++) {
                            paramList.add(Array.get(param, i));
                        }
                        setParams(sql, length, namedParamSql, nameStartIndex);
                    } else {
                        paramList.add(param);
                        sql.append(Chars.QUESTION_CHAR);
                    }
                    if (!emptySymbol) {
                        nameEndIndex++;
                    }
                    if (isSplitSymbol) {
                        sql.append(c);
                    }
                    nameStartIndex = -1;
                    nameEndIndex = -1;
                }
            } else {
                sql.append(c);
            }
        }
        return new SimpleExecution(sql.toString(), paramList.toArray());
    }

    private static void setParams(StringBuilder sql, int length, String namedParamSql, int nameStartIndex) {
        if (isInCondition(namedParamSql, nameStartIndex)) {
            sqlInParams(sql, length);
        } else {
            sql.append(Chars.QUESTION_CHAR);
        }
    }

    private static boolean isInCondition(String namedParamSql, int nameStartIndex) {
        int endIndex = -1;
        for (int i = nameStartIndex; i >= 0; i--) {
            char c = namedParamSql.charAt(i);
            if (isSqlWordSplitChar(c)) {
                if (endIndex < 0) {
                    endIndex = i;
                } else {
                    return "in".equalsIgnoreCase(namedParamSql.substring(i + 1, endIndex).trim());
                }
            }
        }
        return false;
    }

    private static void sqlInParams(StringBuilder sql, int size) {
        sql.append(Chars.PAREN_L_CHAR);
        for (int i = 0; i < size; i++) {
            sql.append(Chars.QUESTION_CHAR).append(Chars.COMMA_CHAR);
        }
        if (sql.length() > 0) {
            sql.deleteCharAt(sql.length() - 1);
        }
        sql.append(Chars.PAREN_R_CHAR);
    }

    private static boolean isSqlWordSplitChar(char c) {
        return c == Chars.SPACE_CHAR || c == Chars.NEW_LINE_CHAR || c == Chars.COMMA_CHAR || c == Chars.PAREN_R_CHAR
                || c == Chars.TAB_CHAR;
        //                || c == ')'
    }

    /**
     * Gets the named param.
     *
     * @param params the params
     * @param name   the name
     * @return the named param
     */
    private static Object getNamedParam(Map<String, Object> params, String name) {
        if (params.containsKey(name)) {
            return params.get(name);
        } else {
            throw new JdbcException("no param found for name -> " + name);
        }
    }
}
