
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
        BrowerHttpClient brower = new BrowerHttpClient();

        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=wwhuWsqLYCMuFPE8fNVPk4iUNzYCIFAk&output=json&coordtype=wgs84ll&location=30.664416,104.072294";

        try {
            ReverseGeocoding reverseGeocoding = brower.get(url, new HashMap<>(), ReverseGeocoding.class);
            System.out.println(reverseGeocoding);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        AtomicBoolean executed = new AtomicBoolean(false);

        //        brower.getCompletion(url, new HashMap<>(), ReverseGeocoding.class).completion(r -> {
        //            System.out.println(r);
        //            executed.set(true);
        //        }).error(e -> {
        //            System.out.println(e.getMessage());
        //            executed.set(true);
        //        });
        //
        //        while (!executed.get()) {
        //
        //        }

        brower.shutdown();

        System.out.println("end");

    }

    @Test
    public void testServer() {
        HttpClientRequest request = new HttpClientRequest(new HttpRequestConfig());

        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=0Zj0WEeN8dTr8XMMAFal5LPE8k5D53tR&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194";

        ReverseGeocoding reverseGeocoding = request.send(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class,
                (ErrorListener) error -> {
                    System.out.println(error.getMessage());
                });

        System.out.println(reverseGeocoding);

        AtomicBoolean executed = new AtomicBoolean(false);

        request.sendCompletion(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class).completion(r -> {
            System.out.println(r);
            executed.set(true);
        }).error(e -> {
            System.out.println(e.getMessage());
            executed.set(true);
        });

        request.sendCompletion(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class).completion(r -> {
            System.out.println("Completion2");
            System.out.println(r);
            executed.set(true);
        }, e -> {
            System.out.println("Completion2 error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        request.sendObservable(HttpMethod.GET, url, new HashMap<>(), ReverseGeocoding.class).subscribe(r -> {
            System.out.println("Observable");
            System.out.println(r);
            executed.set(true);
        }, e -> {
            System.out.println("Observable error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        while (!executed.get()) {

        }

        request.shutdown();

        System.out.println("end");
    }

    @Test
    public void testServerWithHttpClient() {
        HttpClient client = new HttpClient();

        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=0Zj0WEeN8dTr8XMMAFal5LPE8k5D53tR&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194";

        ReverseGeocoding reverseGeocoding = client.get(url, ReverseGeocoding.class);

        System.out.println(reverseGeocoding);

        AtomicBoolean executed = new AtomicBoolean(false);

        HttpAsyncClient asyncClient = new HttpAsyncClient();

        asyncClient.get(url, ReverseGeocoding.class).completion(r -> {
            System.out.println(r);
            executed.set(true);
        }).error(e -> {
            System.out.println(e.getMessage());
            executed.set(true);
        });

        asyncClient.get(url, ReverseGeocoding.class).completion(r -> {
            System.out.println("Completion2");
            System.out.println(r);
            executed.set(true);
        }, e -> {
            System.out.println("Completion2 error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        HttpRxjavaClient rxjavaClient = new HttpRxjavaClient();
        rxjavaClient.get(url, ReverseGeocoding.class).subscribe(r -> {
            System.out.println("Observable");
            System.out.println(r);
            executed.set(true);
        }, e -> {
            System.out.println("Observable error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        while (!executed.get()) {

        }

        rxjavaClient.shutdown();

        System.out.println("end");
    }

    @Test
    public void testServerWithHttp() {
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=0Zj0WEeN8dTr8XMMAFal5LPE8k5D53tR&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194";

        ReverseGeocoding reverseGeocoding = Http.get(url, ReverseGeocoding.class);

        System.out.println(reverseGeocoding);

        AtomicBoolean executed = new AtomicBoolean(false);

        HttpAsync.get(url, ReverseGeocoding.class).completion(r -> {
            System.out.println(r);
            executed.set(true);
        }).error(e -> {
            System.out.println(e.getMessage());
            executed.set(true);
        });

        HttpAsync.get(url, ReverseGeocoding.class).completion(r -> {
            System.out.println("Completion2");
            System.out.println(r);
            executed.set(true);
        }, e -> {
            System.out.println("Completion2 error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        HttpRxjava.get(url, ReverseGeocoding.class).subscribe(r -> {
            System.out.println("Observable");
            System.out.println(r);
            executed.set(true);
        }, e -> {
            System.out.println("Observable error");
            System.out.println(e.getMessage());
            executed.set(true);
        });

        while (!executed.get()) {

        }

        Http.shutdown();

        System.out.println("end");

    }

    public static void main(String[] args) {

        //        Http.download(
        //                "http://api.map.baidu.com/reverse_geocoding/v3/?ak=0Zj0WEeN8dTr8XMMAFal5LPE8k5D53tR&output=json&coordtype=wgs84ll&location=31.225696563611,121.49884033194",
        //                new File("reverse_geocoding.json"));

    }

}
