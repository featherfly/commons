
package cn.featherfly.common.logging;

import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.ServiceLoaderUtils;

/**
 * <p>
 * LoggerManager
 * </p>
 * 
 * @author 钟冀
 */
public class LoggerManager {
    
    private static final LoggerFactory FACTORY = ServiceLoaderUtils
            .load(LoggerFactory.class, new Slf4jLoggerFactory());
    
    /**
     * <p>
     * 根据调用者类型获取日志对象
     * </p>
     * 
     * @return 日志对象
     */
    public static Logger getLogger() {
        return FACTORY.getLogger(LangUtils.getInvoker().getClassName());
    }

    /**
     * <p>
     * 根据name获取日志对象
     * </p>
     * 
     * @param name
     *            名称
     * @return 日志对象
     */
    public static Logger getLogger(String name) {
        return FACTORY.getLogger(name);
    }

    /**
     * <p>
     * 根绝类型获取日志对象
     * </p>
     * 
     * @param clazz
     *            类型
     * @return 日志对象
     */
    public static Logger getLogger(Class<?> clazz) {
        return FACTORY.getLogger(clazz);
    }
}