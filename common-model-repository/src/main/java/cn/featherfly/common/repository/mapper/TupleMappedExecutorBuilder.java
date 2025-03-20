
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-10 15:22:10
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository.mapper;

/**
 * tuple mapper builder.
 *
 * @author zhongj
 */
public interface TupleMappedExecutorBuilder {

    /**
     * Map.
     *
     * @param <T1>   the generic type
     * @param prefix the prefix
     * @param type   the type
     * @return the prefixed bean mapper 1
     */
    <T1> PrefixedBeanMappedExecutor1<T1> map(String prefix, Class<T1> type);

}
