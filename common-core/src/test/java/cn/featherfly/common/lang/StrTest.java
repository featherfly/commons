
/*
 * Thgk-Commons copyright featherfly 2010-2020, all rights reserved. created on
 * May 21, 2010 9:42:54 AM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.vo.User;
import cn.featherfly.common.structure.ChainMapImpl;

public class StrTest {

    @Test
    public void trimFullWidthSpace() {
        String expected = "hello";

        String str = "  hello  ";
        assertEquals(Str.trimStartEndBlank(str), expected);

        str = "　　hello　　";
        assertEquals(Str.trimStartEndBlank(str), expected);

        str = " 　 　 hello 　 　 ";
        assertEquals(Str.trimStartEndBlank(str), expected);

        assertEquals(Str.trimStartEndBlank(expected), expected);

    }

    @Test
    public void isEmpty() {
        assertTrue(Str.isEmpty(""));
        assertFalse(Str.isEmpty("a"));
    }

    @Test
    public void testSubstring() {
        String str = "cn.featherfly.common.lang.Str";
        str = Str.class.getName();
        int lastDot = str.lastIndexOf(".");
        String packageName = Str.substringBefore(str, lastDot);
        String className = Str.substringAfter(str, lastDot);
        System.out.println(packageName);
        System.out.println(className);
        assertEquals(packageName, Str.class.getPackage().getName());
        assertEquals(className, Str.class.getSimpleName());

        assertEquals(Str.substringLast(str, 3), "Str");
    }

    @Test
    public void testFormat() {
        String expected = "hello yufei at 2020 from [yi] at 12:15";
        String name = "yufei";
        String name2 = "yi";
        int year = 2020;
        String time = "12:15";

        Map<String, Object> map = new ChainMapImpl<String, Object>().putChain("name", name).putChain("year", year)
            .putChain("name2", name2).putChain("time", time);
        Object[] args = new Object[] { name, year, name2, time };

        String actual = null;

        actual = Str.format("hello {name} at {year} from [{name2}] at {time}", map, '{', '}');
        assertEquals(actual, expected);

        actual = Str.format("hello $name$ at $year$ from [$name2$] at $time$", map, '$', '$');
        assertEquals(actual, expected);

        actual = Str.format("hello {0} at {1} from [{2}] at {3}", args, '{', '}');
        assertEquals(actual, expected);

        actual = Str.format("hello $0$ at $1$ from [$2$] at $3$", args, '$', '$');
        assertEquals(actual, expected);

        actual = Str.format("hello {name} at {year} from [{name2}] at {time}", map);
        assertEquals(actual, expected);

        actual = Str.format("hello {0} at {1} from [{2}] at {3}", name, year, name2, time);
        assertEquals(actual, expected);

        actual = Str.format("hello {} at {} from [{}] at {}", name, year, name2, time);
        assertEquals(actual, expected);

        actual = Str.format("hello {} at {} from [{0}] at {}", name, year, time);
        assertEquals(actual, "hello yufei at 2020 from [yufei] at 12:15");

        // ------------------

        String str = "hello {yufei} {} at {2020}";
        assertEquals(Str.format("hello {{{0}}} {{}} at {{{1}}}", new Object[] { name, year }, '{', '}'), str);

        assertEquals(Str.format("hello {{{name}}} {{}} at {{{year}}}", map, '{', '}'), str);

        str = "hello $yufei$ $$ at $2020$";
        assertEquals(Str.format("hello $$$0$$$ $$$$ at $$$1$$$", new Object[] { name, year }, '$'), str);

        assertEquals(Str.format("hello $$$name$$$ $$$$ at $$$year$$$", map, '$'), str);
    }

    @Test
    public void testFormat2() {
        String name = "yufei";
        String expected = "hello yufei";
        String src = "hello {name}";
        String src2 = "hello {0}";

        //        System.out.println(Str.format(src, new HashChainMap<String, Object>().putChain("name", name)));
        //        System.out.println(Str.format(src2, name));
        User user = new User("yufei", 15);

        assertEquals(Str.format(src, new ChainMapImpl<String, Object>().putChain("name", name)), expected);
        assertEquals(Str.format(src2, name), expected);
        assertEquals(Str.format(src2, new Object[] { name }), expected);
        assertEquals(Str.formatBean(src, user), expected);
    }

    @Test
    public void testFormat3() {
        String expected = "{index:1, mid:2}";
        String src = "{index:{0}, mid:{1}}";
        int index = 1;
        int mid = 2;

        String actual = Str.format(src, index, mid);

        System.out.println(actual);

        assertEquals(actual, expected);

        src = "{index:{index}, mid:{mid}}";

        actual = Str.format(src, new ChainMapImpl<String, Object>().putChain("index", index).putChain("mid", mid));

        System.out.println(actual);

        assertEquals(actual, expected);
    }

    @Test
    public void testUnicode() {
        assertEquals("\u3000", "　");
    }
}
