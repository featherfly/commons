
package cn.featherfly.common.data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.featherfly.common.enums.Logic;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * 匹配器组
 * </p>
 * @param <M> 泛型
 * @param <O> 泛型
 * @author 钟冀
 */
public class GroupMatcher<M extends Matcher<O>, O> implements Matcher<O>{
    
    private Logic logic = Logic.AND;
    
    private Set<M> matchers = new HashSet<>();
    
    /**
     */
    public GroupMatcher() {
    }
    
    /**
     * @param matchers matchers
     */
    public GroupMatcher(@SuppressWarnings("unchecked") M...matchers) {        
        CollectionUtils.addAll(this.matchers, matchers);
    }
    /**
     * @param logic match logic
     * @param matchers matchers
     */
    public GroupMatcher(Logic logic, @SuppressWarnings("unchecked") M...matchers) {
        setLogic(logic);
        CollectionUtils.addAll(this.matchers, matchers);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(O o) {
        if (LangUtils.isEmpty(this.matchers)) {
            return false;
        }
        if (logic == Logic.AND) {
            return matchAnd(o, this.matchers);
        } else {
            return matchOr(o, this.matchers);
        }
    }
    /**
     * add matcher
     * @param matcher matcher
     */
    public void addMatcher(M matcher) {
        this.matchers.add(matcher);
    }
    /**
     * add matchers
     * @param matchers matchers
     */
    public void addMatcher(@SuppressWarnings("unchecked") M...matchers) {
        CollectionUtils.addAll(this.matchers, matchers);
    }
    /**
     * add matchers
     * @param matchers matchers
     */
    public void addMatcher(Collection<M> matchers) {
        this.matchers.addAll(matchers);
    }    
    
    private boolean matchAnd(O o, Collection<M> matchers) {
        boolean matchResult = false;
        for (M matcher : matchers) {
            matchResult = matcher.match(o);
            if (!matchResult) {
                return false;
            }
        }
        return true;
    }
    
    private boolean matchOr(O o, Collection<M> matchers) {
        boolean matchResult = false;
        for (M matcher : matchers) {
            matchResult = matcher.match(o);
            if (matchResult) {
                return true;
            }
        }
        return false;
    }
    
    // ********************************************************************
    //    setter and getter
    // ********************************************************************

    /**
     * 返回logic
     * @return logic
     */
    public Logic getLogic() {
        return logic;
    }

    /**
     * 设置logic
     * @param logic logic
     */
    public void setLogic(Logic logic) {
        AssertIllegalArgument.isNotNull(logic, "logic can not be null");        
        this.logic = logic;
    }

    /**
     * 返回matchers
     * @return matchers
     */
    public Set<M> getMatchers() {
        return matchers;
    }

    /**
     * 设置matchers
     * @param matchers matchers
     */
    public void setMatchers(Set<M> matchers) {
        this.matchers = matchers;
    }
}
