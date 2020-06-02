//
//package cn.featherfly.common.db.mapping;
//
//import java.io.Serializable;
//import java.sql.PreparedStatement;
//import java.sql.SQLType;
//
//import cn.featherfly.common.lang.GenericType;
//
///**
// * <p>
// * JavaToSqlTypeMapper
// * </p>
// * .
// *
// * @author zhongj
// * @param <E> to regist java type
// */
//public interface JavaToSqlTypeMapper<E extends Serializable> {
//
//    /**
//     * Gets the sql type.
//     *
//     * @param javaType the java type
//     * @return the sql type
//     */
//    SQLType getSqlType(GenericType<E> javaType);
//
//    /**
//     * Sets the value.
//     *
//     * @param value       the value
//     * @param columnIndex the column index
//     * @param prep        the prep
//     */
//    void set(E value, int columnIndex, PreparedStatement prep);
//}
