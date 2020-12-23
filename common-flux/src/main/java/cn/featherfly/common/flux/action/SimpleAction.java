package cn.featherfly.common.flux.action;

/**
 * The Class SimpleAction.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public class SimpleAction<T> implements Action<T> {

    private T data;

    private Type type;

    /**
     * Instantiates a new simple action.
     *
     * @param type the type
     * @param data the data
     */
    public SimpleAction(Type type, T data) {
        super();
        this.data = data;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getData() {
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return type;
    }

}