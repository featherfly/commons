package cn.featherfly.common.flux;

import cn.featherfly.common.flux.ResourcesAction.ResourcesActionType;
import cn.featherfly.common.flux.store.EnumTypeStore;

public class ResourcesEnumTypeStroe extends EnumTypeStore<ResourcesAction<?>, ResourcesActionType> {

    private static final ResourcesEnumTypeStroe STORE = new ResourcesEnumTypeStroe();

    private ResourcesEnumTypeStroe() {
    }

    public static ResourcesEnumTypeStroe get() {
        return STORE;
    }
}
