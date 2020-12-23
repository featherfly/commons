package cn.featherfly.common.flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zj on 2016/3/7.
 */
public class ResourcesBLL {

    public ResourcesBLL() {
    }

    public void loadResource() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        ResourcesActionsCreator.get().loadWorks(strings);
        List<String> strings2 = new ArrayList<>();
        strings2.add("in1");
        strings2.add("in2");
        strings2.add("in3");
        strings2.add("in4");
        ResourcesActionsCreator.get().loadInterests(strings2);
    }

    public void loadResource2() {
        List<String> strings = new ArrayList<>();
        strings.add("a2");
        strings.add("b2");
        strings.add("c2");
        strings.add("d2");
        ResourcesActionsCreator.get().loadWorks(strings);
        List<String> strings2 = new ArrayList<>();
        strings2.add("Interests1");
        strings2.add("Interests2");
        strings2.add("Interests3");
        strings2.add("Interests4");
        ResourcesActionsCreator.get().loadInterests(strings2);
    }
    //
    //    public void loadResource2() {
    //        List<String> strings = new ArrayList<>();
    //        strings.add("a2");
    //        strings.add("b2");
    //        strings.add("c2");
    //        strings.add("d2");
    //        ResourcesActionsCreator actionsCreator = ActionsCreator.get(ResourcesActionsCreator.class);
    //        actionsCreator.loadWorks(strings);
    //    }
}
