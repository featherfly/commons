
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
public interface MappingFactory {

    /**
     * getClassMapping
     *
     * @param <T>  type
     * @param type type
     * @return ClassMapping
     */
    <T> ClassMapping<T> getClassMapping(Class<T> type);
}
