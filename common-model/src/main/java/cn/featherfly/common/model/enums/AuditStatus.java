package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * 审核状态枚举
 */
public enum AuditStatus implements Property<Integer> {
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
}
