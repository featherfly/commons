
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Member抽象匹配接口
 * </p>
 * 
 * @param <T> Member泛型
 * @author zhongj
 */
public abstract class AbstractMemberMatcher<T extends Member> implements MemberMatcher<T>{
    /**
     * logger
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
        
    /**
     */
    public AbstractMemberMatcher() {        
    }
}
