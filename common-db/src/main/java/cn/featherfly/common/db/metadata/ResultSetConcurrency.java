
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-01 15:47:01
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.metadata;

import java.sql.ResultSet;

/**
 * ResultSetConcurrency
 *
 * @author zhongj
 */
public enum ResultSetConcurrency {

    /**
     * The constant indicating the concurrency mode for a {@code ResultSet}
     * object that may NOT be updated.
     *
     * @see java.sql.ResultSet#CONCUR_READ_ONLY
     */
    CONCUR_READ_ONLY(ResultSet.CONCUR_READ_ONLY),

    /**
     * The constant indicating the concurrency mode for a {@code ResultSet}
     * object that may be updated.
     *
     * @see java.sql.ResultSet#CONCUR_UPDATABLE
     */
    CONCUR_UPDATABLE(ResultSet.CONCUR_UPDATABLE),;

    ResultSetConcurrency(int value) {
        this.value = value;
    }

    private int value;

    public int value() {
        return value;
    }

}
