
package cn.featherfly.common.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>
 * MethodParameter
 * </p>
 * 
 * @author 钟冀
 */
public class MethodParameter {

	private Method method;
	
	private String name;
	
	private Class<?> type;
	
	private Annotation[] annotations;

	/**
	 * 返回name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回type
	 * @return type
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * 设置type
	 * @param type type
	 */
	public void setType(Class<?> type) {
		this.type = type;
	}

	/**
	 * 返回annotations
	 * @return annotations
	 */
	public Annotation[] getAnnotations() {
		return annotations;
	}

	/**
	 * 设置annotations
	 * @param annotations annotations
	 */
	public void setAnnotations(Annotation[] annotations) {
		this.annotations = annotations;
	}

	/**
	 * 返回method
	 * @return method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * 设置method
	 * @param method method
	 */
	public void setMethod(Method method) {
		this.method = method;
	}
}
