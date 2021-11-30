
/*
 * All rights Reserved, Designed By zhongj
 * @Title: Gender.java
 * @Package cn.featherfly.model.personal
 * @Description: Gender
 * @author: zhongj
 * @date: 2021-05-18 12:21:18
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * Gender.
 *
 * @author zhongj
 */
public enum Gender implements Property<Integer> {

    MALE(0), FAMALE(1), UNKNOW(2);

    private int value;

    /**
     * Instantiates a new gender.
     *
     * @param value the id
     */
    Gender(int value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return value;
    }

    /**
     * value of Gender.
     *
     * @param value the value
     * @return the platforms
     */
    public static Gender valueOf(Integer value) {
        if (value == null) {
            return null;
        }
        return valueOf(value.intValue());
    }

    /**
     * value of Gender.
     *
     * @param value the value
     * @return the platforms
     */
    public static Gender valueOf(int value) {
        for (Gender p : Gender.values()) {
            if (p.value() == value) {
                return p;
            }
        }
        return null;
    }
}
