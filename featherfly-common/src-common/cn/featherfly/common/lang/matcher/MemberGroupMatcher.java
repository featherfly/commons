
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Member;

import cn.featherfly.common.data.GroupMatcher;
import cn.featherfly.common.enums.Logic;

/**
 * <p>
 * Member匹配组
 * </p>
 *
 * @author 钟冀
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
    public MemberGroupMatcher(Logic logic, @SuppressWarnings("unchecked") MemberMatcher<T>... matchers) {
        super(logic, matchers);
    }

    /**
     * @param matchers matchers
     */
    public MemberGroupMatcher(@SuppressWarnings("unchecked") MemberMatcher<T>... matchers) {
        super(matchers);
    }
    
    
}
