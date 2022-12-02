
package cn.featherfly.common.http;

import static org.testng.Assert.assertEquals;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.testng.annotations.Test;

import cn.featherfly.common.structure.ChainMap;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * HttpUtilssTest.
 *
 * @author zhongj
 */
public class HttpUtilsTest {

    @Test
    public void testAppendParam() {
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
    public void testAppendParams() {
        String url = "www.baidu.com";

        String result = url;

        ChainMap<String, Serializable> params = new ChainMapImpl<>(new LinkedHashMap<String, Serializable>())
                .putChain("id", 1).putChain("name", "yufei");

        result = HttpUtils.appendParams(result, params);
        System.out.println(result);
        assertEquals(result, "www.baidu.com?id=1&name=yufei");

        ChainMap<String, Serializable> params2 = new ChainMapImpl<>(new LinkedHashMap<String, Serializable>())
                .putChain("age", 18);
        result = HttpUtils.appendParams(result, params2);
        assertEquals(result, "www.baidu.com?id=1&name=yufei&age=18");
        System.out.println(result);

    }
}
