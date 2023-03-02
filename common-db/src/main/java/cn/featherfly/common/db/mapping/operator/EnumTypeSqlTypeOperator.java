
/*
 * All rights Reserved, Designed By zhongj
 * @Title: StringJavaSqlTypeOperator.java
 * @Package cn.featherfly.common.db.mapping.operator
 * @Description: StringJavaSqlTypeOperator
 * @author: zhongj
 * @date: 2022-11-10 17:36:10
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.mapping.operator;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.Strings;

/**
 * The Class DefaultJavaSqlTypeOperator.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class EnumTypeSqlTypeOperator<E extends Enum<?>> implements JavaTypeSqlTypeOperator<E> {

    private Class<E> type;

    private boolean enumUseOrdinal;

    /**
     * Instantiates a new default java sql type operator.
     *
     * @param type the type
     */
    public EnumTypeSqlTypeOperator(Class<E> type) {
        this(type, true);
    }

    /**
     * Instantiates a new default java sql type operator.
     *
     * @param type           the type
     * @param enumUseOrdinal the enum use ordinal
     */
    public EnumTypeSqlTypeOperator(Class<E> type, boolean enumUseOrdinal) {
        this(type, enumUseOrdinal, false);
    }

    /**
     * Instantiates a new default java sql type operator.
     *
     * @param type           the type
     * @param enumUseOrdinal the enum use ordinal
     * @param checkType      the check type
     */
    public EnumTypeSqlTypeOperator(Class<E> type, boolean enumUseOrdinal, boolean checkType) {
        super();
        if (checkType && !support(type)) {
            // ENHANCE 后续来优化异常信息
            throw new JdbcMappingException(Strings.format("not support for type {0}", type.getName()));
        }
        this.type = type;
        this.enumUseOrdinal = enumUseOrdinal;
    }

    public static boolean support(Class<?> type) {
        return type.isEnum();
    }

    /**
     * get enumUseOrdinal value
     *
     * @return enumUseOrdinal
     */
    public boolean isEnumUseOrdinal() {
        return enumUseOrdinal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, E value) {
        JdbcUtils.setParameter(prep, parameterIndex, value, enumUseOrdinal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(CallableStatement call, String parameterName, E value) {
        JdbcUtils.setParameter(call, parameterName, value, enumUseOrdinal);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(ResultSet rs, int columnIndex) {
        return JdbcUtils.getResultSetValue(rs, columnIndex, type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(CallableStatement call, int paramIndex) {
        return JdbcUtils.getCallableParam(call, paramIndex, type);
    }

}
