
package cn.featherfly.common.mqtt;

/**
 * <p>
 * Qos
 * </p>
 *
 * @author zhongj
 */
public enum Qos {
    /**
     * 最多一次
     */
    AT_MOST_ONCE,
    /**
     * 最少一次
     */
    AT_LEAST_ONCE,
    /**
     * 只有一次
     */
    ONLY_ONCE;
}
