package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * audit state.
 * 审核状态枚举.
 */
public enum AuditState implements Property<Integer> {
    /**
     * 审核中
     */
    AUDIT,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECT;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return ordinal();
    }

    /**
     * value of AuditState.
     *
     * @param value the value
     * @return the AuditState
     */
    public static AuditState valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        return valueOf(value.intValue());
    }

    /**
     * value of AuditState.
     *
     * @param value the value
     * @return the AuditState
     */
    public static AuditState valueOf(int value) {
        for (AuditState p : AuditState.values()) {
            if (p.value() == value) {
                return p;
            }
        }
        return null;
    }
}
