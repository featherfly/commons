
package cn.featherfly.common.lang.reflect;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * The Class ClassType.
 *
 * @author     zhongj
 * @param  <T> 类型
 */
public class ClassType<T> implements Type<T> {

    public static final ClassType<Boolean> BOOLEAN = new ClassType<>(Boolean.class);
    public static final ClassType<Boolean> BOOL = new ClassType<>(Boolean.TYPE);
    public static final ClassType<Byte> BYTEWRAPPER = new ClassType<>(Byte.class);
    public static final ClassType<Byte> BYTE = new ClassType<>(Byte.TYPE);
    public static final ClassType<Short> SHORTWRAPPER = new ClassType<>(Short.class);
    public static final ClassType<Short> SHORT = new ClassType<>(Short.TYPE);
    public static final ClassType<Integer> INTEGER = new ClassType<>(Integer.class);
    public static final ClassType<Integer> INT = new ClassType<>(Integer.TYPE);
    public static final ClassType<Long> LONGWRAPPER = new ClassType<>(Long.class);
    public static final ClassType<Long> LONG = new ClassType<>(Long.TYPE);
    public static final ClassType<Float> FLOATEWRAPPER = new ClassType<>(Float.class);
    public static final ClassType<Float> FLOAT = new ClassType<>(Float.TYPE);
    public static final ClassType<Double> DOUBLEWRAPPER = new ClassType<>(Double.class);
    public static final ClassType<Double> DOUBLE = new ClassType<>(Double.TYPE);
    public static final ClassType<BigDecimal> BIGDECIMAL = new ClassType<>(BigDecimal.class);
    public static final ClassType<String> STRING = new ClassType<>(String.class);
    public static final ClassType<Date> DATE = new ClassType<>(Date.class);
    public static final ClassType<java.sql.Date> SQLDATE = new ClassType<>(java.sql.Date.class);
    public static final ClassType<Time> TIME = new ClassType<>(Time.class);
    public static final ClassType<Timestamp> TIMESTAMP = new ClassType<>(Timestamp.class);
    public static final ClassType<LocalDateTime> LOCALDATETIME = new ClassType<>(LocalDateTime.class);
    public static final ClassType<LocalDate> LOCALDATE = new ClassType<>(LocalDate.class);
    public static final ClassType<LocalTime> LOCALTIME = new ClassType<>(LocalTime.class);

    private Class<T> type;

    /**
     * Instantiates a new class type.
     *
     * @param type classType
     */
    public ClassType(Class<T> type) {
        AssertIllegalArgument.isNotNull(type, "Class<T> type");
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<T> getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return type.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != ClassType.class) {
            return false;
        }
        return type.equals(((ClassType<?>) obj).type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName() {
        return type.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [type =" + getTypeName() + "]";
    }
}
