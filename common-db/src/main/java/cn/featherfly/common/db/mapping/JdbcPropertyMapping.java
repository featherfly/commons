package cn.featherfly.common.db.mapping;

import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * JdbcPropertyMapping.
 *
 * @author zhongj
 */
public class JdbcPropertyMapping extends PropertyMapping<JdbcPropertyMapping> {

    private JavaTypeSqlTypeOperator<?> javaTypeSqlTypeOperator;

    /**
     * get javaTypeSqlTypeOperator value.
     *
     * @param <E> the element type
     * @return javaTypeSqlTypeOperator
     */
    @SuppressWarnings("unchecked")
    public <E> JavaTypeSqlTypeOperator<E> getJavaTypeSqlTypeOperator() {
        return (JavaTypeSqlTypeOperator<E>) javaTypeSqlTypeOperator;
    }

    /**
     * set javaTypeSqlTypeOperator value.
     *
     * @param <E> the element type
     * @param javaTypeSqlTypeOperator javaTypeSqlTypeOperator
     */
    public <E> void setJavaTypeSqlTypeOperator(JavaTypeSqlTypeOperator<E> javaTypeSqlTypeOperator) {
        this.javaTypeSqlTypeOperator = javaTypeSqlTypeOperator;
    }
}
