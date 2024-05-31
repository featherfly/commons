package cn.featherfly.common.db.mapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.lang.reflect.Type;

/**
 * entity store.
 *
 * @author zhongj
 */
public class TypeStore extends AbstractStore {

    /** The java to sql type mappers with type. */
    private Map<Type<Serializable>, JavaSqlTypeMapper<Serializable>> javaSqlTypeMappers = new HashMap<>(0);

    /**
     * put mapper with type.
     *
     * @param type the type
     * @param mapper the mapper
     */
    public void put(Type<Serializable> type, JavaSqlTypeMapper<Serializable> mapper) {
        javaSqlTypeMappers.put(type, mapper);
    }

    /**
     * get JavaSqlTypeMapper with key.
     *
     * @param type the type
     * @return the java sql type mapper
     */
    public JavaSqlTypeMapper<Serializable> getJavaSqlTypeMapper(Type<Serializable> type) {
        return javaSqlTypeMappers.get(type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<JavaSqlTypeMapper<Serializable>> getJavaSqlTypeMappers() {
        return javaSqlTypeMappers.values();
    }
}
