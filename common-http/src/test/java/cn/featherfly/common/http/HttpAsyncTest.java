
package cn.featherfly.common.http;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicLong;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Str;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.server.User;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * Test.
 *
 * @author zhongj
 */
public class HttpAsyncTest {

    final String OK = "OK";

    final String host = "http://localhost:8080/{0}";

    User user;

    /**
     *
     */
    public HttpAsyncTest() {
        user = new User();
        user.setName("yufei羽飞");
        user.setAge(18);
    }

    //    @Test
    //    public void listener() throws MimeTypeParseException {
    //        String url = Str.format(host, "user2");
    //        Result result = null;
    //        HttpSyncClientImpl http = new HttpSyncClientImpl();
    //        http.setDeserializeWithContentType(true);
    //        HttpListener listener = new HttpListener() {
    //            @Override
    //            public void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType) {
    //                Console.log("onDeserialize: mediaType = {}", mediaType.toString());
    //                System.out.println(new String(responseBody, mediaType.charset()));
    //            }
    //
    //            @Override
    //            public void onSerialize(Object requestBody, byte[] serializeBody, MediaType mediaType) {
    //                Console.log("onSerialize: mediaType = {}", mediaType.toString());
    //                System.out.println(new String(serializeBody, mediaType.charset()));
    //            }
    //        };
    //        HttpListener listener2 = new HttpListener() {
    //            @Override
    //            public void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType) {
    //                System.out.println("onDeserialize2");
    //            }
    //
    //            @Override
    //            public void onSerialize(Object requestBody, byte[] serializeBody, MediaType mediaType) {
    //                System.out.println("onSerialize2");
    //            }
    //        };
    //        HttpListener listener3 = new HttpListener() {
    //            @Override
    //            public void onDeserialize(byte[] responseBody, Object deserializeBody, MediaType mediaType) {
    //                System.out.println("onDeserialize3");
    //            }
    //
    //            @Override
    //            public void onSerialize(Object requestBody, byte[] serializeBody, MediaType mediaType) {
    //                System.out.println("onSerialize3");
    //            }
    //        };
    //        http.on(listener);
    //        http.on(listener3);
    //        http.on(listener2);
    //
    //        result = http.post(url, user, Result.class);
    //        System.out.println(result);
    //        assertEquals(result.getCode(), OK);
    //
    //        http.remove(listener);
    //        http.remove(listener2);
    //        http.remove(listener3);
    //        result = http.post(url, user, Result.class);
    //        System.out.println(result);
    //        assertEquals(result.getCode(), OK);
    //    }

    @Test
    public void get() throws InterruptedException {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String xml = "<User><name>yufei羽飞</name><age>18</age></User>";
        String url = Str.format(host, "user");
        HttpAsync.get(url).completion(result -> {
            System.out.println(result);
            assertEquals(result, json);
        }, er -> System.out.println(er.getMessage()));

        HttpAsync.get(url, null, new ChainMapImpl<String, String>().putChain("Accept", "application/json"))
            .completion(result -> {
                System.out.println(result);
                assertEquals(result, json);
            }, er -> System.out.println(er.getMessage()));

        HttpAsync.get(url, null, new ChainMapImpl<String, String>().putChain("Accept", "application/xml"))
            .completion(result -> {
                System.out.println(result);
                assertEquals(result, xml);
            }, er -> System.out.println(er.getMessage()));

        HttpAsync.get(url + "aaa", null, new ChainMapImpl<String, String>().putChain("Accept", "application/xml"))
            .completion(result -> {
                System.out.println(result);
                assertEquals(result, xml);
            }, er -> {
                // 发生网络错误和返回http code不是200都会进入这里
                System.out.println("error: " + er.getMessage());
            })
            .exceptionally(t -> {
                // 只有发生网络异常才会进入这里
                System.out.println("handler exceptional: " + t.getClass().getName());
                System.out.println("handler exceptional: " + t.getMessage());
                return t.getMessage();
            });

        Thread.sleep(1500);
    }

    @Test
    public void postBody() throws InterruptedException {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String url = Str.format(host, "user");
        HttpAsync.post(url, json,
            new ChainMapImpl<String, String>().putChain("content-type", "application/json; charset=utf-8"))
            .completion(result -> {
                System.out.println(result);
                assertEquals(result, OK);
            });

        HttpAsync.post(url, user).completion(result -> {
            System.out.println(result);
            assertEquals(result, OK);
        });

        HttpAsyncClientImpl xmlClient = new HttpAsyncClientImpl(new HttpRequestConfig(), Serialization.getDefault(),
            HttpUtils.XML_MEDIA_TYPE);

        xmlClient.post(url, user).completion(result -> {
            System.out.println(result);
            assertEquals(result, OK);
        });

        Thread.sleep(1500);
    }

    //    @Test(expectedExceptions = HttpException.class)
    //    public void postBodyContentTypeError() throws InterruptedException {
    //        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
    //        String url = Str.format(host, "user");
    //        HttpAsync.post(url, json).completion(result -> {
    //            System.out.println(result);
    //            assertEquals(result, OK);
    //        }, er -> {
    //            System.out.println(er.getMessage());
    //            throw new HttpException(er.getMessage());
    //        }).exceptionally(t -> {
    //            // 只有发生网络异常才会进入这里
    //            System.out.println("handler exceptional: " + t.getClass().getName());
    //            System.out.println("handler exceptional: " + t.getMessage());
    //            throw (HttpException) t;
    //        });
    //
    //        Thread.sleep(1000);
    //    }

    @Test
    public void upload() throws InterruptedException {
        String url = Str.format(host, "upload");

        HttpAsync.post(url,
            new ChainMapImpl<String, Serializable>().putChain("key", "key2")
                .putChain("file1", new UploadFile("filename1.txt", "text/plain", "abcde".getBytes()))
                .putChain("file2", new UploadFile("测试中文名.txt", "text/plain", "12345".getBytes())))
            .completion(result -> {
                System.out.println(result);
                assertEquals(result, OK);
            }, er -> {
                // 发生网络错误和返回http code不是200都会进入这里
                System.out.println("error: " + er.getMessage());
            })
            .exceptionally(t -> {
                // 只有发生网络异常才会进入这里
                System.out.println("handler exceptional: " + t.getClass().getName());
                System.out.println("handler exceptional: " + t.getMessage());
                return t.getMessage();
            });

        Thread.sleep(1500);
    }

    @Test
    public void download() throws InterruptedException {
        AtomicLong count = new AtomicLong(0);
        String url = "https://mirrors.cloud.tencent.com/debian/dists/stable/main/binary-amd64/Packages.xz";
        File file = new File(SystemPropertyUtils.getJavaIoTmpdir() + "/download/" + System.currentTimeMillis());

        HttpAsync.download(url, file,
            (r, t) -> {
                count.set(r);
                System.out.print(
                    Str.format("\r文件大小：{0}, 下载：{1}， 进度：{2}%", WordUtils.parseUnit(t), WordUtils.parseUnit(r),
                        BigDecimal.valueOf(r).divide(BigDecimal.valueOf(t), 4, RoundingMode.DOWN)
                            .multiply(BigDecimal.valueOf(100)).setScale(2, RoundingMode.DOWN).toString()));
            }).completion(size -> {
                System.out.println();
                System.out.println("download file: " + file.getAbsolutePath());
                assertEquals(size, count.longValue());
            }, er -> {
                // 发生网络错误和返回http code不是200都会进入这里
                System.out.println("error: " + er.getMessage());
            })
            .exceptionally(t -> {
                // 只有发生网络异常才会进入这里
                System.out.println("handler exceptional: " + t.getClass().getName());
                System.out.println("handler exceptional: " + t.getMessage());
                return -1L;
            });

        Thread.sleep(5000);
    }
}
