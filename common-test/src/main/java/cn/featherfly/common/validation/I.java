
/*
 * All rights Reserved, Designed By zhongj
 * @Title: U.java
 * @Package cn.featherfly.common.validation
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-12-30 16:18:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.validation;

import javax.annotation.Nonnull;

/**
 * U.
 *
 * @author zhongj
 */
public interface I {
    default void name(@Nonnull String name) {
        System.out.println("name( " + name + " )");
    }

    void name2(@Nonnull String name);
}
