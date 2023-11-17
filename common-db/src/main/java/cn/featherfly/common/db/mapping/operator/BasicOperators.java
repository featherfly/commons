
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-09 17:31:09
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.mapping.operator;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.mapping.JavaTypeSqlTypeOperator;
import cn.featherfly.common.structure.ChainMap;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * The Class BasicOperators.
 *
 * @author zhongj
 */
public class BasicOperators {

    private static final ChainMap<Class<?>, JavaTypeSqlTypeOperator<?>> OPERATORS = new ChainMapImpl<>();

    static {
        OPERATORS.putChain(Boolean.class, new BooleanSqlTypeOperator()) //
                .putChain(Boolean.TYPE, new BoolSqlTypeOperator()) //
                .putChain(Integer.class, new IntegerSqlTypeOperator()) //
                .putChain(Integer.TYPE, new IntSqlTypeOperator()) //
                .putChain(Byte.class, new ByteSqlTypeOperator()) //
                .putChain(Short.class, new ShortSqlTypeOperator()) //
                .putChain(Long.class, new LongSqlTypeOperator()) //
                .putChain(Double.class, new DoubleSqlTypeOperator()) //
                .putChain(Float.class, new FloatSqlTypeOperator()) //
                .putChain(Date.class, new DateSqlTypeOperator()) //
                .putChain(Time.class, new TimeSqlTypeOperator()) //
                .putChain(Timestamp.class, new TimestampSqlTypeOperator()) //
                .putChain(LocalDate.class, new LocalDateSqlTypeOperator()) //
                .putChain(LocalDateTime.class, new LocalDateTimeSqlTypeOperator()) //
                .putChain(LocalTime.class, new LocalTimeSqlTypeOperator()) //
                .putChain(String.class, new StringSqlTypeOperator()) //
                .putChain(BigDecimal.class, new BigDecimalSqlTypeOperator()) //
                .putChain(byte[].class, new BytesSqlTypeOperator()) //
        ;

        OPERATORS.putChain(Byte.TYPE, OPERATORS.get(Byte.class)) //
                .putChain(Short.TYPE, OPERATORS.get(Short.class)) //
                .putChain(Long.TYPE, OPERATORS.get(Long.class)) //
                .putChain(Double.TYPE, OPERATORS.get(Double.class)) //
                .putChain(Float.TYPE, OPERATORS.get(Float.class)) //
        ;
    }

    /**
     * Gets the operator.
     *
     * @param <T>  the generic type
     * @param type the type
     * @return the operator
     */
    @SuppressWarnings("unchecked")
    public static <T> JavaTypeSqlTypeOperator<T> get(Class<T> type) {
        return (JavaTypeSqlTypeOperator<T>) OPERATORS.get(type);
    }

    /**
     * Gets the exist operator.
     *
     * @param <T>  the generic type
     * @param type the type
     * @return the operator
     * @throws JdbcException no JavaTypeSqlTypeOperator found for type
     */
    public static <T> JavaTypeSqlTypeOperator<T> getExist(Class<T> type) {
        JavaTypeSqlTypeOperator<T> o = get(type);
        if (o == null) {
            throw new JdbcException("no JavaTypeSqlTypeOperator support for " + type.getName());
        }
        return o;
    }
}
