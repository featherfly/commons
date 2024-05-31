/*
 * All rights Reserved, Designed By zhongj
 * @Title: SqlUtils.java
 * @Package cn.featherfly.common.db
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2024年5月17日 下午5:41:18
 * @version V1.0
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */

package cn.featherfly.common.db;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.speedment.common.tuple.MutableTuples;
import com.speedment.common.tuple.mutable.MutableTuple1;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.NamedParamSql.NamedParam;
import cn.featherfly.common.db.mapping.JdbcPropertyMapping;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.CollectionUtils;
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

    private static final NamedSqlConvertFeature NAMEDSQL_CONVERTOR = new NamedSqlConvertFeature();

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
     * Flat params. if item of the param array is array or Collection, will expand them and
     * Sequential insertion.
     *
     * <pre>
     * give ["a", "b", ["1", "2"], "c"] return ["a", "b", "1", "2", "c"]
     * </pre>
     *
     * @param params the params
     * @return the flat object[]
     */
    public static Object[] flatParams(Object... params) {
        if (params == null) {
            return ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        final List<Object> paramList = new ArrayList<>();
        for (Object param : params) {
            Lang.eachObj(param, (Consumer<Object>) paramList::add);
        }
        return paramList.toArray();
    }

    /**
     * Flat params. if item of the param array is array or Collection, will expand them and
     * Sequential insertion.
     *
     * <pre>
     * give ["a", "b", ["1", "2"], "c"] return ["a", "b", "1", "2", "c"]
     * </pre>
     *
     * @param param the param
     * @param propertyMapping the property mapping
     * @return the flat FieldValueOperator Array or FieldValueOperator Object
     */
    @SuppressWarnings("unchecked")
    public static Object flatParamToFieldValueOperator(Object param, JdbcPropertyMapping propertyMapping) {
        if (param == null) {
            return param;
        }
        Object result = null;
        if (param.getClass().isArray()) {
            int length = Array.getLength(param);
            result = new FieldValueOperator[length];
            for (int i = 0; i < length; i++) {
                if (param.getClass() == FieldValueOperator[].class) {
                    Array.set(result, i, Array.get(param, i));
                } else if (param.getClass() == int[].class) {
                    Array.set(result, i, FieldValueOperator.create(propertyMapping, Array.getInt(param, i)));
                } else if (param.getClass() == long[].class) {
                    Array.set(result, i, FieldValueOperator.create(propertyMapping, Array.getLong(param, i)));
                } else if (param.getClass() == boolean[].class) {
                    Array.set(result, i, FieldValueOperator.create(propertyMapping, Array.getBoolean(param, i)));
                } /*
                   * else if (value.getClass() == char[].class) {
                   * Array.set(param, i, FieldValueOperator.create(pm, (byte)
                   * Array.getChar(value, i)));
                   * // database don't support getChar
                   * }
                   */ else if (param.getClass() == byte[].class) {
                    Array.set(result, i, FieldValueOperator.create(propertyMapping, Array.getByte(param, i)));
                } else if (param.getClass() == short[].class) {
                    Array.set(result, i, FieldValueOperator.create(propertyMapping, Array.getShort(param, i)));
                } else if (param.getClass() == double[].class) {
                    Array.set(result, i, FieldValueOperator.create(propertyMapping, Array.getDouble(param, i)));
                } else if (param.getClass() == float[].class) {
                    Array.set(result, i, FieldValueOperator.create(propertyMapping, Array.getFloat(param, i)));
                } else {
                    Array.set(result, i,
                        FieldValueOperator.create(propertyMapping, (Serializable) Array.get(param, i)));
                }
            }
        } else if (param instanceof Collection) {
            result = new ArrayList<>();
            for (Serializable op : (Collection<Serializable>) param) {
                if (op instanceof FieldValueOperator) {
                    ((Collection<FieldValueOperator<? extends Serializable>>) result).add((FieldValueOperator<?>) op);
                } else {
                    ((Collection<FieldValueOperator<? extends Serializable>>) result)
                        .add(FieldValueOperator.create(propertyMapping, op));
                }
            }
        } else if (!(param instanceof FieldValueOperator)) {
            result = FieldValueOperator.create(propertyMapping, (Serializable) param);
        } else {
            result = param;
        }
        return result;
    }

    /**
     * Flat params. if item of the param array is array or Collection, will expand them and
     * Sequential insertion.
     *
     * <pre>
     * give ["a", "b", ["1", "2"], "c"] return ["a", "b", "1", "2", "c"]
     * </pre>
     *
     * @param param the param
     * @return the flat FieldValueOperator Array or FieldValueOperator Object
     */
    public static Object flatParamToFieldValueOperator(Object param) {
        if (param == null) {
            return null;
        }
        @SuppressWarnings("rawtypes")
        final List<FieldValueOperator> paramList = new ArrayList<>();
        Lang.eachObj(param, p -> {
            if (p instanceof FieldValueOperator) {
                paramList.add((FieldValueOperator<?>) p);
            } else {
                paramList.add(FieldValueOperator.create((Serializable) p));
            }
        });
        if (paramList.isEmpty()) {
            return null;
        } else if (paramList.size() == 1) {
            return paramList.get(0);
        } else {
            return CollectionUtils.toArray(paramList, FieldValueOperator.class);
        }
    }

    /**
     * Flat params. if item of the param array is array or Collection, will expand them and
     * Sequential insertion.
     *
     * <pre>
     * give ["a", "b", ["1", "2"], "c"] return ["a", "b", "1", "2", "c"]
     * </pre>
     *
     * @param params the params
     * @return the flat FieldValueOperator[]
     */
    public static FieldValueOperator<?>[] flatParamsToFieldValueOperator(Object... params) {
        if (params == null) {
            return FieldValueOperator.EMPTY_ARRAY;
        }
        @SuppressWarnings("rawtypes")
        final List<FieldValueOperator> paramList = new ArrayList<>();
        for (Object param : params) {
            Lang.eachObj(param, p -> {
                if (p instanceof FieldValueOperator) {
                    paramList.add((FieldValueOperator<?>) p);
                } else {
                    paramList.add(FieldValueOperator.create((Serializable) p));
                }
            });
        }
        return CollectionUtils.toArray(paramList, FieldValueOperator.class);
    }

    /**
     * Convert in params placeholder. such as (?,?,?).
     *
     * @param inParam the in param
     * @return in params placeholder
     */
    public static String convertInParamsPlaceholder(Object inParam) {
        if (inParam == null) {
            return convertInParamsPlaceholder(1);
        } else if (inParam instanceof Collection) {
            return convertInParamsPlaceholder(((Collection<?>) inParam).size());
        } else if (inParam.getClass().isArray()) {
            int length = Array.getLength(inParam);
            return convertInParamsPlaceholder(length);
        } else {
            return convertInParamsPlaceholder(1);
        }
    }

    /**
     * Convert in params placeholder. such as (?,?,?).
     *
     * @param placeholderNum the placeholder number
     * @return in params placeholder
     */
    public static String convertInParamsPlaceholder(int placeholderNum) {
        if (placeholderNum <= 0) {
            throw new IllegalArgumentException("size must be > 0");
        }
        StringBuilder sql = new StringBuilder();
        sql.append(Chars.PAREN_L_CHAR);
        for (int i = 0; i < placeholderNum; i++) {
            sql.append(Chars.QUESTION_CHAR).append(Chars.COMMA_CHAR);
        }
        if (sql.length() > 0) {
            sql.deleteCharAt(sql.length() - 1);
        }
        sql.append(Chars.PAREN_R_CHAR);
        return sql.toString();
    }

    /**
     * convert named param sql with {@link #PARAM_NAME_START_SYMBOL}.
     * <p>
     * transfer <code>select * from user where name = :user</code> to
     * <code>select * from user where name = ?</code>
     * <p>
     *
     * @param namedParamSql the named param sql
     * @param params the params
     * @return the execution contains jdbc placeholder sql and params array
     */
    public static Execution convertNamedParamSql(String namedParamSql, Map<String, Object> params) {
        return convertNamedParamSql(namedParamSql, params, PARAM_NAME_START_SYMBOL);
    }

    /**
     * convert named param sql with startSymbol.
     *
     * @param namedParamSql the named param sql
     * @param params the params
     * @param startSymbol the start symbol
     * @return the execution contains jdbc placeholder sql and params array
     */
    public static Execution convertNamedParamSql(String namedParamSql, Map<String, Object> params, char startSymbol) {
        return convertNamedParamSql(namedParamSql, params, startSymbol, null);
    }

    /**
     * convert named param sql startSymbol and endSymbol.
     *
     * @param namedParamSql the named param sql
     * @param params the params
     * @param startSymbol the start symbol
     * @param endSymbol the end symbol
     * @return the execution contains jdbc placeholder sql and params array
     */
    public static Execution convertNamedParamSql(String namedParamSql, Map<String, Object> params, char startSymbol,
        Character endSymbol) {
        final MutableTuple1<Object[]> paramsTuple = MutableTuples.create1();
        return new SimpleExecution(convertNamedParamSql(namedParamSql, params, startSymbol, endSymbol,
            paramArray -> paramsTuple.set0(paramArray), null, null), paramsTuple.get0().get());
    }

    // ----------------------------------------------------------------------------------------------------------------

    /**
     * convert named param sql with {@link #PARAM_NAME_START_SYMBOL}.
     * <p>
     * transfer <code>select * from user where name = :user</code> to
     * <code>select * from user where name = ?</code>
     * <p>
     *
     * @param namedParamSql the named param sql
     * @param featureConsumer the feature consumer
     * @return jdbc placeholder sql
     */
    public static String convertNamedParamSql(String namedParamSql, Consumer<NamedSqlConvertFeature> featureConsumer) {
        return convertNamedParamSql(namedParamSql, PARAM_NAME_START_SYMBOL, null, featureConsumer);
    }

    /**
     * convert named param sql with startSymbol and endSymbol.
     *
     * @param namedParamSql the named param sql
     * @param startSymbol the start symbol
     * @param endSymbol the end symbol
     * @param featureConsumer the feature consumer
     * @return jdbc placeholder sql
     */
    public static String convertNamedParamSql(String namedParamSql, char startSymbol, Character endSymbol,
        Consumer<NamedSqlConvertFeature> featureConsumer) {
        NamedSqlConvertFeature convertor = new NamedSqlConvertFeature();
        featureConsumer.accept(convertor);
        return convertNamedParamSql(namedParamSql, null, startSymbol, endSymbol, null, null, convertor);
    }

    /**
     * convert named param sql with {@link #PARAM_NAME_START_SYMBOL}.
     * <p>
     * transfer <code>select * from user where name = :user</code> to
     * <code>select * from user where name = ?</code>
     * <p>
     *
     * @param namedParamSql the named param sql
     * @return NamedParamSql
     */
    public static NamedParamSql convertNamedParamSql(String namedParamSql) {
        return convertNamedParamSql(namedParamSql, PARAM_NAME_START_SYMBOL);
    }

    /**
     * convert named param sql with startSymbol argument.
     *
     * @param namedParamSql the named param sql
     * @param startSymbol the start symbol
     * @return NamedParamSql
     */
    public static NamedParamSql convertNamedParamSql(String namedParamSql, char startSymbol) {
        return convertNamedParamSql(namedParamSql, startSymbol, null);
    }

    /**
     * convert named param sql with startSymbol and endSymbol.
     *
     * @param namedParamSql the named param sql
     * @param startSymbol the start symbol
     * @param endSymbol the end symbol
     * @return NamedParamSql
     */
    public static NamedParamSql convertNamedParamSql(String namedParamSql, char startSymbol, Character endSymbol) {
        final MutableTuple1<NamedParam[]> paramNamesTuple = MutableTuples.create1();
        String sql = convertNamedParamSql(namedParamSql, null, startSymbol, endSymbol, null,
            namedParamArray -> paramNamesTuple.set0(namedParamArray), NAMEDSQL_CONVERTOR);
        return new NamedParamSql(sql, paramNamesTuple.get0().get());
    }

    private static String convertNamedParamSql(String namedParamSql, Map<String, Object> params, char startSymbol,
        Character endSymbol, Consumer<Object[]> paramValuesConsumer, Consumer<NamedParam[]> namedParamsConsumer,
        NamedSqlConvertFeature convertFeature) {
        AssertIllegalArgument.isNotNull(namedParamSql, "namedParamSql");
        AssertIllegalArgument.isNotEmpty(startSymbol, "startSymbol");

        StringBuilder sql = new StringBuilder();
        List<Object> paramList = null;
        if (paramValuesConsumer != null) {
            paramList = new ArrayList<>(params.size());
        }
        List<NamedParam> nameList = null;
        if (namedParamsConsumer != null) {
            nameList = new ArrayList<>(0);
        }

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
                    boolean isIn = isInCondition(namedParamSql, nameStartIndex);
                    if (namedParamsConsumer != null) {
                        if (isIn) {
                            nameList.add(new NamedParam(name, sql.length()));
                        } else {
                            nameList.add(new NamedParam(name));
                        }
                    }
                    if (params != null) {
                        // create native sql
                        Object param = getNamedParam(params, name);
                        String paramSql = addParam(param, paramList, isIn);
                        sql.append(paramSql);
                    } else {
                        // craete sql pattern for NamedParamSql
                        if (!isIn) {
                            sql.append(Chars.QUESTION_CHAR);
                        } else if (convertFeature != null) {
                            sql.append(convertFeature.inParamsPlaceholder.apply(name));
                        }
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
        if (paramValuesConsumer != null) {
            paramValuesConsumer.accept(paramList.toArray());
        }
        if (namedParamsConsumer != null) {
            namedParamsConsumer.accept(nameList.toArray(new NamedParam[nameList.size()]));
        }
        return sql.toString();
    }

    //    private static void sqlInParams(StringBuilder sql, int size) {
    //        sql.append(inParamsSql(size));
    //    }
    //
    //    private static void setParams(StringBuilder sql, int length, String namedParamSql, int nameStartIndex) {
    //        if (isInCondition(namedParamSql, nameStartIndex)) {
    //            sqlInParams(sql, length);
    //        } else {
    //            sql.append(Chars.QUESTION_CHAR);
    //        }
    //    }

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

    /**
     * Checks if is sql word split char.
     *
     * @param c the c
     * @return true, if is sql word split char
     */
    public static boolean isSqlWordSplitChar(char c) {
        return c == Chars.SPACE_CHAR || c == Chars.NEW_LINE_CHAR || c == Chars.COMMA_CHAR || c == Chars.PAREN_R_CHAR
            || c == Chars.TAB_CHAR;
        //                || c == ')'
    }

    /**
     * Gets the named param.
     *
     * @param params the params
     * @param name the name
     * @return the named param
     */
    static Object getNamedParam(Map<String, Object> params, String name) {
        if (params.containsKey(name)) {
            return params.get(name);
        } else {
            throw new JdbcException("no param found for name -> " + name);
        }
    }

    /**
     * Adds the param.
     *
     * @param param the param
     * @param paramList the param list
     * @return the string
     */
    static String addParam(Object param, List<Object> paramList) {
        return addParam(param, paramList, false);
    }

    /**
     * Adds the param.
     *
     * @param param the param
     * @param paramList the param list
     * @param isIn the is in
     * @return the string
     */
    static String addParam(Object param, List<Object> paramList, boolean isIn) {
        if (isIn) {
            if (param == null) {
                paramList.add(param);
                return convertInParamsPlaceholder(1);
            } else if (param instanceof Collection) {
                paramList.addAll((Collection<?>) param);
                return convertInParamsPlaceholder(((Collection<?>) param).size());
            } else if (param.getClass().isArray()) {
                int length = Array.getLength(param);
                for (int i = 0; i < length; i++) {
                    paramList.add(Array.get(param, i));
                }
                return convertInParamsPlaceholder(length);
            } else {
                paramList.add(param);
                return convertInParamsPlaceholder(1);
            }
        }
        paramList.add(param);
        return Chars.QUESTION;
    }

    /**
     * The Class NamedSqlConvertFeature.
     */
    public static final class NamedSqlConvertFeature {

        private Function<String, String> inParamsPlaceholder;

        private NamedSqlConvertFeature() {
            this((name) -> "");
        }

        private NamedSqlConvertFeature(Function<String, String> inParamsPlaceholder) {
            setInParamsPlaceholder(inParamsPlaceholder);
        }

        /**
         * Sets the in params placeholder.
         *
         * @param inParamsPlaceholder the in params placeholder
         * @return the convertor
         */
        public NamedSqlConvertFeature setInParamsPlaceholder(Function<String, String> inParamsPlaceholder) {
            AssertIllegalArgument.isNotNull(inParamsPlaceholder, "inParamsPlaceholder");
            this.inParamsPlaceholder = inParamsPlaceholder;
            return this;
        }
    }
}
