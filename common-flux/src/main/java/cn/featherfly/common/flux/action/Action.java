package cn.featherfly.common.flux.action;

/**
 * The Interface Action.
 *
 * @author zhongj
 * @param <T> the generic type
 */
public interface Action<T> {

    /**
     * The Interface Type.
     *
     * @author zhongj
     */
    public interface Type {

        /**
         * Name.
         *
         * @return the string
         */
        String name();
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    Type getType();

    /**
     * Gets the data.
     *
     * @return the data
     */
    T getData();

}