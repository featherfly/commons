
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Field;

/**
 * <p>
 * 正则匹配Field名称的实现
 * </p>
 * @author 钟冀
 */
public class FieldNameRegexMatcher extends MemberNameRegexMatcher<Field>
    implements FieldMatcher {

    /**
     *
     * @param nameRegex 名称匹配的正则表达式（使用大小写敏感模式）
     */
    public FieldNameRegexMatcher(String nameRegex) {
        super(nameRegex);
    }
    /**
     *
     * @param nameRegex 名称匹配的正则表达式
     * @param flags 正则模式 
     */
    public FieldNameRegexMatcher(String nameRegex, int flags) {
        super(nameRegex, flags);
    }
}
