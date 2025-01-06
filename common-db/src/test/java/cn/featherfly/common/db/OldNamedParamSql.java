
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-05 02:56:05
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.repository.Execution;
import cn.featherfly.common.repository.SimpleExecution;

/**
 * NamedParamSql.
 *
 * @author zhongj
 */
public class OldNamedParamSql {

    private final String sql;

    private final OldNamedParam[] paramNames;

    private final int inParamCount;

    /**
     * Instantiates a new pattern.
     *
     * @param sql the sql
     * @param paramNames the param names
     */
    OldNamedParamSql(String sql, OldNamedParam[] paramNames) {
        super();
        AssertIllegalArgument.isNotBlank(sql, "sql");
        AssertIllegalArgument.isNotNull(paramNames, "paramNames");
        this.sql = sql;
        this.paramNames = paramNames;
        inParamCount = Arrays.stream(this.paramNames).filter(pn -> pn.in).mapToInt(value -> 1).sum();
    }

    /**
     * compile named param sql with {@link SqlUtils#PARAM_NAME_START_SYMBOL}.
     *
     * @param namedParamSql the named param sql
     * @return the named param sql
     * @see SqlUtils#convertNamedParamSql(String)
     */
    public static OldNamedParamSql compile(String namedParamSql) {
        return OldSqlUtils.convertNamedParamSql(namedParamSql);
    }

    /**
     * compile named param sql with startSymbol.
     *
     * @param namedParamSql the named param sql
     * @param startSymbol the start symbol
     * @return NamedParamSql
     * @see SqlUtils#convertNamedParamSql(String,char)
     */
    public static OldNamedParamSql compile(String namedParamSql, char startSymbol) {
        return OldSqlUtils.convertNamedParamSql(namedParamSql, startSymbol);
    }

    /**
     * compile named param sql with startSymbol and endSymbol.
     *
     * @param namedParamSql the named param sql
     * @param startSymbol the start symbol
     * @param endSymbol the end symbol
     * @return NamedParamSql
     * @see SqlUtils#convertNamedParamSql(String, char, Character)
     */
    public static NamedParamSql compile(String namedParamSql, char startSymbol, Character endSymbol) {
        return SqlUtils.convertNamedParamSql(namedParamSql, startSymbol, endSymbol);
    }

    /**
     * Gets the execution.
     *
     * @param params the params
     * @return the Execution
     */
    public Execution getExecution(Map<String, Serializable> params) {
        List<Serializable> paramList = new ArrayList<>();
        final Map<String, Object> inParamsSqls = new HashMap<>(inParamCount);
        for (OldNamedParam pn : paramNames) {
            Object param = SqlUtils.getNamedParam(params, pn.name);
            if (pn.in) {
                inParamsSqls.put(pn.name, SqlUtils.addParam(param, paramList, pn.in));
            } else {
                SqlUtils.addParam(param, paramList);
            }
        }

        if (inParamCount > 0) {
            return new SimpleExecution(Str.format(sql, inParamsSqls), paramList.toArray());
        } else {
            return new SimpleExecution(sql, paramList.toArray());
        }
    }

    /**
     * get sql value.
     *
     * @return sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * get paramNames value.
     *
     * @return paramNames
     */
    public OldNamedParam[] getParamNames() {
        return paramNames;
    }

    /**
     * The Class NamedParam.
     */
    public static class OldNamedParam {

        private final String name;

        private final boolean in;

        /**
         * Instantiates a new named param.
         *
         * @param name the name
         */
        public OldNamedParam(String name) {
            this(name, false);
        }

        /**
         * Instantiates a new named param.
         *
         * @param name the name
         * @param in the in
         */
        public OldNamedParam(String name, boolean in) {
            super();
            this.name = name;
            this.in = in;
        }

        /**
         * get name value.
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * get in value.
         *
         * @return in
         */
        public boolean isIn() {
            return in;
        }
    }
}
