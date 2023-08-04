/*
 * All rights Reserved, Designed By zhongj
 * @Title: SortBuilder.java
 * @Package cn.featherfly.common.repository.builder.dml
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2023年8月1日 下午2:51:13
 * @version V1.0
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.builder.dml;

import java.util.List;

import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.Field;
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
     * @param fields the fields
     * @return this
     */
    SortBuilder asc(String... fields);

    /**
     * 添加升序条件.
     *
     * @param fields the fields
     * @return this
     */
    default SortBuilder asc(List<String> fields) {
        if (Lang.isNotEmpty(fields)) {
            return asc(CollectionUtils.toArray(fields));
        } else {
            return this;
        }
    }

    /**
     * 添加升序条件.
     *
     * @param tableAlias the table alias
     * @param field      the field
     * @return the sort builder
     */
    SortBuilder asc(String tableAlias, Field field);

    /**
     * 添加降序条件.
     *
     * @param fields the fields
     * @return this
     */
    SortBuilder desc(String... fields);

    /**
     * 添加降序条件.
     *
     * @param fields the fields
     * @return this
     */
    default SortBuilder desc(List<String> fields) {
        if (Lang.isNotEmpty(fields)) {
            return desc(CollectionUtils.toArray(fields));
        } else {
            return this;
        }
    }

    /**
     * 添加降序条件.
     *
     * @param tableAlias the table alias
     * @param field      the field
     * @return the sort builder
     */
    SortBuilder desc(String tableAlias, Field field);
}