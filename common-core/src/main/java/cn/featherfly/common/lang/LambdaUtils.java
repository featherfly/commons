
package cn.featherfly.common.lang;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.featherfly.common.exception.ReflectException;

/**
 * <p>
 * LambdaUtils
 * </p>
 * <p>
 * 2019-09-17
 * </p>
 *
 * @author zhongj
 */
public class LambdaUtils {

    private static final Map<SerializedLambda, SerializedLambdaInfo> CACHE_LAMBDA_INFO = new ConcurrentHashMap<>(8);

    // private static final Map<SerializedLambda, String> CACHE_FIELD_NAME = new
    // ConcurrentHashMap<>(8);

    public static class SerializedLambdaInfo {

        private String methodName;

        private String methodDeclaredClassName;

        private String methodInstanceClassName;

        private String propertyName;

        private Method method;

        private SerializedLambda serializedLambda;

        /**
         * 返回propertyName
         *
         * @return propertyName
         */
        public String getPropertyName() {
            return propertyName;
        }

        /**
         * 返回methodName
         *
         * @return methodName
         */
        public String getMethodName() {
            return methodName;
        }

        /**
         * 返回methodDeclaredClassName
         *
         * @return methodDeclaredClassName
         */
        public String getMethodDeclaredClassName() {
            return methodDeclaredClassName;
        }

        /**
         * 返回methodInstanceClassName
         *
         * @return methodInstanceClassName
         */
        public String getMethodInstanceClassName() {
            return methodInstanceClassName;
        }

        /**
         * 返回serializedLambda
         *
         * @return serializedLambda
         */
        public SerializedLambda getSerializedLambda() {
            return serializedLambda;
        }

        /**
         * 返回method
         *
         * @return method
         */
        public Method getMethod() {
            return method;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "SerializedLambdaInfo [methodName=" + methodName + ", methodDeclaredClassName="
                    + methodDeclaredClassName + ", methodInstanceClassName=" + methodInstanceClassName
                    + ", propertyName=" + propertyName + ", method=" + method + ", serializedLambda=" + serializedLambda
                    + "]";
        }

    }

    public static SerializedLambdaInfo getLambdaInfo(Serializable lambda) {
        SerializedLambda serializedLambda = getSerializedLambda(lambda);
        SerializedLambdaInfo info = CACHE_LAMBDA_INFO.get(serializedLambda);
        if (null != info) {
            return info;
        }
        SerializedLambdaInfo info2 = new SerializedLambdaInfo();
        info2.serializedLambda = serializedLambda;
        info2.methodDeclaredClassName = serializedLambda.getImplClass().replaceAll("/", ".");
        info2.methodName = serializedLambda.getImplMethodName();
        info2.method = ClassUtils.findMethod(ClassUtils.forName(info2.methodDeclaredClassName),
                o -> serializedLambda.getCapturedArgCount() == o.getParameterCount()
                        && o.getName().equals(info2.methodName));
        info2.propertyName = methodToPropertyName(info2.methodName);
        if (lambda instanceof java.util.function.Function) {
            info2.methodInstanceClassName = org.apache.commons.lang3.StringUtils
                    .substringBefore(serializedLambda.getInstantiatedMethodType(), ";").substring(2)
                    .replaceAll("/", ".");
        } else {
            info2.methodInstanceClassName = lambda.getClass().getDeclaredMethods()[2].getParameterTypes()[0].getName();
        }
        CACHE_LAMBDA_INFO.put(serializedLambda, info2);
        return info2;
    }

    public static SerializedLambda getSerializedLambda(Serializable lambda) {
        return computeSerializedLambda(lambda);
    }

    public static Method getLambdaMethod(SerializedLambda lambda) {
        String className = lambda.getImplClass().replaceAll("/", ".");
        String methodName = lambda.getImplMethodName();
        Method method = ClassUtils.findMethod(ClassUtils.forName(className),
                o -> lambda.getCapturedArgCount() == o.getParameterCount() && o.getName().equals(methodName));
        return method;
    }

    public static Method getLambdaMethod(Serializable lambda) {
        return getLambdaMethod(getSerializedLambda(lambda));
    }

    public static String getLambdaMethodName(Serializable lambda) {
        return getLambdaMethodName(computeSerializedLambda(lambda));
    }

    public static String getLambdaMethodName(SerializedLambda lambda) {
        return lambda.getImplMethodName();
    }

    public static String getLambdaPropertyName(Serializable lambda) {
        return getLambdaPropertyName(computeSerializedLambda(lambda));
    }

    public static String getLambdaPropertyName(SerializedLambda lambda) {
        return methodToPropertyName(getLambdaMethodName(lambda));
    }

    private static SerializedLambda computeSerializedLambda(Serializable lambda) {
        try {
            Class<?> cl = lambda.getClass();
            Method m = cl.getDeclaredMethod("writeReplace");
            m.setAccessible(true);
            Object replacement = m.invoke(lambda);
            if (!(replacement instanceof SerializedLambda)) {
                return null; // custom interface implementation
            }
            return (SerializedLambda) replacement;
        } catch (Exception e) {
            throw new ReflectException("get SerializedLambda fail", e);
        }
    }

    private static String methodToPropertyName(String methodName) {
        String name = null;
        if (methodName.startsWith("get")) {
            name = methodName.substring(3);
        } else if (methodName.startsWith("is")) {
            name = methodName.substring(2);
        } else {
            name = methodName;
        }
        if (name != null) {
            name = name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        return name;
    }
}
