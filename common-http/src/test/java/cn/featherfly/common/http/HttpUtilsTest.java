
package cn.featherfly.common.http;

import static org.testng.Assert.assertEquals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.structure.ChainMap;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * HttpUtilssTest.
 *
 * @author zhongj
 */
public class HttpUtilsTest {

    @Test
    public void appendParam() {
        String url = "www.baidu.com";

        String result = url;

        result = HttpUtils.appendParam(result, "id", 1);
        System.out.println(result);
        assertEquals(result, "www.baidu.com?id=1");

        result = HttpUtils.appendParam(result, "name", "yufei");
        System.out.println(result);
        assertEquals(result, "www.baidu.com?id=1&name=yufei");
    }

    @Test
    public void appendParamMulitiValues() {
        String url = "www.baidu.com";

        String result = url;
        result = HttpUtils.appendParam(result, "name", Lang.array("yufei", "yi"));
        System.out.println(result);
        assertEquals(result, "www.baidu.com?name=yufei&name=yi");
        result = HttpUtils.appendParam(result, "id", (ArrayList<Integer>) Lang.list(18, 20));
        System.out.println(result);
        assertEquals(result, "www.baidu.com?name=yufei&name=yi&id=18&id=20");
    }

    @Test
    public void appendParamMap() {
        String url = "www.baidu.com";

        String result = url;

        ChainMap<String, Serializable> params = new ChainMapImpl<>(new LinkedHashMap<String, Serializable>())
            .putChain("id", 1).putChain("name", "yufei");

        result = HttpUtils.appendParam(result, params);
        System.out.println(result);
        assertEquals(result, "www.baidu.com?id=1&name=yufei");

        ChainMap<String,
            Serializable> params2 = new ChainMapImpl<>(new LinkedHashMap<String, Serializable>()).putChain("age", 18);
        result = HttpUtils.appendParam(result, params2);
        assertEquals(result, "www.baidu.com?id=1&name=yufei&age=18");
        System.out.println(result);
    }

    @Test
    public void appendParamMapMulitiValues() {
        String url = "www.baidu.com";

        String result = url;

        ChainMap<String, Serializable> params = new ChainMapImpl<>(new LinkedHashMap<String, Serializable>())
            .putChain("id", new Integer[] { 1, 2 }).putChain("name", "yufei");

        result = HttpUtils.appendParam(url, params);
        System.out.println(result);
        assertEquals(result, "www.baidu.com?id=1&id=2&name=yufei");

        ChainMap<String, Serializable> params2 = new ChainMapImpl<>(new LinkedHashMap<String, Serializable>())
            .putChain("id", (ArrayList<Integer>) Lang.list(1, 2, 3)).putChain("name", Lang.array("yufei", "yi"));
        result = HttpUtils.appendParam(url, params2);
        assertEquals(result, "www.baidu.com?id=1&id=2&id=3&name=yufei&name=yi");
        System.out.println(result);

        ChainMap<String,
            Serializable> params3 = new ChainMapImpl<>(new LinkedHashMap<String, Serializable>()).putChain("age", "18");
        result = HttpUtils.appendParam(result, params3);
        assertEquals(result, "www.baidu.com?id=1&id=2&id=3&name=yufei&name=yi&age=18");
        System.out.println(result);

    }
}
