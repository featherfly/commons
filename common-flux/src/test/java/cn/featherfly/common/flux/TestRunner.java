
package cn.featherfly.common.flux;

import org.testng.annotations.Test;

/**
 * TestRunner.
 *
 * @author zhongj
 */
public class TestRunner {

    @Test
    void test() {
        //        Dispatcher.get().register(ResourcesStroe.get());
        System.out.println(ResourcesStroe.get().getWorks());

        ResourcesStroe.get().register(e -> {
            System.out.println("change " + e.getAction());
        });

        ResourcesBLL r = new ResourcesBLL();
        r.loadResource();
        System.out.println(ResourcesStroe.get().getWorks());
        System.out.println(ResourcesStroe.get().getInterests());
        r.loadResource2();
        System.out.println(ResourcesStroe.get().getWorks());
        System.out.println(ResourcesStroe.get().getInterests());
    }

    //    @Test
    //    void test2() {
    //        //        Dispatcher.get().register(ResourcesStroe.get());
    //        System.out.println(ResourcesStroe.get().getWorks());
    //        ResourcesBLL r = new ResourcesBLL();
    //        r.loadResource2();
    //        System.out.println(ResourcesStroe.get().getWorks());
    //    }
}
