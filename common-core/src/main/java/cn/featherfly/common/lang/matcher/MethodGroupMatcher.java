
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Method;

import cn.featherfly.common.operator.LogicOperator;

/**
 * <p>
 * Method匹配组
 * </p>
 *
 * @author zhongj
 */
public class MethodGroupMatcher extends MemberGroupMatcher<Method>
    implements MethodMatcher{
    
    /**
     * 
     */
    public MethodGroupMatcher() {
        super();
    }

    /**
     * @param logic logic
     * @param matchers matchers
     */
    public MethodGroupMatcher(LogicOperator logic, MethodMatcher... matchers) {
        super(logic, matchers);
    }

    /**
     * @param matchers matchers
     */
    public MethodGroupMatcher(MethodMatcher... matchers) {
        super(matchers);
    }
    
    
}
