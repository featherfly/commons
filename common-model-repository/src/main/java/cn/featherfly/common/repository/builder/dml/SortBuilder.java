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
import java.util.function.Supplier;

import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.repository.AliasField;
import cn.featherfly.common.repository.AliasRepository;
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
     * @param fields the fields
     * @return this
     */
    default SortBuilder asc(Field... fields) {
        if (Lang.isNotEmpty(fields)) {
            for (Field field : fields) {
                if (field == null) {
                    continue;
                }
                if (field instanceof AliasField) {
                    asc(((AliasField) field).getAliasOrName());
                } else {
                    asc(field.name());
                }
            }
        }
        return this;
    }

    /**
     * 添加升序条件.
     *
     * @param aliasableRepository the aliasable repository
     * @param field               the field
     * @return the sort builder
     */
    default SortBuilder asc(AliasRepository aliasableRepository, String field) {
        return ascWith(Lang.ifNotNull(aliasableRepository, AliasRepository::getAliasOrName), field);
    }

    /**
     * 添加升序条件.
     *
     * @param repositoryAlias the repository alias
     * @param field           the field
     * @return the sort builder
     */
    SortBuilder ascWith(String repositoryAlias, String field);

    /**
     * 添加升序条件.
     *
     * @param repositoryAlias the repository alias
     * @param field           the field
     * @return the sort builder
     */
    default SortBuilder asc(String repositoryAlias, Field field) {
        return ascWith(repositoryAlias, Lang.ifNotNull(field, Field::name));
    }

    /**
     * 添加升序条件.
     *
     * @param repositoryAlias the repository alias
     * @param fieldsSuppler   the fields suppler
     * @return this
     */
    default SortBuilder asc(String repositoryAlias, Supplier<String[]> fieldsSuppler) {
        String[] fields;
        if (fieldsSuppler != null && (fields = fieldsSuppler.get()) != null) {
            for (String field : fields) {
                if (Lang.isEmpty(field)) {
                    continue;
                }
                ascWith(repositoryAlias, field);
            }
        }
        return this;
    }

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
     * @param fields the fields
     * @return this
     */
    default SortBuilder desc(Field... fields) {
        if (Lang.isNotEmpty(fields)) {
            for (Field field : fields) {
                if (field == null) {
                    continue;
                }
                if (field instanceof AliasField) {
                    desc(((AliasField) field).getAliasOrName());
                } else {
                    desc(field.name());
                }
            }
        }
        return this;
    }

    /**
     * 添加降序条件.
     *
     * @param aliasableRepository the aliasable repository
     * @param field               the field
     * @return the sort builder
     */
    default SortBuilder desc(AliasRepository aliasableRepository, String field) {
        return descWith(Lang.ifNotNull(aliasableRepository, AliasRepository::getAliasOrName), field);
    }

    /**
     * 添加降序条件.
     *
     * @param repositoryAlias the repository alias
     * @param field           the field
     * @return the sort builder
     */
    SortBuilder descWith(String repositoryAlias, String field);

    /**
     * 添加降序条件.
     *
     * @param repositoryAlias the repository alias
     * @param field           the field
     * @return the sort builder
     */
    default SortBuilder desc(String repositoryAlias, Field field) {
        return descWith(repositoryAlias, Lang.ifNotNull(field, Field::name));
    }

    /**
     * 添加降序条件.
     *
     * @param repositoryAlias the repository alias
     * @param fieldsSuppler   the fields suppler
     * @return this
     */
    default SortBuilder desc(String repositoryAlias, Supplier<String[]> fieldsSuppler) {
        String[] fields;
        if (fieldsSuppler != null && (fields = fieldsSuppler.get()) != null) {
            for (String field : fields) {
                if (Lang.isEmpty(field)) {
                    continue;
                }
                descWith(repositoryAlias, field);
            }
        }
        return this;
    }

}