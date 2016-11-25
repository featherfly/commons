
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Member;

import cn.featherfly.common.lang.reflect.Modifier;

/**
 * <p>
 * 匹配Member Modifier的实现
 * </p>
 *
 * @author 钟冀
 */
public class MemberModifierMatcher<T extends Member> extends AbstractMemberMatcher<T>{
    
    private Modifier modifier = Modifier.PUBLIC;
    
    /**
     */
    public MemberModifierMatcher() {
    }
    
    /**
     * 
     * @param modifier modifier
     */
    public MemberModifierMatcher(Modifier modifier) {        
        this.modifier = modifier;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(T member) {
        if (logger.isDebugEnabled()) {
            logger.debug("目标成员 Modifiers {} 匹配 {}", java.lang.reflect.Modifier.toString(member.getModifiers()), Modifier.PUBLIC);
        }
        return this.modifier.isModifier(member.getModifiers());
    }
}
