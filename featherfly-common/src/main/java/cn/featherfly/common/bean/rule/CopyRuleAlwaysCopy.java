
package cn.featherfly.common.bean.rule;

/**
 * <p>
 * 始终复制
 * </p>
 * 
 * @author 钟冀
 */
public class CopyRuleAlwaysCopy implements CopyRule{
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
