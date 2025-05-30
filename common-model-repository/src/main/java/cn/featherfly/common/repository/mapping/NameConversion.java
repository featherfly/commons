
package cn.featherfly.common.repository.mapping;

/**
 * name conversion.
 *
 * @author zhongj
 */
public interface NameConversion<T> {
    /**
     * convert type to mapping name.
     *
     * @param type type
     * @return mapping name
     */
    String getMappingName(T type);
}
