
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-23 18:03:23
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.function;

/**
 * byte supplier.
 *
 * @author zhongj
 */
public interface ByteSupplier extends PrimitiveSupplier {
    /**
     * Gets a result.
     *
     * @return a result
     */
    byte getAsByte();

    /**
     * Gets a result.
     *
     * @return a result
     */
    default byte get() {
        return getAsByte();
    }
}
