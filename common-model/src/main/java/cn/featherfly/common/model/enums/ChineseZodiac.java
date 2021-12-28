
/*
 * All rights Reserved, Designed By zhongj
 * @Title: ChineseZodiacSigns.java
 * @Package cn.featherfly.common.model.enums
 * @Description: ChineseZodiac
 * @author: zhongj
 * @date: 2021-12-28 17:02:28
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.model.enums;

import cn.featherfly.common.model.Property;

/**
 * ChineseZodiac.
 * <p>
 * 十二生肖.
 * </p>
 *
 * @author zhongj
 */
public enum ChineseZodiac implements Property<Integer> {
    /**
     * 鼠
     */
    RAT,
    /**
     * 牛
     */
    OX,

    /**
     * 虎
     */
    TIGER,
    /**
     * 兔
     */
    RABBIT,
    /**
     * 龙
     */
    DRAGON,
    /**
     * 蛇
     */
    SNAKE,
    /**
     * 马
     */
    HORSE,
    /**
     * 羊
     */
    SHEEP,
    /**
     * 猴
     */
    MONKEY,
    /**
     * 鸡
     */
    ROOSTER,
    /**
     * 狗
     */
    DOG,
    /**
     * 猪
     */
    PIG;

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value() {
        return ordinal();
    }
}
