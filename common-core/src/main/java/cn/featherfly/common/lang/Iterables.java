
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-04-22 14:02:22
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

/**
 * IterableTypes.
 *
 * @author zhongj
 */
public enum Iterables {
    ARRAY,
    COLLECTION,
    LIST,
    QUEUE,
    SET;

    Iterables() {
    }

    public boolean isCollection() {
        if (this == ARRAY) {
            return false;
        }
        return true;
    }
}
