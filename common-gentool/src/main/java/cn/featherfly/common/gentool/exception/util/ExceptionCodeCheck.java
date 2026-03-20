
package cn.featherfly.common.gentool.exception.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ArrayUtils;

/**
 * ExceptionCodeCheck.
 *
 * @author zhongj
 */
public final class ExceptionCodeCheck {

    private ExceptionCodeCheck() {
    }

    /**
     * checkCode
     *
     * @param exceptionCodeType exceptionCodeType
     */
    public static void checkCode(Class<?> exceptionCodeType) {
        ExceptionCodeCheckInstance.INSTANCE.checkCodeDuplicate(exceptionCodeType);
    }

    /**
     * checkCode
     *
     * @param exceptionCodeTypes exceptionCodeTypes
     */
    public static void checkCode(Collection<Class<?>> exceptionCodeTypes) {
        exceptionCodeTypes.forEach(ExceptionCodeCheck::checkCode);
    }

    /**
     * checkCode
     *
     * @param exceptionCodeTypes exceptionCodeTypes
     */
    public static void checkCode(Class<?>[] exceptionCodeTypes) {
        checkCode(ArrayUtils.toList(exceptionCodeTypes));
    }

    private enum ExceptionCodeCheckInstance {

        INSTANCE;

        private final Map<String, Class<?>> codes = new HashMap<>(0);

        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        private void checkCodeDuplicate(Class<?> exceptionCodeType) {
            // Class codeEnumType = ClassUtils.forName(exceptionCodeType.getName() + "$" + GenExceptionCode.class.getSimpleName() + "s");
            Class<?> codeEnumType = exceptionCodeType.getClasses()[0];
            for (Object e : codeEnumType.getEnumConstants()) {
                String code = e.toString();
                logger.debug("check code {} declared in type {}", code, exceptionCodeType.getName());
                Class<?> t = codes.put(e.toString(), exceptionCodeType);
                if (t != null && t != exceptionCodeType) {
                    throw new IllegalArgumentException("duplicate code " + code
                        + " declared in type " + exceptionCodeType.getName() + " "
                        + t.getName());
                }
            }
        }

    }
}
