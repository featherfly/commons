package cn.featherfly.common.flux;

import java.util.List;

import cn.featherfly.common.flux.action.ActionsCreator;
import cn.featherfly.common.flux.dispatcher.Dispatcher;

public class ResourcesActionsCreator extends ActionsCreator {

    public static ResourcesActionsCreator instance;

    private ResourcesActionsCreator(Dispatcher dispatcher) {
        super(dispatcher);
    }

    public static ResourcesActionsCreator get() {
        if (instance == null) {
            instance = new ResourcesActionsCreator(Dispatcher.get());
        }
        //        return ActionsCreator.get(ResourcesActionsCreator.class);
        return instance;
    }

    public void loadWorks(List<String> works) {
        dispatcher.dispatch(new ResourcesAction<>(ResourcesAction.ResourcesActionType.LOAD_WORK, works));
    }

    public void loadInterests(List<String> interests) {
        dispatcher.dispatch(new ResourcesAction<>(ResourcesAction.ResourcesActionType.LOAD_INTERESTS, interests));
    }
}
