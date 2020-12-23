package cn.featherfly.common.flux;

import cn.featherfly.common.flux.action.SimpleAction;

public class ResourcesAction<T> extends SimpleAction<T> {

    public enum ResourcesActionType implements Type {
        LOAD_RESOURCES_VERSION,
        LOAD_WORK,
        LOAD_INTERESTS,
        LOAD_INDUSTRY,
        LOAD_DISTRICTS,
        LOAD_DISTANCE_RANGES,
        LOAD_SALARY_RANGES,
        LOAD_DISTRICT_DOMAIN,
        LOAD_CLOSETOSHOP_CONFIG,
        LOAD_WEBPAGE_URLS;
    }

    public ResourcesAction(ResourcesActionType type, T data) {
        super(type, data);
    }
}
