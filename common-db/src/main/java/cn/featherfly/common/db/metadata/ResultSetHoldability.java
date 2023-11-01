
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
 * ResultSetHoldability.
 *
 * @author zhongj
 */
public enum ResultSetHoldability {

    /**
     * The constant indicating that open {@code ResultSet} objects with this
     * holdability will remain open when the current transaction is committed.
     *
     * @see java.sql.ResultSet#HOLD_CURSORS_OVER_COMMIT
     */
    HOLD_CURSORS_OVER_COMMIT(ResultSet.HOLD_CURSORS_OVER_COMMIT),

    /**
     * The constant indicating that open {@code ResultSet} objects with this
     * holdability will be closed when the current transaction is committed.
     *
     * @see java.sql.ResultSet#CLOSE_CURSORS_AT_COMMIT
     */
    CLOSE_CURSORS_AT_COMMIT(ResultSet.CLOSE_CURSORS_AT_COMMIT),;

    ResultSetHoldability(int value) {
        this.value = value;
    }

    private int value;

    public int value() {
        return value;
    }

}
