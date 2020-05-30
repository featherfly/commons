
package cn.featherfly.common.db.builder;

import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * 条件建造者工具
 * </p>
 *
 * @author zhongj
 */
public class BuilderUtils {

    /**
     * <p>
     * link sql part
     * </p>
     *
     * @param sqlPart      sql part
     * @param linkSqlParts to link sql parts
     * @return linked sqlPart
     */
    public static String link(String sqlPart, String... linkSqlParts) {
        for (String appendCondition : linkSqlParts) {
            sqlPart = link(sqlPart, appendCondition);
        }
        return sqlPart;
    }

    /**
     * <p>
     * link sql part
     * </p>
     *
     * @param sqlPart     sql part
     * @param linkSqlPart to link sql part
     * @return linked sqlPart
     */
    public static String link(String sqlPart, String linkSqlPart) {
        if (Lang.isEmpty(sqlPart) && Lang.isEmpty(linkSqlPart)) {
            return "";
        } else if (Lang.isEmpty(sqlPart) && Lang.isNotEmpty(linkSqlPart)) {
            return linkSqlPart.trim();
        } else if (Lang.isNotEmpty(sqlPart) && Lang.isEmpty(linkSqlPart)) {
            return sqlPart.trim();
        } else if (sqlPart.charAt(sqlPart.length() - 1) == ' ') {
            return sqlPart + linkSqlPart.trim();
        } else {
            return sqlPart + " " + linkSqlPart.trim();
        }
    }

    /**
     * <p>
     * link sql part
     * </p>
     *
     * @param sqlPart      sql part
     * @param linkSqlParts to link sql part
     */
    public static void link(StringBuilder sqlPart, String... linkSqlParts) {
        for (String appendCondition : linkSqlParts) {
            link(sqlPart, appendCondition);
        }
    }

    /**
     * <p>
     * 条件添加工具
     * </p>
     *
     * @param sqlPart     sql part
     * @param linkSqlPart to link sql part
     */
    public static void link(StringBuilder sqlPart, String linkSqlPart) {
        if (sqlPart != null && linkSqlPart != null) {
            if (sqlPart.length() == 0) {
                sqlPart.append(linkSqlPart.trim());
            } else if (sqlPart.charAt(sqlPart.length() - 1) == ' ') {
                sqlPart.append(linkSqlPart.trim());
            } else {
                sqlPart.append(" ").append(linkSqlPart.trim());
            }
        }
    }
}
