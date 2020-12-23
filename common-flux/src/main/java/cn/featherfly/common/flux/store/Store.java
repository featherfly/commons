package cn.featherfly.common.flux.store;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.featherfly.common.bus.RxBus;
import cn.featherfly.common.flux.action.Action;
import cn.featherfly.common.flux.action.Action.Type;
import cn.featherfly.common.flux.dispatcher.Dispatcher;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * The Class Store.
 *
 * @author zhongj
 * @param <A> the generic type
 */
public abstract class Store<A extends Action<?>> {

    private static final RxBus bus = RxBus.getDefault();

    private Map<Type, StoreAction<A>> storeActions;

    private ConcurrentHashMap<StoreChangeListener, Disposable> toObserverableViews = new ConcurrentHashMap<>();

    /**
     * Instantiates a new store.
     */
    protected Store() {
        storeActions = initStoreActions();
        if (storeActions == null) {
            storeActions = new HashMap<>(0);
        }
        Dispatcher.get().register(this);
    }

    /**
     * Register.
     *
     * @param listener the listener
     */
    public void register(final StoreChangeListener listener) {
        if (toObserverableViews.containsKey(listener)) {
            return;
        }
        toObserverableViews.putIfAbsent(listener,
                bus.toObserverable(StoreChangeEvent.class).subscribe(event -> listener.onStoreChange(event)));
    }

    /**
     * Unregister.
     *
     * @param view the view
     */
    public void unregister(final StoreChangeListener view) {
        Disposable disposable = toObserverableViews.remove(view);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        //        if (disposable != null && !disposable.isUnsubscribed()) {
        //            disposable.unsubscribe();
        //        }
    }

    /**
     * On action.
     *
     * @param action the action
     */
    public void onAction(A action) {
        StoreAction<A> storeAction = storeActions.get(action.getType());
        if (storeAction != null) {
            storeAction.onAction(action);
        }
    }

    /**
     * Store change.
     *
     * @param action the action
     */
    protected void storeChange(A action) {
        bus.post(changeEvent(action));
    }

    /**
     * Inits the store actions.
     *
     * @return the map
     */
    //    protected abstract <AC extends Action<?>> Map<AC, StoreAction<A>> initStoreActions();
    protected abstract Map<Type, StoreAction<A>> initStoreActions();

    /**
     * Change event.
     *
     * @param action the action
     * @return the store change event
     */
    public StoreChangeEvent changeEvent(A action) {
        return new StoreChangeEvent(action);
    }
}