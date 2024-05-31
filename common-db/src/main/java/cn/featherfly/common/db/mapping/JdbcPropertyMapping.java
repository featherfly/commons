package cn.featherfly.common.db.mapping;

import java.io.Serializable;

import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * JdbcPropertyMapping.
 *
 * @author zhongj
 */
public class JdbcPropertyMapping extends PropertyMapping<JdbcPropertyMapping> {

    private JavaTypeSqlTypeOperator<? extends Serializable> javaTypeSqlTypeOperator;

    /**
     * get javaTypeSqlTypeOperator value.
     *
     * @param <E> the element type
     * @return javaTypeSqlTypeOperator
     */
    @SuppressWarnings("unchecked")
    public <E extends Serializable> JavaTypeSqlTypeOperator<E> getJavaTypeSqlTypeOperator() {
        return (JavaTypeSqlTypeOperator<E>) javaTypeSqlTypeOperator;
    }

    /**
     * set javaTypeSqlTypeOperator value.
     *
     * @param <E> the element type
     * @param javaTypeSqlTypeOperator javaTypeSqlTypeOperator
     */
    public <
        E extends Serializable> void setJavaTypeSqlTypeOperator(JavaTypeSqlTypeOperator<E> javaTypeSqlTypeOperator) {
        this.javaTypeSqlTypeOperator = javaTypeSqlTypeOperator;
    }
}
