
/*
 * All rights Reserved, Designed By zhongj
 * @Title: NameTest.java
 * @Package cn.featherfly.common.validation
 * @Description: NameTest
 * @author: zhongj
 * @date: 2021-12-30 16:22:30
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.validation;

import org.testng.annotations.Test;

/**
 * NameTest.
 *
 * @author zhongj
 */
public class NameTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testNameNull() {
        new Name().name(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void testName2Null() {
        new Name().name2(null);
    }
}
