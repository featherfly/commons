
package cn.featherfly.common.logging;

/**
 * <p>
 * LoggerSlf4jFactory
 * </p>
 * 
 * @author 钟冀
 * @version 1.3
 * @since 1.3
 */
public class Slf4jLoggerFactory implements LoggerFactory{

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(String name) {
        return new Slf4jLoggerProxy(org.slf4j.LoggerFactory.getLogger(name));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Logger getLogger(Class<?> clazz) {
        return new Slf4jLoggerProxy(org.slf4j.LoggerFactory.getLogger(clazz));
    }

}
