
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
import java.util.List;
import java.util.Map;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.repository.Execution;
import cn.featherfly.common.repository.SimpleExecution;

/**
 * NamedParamSql.
 *
 * @author zhongj
 */
public class NamedParamSql {

    private final String sql;

    private final NamedParam[] paramNames;

    private final int inParamCount;

    /**
     * Instantiates a new pattern.
     *
     * @param sql the sql
     * @param paramNames the param names
     */
    NamedParamSql(String sql, NamedParam[] paramNames) {
        super();
        AssertIllegalArgument.isNotBlank(sql, "sql");
        AssertIllegalArgument.isNotNull(paramNames, "paramNames");
        this.sql = sql;
        this.paramNames = paramNames;
        inParamCount = Arrays.stream(this.paramNames).filter(pn -> pn.isIn()).mapToInt(value -> 1).sum();
    }

    /**
     * compile named param sql with {@link SqlUtils#PARAM_NAME_START_SYMBOL}.
     *
     * @param namedParamSql the named param sql
     * @return the named param sql
     * @see SqlUtils#convertNamedParamSql(String)
     */
    public static NamedParamSql compile(String namedParamSql) {
        return SqlUtils.convertNamedParamSql(namedParamSql);
    }

    /**
     * compile named param sql with startSymbol.
     *
     * @param namedParamSql the named param sql
     * @param startSymbol the start symbol
     * @return NamedParamSql
     * @see SqlUtils#convertNamedParamSql(String,char)
     */
    public static NamedParamSql compile(String namedParamSql, char startSymbol) {
        return SqlUtils.convertNamedParamSql(namedParamSql, startSymbol);
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
        final List<Tuple2<NamedParam, String>> inParams = new ArrayList<>(inParamCount);
        for (NamedParam pn : paramNames) {
            Object param = SqlUtils.getNamedParam(params, pn.name);
            if (pn.isIn()) {
                inParams.add(Tuples.of(pn, SqlUtils.addParam(param, paramList, pn.isIn())));
            } else {
                SqlUtils.addParam(param, paramList);
            }
        }

        if (inParamCount > 0) {
            StringBuilder sql = new StringBuilder(this.sql);
            for (int i = inParams.size() - 1; i >= 0; i--) {
                Tuple2<NamedParam, String> tuple = inParams.get(i);
                sql.insert(tuple.get0().in, tuple.get1());
            }
            return new SimpleExecution(sql.toString(), paramList.toArray(new Serializable[paramList.size()]));
        } else {
            return new SimpleExecution(sql, paramList.toArray(new Serializable[paramList.size()]));
        }
    }

    /**
     * get paramNames value.
     *
     * @return paramNames
     */
    public NamedParam[] getParamNames() {
        return paramNames;
    }

    /**
     * get sql value
     *
     * @return sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * The Class NamedParam.
     */
    public static class NamedParam {

        private final String name;

        private final int in;

        /**
         * Instantiates a new named param.
         *
         * @param name the name
         */
        public NamedParam(String name) {
            this(name, -1);
        }

        /**
         * Instantiates a new named param.
         *
         * @param name the name
         * @param in the in
         */
        public NamedParam(String name, int in) {
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
         * Checks if is in.
         *
         * @return in
         */
        public boolean isIn() {
            return in >= 0;
        }

        /**
         * get in value.
         *
         * @return in
         */
        public int getIn() {
            return in;
        }
    }
}
