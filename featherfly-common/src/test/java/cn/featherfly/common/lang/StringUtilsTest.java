
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 9:42:54 AM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class StringUtilsTest {
    @Test
    public void testSubstring() {
        String str = "cn.featherfly.common.lang.StringUtils";
        str = StringUtils.class.getName();
        int lastDot = str.lastIndexOf(".");
        String packageName = StringUtils.substringBefore(str, lastDot);
        String className = StringUtils.substringAfter(str, lastDot);
        System.out.println(packageName);
        System.out.println(className);
        assertEquals(packageName, StringUtils.class.getPackage().getName());
        assertEquals(className, StringUtils.class.getSimpleName());

        assertEquals(StringUtils.substringLast(str, 5), "Utils");
    }

}
