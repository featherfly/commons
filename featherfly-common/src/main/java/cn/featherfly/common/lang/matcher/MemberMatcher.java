package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Member;

import cn.featherfly.common.data.Matcher;

/**
 * <p>
 * Member匹配接口
 * </p>
 * @param <T> Member泛型
 * @author 钟冀
 */
public interface MemberMatcher<T extends Member> extends Matcher<T>{
    
}
