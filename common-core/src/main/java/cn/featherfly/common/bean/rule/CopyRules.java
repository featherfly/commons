
package cn.featherfly.common.bean.rule;

/**
 * 自带的复制规则类型的枚举.
 *
 * @author zhongj
 */
public enum CopyRules {
    /**
     * ALWAYS 总是复制.
     */
    ALWAYS(new CopyRuleAlwaysCopy()),
    /**
     * IGNORE_NULL 忽略源对象为NULL的属性.
     */
    IGNORE_NULL(new CopyRuleIgnoreCaseNull()),
    /**
     * IGNORE_EMPTY 忽略源对象为EMPTY的属性（包含null并判断array,collection,map,string为empty）
     */
    IGNORE_EMPTY(new CopyRuleIgnoreCaseEmpty());

    private CopyRule copyRule;

    CopyRules(CopyRule copyRule) {
        this.copyRule = copyRule;
    }

    /**
     * get CopyRule
     *
     * @return 复制规则
     */
    public CopyRule getCopyRule() {
        return copyRule;
    }
}
