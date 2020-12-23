package cn.featherfly.common.flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.featherfly.common.flux.action.Action.Type;
import cn.featherfly.common.flux.store.Store;
import cn.featherfly.common.flux.store.StoreAction;

public class ResourcesStroe extends Store<ResourcesAction<?>> {

    private static final ResourcesStroe STORE = new ResourcesStroe();

    private List<String> works;
    private List<String> interests;

    private ResourcesStroe() {
    }

    public static ResourcesStroe get() {
        return STORE;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Map<Type, StoreAction<ResourcesAction<?>>> initStoreActions() {
        Map<Type, StoreAction<ResourcesAction<?>>> storeActions = new HashMap<>();

        // 重新加载 works
        storeActions.put(ResourcesAction.ResourcesActionType.LOAD_WORK,
                action -> works = (List<String>) action.getData());
        storeActions.put(ResourcesAction.ResourcesActionType.LOAD_INTERESTS,
                action -> interests = (List<String>) action.getData());

        return storeActions;
    }

    public List<String> getWorks() {
        return works;
    }

    public List<String> getInterests() {
        return interests;
    }

    //    /**
    //     * {@inheritDoc}
    //     */
    //    @Override
    //    public StoreChangeEvent changeEvent(ResourcesAction<?> action) {
    //        return new StoreChangeEvent(action);
    //    }

}
