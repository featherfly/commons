
/*
 * All rights Reserved, Designed By zhongj
 * @Title: GroupableTest.java
 * @Package cn.featherfly.common.structure
 * @Description: GroupableTest
 * @author: zhongj
 * @date: 2021-11-19 16:49:19
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.structure;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

/**
 * GroupableTest.
 *
 * @author zhongj
 */
public class GroupableTest {

    @Test
    public void test() {
        Integer i = new Integer(0);
        Groupable<String, Integer> groupable = new Groupable<>(i);
        assertEquals(groupable.getValue(), i);
        assertEquals(groupable.getValue("aaa"), i);
        assertNull(groupable.getValueByGroup("aaa"));
    }
}
