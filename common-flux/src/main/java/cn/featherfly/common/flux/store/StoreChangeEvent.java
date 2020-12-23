package cn.featherfly.common.flux.store;

/**
 * The Class StoreChangeEvent.
 *
 * @author zhongj
 */
public class StoreChangeEvent {

    private Object data;

    /**
     * Instantiates a new store change event.
     *
     * @param data the data
     */
    public StoreChangeEvent(Object data) {
        super();
        this.data = data;
    }

    /**
     * 返回data.
     *
     * @param <M> the generic type
     * @return data
     */
    @SuppressWarnings("unchecked")
    public <M> M getData() {
        return (M) data;
    }
}