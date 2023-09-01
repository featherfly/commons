
package cn.featherfly.common.bean.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanProperty;

/**
 * 正则匹配名称的属性查找条件类.
 *
 * @author zhongj
 */
public class BeanPropertyNameRegexMatcher implements BeanPropertyMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanPropertyNameRegexMatcher.class);

    private Pattern namePattern;

    private String nameRegex;

    /**
     * @param nameRegex 名称匹配的正则表达式（使用大小写敏感模式）
     */
    public BeanPropertyNameRegexMatcher(String nameRegex) {
        this(nameRegex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * @param nameRegex 名称匹配的正则表达式
     * @param flags     正则模式
     */
    public BeanPropertyNameRegexMatcher(String nameRegex, int flags) {
        this.nameRegex = nameRegex;
        namePattern = Pattern.compile(nameRegex, flags);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(BeanProperty<?, ?> beanProperty) {
        LOGGER.debug("属性{}匹配正则{}", beanProperty.getName(), nameRegex);
        Matcher m = namePattern.matcher(beanProperty.getName());
        return m.matches();
    }
}
