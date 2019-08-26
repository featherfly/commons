
/**
 * @author zhongj - yufei Feb 25, 2009
 */
package cn.featherfly.common.lang;

import java.lang.reflect.Field;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhongj - yufei
 */
public class LogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);

    /**
     */
    public LogUtils() {
    }

    /**
     * trace
     *
     * @param logger
     * @param msg
     * @param loggerEnabled
     */
    public static void trace(Logger logger, String msg, LoggerEnabled loggerEnabled) {
        if (logger.isTraceEnabled()) {
            logger.trace(msg, loggerEnabled.arguments());
        }
    }

    /**
     * debug
     *
     * @param logger
     * @param msg
     * @param loggerEnabled
     */
    public static void debug(Logger logger, String msg, LoggerEnabled loggerEnabled) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, loggerEnabled.arguments());
        }
    }

    /**
     * info
     *
     * @param logger
     * @param msg
     * @param loggerEnabled
     */
    public static void info(Logger logger, String msg, LoggerEnabled loggerEnabled) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, loggerEnabled.arguments());
        }
    }

    /**
     * info
     *
     * @param logger
     * @param msg
     * @param loggerEnabled
     */
    public static void warn(Logger logger, String msg, LoggerEnabled loggerEnabled) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, loggerEnabled.arguments());
        }
    }

    /**
     * error
     *
     * @param logger
     * @param msg
     * @param loggerEnabled
     */
    public static void error(Logger logger, String msg, LoggerEnabled loggerEnabled) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, loggerEnabled.arguments());
        }
    }

    /**
     * <p>
     * 记录日志（debug级别）
     * </p>
     *
     * @param e   异常信息
     * @param log 日志对象
     */
    public static void debug(Exception e, Logger log) {
        if (log.isDebugEnabled()) {
            log.debug(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * <p>
     * 记录日志（warn级别）
     * </p>
     *
     * @param e   异常信息
     * @param log 日志对象
     */
    public static void warn(Exception e, Logger log) {
        if (log.isWarnEnabled()) {
            log.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * <p>
     * 记录日志（error级别）
     * </p>
     *
     * @param e   异常信息
     * @param log 日志对象
     */
    public static void error(Exception e, Logger log) {
        if (log.isErrorEnabled()) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * <p>
     * 记录日志（info级别）
     * </p>
     *
     * @param e   异常信息
     * @param log 日志对象
     */
    public static void info(Exception e, Logger log) {
        if (log.isInfoEnabled()) {
            log.info(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * <p>
     * 记录日志（trace级别）
     * </p>
     *
     * @param e   异常信息
     * @param log 日志对象
     */
    public static void trace(Exception e, Logger log) {
        if (log.isTraceEnabled()) {
            log.trace(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * <p>
     * 将对象属性信息图输出到日志
     * </p>
     *
     * @param target 对象
     * @param log    日志
     */
    public void debugObject(Object target, Logger log) {
        debugObject(target, log, null);
    }

    /**
     * <p>
     * 将对象属性信息图输出到日志
     * </p>
     *
     * @param target 对象
     * @param logger 日志
     * @param desc   前置描述
     */
    public void debugObject(Object target, Logger logger, String desc) {
        try {
            if (isEnabled() && logger.isDebugEnabled()) {
                String des = LangUtils.isEmpty(desc) ? "" : desc + " ->\n";
                String logMsg = des + getObjectInfo(target);
                logger.debug(logMsg);
            }
        } catch (Exception e) {
            debug(e, LOGGER);
        }
    }

    /**
     * <p>
     * 返回对象属性信息.发生异常返回null
     * </p>
     *
     * @param target 目标对象
     * @return 对象属性信息
     */
    public String getObjectInfo(Object target) {
        try {
            return getObjectInfo(target, 1);
        } catch (Exception e) {
            debug(e, LOGGER);
            return StringUtils.NULL_STRING;
        }
    }

    private String getObjectInfo(Object target, int level) throws IllegalAccessException {
        if (target == null) {
            return StringUtils.NULL_STRING;
        }
        Class<?> tc = target.getClass();
        StringBuilder sb = new StringBuilder();
        String spliter = "\n";
        String tab = "   ";
        Field[] fs = tc.getDeclaredFields();
        sb.append(tc.getName()).append(" {");
        for (Field field : fs) {
            field.setAccessible(true);
            sb.append(spliter);
            for (int i = 0; i < level; i++) {
                sb.append(tab);
            }
            sb.append(field.getName()).append(" = ");
            Object value = field.get(target);
            Class<?> filedType = field.getType();
            if (ClassUtils.isBasicType(filedType) || ClassUtils.isCellection(filedType)
                    || ClassUtils.isMap(filedType)) {
                sb.append(value);
            } else {
                if (level > getRecursionLevel()) {
                    sb.append(value);
                } else {
                    sb.append(getObjectInfo(value, level + 1));
                }
            }
        }
        sb.append(spliter);
        for (int i = 1; i < level; i++) {
            sb.append(tab);
        }
        sb.append("}");
        return sb.toString();
    }

    private int recursionLevel = 1;

    private boolean enabled = true;

    /**
     * @return 返回recursionLevel
     */
    public int getRecursionLevel() {
        return recursionLevel;
    }

    /**
     * @param recursionLevel 设置recursionLevel
     */
    public void setRecursionLevel(int recursionLevel) {
        if (recursionLevel < 1) {
            recursionLevel = 1;
        }
        this.recursionLevel = recursionLevel;
    }

    /**
     * @return 返回enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled 设置enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}