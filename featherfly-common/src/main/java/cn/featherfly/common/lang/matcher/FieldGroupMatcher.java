
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Field;

import cn.featherfly.common.enums.Logic;

/**
 * <p>
 * Field匹配组
 * </p>
 *
 * @author 钟冀
 */
public class FieldGroupMatcher extends MemberGroupMatcher<Field>
    implements FieldMatcher{
    
    /**
     * 
     */
    public FieldGroupMatcher() {
        super();
    }

    /**
     * @param logic logic
     * @param matchers matchers
     */
    public FieldGroupMatcher(Logic logic, FieldMatcher... matchers) {
        super(logic, matchers);
    }

    /**
     * @param matchers matchers
     */
    public FieldGroupMatcher(FieldMatcher... matchers) {
        super(matchers);
    }
    
    
}
