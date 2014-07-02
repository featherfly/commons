
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Method;

import cn.featherfly.common.enums.Logic;

/**
 * <p>
 * Method匹配组
 * </p>
 *
 * @author 钟冀
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
	 * @param logic
	 * @param matchers
	 */
	public MethodGroupMatcher(Logic logic, MethodMatcher... matchers) {
		super(logic, matchers);
	}

	/**
	 * @param matchers
	 */
	public MethodGroupMatcher(MethodMatcher... matchers) {
		super(matchers);
	}
	
	
}
