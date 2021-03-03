
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 9:42:54 AM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.structure.HashChainMap;

public class StringsTest {
    @Test
    public void testSubstring() {
        String str = "cn.featherfly.common.lang.Strings";
        str = Strings.class.getName();
        int lastDot = str.lastIndexOf(".");
        String packageName = Strings.substringBefore(str, lastDot);
        String className = Strings.substringAfter(str, lastDot);
        System.out.println(packageName);
        System.out.println(className);
        assertEquals(packageName, Strings.class.getPackage().getName());
        assertEquals(className, Strings.class.getSimpleName());

        assertEquals(Strings.substringLast(str, 7), "Strings");
    }

    @Test
    public void testFormat() {
        String expected = "hello yufei at 2020 from [yi] at 12:15";
        String name = "yufei";
        String name2 = "yi";
        int year = 2020;
        String time = "12:15";

        Map<String, Object> map = new HashChainMap<String, Object>().putChain("name", name).putChain("year", year)
                .putChain("name2", name2).putChain("time", time);
        Object[] args = new Object[] { name, year, name2, time };

        String actual = null;

        actual = Strings.format("hello {name} at {year} from [{name2}] at {time}", map, '{', '}');
        assertEquals(actual, expected);

        actual = Strings.format("hello $name$ at $year$ from [$name2$] at $time$", map, '$', '$');
        assertEquals(actual, expected);

        actual = Strings.format("hello {0} at {1} from [{2}] at {3}", args, '{', '}');
        assertEquals(actual, expected);

        actual = Strings.format("hello $0$ at $1$ from [$2$] at $3$", args, '$', '$');
        assertEquals(actual, expected);

        actual = Strings.format("hello {name} at {year} from [{name2}] at {time}", map);
        assertEquals(actual, expected);

        actual = Strings.format("hello {0} at {1} from [{2}] at {3}", name, year, name2, time);
        assertEquals(actual, expected);

        // ------------------

        String str = "hello {yufei} {} at {2020}";
        assertEquals(Strings.format("hello {{{0}}} {{}} at {{{1}}}", new Object[] { name, year }, '{', '}'), str);

        assertEquals(Strings.format("hello {{{name}}} {{}} at {{{year}}}", map, '{', '}'), str);

        str = "hello $yufei$ $$ at $2020$";
        assertEquals(Strings.format("hello $$$0$$$ $$$$ at $$$1$$$", new Object[] { name, year }, '$'), str);

        assertEquals(Strings.format("hello $$$name$$$ $$$$ at $$$year$$$", map, '$'), str);
    }

    @Test
    public void testFormat2() {
        String name = "yufei";
        String expected = "hello yufei";
        String src = "hello {name}";
        String src2 = "hello {0}";

        //        System.out.println(Strings.format(src, new HashChainMap<String, Object>().putChain("name", name)));
        //        System.out.println(Strings.format(src2, name));

        assertEquals(Strings.format(src, new HashChainMap<String, Object>().putChain("name", name)), expected);
        assertEquals(Strings.format(src2, name), expected);
    }

    @Test
    public void testFormat3() {
        String expected = "{index:1, mid:2}";
        String src = "{index:{0}, mid:{1}}";
        int index = 1;
        int mid = 2;

        String actual = Strings.format(src, index, mid);

        System.out.println(actual);

        assertEquals(actual, expected);

        src = "{index:{index}, mid:{mid}}";

        actual = Strings.format(src, new HashChainMap<String, Object>().putChain("index", index).putChain("mid", mid));

        System.out.println(actual);

        assertEquals(actual, expected);
    }
}
