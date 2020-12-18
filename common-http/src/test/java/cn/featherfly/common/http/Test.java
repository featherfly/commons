
package cn.featherfly.common.http;

import java.util.concurrent.CompletableFuture;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import okhttp3.MediaType;

/**
 * Test.
 *
 * @author zhongj
 */
public class Test {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @org.testng.annotations.Test
    public void test1() throws MimeTypeParseException {
        System.out.println(JSON.type());
        System.out.println(JSON.subtype());
        System.out.println(JSON.type() + "/" + JSON.subtype());
        System.out.println(new MimeType(JSON.type(), JSON.subtype()));

        CompletableFuture.supplyAsync(() -> "yufei").thenAccept(s -> System.out.println(s));

        final CompletableFuture<String> completableFuture = new CompletableFuture<>();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            System.out.println("completableFuture.complete");
            completableFuture.complete("featherfly");
        }).start();

        completableFuture.thenAccept(s -> System.out.println(s));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // YUFEI_TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
