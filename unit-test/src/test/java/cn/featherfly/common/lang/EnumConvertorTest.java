
/*
 * All rights Reserved, Designed By zhongj
 * @Title: EnumConvertorTest.java
 * @Package cn.featherfly.common.lang
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-11-30 19:40:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import cn.featherfly.common.model.app.Platforms;

/**
 * EnumConvertorTest.
 *
 * @author zhongj
 */
public class EnumConvertorTest {

    @Test
    public void test() {
        assertEquals(Platforms.MOBILE_H5, Lang.toEnum(Platforms.class, Platforms.MOBILE_H5.value()));
    }
}
