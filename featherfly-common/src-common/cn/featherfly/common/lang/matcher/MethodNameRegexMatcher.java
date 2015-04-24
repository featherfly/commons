
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Method;

/**
 * <p>
 * 正则匹配Method名称的实现
 * </p>
 * @author 钟冀
 */
public class MethodNameRegexMatcher extends MemberNameRegexMatcher<Method>
    implements MethodMatcher {

    /**
     *
     * @param nameRegex 名称匹配的正则表达式（使用大小写敏感模式）
     */
    public MethodNameRegexMatcher(String nameRegex) {
        super(nameRegex);
    }
    /**
     *
     * @param nameRegex 名称匹配的正则表达式
     * @param flags 正则模式 
     */
    public MethodNameRegexMatcher(String nameRegex, int flags) {
        super(nameRegex, flags);
    }
}
