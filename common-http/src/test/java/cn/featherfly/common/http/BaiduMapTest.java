
package cn.featherfly.common.http;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.testng.annotations.Test;

import cn.featherfly.common.http.po.ReverseGeocoding;

/**
 * BaiduMapTest.
 *
 * @author zhongj
 */
public class BaiduMapTest {

    @Test
    public void testBrower() {
        OkHttpRequest request = new OkHttpRequest(new HttpRequestConfig());

        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=wwhuWsqLYCMuFPE8fNVPk4iUNzYCIFAk&output=json&coordtype=wgs84ll&location=30.664416,104.072294";

        ReverseGeocoding reverseGeocoding = request.send(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class,
                (ErrorListener) error -> {
                    System.out.println(error.getMessage());
                });

        System.out.println(reverseGeocoding);

        AtomicBoolean executed = new AtomicBoolean(false);

        request.send(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class).completion(r -> {
            System.out.println(r);
            executed.set(true);
        }).error(e -> {
            System.out.println(e.getMessage());
            executed.set(true);
        });

        while (!executed.get()) {

        }

        request.shutdown();

        System.out.println("end");

    }

    @Test
    public void testServer() {
        OkHttpRequest request = new OkHttpRequest(new HttpRequestConfig());

        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=0Zj0WEeN8dTr8XMMAFal5LPE8k5D53tR&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194";

        ReverseGeocoding reverseGeocoding = request.send(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class,
                (ErrorListener) error -> {
                    System.out.println(error.getMessage());
                });

        System.out.println(reverseGeocoding);

        AtomicBoolean executed = new AtomicBoolean(false);

        request.send(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class).completion(r -> {
            System.out.println(r);
            executed.set(true);
        }).error(e -> {
            System.out.println(e.getMessage());
            executed.set(true);
        });

        while (!executed.get()) {

        }

        request.shutdown();

        System.out.println("end");
    }

    public static void main(String[] args) {

        //        Http.download(
        //                "http://api.map.baidu.com/reverse_geocoding/v3/?ak=0Zj0WEeN8dTr8XMMAFal5LPE8k5D53tR&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194",
        //                new File("reverse_geocoding.json"));

    }

}
