package cn.featherfly.common.flux.store;

import cn.featherfly.common.flux.action.Action;

/**
 * The Interface StoreAction.
 *
 * @author zhongj
 * @param <A> the generic type
 */
public interface StoreAction<A extends Action<?>> {

    /**
     * On action.
     *
     * @param a the a
     */
    void onAction(A a);
}
