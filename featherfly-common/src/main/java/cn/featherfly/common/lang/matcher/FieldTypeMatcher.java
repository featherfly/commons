
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ClassUtils;

/**
 * <p>
 * 匹配Field的类型的实现
 * </p>
 * @author zhongj
 */
public class FieldTypeMatcher implements FieldMatcher{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FieldTypeMatcher.class);

    private Class<?> fieldType;
    
    private boolean matchSubType;

    /**
     * @param fieldType 字段类型
     */
    public FieldTypeMatcher(Class<?> fieldType) {
        this.fieldType = fieldType;
    }
    /**
     * @param fieldType 字段类型
     * @param matchSubType 是否匹配子类型
     */
    public FieldTypeMatcher(Class<?> fieldType, boolean matchSubType) {
        this.fieldType = fieldType;
        this.matchSubType = matchSubType;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean match(Field field) {
        if (fieldType == null || field == null) {
            return false;
        }
        if (matchSubType) {
            LOGGER.debug("{} 匹配类型（包含子类型） {}", fieldType.getName(), field.getType().getName());
            return ClassUtils.isParent(fieldType, field.getType());
        } else {
            LOGGER.debug("{} 匹配类型 {}", fieldType.getName(), field.getType().getName());
            return fieldType == field.getType();
        }
    }
}
