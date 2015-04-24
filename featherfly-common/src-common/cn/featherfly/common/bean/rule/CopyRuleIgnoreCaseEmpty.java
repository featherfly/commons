
package cn.featherfly.common.bean.rule;

import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * 如果复制源属性值为null或empty则不复制（empty判断array,collection,map,string）
 * </p>
 *
 * @author 钟冀
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
        if (LangUtils.isEmpty(propertyValue)) {
            return false;
        }
        return true;
    }
}
