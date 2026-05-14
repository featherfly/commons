
package cn.featherfly.common.http;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicLong;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Console;
import cn.featherfly.common.lang.Str;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.server.Result;
import cn.featherfly.common.server.User;
import cn.featherfly.common.structure.ChainMapImpl;
import okhttp3.MediaType;

/**
 * Test.
 *
 * @author zhongj
 */
public class HttpTest {

    final String OK = "OK";

    final String host = "http://localhost:8080/{0}";

    User user;

    /**
     *
     */
    public HttpTest() {
        user = new User();
        user.setName("yufei羽飞");
        user.setAge(18);
    }

    @Test
    public void listener() {
        String url = Str.format(host, "user2");
        Result result = null;
        HttpSyncClientImpl http = new HttpSyncClientImpl();
        http.setDeserializeWithContentType(true);
        HttpListener listener = new HttpListener() {
            @Override
            public void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType) {
                Console.log("onDeserialize: mediaType = {}", mediaType.toString());
                System.out.println(new String(responseBody, mediaType.charset()));
            }

            @Override
            public void onSerialize(Object requestBody, byte[] serializeBody, MediaType mediaType) {
                Console.log("onSerialize: mediaType = {}", mediaType.toString());
                System.out.println(new String(serializeBody, mediaType.charset()));
            }
        };
        HttpListener listener2 = new HttpListener() {
            @Override
            public void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType) {
                System.out.println("onDeserialize2");
            }

            @Override
            public void onSerialize(Object requestBody, byte[] serializeBody, MediaType mediaType) {
                System.out.println("onSerialize2");
            }
        };
        HttpListener listener3 = new HttpListener() {
            @Override
            public void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType) {
                System.out.println("onDeserialize3");
            }

            @Override
            public void onSerialize(Object requestBody, byte[] serializeBody, MediaType mediaType) {
                System.out.println("onSerialize3");
            }
        };
        http.on(listener);
        http.on(listener3);
        http.on(listener2);

        result = http.post(url, user, Result.class);
        System.out.println(result);
        assertEquals(result.getCode(), OK);

        http.remove(listener);
        http.remove(listener2);
        http.remove(listener3);
        result = http.post(url, user, Result.class);
        System.out.println(result);
        assertEquals(result.getCode(), OK);

        //        HttpSyncClientImpl xmlClient = new HttpSyncClientImpl(new HttpRequestConfig(), Serialization.getDefault(),
        //            HttpUtils.XML_MEDIA_TYPE);
        //
        //        result = xmlClient.post(url, user, Result.class);
        //        System.out.println(result);
        //        assertEquals(result.getCode(), OK);
    }

    @Test
    public void get() {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String xml = "<User><name>yufei羽飞</name><age>18</age></User>";
        String url = Str.format(host, "user");
        String result = Http.get(url);
        System.out.println(result);
        assertEquals(result, json);

        result = Http.get(url, null, new ChainMapImpl<String, String>().putChain("Accept", "application/json"));
        System.out.println(result);
        assertEquals(result, json);

        result = Http.get(url, null, new ChainMapImpl<String, String>().putChain("Accept", "application/xml"));
        System.out.println(result);
        assertEquals(result, xml);
    }

    @Test
    public void postBody() {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String url = Str.format(host, "user");
        String result = null;
        result = Http.post(url, json,
            new ChainMapImpl<String, String>().putChain("content-type", "application/json; charset=utf-8"));
        System.out.println(result);
        assertEquals(result, OK);

        result = Http.post(url, user);
        System.out.println(result);
        assertEquals(result, OK);

        HttpSyncClientImpl xmlClient = new HttpSyncClientImpl(new HttpRequestConfig(), Serialization.getDefault(),
            HttpUtils.XML_MEDIA_TYPE);

        result = xmlClient.post(url, user);
        System.out.println(result);
        assertEquals(result, OK);
    }

    @Test(expectedExceptions = HttpException.class)
    public void postBodyContentTypeError() {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String url = Str.format(host, "user");
        String result = Http.post(url, json);
        System.out.println(result);
        assertEquals(result, OK);
    }

    @Test
    public void upload() {
        String url = Str.format(host, "upload");

        String result = Http.post(url,
            new ChainMapImpl<String, Serializable>().putChain("key", "key2")
                .putChain("file1", new UploadFile("filename1.txt", "text/plain", "abcde".getBytes()))
                .putChain("file2", new UploadFile("测试中文名.txt", "text/plain", "12345".getBytes())));

        assertEquals(result, OK);
    }

    @Test
    public void download() {
        AtomicLong count = new AtomicLong(0);
        //        String url = "https://mirrors.openanolis.cn/anolis/23.2/isos/GA/x86_64/AnolisOS-23.2-x86_64-boot.iso";
        String url = "https://mirrors.cloud.tencent.com/debian/dists/stable/main/binary-amd64/Packages.xz";
        File file = new File(SystemPropertyUtils.getJavaIoTmpdir() + "/download/" + System.currentTimeMillis());

        long size =
            Http.download(url, file,
                (r, t) -> {
                    count.set(r);
                    System.out.print(
                        Str.format("\r文件大小：{0}, 下载：{1}， 进度：{2}%", WordUtils.parseUnit(t), WordUtils.parseUnit(r),
                            BigDecimal.valueOf(r).divide(BigDecimal.valueOf(t), 4, RoundingMode.DOWN)
                                .multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.DOWN).toString()));
                });
        System.out.println();
        System.out.println("download file: " + file.getAbsolutePath());
        assertEquals(size, count.longValue());
    }

    public static void main(String[] args) {
        AtomicLong count = new AtomicLong(0);
        count.addAndGet(1);
        count.addAndGet(1);
        System.out.println(count.longValue());
    }
}
