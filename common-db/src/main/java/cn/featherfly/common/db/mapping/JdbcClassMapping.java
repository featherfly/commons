
/*
 * All rights Reserved, Designed By zhongj
 * @Title: JdbcClassMapping.java
 * @Package cn.featherfly.common.db.mapping
 * @Description: JdbcClassMapping
 * @author: zhongj
 * @date: 2022-11-25 21:02:25
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.mapping;

import cn.featherfly.common.repository.mapping.ClassMapping;

/**
 * JdbcClassMapping.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class JdbcClassMapping<T> extends ClassMapping<T, JdbcPropertyMapping> {

    /**
     * Instantiates a new jdbc class mapping.
     *
     * @param type           the type
     * @param repositoryName the repository name
     * @param schema         the schema
     * @param remark         the remark
     */
    public JdbcClassMapping(Class<T> type, String repositoryName, String schema, String remark) {
        super(type, repositoryName, schema, remark);
    }

    /**
     * Instantiates a new jdbc class mapping.
     *
     * @param type           the type
     * @param repositoryName the repository name
     * @param schema         the schema
     */
    public JdbcClassMapping(Class<T> type, String repositoryName, String schema) {
        super(type, repositoryName, schema);
    }

    /**
     * Instantiates a new jdbc class mapping.
     *
     * @param type           the type
     * @param repositoryName the repository name
     */
    public JdbcClassMapping(Class<T> type, String repositoryName) {
        super(type, repositoryName);
    }
}
