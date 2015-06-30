
package cn.featherfly.common.logging;

/**
 * <p>
 * LoggerFactory
 * </p>
 * 
 * @author 钟冀
 * @version 1.3
 * @since 1.3
 */
public interface LoggerFactory {
    /**
     * <p>
     * 根据name获取日志对象
     * </p>
     * @param name 名称
     * @return 日志对象
     */
    Logger getLogger(String name);
    /**
     * <p>
     * 根绝类型获取日志对象
     * </p>
     * @param clazz 类型
     * @return 日志对象
     */
    Logger getLogger(Class<?> clazz);
}
