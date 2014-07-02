
package cn.featherfly.common.lang.matcher;

import java.lang.reflect.Field;

import cn.featherfly.common.enums.Logic;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * 匹配Field的注解的实现
 * </p>
 * @author 钟冀
 */
public class FieldAnnotationMatcher implements FieldMatcher{

	private Class<?>[] annotationClasses;

	/**
	 * 使用并集判断逻辑来匹配.
	 * @param annotationClasses 注解类型
	 */
	public FieldAnnotationMatcher(Class<?>...annotationClasses) {
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
	public FieldAnnotationMatcher(Logic logic, Class<?>...annotationClasses) {
		this(annotationClasses);
		AssertIllegalArgument.isNotNull(logic, "logic can not be null");
		this.logic = logic;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean match(Field field) {
		if (LangUtils.isEmpty(annotationClasses)
				|| field == null) {
			return false;
		}
		if (logic == Logic.AND) {
			//并集，所有注解都要标注
			return matchAnd(field);
		} else {
			//交集，只要有一个标注就行
			return matchOr(field);			
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean matchAnd(Field field) {
		for (@SuppressWarnings("rawtypes") Class annotationClass : annotationClasses) {
			if (field.getAnnotation(annotationClass) == null) {
				return false;
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private boolean matchOr(Field field) {
		for (@SuppressWarnings("rawtypes") Class annotationClass : annotationClasses) {
			if (field.getAnnotation(annotationClass) != null) {
				return true;
			}
		}
		return false;
	}

	private Logic logic = Logic.AND;

}
