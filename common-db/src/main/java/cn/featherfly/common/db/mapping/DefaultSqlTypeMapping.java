
package cn.featherfly.common.db.mapping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.db.metadata.SqlType;

/**
 * <p>
 * BasicSqlTypeMapping
 * </p>
 *
 * @author zhongj
 */
public class DefaultSqlTypeMapping {

    private static final Map<Class<? extends Serializable>, SqlType> JAVA_TO_SQL_MAP = new HashMap<>();

    private static final Map<SqlType, Class<? extends Serializable>> SQL_TO_JAVA_MAP = new HashMap<>();

    static {
        // ------------------------------------------------------------------------------------------
        // java to sql
        // ------------------------------------------------------------------------------------------
        JAVA_TO_SQL_MAP.put(Boolean.TYPE, SqlType.BOOLEAN);
        JAVA_TO_SQL_MAP.put(Boolean.class, SqlType.BOOLEAN);
        JAVA_TO_SQL_MAP.put(Character.TYPE, SqlType.CHAR);
        JAVA_TO_SQL_MAP.put(Character.class, SqlType.CHAR);
        JAVA_TO_SQL_MAP.put(Byte.TYPE, SqlType.TINYINT);
        JAVA_TO_SQL_MAP.put(Byte.class, SqlType.TINYINT);
        JAVA_TO_SQL_MAP.put(Short.TYPE, SqlType.SMALLINT);
        JAVA_TO_SQL_MAP.put(Short.class, SqlType.SMALLINT);
        JAVA_TO_SQL_MAP.put(Integer.TYPE, SqlType.INTEGER);
        JAVA_TO_SQL_MAP.put(Integer.class, SqlType.INTEGER);
        JAVA_TO_SQL_MAP.put(Long.TYPE, SqlType.BIGINT);
        JAVA_TO_SQL_MAP.put(Long.class, SqlType.BIGINT);
        JAVA_TO_SQL_MAP.put(Float.TYPE, SqlType.FLOAT);
        JAVA_TO_SQL_MAP.put(Float.class, SqlType.FLOAT);
        JAVA_TO_SQL_MAP.put(Double.TYPE, SqlType.DOUBLE);
        JAVA_TO_SQL_MAP.put(Double.class, SqlType.DOUBLE);
        JAVA_TO_SQL_MAP.put(BigInteger.class, SqlType.BIGINT);
        JAVA_TO_SQL_MAP.put(BigDecimal.class, SqlType.DECIMAL);
        JAVA_TO_SQL_MAP.put(String.class, SqlType.VARCHAR);
        JAVA_TO_SQL_MAP.put(Date.class, SqlType.TIMESTAMP);
        JAVA_TO_SQL_MAP.put(Timestamp.class, SqlType.TIMESTAMP);
        JAVA_TO_SQL_MAP.put(LocalDateTime.class, SqlType.TIMESTAMP);
        JAVA_TO_SQL_MAP.put(java.sql.Date.class, SqlType.DATE);
        JAVA_TO_SQL_MAP.put(LocalDate.class, SqlType.DATE);
        JAVA_TO_SQL_MAP.put(java.sql.Time.class, SqlType.DATE);
        JAVA_TO_SQL_MAP.put(LocalTime.class, SqlType.TIME);

        // ------------------------------------------------------------------------------------------
        // sql to java
        // ------------------------------------------------------------------------------------------
        SQL_TO_JAVA_MAP.put(SqlType.BOOLEAN, Boolean.TYPE);
        //  str types
        SQL_TO_JAVA_MAP.put(SqlType.CHAR, String.class);
        SQL_TO_JAVA_MAP.put(SqlType.NCHAR, String.class);
        SQL_TO_JAVA_MAP.put(SqlType.VARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(SqlType.NVARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(SqlType.LONGVARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(SqlType.LONGNVARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(SqlType.CLOB, String.class);
        SQL_TO_JAVA_MAP.put(SqlType.NCLOB, String.class);

        //  number types
        SQL_TO_JAVA_MAP.put(SqlType.TINYINT, Integer.class);
        SQL_TO_JAVA_MAP.put(SqlType.SMALLINT, Integer.class);
        SQL_TO_JAVA_MAP.put(SqlType.INTEGER, Integer.class);
        SQL_TO_JAVA_MAP.put(SqlType.BIGINT, Long.class);
        SQL_TO_JAVA_MAP.put(SqlType.FLOAT, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(SqlType.DOUBLE, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(SqlType.REAL, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(SqlType.NUMERIC, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(SqlType.DECIMAL, BigDecimal.class);

        // date types
        SQL_TO_JAVA_MAP.put(SqlType.DATE, Date.class);
        SQL_TO_JAVA_MAP.put(SqlType.TIME, Date.class);
        SQL_TO_JAVA_MAP.put(SqlType.TIMESTAMP, Date.class);
        SQL_TO_JAVA_MAP.put(SqlType.TIME_WITH_TIMEZONE, LocalTime.class);
        SQL_TO_JAVA_MAP.put(SqlType.TIMESTAMP_WITH_TIMEZONE, LocalDateTime.class);

        // data binary
        SQL_TO_JAVA_MAP.put(SqlType.BLOB, byte[].class);
        SQL_TO_JAVA_MAP.put(SqlType.BINARY, byte[].class);
        SQL_TO_JAVA_MAP.put(SqlType.LONGVARBINARY, byte[].class);
        SQL_TO_JAVA_MAP.put(SqlType.VARBINARY, byte[].class);

        SQL_TO_JAVA_MAP.put(SqlType.BIT, BigInteger.class);
    }

    public DefaultSqlTypeMapping() {
    }

    public <E extends Serializable> SqlType getSqlType(Class<E> javaType) {
        if (javaType.isEnum()) {
            return SqlType.VARCHAR;
        }
        return JAVA_TO_SQL_MAP.get(javaType);
    }

    /**
     * Gets the java type.
     *
     * @param <E>     the element type
     * @param sqlType the sql type
     * @return the java type
     */
    @SuppressWarnings("unchecked")
    public <E extends Serializable> Class<E> getJavaType(SqlType sqlType) {
        return (Class<E>) SQL_TO_JAVA_MAP.get(sqlType);
    }

}
