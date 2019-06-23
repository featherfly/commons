
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ClassUtils;

/**
 * <p>
 * 匹配Method的返回类型的实现
 * </p>
 * @author 钟冀
 */
public class MethodReturnTypeMatcher implements MethodMatcher{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodReturnTypeMatcher.class);

    private Class<?> returnType;
    
    private boolean matchSubType;

    /**
     * @param returnType 返回类型
     */
    public MethodReturnTypeMatcher(Class<?> returnType) {
        this.returnType = returnType;
    }
    /**
     * @param returnType 返回类型
     * @param matchSubType 是否匹配子类型
     */
    public MethodReturnTypeMatcher(Class<?> returnType, boolean matchSubType) {
        this.returnType = returnType;
        this.matchSubType = matchSubType;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(Method method) {
        if (returnType == null || method == null) {
            return false;
        }
        if (matchSubType) {
            LOGGER.debug("{} 匹配类型（包含子类型） {}", returnType.getName(), method.getReturnType().getName());
            return ClassUtils.isParent(returnType, method.getReturnType());
        } else {
            LOGGER.debug("{} 匹配类型 {}", returnType.getName(), method.getReturnType().getName());
            return returnType == method.getReturnType();
        }
    }
}
