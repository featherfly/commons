
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-03-28 17:17:28
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.repository;

/**
 * RepositoryField.
 *
 * @author zhongj
 */
public interface RepositoryAwareField extends Field {

    /**
     * Repository.
     *
     * @return the repository
     */
    Repository repository();
}
