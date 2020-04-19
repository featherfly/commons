
package cn.featherfly.common.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.repository.Execution;
import cn.featherfly.common.repository.SimpleExecution;

/**
 * <p>
 * Sql的相关工具类
 * </p>
 * .
 *
 * @author zhongj
 */
public final class SqlUtils {

    /** The Constant PARAM_NAME_START_SYMBOL. */
    public static final char PARAM_NAME_START_SYMBOL = Chars.COLON_CHAR;

    /** The Constant SELECT_PATTERN. */
    private static final Pattern SELECT_PATTERN = Pattern.compile("((select )(distinct [\\w-_.]+)?,?.+)(from .+)",
            Pattern.CASE_INSENSITIVE);

    //	private static final Pattern SELECT_PATTERN =
    //	        Pattern.compile("((select )(distinct [\\w-_.]+)?,?.+)(from +[\\w-_.]+(( as)? +[\\w-_.&&[^where]]+)?)( +where +[\\w-_.]+ ?\\W ?[\\w-_.]+)?( +order +by +[\\w-_.]+ (desc|asc))?.?"
    //	                , Pattern.CASE_INSENSITIVE);

    /**
     * Instantiates a new sql utils.
     */
    private SqlUtils() {
    }

    /**
     * <p>
     * 转换查询sql为统计sql
     * </p>
     * .
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
        if (LangUtils.isEmpty(distinctColumn)) {
            countSql.append("*");
        } else {
            countSql.append(distinctColumn);
        }
        return countSql.append(") ").append(fromSql).toString();
    }

    /**
     * <p>
     * 将字符串转义.会将\ ' "转义为\\ \' \"
     * </p>
     *
     * @param str str
     * @return 转义后的字符串
     */
    public static String transferStringForSql(String str) {
        if (LangUtils.isEmpty(str)) {
            return str;
        }
        return str.replaceAll("[\\\\\'\"]", "\\\\$0");
    }

    /**
     * Transfer named param sql with {@link #PARAM_NAME_START_SYMBOL}.
     *
     * @param namedParamSql the named param sql
     * @param params        the params
     * @return the execution
     */
    public static Execution transferNamedParamSql(String namedParamSql, Map<String, Object> params) {
        return transferNamedParamSql(namedParamSql, params, PARAM_NAME_START_SYMBOL);
    }

    /**
     * Transfer named param sql.
     *
     * @param namedParamSql the named param sql
     * @param params        the params
     * @param startSymbol   the start symbol
     * @return the execution
     */
    public static Execution transferNamedParamSql(String namedParamSql, Map<String, Object> params, char startSymbol) {
        return transferNamedParamSql(namedParamSql, params, startSymbol, null);
    }

    /**
     * Transfer named param sql.
     *
     * @param namedParamSql the named param sql
     * @param params        the params
     * @param startSymbol   the start symbol
     * @param endSymbol     the end symbol
     * @return the execution
     */
    public static Execution transferNamedParamSql(String namedParamSql, Map<String, Object> params, char startSymbol,
            Character endSymbol) {
        AssertIllegalArgument.isNotEmpty(namedParamSql, "namedParamSql");
        AssertIllegalArgument.isNotEmpty(startSymbol, "startSymbol");
        List<Object> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(namedParamSql);
        int nameStartIndex = -1;
        int nameEndIndex = -1;
        boolean emptySymbol = LangUtils.isEmpty(endSymbol);
        char end = emptySymbol ? Chars.SPACE_CHAR : endSymbol;
        boolean isEnd = false;
        for (int index = 0; index < sql.length(); index++) {
            char c = sql.charAt(index);
            if (startSymbol == c) {
                nameStartIndex = index;
            }
            if (nameStartIndex > 0) {
                isEnd = index == sql.length() - 1;
                if (c == end || isEnd) {
                    nameEndIndex = index;
                    if (isEnd && emptySymbol) {
                        nameEndIndex++;
                    }
                    String name = sql.substring(nameStartIndex + 1, nameEndIndex);
                    Object param = getNamedParam(params, name);
                    list.add(param);
                    if (!emptySymbol) {
                        nameEndIndex++;
                    }
                    sql.insert(nameEndIndex, Chars.QUESTION_CHAR);
                    sql.delete(nameStartIndex, nameEndIndex);
                    index -= nameEndIndex - nameStartIndex - 1;
                    // 查找name完成，start index 重置
                    nameStartIndex = -1;
                    nameEndIndex = -1;
                }
            }
        }
        return new SimpleExecution(sql.toString(), list.toArray());
    }

    /**
     * Gets the named param.
     *
     * @param params the params
     * @param name   the name
     * @return the named param
     */
    private static Object getNamedParam(Map<String, Object> params, String name) {
        Object param = params.get(name);
        if (param == null) {
            throw new JdbcException("no param found for name -> " + name);
        }
        return param;
    }
}
