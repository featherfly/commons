
package cn.featherfly.common.bean.rule;

/**
 * <p>
 * 属性复制的规则
 * </p>
 * 
 * @author zhongj
 */
public interface CopyRule {
    /** 
     * <p>
     * 是否复制
     * </p>
     * @param target 目标对象
     * @param from 源对象
     * @param propertyName 属性名
     * @param propertyValue 属性值
     * @return 是否复制
     */
    boolean isCopyEnabled(Object target, Object from, String propertyName, Object propertyValue);
}
