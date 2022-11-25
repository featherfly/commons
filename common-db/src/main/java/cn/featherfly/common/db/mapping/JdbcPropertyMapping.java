package cn.featherfly.common.db.mapping;

import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * JdbcPropertyMapping.
 *
 * @author zhongj
 */
public class JdbcPropertyMapping extends PropertyMapping {

    private JavaTypeSqlTypeOperator<?> javaTypeSqlTypeOperator;

    /**
     * get javaTypeSqlTypeOperator value
     *
     * @return javaTypeSqlTypeOperator
     */
    public JavaTypeSqlTypeOperator<?> getJavaTypeSqlTypeOperator() {
        return javaTypeSqlTypeOperator;
    }

    /**
     * set javaTypeSqlTypeOperator value
     *
     * @param javaTypeSqlTypeOperator javaTypeSqlTypeOperator
     */
    public void setJavaTypeSqlTypeOperator(JavaTypeSqlTypeOperator<?> javaTypeSqlTypeOperator) {
        this.javaTypeSqlTypeOperator = javaTypeSqlTypeOperator;
    }
}
