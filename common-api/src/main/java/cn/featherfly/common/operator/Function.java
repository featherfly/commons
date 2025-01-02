
package cn.featherfly.common.operator;

/**
 * Function.
 *
 * @author zhongj
 */
public interface Function {

    /**
     * function name.
     *
     * @return the string
     */
    String name();

    /**
     * Gets the parameter count.
     *
     * @return the parameter count
     */
    int getParameterCount();
}
