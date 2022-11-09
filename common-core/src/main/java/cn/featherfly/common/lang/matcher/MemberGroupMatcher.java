
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Member;

import cn.featherfly.common.data.GroupMatcher;
import cn.featherfly.common.enums.LogicOperator;

/**
 * <p>
 * Member匹配组
 * </p>
 * @param <T> Member泛型
 * @author zhongj
 */
public class MemberGroupMatcher<T extends Member> extends GroupMatcher<MemberMatcher<T>, T>
    implements MemberMatcher<T>{
    
    /**
     * 
     */
    public MemberGroupMatcher() {
        super();
    }

    /**
     * @param logic logic
     * @param matchers matchers
     */
    public MemberGroupMatcher(LogicOperator logic, @SuppressWarnings("unchecked") MemberMatcher<T>... matchers) {
        super(logic, matchers);
    }

    /**
     * @param matchers matchers
     */
    public MemberGroupMatcher(@SuppressWarnings("unchecked") MemberMatcher<T>... matchers) {
        super(matchers);
    }
    
    
}
