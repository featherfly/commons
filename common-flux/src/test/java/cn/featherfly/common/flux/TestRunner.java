
package cn.featherfly.common.flux;

import java.util.List;

import org.testng.annotations.Test;

import cn.featherfly.common.flux.ResourcesAction.ResourcesActionType;

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

    @Test
    void test2() {
        List<String> workList = (List<String>) ResourcesEnumTypeStroe.get().getData(ResourcesActionType.LOAD_WORK);
        System.out.println(workList);

        ResourcesStroe.get().register(e -> {
            System.out.println("change " + e.getAction());
        });

        ResourcesBLL r = new ResourcesBLL();
        r.loadResource();
        System.out.println(ResourcesEnumTypeStroe.get().getData(ResourcesActionType.LOAD_WORK).toString());
        System.out.println(ResourcesEnumTypeStroe.get().getData(ResourcesActionType.LOAD_INTERESTS).toString());
    }
}
