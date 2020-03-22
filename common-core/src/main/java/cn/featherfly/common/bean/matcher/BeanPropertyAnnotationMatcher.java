
package cn.featherfly.common.bean.matcher;

import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.enums.Logic;

/**
 * <p>
 * 注解匹配的属性查找条件类
 * </p>
 * @author zhongj
 */
public class BeanPropertyAnnotationMatcher implements BeanPropertyMatcher{

    private Class<?>[] annotationClasses;

    /**
     * 使用并集判断逻辑来匹配.
     * @param annotationClasses 注解类型
     */
    public BeanPropertyAnnotationMatcher(Class<?>...annotationClasses) {
        for (@SuppressWarnings("rawtypes") Class annotationClass : annotationClasses) {
            if (!annotationClass.isAnnotation()) {
                throw new IllegalArgumentException(
                        String.format("类%s不是注解", annotationClass.getName()));
            }
        }
        this.annotationClasses = annotationClasses;
    }

    /**
     * 使用指定的判断逻辑来匹配.
     * 并集，所有注解都要标注才算匹配；交集，只要有一个标注就算匹配。参见{@link Logic}
     * @param logic 判断逻辑
     * @param annotationClasses 注解类型
     */
    public BeanPropertyAnnotationMatcher(Logic logic, Class<?>...annotationClasses) {
        this(annotationClasses);
        this.logic = logic;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean match(BeanProperty<?> beanProperty) {
        boolean result = false;
        if (logic == Logic.AND) {
            //并集，所有注解都要标注
            for (@SuppressWarnings("rawtypes") Class annotationClass : annotationClasses) {
                if (!beanProperty.hasAnnotation(annotationClass)) {
                    return false;
                }
            }
            result = true;
        } else {
            //交集，只要有一个标注就行
            for (@SuppressWarnings("rawtypes") Class annotationClass : annotationClasses) {
                if (beanProperty.hasAnnotation(annotationClass)) {
                    return true;
                }
            }
            result = false;
        }
        return result;
    }

    private Logic logic = Logic.AND;
}
