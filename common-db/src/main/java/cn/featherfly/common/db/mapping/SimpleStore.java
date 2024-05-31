package cn.featherfly.common.db.mapping;

import java.io.Serializable;
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
    private Set<JavaSqlTypeMapper<Serializable>> javaSqlTypeMappers = new LinkedHashSet<>(0);

    /**
     * Adds the.
     *
     * @param <E> the element type
     * @param mapper the mapper
     */
    @SuppressWarnings("unchecked")
    public <E extends Serializable> void add(JavaSqlTypeMapper<E> mapper) {
        javaSqlTypeMappers.add((JavaSqlTypeMapper<Serializable>) mapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<JavaSqlTypeMapper<Serializable>> getJavaSqlTypeMappers() {
        return javaSqlTypeMappers;
    }
}
