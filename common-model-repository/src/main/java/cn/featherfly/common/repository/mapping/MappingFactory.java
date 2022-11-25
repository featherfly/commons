
package cn.featherfly.common.repository.mapping;

/**
 * <p>
 * MappingFactory
 * </p>
 *
 * @author zhongj
 * @since 0.1.0
 * @version 0.1.0
 */
public interface MappingFactory<P extends PropertyMapping<P>> {

    /**
     * getClassMapping
     *
     * @param <T>  type
     * @param type type
     * @return ClassMapping
     */
    //    <T, P extends PropertyMapping<P>> ClassMapping<T, P> getClassMapping(Class<T> type);
    <T> ClassMapping<T, P> getClassMapping(Class<T> type);
}
