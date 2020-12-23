package cn.featherfly.common.flux.dispatcher;

import java.util.HashSet;
import java.util.Set;

import cn.featherfly.common.flux.action.Action;
import cn.featherfly.common.flux.store.Store;

public class Dispatcher {

    private static Dispatcher instance;

    private final Set<Store<?>> stores = new HashSet<>();

    public static Dispatcher get() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

    Dispatcher() {
    }

    public <A extends Action<?>> void register(final Store<A> store) {
        stores.add(store);
    }

    public <A extends Action<?>> void unregister(final Store<A> store) {
        stores.remove(store);
    }

    public void dispatch(Action<?> action) {
        post(action);
    }

    @SuppressWarnings("unchecked")
    private void post(final Action<?> action) {
        for (Store<?> store : stores) {
            ((Store<Action<?>>) store).onAction(action);
        }
    }
}