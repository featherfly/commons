
package cn.featherfly.common.structure;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * <p>
 * ThreadLocalMapTest
 * </p>
 *
 * @author zhongj
 */
public class ThreadLocalMapTest {

    public static class Run implements Runnable {

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            ThreadLocalMapHolder.set("name", name);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " -> " + ThreadLocalMapHolder.get("name"));
            assertEquals(ThreadLocalMapHolder.get("name"), name);
        }
    }

    @Test
    public void test1() throws InterruptedException {
        String name = "yufei";
        ThreadLocalMapHolder.set("name", name);
        assertEquals(ThreadLocalMapHolder.get("name"), name);
        for (int i = 0; i < 20; i++) {
            new Thread(new Run()).start();
        }
        Thread.sleep(1500);
    }
}
