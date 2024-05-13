
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-12 16:12:12
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db;

import org.testng.annotations.Test;

/**
 * Perf.
 *
 * @author zhongj
 */
public class Perf {

    int max = 1000000;

    String sql = "select id, name, age from `user` where id = ?";

    int columnNameIndex[] = new int[] { 7, 11, 17, 21 };
    int tableNameEndIndex = 36;

    private String cacheIndex(String sql, String alias) {
        StringBuilder sb = new StringBuilder(sql);
        sb.insert(tableNameEndIndex, " " + alias);
        String columnPrefix = alias + ".";
        for (int i = columnNameIndex.length - 1; i > -1; i--) {
            sb.insert(columnNameIndex[i], columnPrefix);
        }
        return sb.toString();
    }

    private static final String SELECT = "select";

    private static final char[] SELECT_CHARS = new char[] { 's', 'e', 'l', 'e', 'c', 't', 'S', 'E', 'L', 'E', 'C',
            'T' };

    private static final int SELECT_LEN = SELECT.length();

    private static final String FROM = "select";

    private static final char[] FROM_CHARS = new char[] { 's', 'e', 'l', 'e', 'c', 't', 'S', 'E', 'L', 'E', 'C', 'T' };

    private static final int FROM_LEN = SELECT.length();

    private boolean isSelect(String sql, int index, StringBuilder newSql) {
        for (int j = 1; j < SELECT_LEN; j++) {
            char cc = sql.charAt(index + j);
            if (cc == SELECT_CHARS[j] || cc == SELECT_CHARS[j + SELECT_LEN]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isFrom(String sql, int index, StringBuilder newSql) {
        for (int j = 1; j < SELECT_LEN; j++) {
            char cc = sql.charAt(index + j);
            newSql.append(cc);
            if (cc == SELECT_CHARS[j] || cc == SELECT_CHARS[j + SELECT_LEN]) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    //    private boolean isSpaceOrNewLine(char c) {
    //        return c == ' ' || c == '\n' || c == '\t';
    //    }
    //    private void appendAlias(StringBuilder sql, String alias, String originalSql, int index) {
    //
    //    }
    //
    //    private String applyTableAlias(String sql, String tableAlias) {
    //        char c = '\0';
    //        boolean afterSelect = false;
    //        StringBuilder newSql = new StringBuilder();
    //        boolean letterStart = false;
    //        for (int i = 0; i < sql.length(); i++) {
    //            c = sql.charAt(i);
    //            newSql.append(c);
    //            if ((c == 's' || c == 'S') && i + SELECT_LEN < sql.length()) {
    //                afterSelect = isSelect(sql, i, newSql) && isSpaceOrNewLine(sql.charAt(i + SELECT_LEN + 1));
    //                i = i + SELECT_LEN + 1;
    //                i = applySelectPart(sql, tableAlias, i, newSql);
    //                continue;
    //            }
    //
    //            if (afterSelect) {
    //                if (Character.isLetter(c)) {
    //                    letterStart = true;
    //                }
    //            }
    //        }
    //    }
    //
    //    private int wordEnd(String sql, String tableAlias, int startIndex, StringBuilder newSql) {
    //        int i = startIndex;
    //        newSql.append(tableAlias).append(".");
    //        for (; i < sql.length(); i++) {
    //            char c = sql.charAt(i);
    //            newSql.append(c);
    //            if (SqlUtils.isSqlWordSplitChar(c)) {
    //                return i + 1;
    //            }
    //        }
    //        return i;
    //    }
    //
    //    private int applyWord(String sql, String tableAlias, int startIndex, StringBuilder newSql) {
    //        int i = startIndex;
    //        i = wordEnd(sql, tableAlias, startIndex, newSql);
    //        for (; i < sql.length(); i++) {
    //            char c = sql.charAt(i);
    //            if (Character.isLetter(c)) {
    //                i = wordEnd(sql, tableAlias, startIndex, newSql);
    //            } else {
    //                newSql.append(c);
    //            }
    //        }
    //    }
    //
    //    private int applySelectPart(String sql, String tableAlias, int startIndex, StringBuilder newSql) {
    //        int i = startIndex;
    //        for (; i < sql.length(); i++) {
    //            char c = sql.charAt(i);
    //            if (Character.isLetter(c)) {
    //                applyWord(sql, tableAlias, i, newSql);
    //            } else {
    //                newSql.append(c);
    //            }
    //        }
    //        return i;
    //    }

    @Test
    void loopCached() {
        for (int i = 0; i < max; i++) {
            cacheIndex(sql, "u");
        }
    }

    @Test
    void loopApply() {
        for (int i = 0; i < max; i++) {

        }
    }

}
