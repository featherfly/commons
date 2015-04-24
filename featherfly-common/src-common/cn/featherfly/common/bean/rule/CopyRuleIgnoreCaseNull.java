
package cn.featherfly.common.bean.rule;

/**
 * <p>
 * 如果复制源属性值为null则不复制
 * </p>
 * 
 * @author 钟冀
 */
public class CopyRuleIgnoreCaseNull implements CopyRule{
    
    /**
     * 
     */
    public CopyRuleIgnoreCaseNull() {        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCopyEnabled(Object target, Object from, String propertyName, Object propertyValue) {
        if (propertyValue == null) {
            return false;
        }
        return true;
    }
}
