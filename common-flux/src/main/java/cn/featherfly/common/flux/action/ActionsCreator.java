package cn.featherfly.common.flux.action;

import cn.featherfly.common.flux.dispatcher.Dispatcher;

/**
 * The Class ActionsCreator.
 *
 * @author zhongj
 */
public abstract class ActionsCreator {

    /** The dispatcher. */
    protected final Dispatcher dispatcher;

    //    private static final Map<Class<?>, Object> CREATORS = new HashMap<>();

    /**
     * Instantiates a new actions creator.
     *
     * @param dispatcher the dispatcher
     */
    protected ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    //    /**
    //     * Gets the ActionsCreator.
    //     *
    //     * @param <C>  the generic type
    //     * @param type the type
    //     * @return the c
    //     */
    //    public static <C extends ActionsCreator> C get(Class<C> type) {
    //        @SuppressWarnings("unchecked")
    //        C c = (C) CREATORS.get(type);
    //        if (c == null) {
    //            try {
    //                Constructor<C> constructor = type.getDeclaredConstructor(Dispatcher.class);
    //                constructor.setAccessible(true);
    //                c = constructor.newInstance(Dispatcher.get());
    //                CREATORS.put(type, c);
    //            } catch (Exception e) {
    //                throw new RuntimeException(e);
    //            }
    //        }
    //        return c;
    //    }
}