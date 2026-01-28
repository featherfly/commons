
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-01-28 15:05:28
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Field;

import org.testng.annotations.Test;

/**
 * TypeNameHashCodesTest.
 *
 * @author zhongj
 */
public class TypeNamesTest {

    @Test
    public void nameHashCode() throws IllegalArgumentException, IllegalAccessException {
        String name = null;
        for (Field field : TypeNames.class.getDeclaredFields()) {
            if (field.getType() == String.class) {
                name = (String) field.get(TypeNames.class);
            } else {
                int hashCode = (int) field.get(TypeNames.class);
                System.out.println(name + ": " + hashCode);
                assertEquals(name.hashCode(), hashCode);
                name = null;
            }
        }
    }
}
