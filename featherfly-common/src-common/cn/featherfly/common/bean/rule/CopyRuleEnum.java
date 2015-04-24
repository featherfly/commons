
package cn.featherfly.common.bean.rule;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 自带的复制规则类型的枚举
 * </p>
 *
 * @author 钟冀
 */
public enum CopyRuleEnum {
    /**
     * always 总是复制
     */
    always,
    /**
     * ignoreCaseNull 忽略源对象为NULL的属性
     */
    ignoreCaseNull,
    /**
     * ignoreCaseEmpty 忽略源对象为EMPTY的属性（包含null并判断array,collection,map,string为empty）
     */
    ignoreCaseEmpty;

    private static Map<CopyRuleEnum, CopyRule> copyRules;
    static {
        copyRules = new HashMap<CopyRuleEnum, CopyRule>();
        copyRules.put(CopyRuleEnum.always, new CopyRuleAlwaysCopy());
        copyRules.put(CopyRuleEnum.ignoreCaseNull, new CopyRuleIgnoreCaseNull());
        copyRules.put(CopyRuleEnum.ignoreCaseEmpty, new CopyRuleIgnoreCaseEmpty());
    }

    /**
     * <p>
     * 返回复制规则
     * </p>
     * @return 复制规则
     */
    public CopyRule getCopyRule() {
        return copyRules.get(this);
    }
}
