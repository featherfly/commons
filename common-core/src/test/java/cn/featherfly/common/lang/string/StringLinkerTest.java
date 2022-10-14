
/*
 * All rights Reserved, Designed By zhongj
 * @Title: StringLinkerTest.java
 * @Package cn.featherfly.common.lang.string
 * @Description: StringLinkerTest
 * @author: zhongj
 * @date: 2022-10-14 14:42:14
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.string;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * StringLinkerTest.
 *
 * @author zhongj
 */
public class StringLinkerTest {

    @Test
    void test1() {
        StringLinker linker = new StringLinker("-");
        linker.link("abc", "123", "-cde-", "-456-");
        //        "abc-123-cde-456-"
        assertEquals(linker.getValue(), "abc-123-cde-456-");
        //        System.out.println(linker.getValue());

        linker = new StringLinker("/");
        linker.link("/abc", "123", "/cde/", "/456/");
        assertEquals(linker.getValue(), "/abc/123/cde/456/");
    }
}
