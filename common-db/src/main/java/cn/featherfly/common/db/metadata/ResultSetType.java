
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
 * ResultSetType.
 *
 * @author zhongj
 */
public enum ResultSetType {
    /**
     * The constant indicating the type for a {@code ResultSet} object whose
     * cursor may move only forward.
     *
     * @see java.sql.ResultSet#TYPE_FORWARD_ONLY
     */
    FORWARD_ONLY(ResultSet.TYPE_FORWARD_ONLY),
    /**
     * The constant indicating the type for a {@code ResultSet} object that is
     * scrollable but generally not sensitive to changes to the data that
     * underlies the {@code ResultSet}.
     *
     * @see java.sql.ResultSet#TYPE_SCROLL_INSENSITIVE
     */
    SCROLL_INSENSITIVE(ResultSet.TYPE_SCROLL_INSENSITIVE),

    /**
     * The constant indicating the type for a {@code ResultSet} object that is
     * scrollable and generally sensitive to changes to the data that underlies
     * the {@code ResultSet}.
     *
     * @see java.sql.ResultSet#TYPE_SCROLL_SENSITIVE
     */
    SCROLL_SENSITIVE(ResultSet.TYPE_SCROLL_SENSITIVE);

    ResultSetType(int value) {
        this.value = value;
    }

    private int value;

    public int value() {
        return value;
    }

}
