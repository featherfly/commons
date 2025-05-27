
package cn.featherfly.common.http;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicLong;

import javax.activation.MimeTypeParseException;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.lang.WordUtils;
import cn.featherfly.common.serialization.Serialization;
import cn.featherfly.common.server.User;
import cn.featherfly.common.structure.ChainMapImpl;
import cn.featherfly.common.structure.HashChainMap;

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
    public void get() throws MimeTypeParseException {
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
    public void postBody() throws MimeTypeParseException {
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

        HttpSyncClientImpl xmlClient = new HttpSyncClientImpl(new HttpRequestConfig(), Serialization.getDefault(),
            HttpUtils.XML_MEDIA_TYPE);

        result = xmlClient.post(url, user);
        System.out.println(result);
        assertEquals(result, OK);
    }

    @Test(expectedExceptions = HttpException.class)
    public void postBodyContentTypeError() throws MimeTypeParseException {
        String json = "{\"name\":\"yufei羽飞\",\"age\":18}";
        String url = Strings.format(host, "user");
        String result = Http.post(url, json);
        System.out.println(result);
        assertEquals(result, OK);
    }

    @Test
    public void upload() throws MimeTypeParseException {
        String url = Strings.format(host, "upload");

        String result = Http.post(url,
            new HashChainMap<String, Serializable>().putChain("key", "key2")
                .putChain("file1", new UploadFile("filename1.txt", "text/plain", "abcde".getBytes()))
                .putChain("file2", new UploadFile("测试中文名.txt", "text/plain", "12345".getBytes())));

        assertEquals(result, OK);
    }

    @Test
    public void download() throws MimeTypeParseException {
        AtomicLong count = new AtomicLong(0);
        //        String url = "https://mirrors.openanolis.cn/anolis/23.2/isos/GA/x86_64/AnolisOS-23.2-x86_64-boot.iso";
        String url = "https://mirrors.cloud.tencent.com/debian/dists/Debian12.11/main/binary-amd64/Packages.xz";
        File file = new File(SystemPropertyUtils.getJavaIoTmpdir() + "/download/" + System.currentTimeMillis());

        long size =
            Http.download(url, file,
                (r, t) -> {
                    count.set(r);
                    System.out.print(
                        Strings.format("\r文件大小：{0}, 下载：{1}， 进度：{2}%", WordUtils.parseUnit(t), WordUtils.parseUnit(r),
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
