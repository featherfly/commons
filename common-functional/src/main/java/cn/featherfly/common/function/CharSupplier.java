
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-23 18:03:23
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.function;

/**
 * char supplier.
 *
 * @author zhongj
 */
public interface CharSupplier {
    /**
     * Gets a result.
     *
     * @return a result
     */
    char getAsChar();

    /**
     * Gets a result.
     *
     * @return a result
     */
    default char get() {
        return getAsChar();
    }
}
