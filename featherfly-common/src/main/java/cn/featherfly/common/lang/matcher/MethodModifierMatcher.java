
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Method;

import cn.featherfly.common.lang.reflect.Modifier;

/**
 * <p>
 * 匹配Method Modifier的实现
 * </p>
 *
 * @author zhongj
 */
public class MethodModifierMatcher extends MemberModifierMatcher<Method>
    implements MethodMatcher{
    
    /**
     */
    public MethodModifierMatcher() {
    }
    
    /**
     * @param modifier modifier
     */
    public MethodModifierMatcher(Modifier modifier) {        
        super(modifier);
    }
}
