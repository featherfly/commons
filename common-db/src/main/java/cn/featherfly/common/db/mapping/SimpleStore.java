package cn.featherfly.common.db.mapping;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The Class SimpleStore.
 *
 * @author zhongj
 */
public class SimpleStore extends AbstractStore {

    /** The java to sql type mappers. */
    private Set<JavaSqlTypeMapper<?>> javaSqlTypeMappers = new LinkedHashSet<>(0);

    /**
     * Adds the.
     *
     * @param mapper the mapper
     */
    public void add(JavaSqlTypeMapper<?> mapper) {
        javaSqlTypeMappers.add(mapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<JavaSqlTypeMapper<?>> getJavaSqlTypeMappers() {
        return javaSqlTypeMappers;
    }
}
