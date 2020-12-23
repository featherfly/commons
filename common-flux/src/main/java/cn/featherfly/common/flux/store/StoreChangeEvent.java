package cn.featherfly.common.flux.store;

import cn.featherfly.common.flux.action.Action;

/**
 * The Class StoreChangeEvent.
 *
 * @author zhongj
 */
public class StoreChangeEvent {

    private Action<?> action;

    /**
     * Instantiates a new store change event.
     *
     * @param action the action
     */
    public StoreChangeEvent(Action<?> action) {
        super();
        this.action = action;
    }

    /**
     * get action value
     *
     * @return action
     */
    @SuppressWarnings("unchecked")
    public <D> Action<D> getAction() {
        return (Action<D>) action;
    }

    /**
     * 返回data.
     *
     * @param <D> the generic type
     * @return data
     */
    @SuppressWarnings("unchecked")
    public <D> D getData() {
        return (D) action.getData();
    }
}