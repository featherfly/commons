
package cn.featherfly.common.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * <p>
 * MethodDescriptor
 * </p>
 * 
 * @author 钟冀
 */
public class MethodDescriptor {
	
	/**
	 * @param method method
	 */
	public MethodDescriptor(Method method) {
		this.method = method;
		Class<?>[] types = method.getParameterTypes();
		methodParameters = new MethodParameter[types.length];
		for (int i = 0; i < types.length; i++) {
			Annotation[] as = method.getParameterAnnotations()[i];
			MethodParameter mp = new MethodParameter();
			mp.setAnnotations(as);
			mp.setType(types[i]);
			mp.setMethod(method);
//			mp.setName(name); 使用字节码技术才能获取
			methodParameters[i] = mp;
		}
	}

	private Method method;

	private MethodParameter[] methodParameters;

	/**
	 * 返回name
	 * @return name
	 */
	public String getName() {
		return method.getName();
	}

	/**
	 * 返回methodParameters
	 * @return methodParameters
	 */
	public MethodParameter[] getMethodParameters() {
		return methodParameters;
	}

	/**
	 * 设置methodParameters
	 * @param methodParameters methodParameters
	 */
	public void setMethodParameters(MethodParameter[] methodParameters) {
		this.methodParameters = methodParameters;
	}

	
	/**
	 * 返回returnType
	 * @return returnType
	 */
	public Class<?> getReturnType() {
		return method.getReturnType();
	}
}
