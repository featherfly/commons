
package cn.featherfly.common.bean.rule;

/**
 * 始终复制.
 *
 * @author zhongj
 */
public class CopyRuleAlwaysCopy implements CopyRule {
    /**
     *
     */
    public CopyRuleAlwaysCopy() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCopyEnabled(Object target, Object from, String propertyName, Object propertyValue) {
        return true;
    }
}
