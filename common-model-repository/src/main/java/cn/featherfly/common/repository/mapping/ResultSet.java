
package cn.featherfly.common.repository.mapping;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * DataRes
 * </p>
 *
 * @author zhongj
 */
public interface ResultSet {

    int getRowNum();

    int getInt(int index);

    int getInt(String name);

    long getLong(int index);

    long getLong(String name);

    float getFloat(int index);

    float getFloat(String name);

    double getDouble(int index);

    double getDouble(String name);

    byte getByte(int index);

    byte getByte(String name);

    byte[] getBytes(int index);

    byte[] getBytes(String name);

    Date getDate(int index);

    Date getDate(String name);

    String getString(int index);

    String getString(String name);

    <T extends Enum<T>> T getEnum(int index, Class<T> type);

    <T extends Enum<T>> T getEnum(String name, Class<T> type);

    Object getObject(int index);

    Object getObject(String name);

    <O> O getObject(int index, Class<O> type);

    <O> O getObject(String name, Class<O> type);

    BigDecimal getBigDecimal(int index);

    BigDecimal getBigDecimal(String name);
}
