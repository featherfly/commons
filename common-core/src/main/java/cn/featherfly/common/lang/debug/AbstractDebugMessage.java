package cn.featherfly.common.lang.debug;

import java.util.Collection;
import java.util.Map;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public TableMessage addColumn(String column) {
        return debug ? super.addColumn(column) : this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TableMessage addColumn(String... columns) {
        return debug ? super.addColumn(columns) : this;
    }

    /**
     * Adds the column.
     *
     * @param columns the columns
     * @return the table message
     */
    @Override
    public TableMessage addColumn(Collection<String> columns) {
        return debug ? super.addColumn(columns) : this;
    }

    @Override
    public TableMessage addRow(Map<String, Object> row) {
        return debug ? super.addRow(row) : this;
    }

    @Override
    public String toString() {
        if (debug) {
            return super.toString();
        } else {
            return "";
        }
    }
}