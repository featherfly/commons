package cn.featherfly.common.repository.builder.dml;

import java.util.List;

import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.builder.Builder;

/**
 * 排序构建接口.
 *
 * @author zhongj
 */
public interface SortBuilder extends Builder {
    /**
     * 添加升序条件.
     *
     * @param names 名称
     * @return this
     */
    SortBuilder asc(String... names);

    /**
     * 添加升序条件.
     *
     * @param names 名称
     * @return this
     */
    default SortBuilder asc(List<String> names) {
        if (Lang.isNotEmpty(names)) {
            return asc(CollectionUtils.toArray(names));
        } else {
            return this;
        }
    }

    /**
     * 添加降序条件.
     *
     * @param names 名称
     * @return this
     */
    SortBuilder desc(String... names);

    /**
     * 添加降序条件.
     *
     * @param names 名称
     * @return this
     */
    default SortBuilder desc(List<String> names) {
        if (Lang.isNotEmpty(names)) {
            return desc(CollectionUtils.toArray(names));
        } else {
            return this;
        }
    }
}