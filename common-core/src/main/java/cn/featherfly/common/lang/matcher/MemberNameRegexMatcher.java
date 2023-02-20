
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Member;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配Member名称的实现.
 *
 * @param <T> Member泛型
 * @author zhongj
 */
public class MemberNameRegexMatcher<T extends Member> extends AbstractMemberMatcher<T> {

    private Pattern namePattern;

    private String nameRegex;

    /**
     * @param nameRegex 名称匹配的正则表达式（使用大小写敏感模式）
     */
    public MemberNameRegexMatcher(String nameRegex) {
        this(nameRegex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * @param nameRegex 名称匹配的正则表达式
     * @param flags     正则模式
     */
    public MemberNameRegexMatcher(String nameRegex, int flags) {
        this.nameRegex = nameRegex;
        namePattern = Pattern.compile(nameRegex, flags);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Member member) {
        Matcher m = namePattern.matcher(member.getName());
        boolean result = m.matches();
        if (logger.isDebugEnabled()) {
            logger.debug("目标成员 {} 匹配正则 {} 结果 {}", member.getName(), nameRegex, result);
        }
        return result;
    }
}
