
package cn.featherfly.common.http;

import static org.testng.Assert.assertEquals;

import java.io.Serializable;

import javax.activation.MimeTypeParseException;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.server.User;
import cn.featherfly.common.structure.ChainMapImpl;
import cn.featherfly.common.structure.HashChainMap;

/**
 * Test.
 *
 * @author zhongj
 */
public class TestHttp {

    final String OK = "OK";

    final String host = "http://localhost:8080/{0}";

    User user;

    /**
     *
     */
    public TestHttp() {
        user = new User();
        user.setName("yufei羽飞");
        user.setAge(18);
    }

    @Test
    public void testGet() throws MimeTypeParseException {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String xml = "<User><name>yufei羽飞</name><age>18</age></User>";
        String url = Strings.format(host, "user");
        String result = Http.get(url);
        System.out.println(result);
        assertEquals(result, json);

        result = Http.get(url, null, new HashChainMap<String, String>().putChain("Accept", "application/json"));
        System.out.println(result);
        assertEquals(result, json);

        result = Http.get(url, null, new HashChainMap<String, String>().putChain("Accept", "application/xml"));
        System.out.println(result);
        assertEquals(result, xml);
    }

    @Test
    public void testPostBody() throws MimeTypeParseException {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String url = Strings.format(host, "user");
        String result = null;
        result = Http.post(url, json,
                new ChainMapImpl<String, String>().putChain("content-type", "application/json; charset=utf-8"));
        System.out.println(result);
        assertEquals(result, OK);

        result = Http.post(url, user);
        System.out.println(result);
        assertEquals(result, OK);

        HttpSyncClient xmlClient = new HttpSyncClient(new HttpRequestConfig(), Serialization.getDefault(),
                HttpUtils.XML_MEDIA_TYPE);

        result = xmlClient.post(url, user);
        System.out.println(result);
        assertEquals(result, OK);
    }

    @Test(expectedExceptions = HttpException.class)
    public void testPostBodyContentTypeError() throws MimeTypeParseException {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String url = Strings.format(host, "user");
        String result = Http.post(url, json);
        System.out.println(result);
        assertEquals(result, OK);
    }

    @Test
    public void testUpload() throws MimeTypeParseException {
        String url = Strings.format(host, "upload");

        String result = Http.post(url,
                new HashChainMap<String, Serializable>().putChain("key", "key2")
                        .putChain("file1", new UploadFile("filename1.txt", "text/plain", "abcde".getBytes()))
                        .putChain("file2", new UploadFile("测试中文名.txt", "text/plain", "12345".getBytes())));

        assertEquals(result, OK);
    }
}
