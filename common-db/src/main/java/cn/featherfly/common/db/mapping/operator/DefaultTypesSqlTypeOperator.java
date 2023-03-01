
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.db.mapping.JdbcMappingException;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Strings;

/**
 * The Class DefaultJavaSqlTypeOperator.
 *
 * @author zhongj
 * @param <E> the element type
 */
public class DefaultTypesSqlTypeOperator<E> implements JavaTypeSqlTypeOperator<E> {

    private Class<E> type;

    /**
     * Instantiates a new default java sql type operator.
     *
     * @param type the type
     */
    public DefaultTypesSqlTypeOperator(Class<E> type) {
        this(type, false);
    }

    /**
     * Instantiates a new default java sql type operator.
     *
     * @param type      the type
     * @param checkType the check type
     */
    public DefaultTypesSqlTypeOperator(Class<E> type, boolean checkType) {
        super();
        if (checkType && !support(type)) {
            // ENHANCE 后续来优化异常信息
            throw new JdbcMappingException(Strings.format("not support for type {0}", type.getName()));
        }
        this.type = type;
    }

    public static boolean support(Class<?> type) {
        return type.isPrimitive() || ClassUtils.isParent(Number.class, type)
                || ClassUtils.isParent(java.util.Date.class, type) || type == Boolean.class || type == String.class
                || type == Character.class || type == LocalDate.class || type == LocalDateTime.class
                || type == LocalTime.class || type == AtomicBoolean.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(PreparedStatement prep, int parameterIndex, E value) {
        JdbcUtils.setParameter(prep, parameterIndex, value);
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
