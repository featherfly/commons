
package cn.featherfly.common.db.mapping;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.JDBCType;
import java.sql.SQLType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * BasicSqlTypeMapping
 * </p>
 * .
 *
 * @author zhongj
 */
public class DefaultSqlTypeMapping {

    private static final Map<Class<? extends Object>, SQLType> JAVA_TO_SQL_MAP = new HashMap<>();

    private static final Map<SQLType, Class<? extends Object>> SQL_TO_JAVA_MAP = new HashMap<>();

    private boolean enumWithOrdinal;

    private SQLType enumOrdinalType = JDBCType.TINYINT;

    static {
        // ------------------------------------------------------------------------------------------
        // java to sql
        // ------------------------------------------------------------------------------------------
        JAVA_TO_SQL_MAP.put(Boolean.TYPE, JDBCType.BOOLEAN);
        JAVA_TO_SQL_MAP.put(Boolean.class, JDBCType.BOOLEAN);
        JAVA_TO_SQL_MAP.put(Character.TYPE, JDBCType.CHAR);
        JAVA_TO_SQL_MAP.put(Character.class, JDBCType.CHAR);
        JAVA_TO_SQL_MAP.put(Byte.TYPE, JDBCType.TINYINT);
        JAVA_TO_SQL_MAP.put(Byte.class, JDBCType.TINYINT);
        JAVA_TO_SQL_MAP.put(Short.TYPE, JDBCType.SMALLINT);
        JAVA_TO_SQL_MAP.put(Short.class, JDBCType.SMALLINT);
        JAVA_TO_SQL_MAP.put(Integer.TYPE, JDBCType.INTEGER);
        JAVA_TO_SQL_MAP.put(Integer.class, JDBCType.INTEGER);
        JAVA_TO_SQL_MAP.put(Long.TYPE, JDBCType.BIGINT);
        JAVA_TO_SQL_MAP.put(Long.class, JDBCType.BIGINT);
        JAVA_TO_SQL_MAP.put(BigInteger.class, JDBCType.BIGINT);
        JAVA_TO_SQL_MAP.put(Float.TYPE, JDBCType.DECIMAL);
        JAVA_TO_SQL_MAP.put(Float.class, JDBCType.DECIMAL);
        JAVA_TO_SQL_MAP.put(Double.TYPE, JDBCType.DECIMAL);
        JAVA_TO_SQL_MAP.put(Double.class, JDBCType.DECIMAL);
        JAVA_TO_SQL_MAP.put(BigDecimal.class, JDBCType.DECIMAL);
        JAVA_TO_SQL_MAP.put(String.class, JDBCType.VARCHAR);
        JAVA_TO_SQL_MAP.put(Date.class, JDBCType.TIMESTAMP);
        JAVA_TO_SQL_MAP.put(LocalDate.class, JDBCType.DATE);
        JAVA_TO_SQL_MAP.put(LocalTime.class, JDBCType.TIME);
        JAVA_TO_SQL_MAP.put(LocalDateTime.class, JDBCType.TIMESTAMP);
        JAVA_TO_SQL_MAP.put(java.sql.Date.class, JDBCType.DATE);
        JAVA_TO_SQL_MAP.put(java.sql.Time.class, JDBCType.TIME);
        JAVA_TO_SQL_MAP.put(java.sql.Timestamp.class, JDBCType.TIMESTAMP);
        JAVA_TO_SQL_MAP.put(byte[].class, JDBCType.VARBINARY);
        JAVA_TO_SQL_MAP.put(Byte[].class, JDBCType.VARBINARY);

        // ------------------------------------------------------------------------------------------
        // sql to java
        // ------------------------------------------------------------------------------------------
        SQL_TO_JAVA_MAP.put(JDBCType.BOOLEAN, Boolean.TYPE);
        //  str types
        SQL_TO_JAVA_MAP.put(JDBCType.CHAR, String.class);
        SQL_TO_JAVA_MAP.put(JDBCType.NCHAR, String.class);
        SQL_TO_JAVA_MAP.put(JDBCType.VARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(JDBCType.NVARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(JDBCType.LONGVARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(JDBCType.LONGNVARCHAR, String.class);
        SQL_TO_JAVA_MAP.put(JDBCType.CLOB, String.class);
        SQL_TO_JAVA_MAP.put(JDBCType.NCLOB, String.class);

        //  number types
        SQL_TO_JAVA_MAP.put(JDBCType.TINYINT, Integer.class);
        SQL_TO_JAVA_MAP.put(JDBCType.SMALLINT, Integer.class);
        SQL_TO_JAVA_MAP.put(JDBCType.INTEGER, Integer.class);
        SQL_TO_JAVA_MAP.put(JDBCType.BIGINT, Long.class);
        SQL_TO_JAVA_MAP.put(JDBCType.FLOAT, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(JDBCType.DOUBLE, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(JDBCType.REAL, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(JDBCType.NUMERIC, BigDecimal.class);
        SQL_TO_JAVA_MAP.put(JDBCType.DECIMAL, BigDecimal.class);

        // date types
        SQL_TO_JAVA_MAP.put(JDBCType.DATE, Date.class);
        SQL_TO_JAVA_MAP.put(JDBCType.TIME, Date.class);
        SQL_TO_JAVA_MAP.put(JDBCType.TIMESTAMP, Date.class);
        SQL_TO_JAVA_MAP.put(JDBCType.TIME_WITH_TIMEZONE, LocalTime.class);
        SQL_TO_JAVA_MAP.put(JDBCType.TIMESTAMP_WITH_TIMEZONE, LocalDateTime.class);

        // data binary
        SQL_TO_JAVA_MAP.put(JDBCType.BLOB, byte[].class);
        SQL_TO_JAVA_MAP.put(JDBCType.BINARY, byte[].class);
        SQL_TO_JAVA_MAP.put(JDBCType.LONGVARBINARY, byte[].class);
        SQL_TO_JAVA_MAP.put(JDBCType.VARBINARY, byte[].class);

        SQL_TO_JAVA_MAP.put(JDBCType.BIT, Boolean.class);
    }

    /**
     * Instantiates a new default sql type mapping.
     */
    public DefaultSqlTypeMapping() {
    }

    /**
     * Gets the sql type.
     *
     * @param <E>      the element type
     * @param javaType the java type
     * @return the sql type
     */
    public <E extends Object> SQLType getSqlType(Class<E> javaType) {
        if (javaType.isEnum()) {
            if (enumWithOrdinal) {
                return enumOrdinalType;
            } else {
                return JDBCType.VARCHAR;
            }
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
    public <E extends Object> Class<E> getJavaType(SQLType sqlType) {
        return (Class<E>) SQL_TO_JAVA_MAP.get(sqlType);
    }

    /**
     * 返回enumWithOrdinal.
     *
     * @return enumWithOrdinal
     */
    public boolean isEnumWithOrdinal() {
        return enumWithOrdinal;
    }

    /**
     * 设置enumWithOrdinal.
     *
     * @param enumWithOrdinal enumWithOrdinal
     */
    public void setEnumWithOrdinal(boolean enumWithOrdinal) {
        this.enumWithOrdinal = enumWithOrdinal;
    }

    /**
     * 返回enumOrdinalType.
     *
     * @return enumOrdinalType
     */
    public SQLType getEnumOrdinalType() {
        return enumOrdinalType;
    }

    /**
     * 设置enumOrdinalType.
     *
     * @param enumOrdinalType enumOrdinalType
     */
    public void setEnumOrdinalType(SQLType enumOrdinalType) {
        if (enumOrdinalType == JDBCType.TINYINT || enumOrdinalType == JDBCType.SMALLINT
                || enumOrdinalType == JDBCType.INTEGER) {
            this.enumOrdinalType = enumOrdinalType;
        } else {
            throw new JdbcMappingException(
                    "enumWithOrdinal only can be JDBCType.TINYINT, JDBCType.SMALLINT, JDBCType.INTEGER");
        }
    }
}
