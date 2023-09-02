
package cn.featherfly.common.lang.string;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.vo.User;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * <p>
 * StringFormatterTest
 * </p>
 *
 * @author zhongj
 */
public class StringFormatterTest {

    String expected = "hello yufei at 2020 from [yi] at 12:15";
    String expectedUser = "hello featherfly , age is 20";
    String name = "yufei";
    String name2 = "yi";
    int year = 2020;
    String time = "12:15";
    String actual = null;

    User user = new User("featherfly", 20);

    Map<String, Object> argsMap = new ChainMapImpl<String, Object>().putChain("name", name).putChain("year", year)
            .putChain("name2", name2).putChain("time", time);

    Object[] args = new Object[] { name, year, name2, time };

    @Test
    public void test1() {
        StringFormatter formatter = new StringFormatter('{', '}');

        actual = formatter.format("hello {name} at {year} from [{name2}] at {time}", argsMap);
        assertEquals(actual, expected);

        actual = formatter.format("hello {name} , age is {age}", user);
        assertEquals(actual, expectedUser);

        formatter = new StringFormatter('$', '$');

        actual = formatter.format("hello $name$ at $year$ from [$name2$] at $time$", argsMap);
        assertEquals(actual, expected);
    }

    @Test
    public void test2() {
        StringFormatter formatter = new StringFormatter('{', '}');

        actual = formatter.format("hello {0} at {1} from [{2}] at {3}", args);
        assertEquals(actual, expected);

        formatter = new StringFormatter('$', '$');

        actual = formatter.format("hello $0$ at $1$ from [$2$] at $3$", args);
        assertEquals(actual, expected);
    }

    @Test
    public void test3() {
        StringFormatter formatter = new StringFormatter('{', '}');
        String str = "hello {yufei} {} at {2020}";
        assertEquals(formatter.format("hello {{{0}}} {{}} at {{{1}}}", name, year), str);

        assertEquals(formatter.format("hello {{{name}}} {{}} at {{{year}}}", argsMap), str);
    }

    @Test
    public void test4() {
        StringFormatter formatter = new StringFormatter('$', '$');
        String str = "hello $yufei$ $$ at $2020$";
        assertEquals(formatter.format("hello $$$0$$$ $$$$ at $$$1$$$", name, year), str);

        assertEquals(formatter.format("hello $$$name$$$ $$$$ at $$$year$$$", argsMap), str);
    }

    @Test
    public void test5() {
        StringFormatter formatter = new StringFormatter('{', '}', true);

        //        actual = formatter.format("hello {name} at {year} from [{name2}] at {time}", argsMap);
        actual = formatter.format("hello {} at {} from [{}] at {}", name, year, name2, time);
        assertEquals(actual, expected);

        assertEquals(formatter.format("hello {} at {} from [{0}] at {}", name, year, time),
                "hello yufei at 2020 from [yufei] at 12:15");

        actual = formatter.format("hello {} array:{}", name, new Object[] { name, name2 });
        assertEquals(actual, "hello yufei array:[yufei,yi]");

    }
}
