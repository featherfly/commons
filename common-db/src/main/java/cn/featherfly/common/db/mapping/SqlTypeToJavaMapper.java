//
//package cn.featherfly.common.db.mapping;
//
//import java.io.Serializable;
//import java.sql.ResultSet;
//import java.sql.SQLType;
//
//import cn.featherfly.common.lang.GenericType;
//
///**
// * <p>
// * SqlTypeToJavaRegister
// * </p>
// * .
// *
// * @author zhongj
// * @param <E> to regist java type
// */
//public interface SqlTypeToJavaMapper<E extends Serializable> {
//
//    boolean support(SQLType sqlType, GenericType<E> type);
//
//    /**
//     * Gets the sql type.
//     *
//     * @param sqlType the sql type
//     * @return the sql type
//     */
//    Class<E> getJavaType(SQLType sqlType);
//
//    /**
//     * Gets the.
//     *
//     * @param rs          the rs
//     * @param columnIndex the column index
//     * @return the e
//     */
//    E get(ResultSet rs, int columnIndex);
//
//    /**
//     * Gets the.
//     *
//     * @param rs         the rs
//     * @param columnName the column name
//     * @return the e
//     */
//    E get(ResultSet rs, String columnName);
//}
