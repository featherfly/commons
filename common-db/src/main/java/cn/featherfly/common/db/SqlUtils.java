
package cn.featherfly.common.db;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * Sql的相关工具类
 * </p>
 * 
 * @author zhongj
 */
public final class SqlUtils {

    private static final Pattern SELECT_PATTERN = Pattern.compile("((select )(distinct [\\w-_.]+)?,?.+)(from .+)",
            Pattern.CASE_INSENSITIVE);

    //	private static final Pattern SELECT_PATTERN = 
    //	        Pattern.compile("((select )(distinct [\\w-_.]+)?,?.+)(from +[\\w-_.]+(( as)? +[\\w-_.&&[^where]]+)?)( +where +[\\w-_.]+ ?\\W ?[\\w-_.]+)?( +order +by +[\\w-_.]+ (desc|asc))?.?" 
    //	                , Pattern.CASE_INSENSITIVE);

    private SqlUtils() {
    }

    /**
     * <p>
     * 转换查询sql为统计sql
     * </p>
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
}
