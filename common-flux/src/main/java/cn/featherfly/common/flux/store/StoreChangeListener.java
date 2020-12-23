
package cn.featherfly.common.flux.store;

/**
 * StoreChangeListener.
 *
 * @author zhongj
 */
public interface StoreChangeListener {

    /**
     * On store change.
     *
     * @param event the event
     */
    void onStoreChange(StoreChangeEvent event);
}
