package cn.featherfly.common.lang.debug;

import java.util.function.Consumer;

/**
 * @author zhongj
 */
public abstract class AbstractDebugMessage<T extends AbstractDebugMessage<T>> extends TableMessage {

    private boolean debug;

    /**
     * Instantiates a new mapping debug message.
     *
     * @param debug the debug
     */
    protected AbstractDebugMessage(boolean debug) {
        this.debug = debug;
    }

    /**
     * Debug.
     *
     * @param consumer the consumer
     * @return the mapping debug message
     */
    @SuppressWarnings("unchecked")
    public T debug(Consumer<T> consumer) {
        if (debug && consumer != null) {
            consumer.accept((T) this);
        }
        return (T) this;
    }
}