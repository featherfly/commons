
package cn.featherfly.common.bean.rule;

import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * 如果复制源属性值为null或empty则不复制（empty判断array,collection,map,string）
 * </p>
 *
 * @author zhongj
 */
public class CopyRuleIgnoreCaseEmpty implements CopyRule{

    /**
     *
     */
    public CopyRuleIgnoreCaseEmpty() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCopyEnabled(Object target, Object from, String propertyName, Object propertyValue) {
        if (Lang.isEmpty(propertyValue)) {
            return false;
        }
        return true;
    }
}
