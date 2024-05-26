
package cn.featherfly.common.repository.mapper;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ResultSet.
 *
 * @author zhongj
 */
public interface ResultSet {
    /**
     * Gets the row num.
     *
     * @return the row num
     */
    int getRowNum();

    /**
     * Gets the int.
     *
     * @param index the index
     * @return the int
     */
    int getInt(int index);

    /**
     * Gets the int.
     *
     * @param name the name
     * @return the int
     */
    int getInt(String name);

    /**
     * Gets the long.
     *
     * @param index the index
     * @return the long
     */
    long getLong(int index);

    /**
     * Gets the long.
     *
     * @param name the name
     * @return the long
     */
    long getLong(String name);

    /**
     * Gets the float.
     *
     * @param index the index
     * @return the float
     */
    float getFloat(int index);

    /**
     * Gets the float.
     *
     * @param name the name
     * @return the float
     */
    float getFloat(String name);

    /**
     * Gets the double.
     *
     * @param index the index
     * @return the double
     */
    double getDouble(int index);

    /**
     * Gets the double.
     *
     * @param name the name
     * @return the double
     */
    double getDouble(String name);

    /**
     * Gets the byte.
     *
     * @param index the index
     * @return the byte
     */
    byte getByte(int index);

    /**
     * Gets the byte.
     *
     * @param name the name
     * @return the byte
     */
    byte getByte(String name);

    /**
     * Gets the bytes.
     *
     * @param index the index
     * @return the bytes
     */
    byte[] getBytes(int index);

    /**
     * Gets the bytes.
     *
     * @param name the name
     * @return the bytes
     */
    byte[] getBytes(String name);

    /**
     * Gets the date.
     *
     * @param index the index
     * @return the date
     */
    Date getDate(int index);

    /**
     * Gets the date.
     *
     * @param name the name
     * @return the date
     */
    Date getDate(String name);

    /**
     * Gets the string.
     *
     * @param index the index
     * @return the string
     */
    String getString(int index);

    /**
     * Gets the string.
     *
     * @param name the name
     * @return the string
     */
    String getString(String name);

    /**
     * Gets the enum.
     *
     * @param <T> the generic type
     * @param index the index
     * @param type the type
     * @return the enum
     */
    <T extends Enum<T>> T getEnum(int index, Class<T> type);

    /**
     * Gets the enum.
     *
     * @param <T> the generic type
     * @param name the name
     * @param type the type
     * @return the enum
     */
    <T extends Enum<T>> T getEnum(String name, Class<T> type);

    /**
     * Gets the object.
     *
     * @param index the index
     * @return the object
     */
    Object getObject(int index);

    /**
     * Gets the object.
     *
     * @param name the name
     * @return the object
     */
    Object getObject(String name);

    /**
     * Gets the object.
     *
     * @param <O> the generic type
     * @param index the index
     * @param type the type
     * @return the object
     */
    <O> O getObject(int index, Class<O> type);

    /**
     * Gets the object.
     *
     * @param <O> the generic type
     * @param name the name
     * @param type the type
     * @return the object
     */
    <O> O getObject(String name, Class<O> type);

    /**
     * Gets the big decimal.
     *
     * @param index the index
     * @return the big decimal
     */
    BigDecimal getBigDecimal(int index);

    /**
     * Gets the big decimal.
     *
     * @param name the name
     * @return the big decimal
     */
    BigDecimal getBigDecimal(String name);
}
