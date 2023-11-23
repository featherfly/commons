
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-23 18:03:23
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.function;

/**
 * short supplier.
 *
 * @author zhongj
 */
public interface ShortSupplier {
    /**
     * Gets a result.
     *
     * @return a result
     */
    short getAsShort();

    /**
     * Gets a result.
     *
     * @return a result
     */
    default short get() {
        return getAsShort();
    }
}
