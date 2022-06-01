
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * 匹配Method的返回类型的实现
 * </p>
 *
 * @author zhongj
 */
public class MethodParamTypeMatcher implements MethodMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodParamTypeMatcher.class);

    private Class<?>[] parameterTypes;

    private boolean matchSubType;

    /**
     * @param parameterTypes 请求参数类型
     */
    public MethodParamTypeMatcher(Class<?>... parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Method method) {
        if (Lang.isEmpty(parameterTypes) || method == null) {
            return false;
        }
        Class<?>[] types = method.getParameterTypes();
        if (parameterTypes.length != types.length) {
            return false;
        }
        for (int i = 0; i < types.length; i++) {
            if (matchSubType) {
                if (!ClassUtils.isParent(parameterTypes[i], types[i])) {
                    return false;
                }
            } else {
                if (!types[i].equals(parameterTypes[i])) {
                    return false;
                }
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("parameterTypes {} match {} ", ArrayUtils.toString(parameterTypes),
                    ArrayUtils.toString(types));
        }
        return true;
    }
}
