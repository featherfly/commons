
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

    private static final Map<SerializedLambda, String> CACHE_LAMBDA_NAME = new ConcurrentHashMap<>(8);

    //    private static final Map<SerializedLambda, String> CACHE_FIELD_NAME = new ConcurrentHashMap<>(8);

    public static class SerializedLambdaInfo {

        private String methodName;

        private String methodDeclaredClassName;

        private String methodInstanceClassName;

        private Method method;

        private SerializedLambda serializedLambda;

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
                    + methodDeclaredClassName + ", methodInstanceClassName=" + methodInstanceClassName + ", method="
                    + method + ", serializedLambda=" + serializedLambda + "]";
        }

    }

    public static SerializedLambdaInfo getLambdaInfo(Serializable lambda) {
        return getLambdaInfo(getSerializedLambda(lambda));
    }

    public static SerializedLambdaInfo getLambdaInfo(SerializedLambda lambda) {
        SerializedLambdaInfo info = new SerializedLambdaInfo();
        info.serializedLambda = lambda;
        info.methodDeclaredClassName = lambda.getImplClass().replaceAll("/", ".");
        info.methodName = lambda.getImplMethodName();
        info.methodInstanceClassName = org.apache.commons.lang3.StringUtils
                .substringBefore(lambda.getInstantiatedMethodType(), ";").substring(2).replaceAll("/", ".");
        info.method = ClassUtils.findMethod(ClassUtils.forName(info.methodDeclaredClassName),
                o -> lambda.getCapturedArgCount() == o.getParameterCount() && o.getName().equals(info.methodName));
        return info;
    }

    public static Method getLambdaMethod(Serializable lambda) {
        return getLambdaMethod(getSerializedLambda(lambda));
    }

    public static Method getLambdaMethod(SerializedLambda lambda) {
        String className = lambda.getImplClass().replaceAll("/", ".");
        String methodName = lambda.getImplMethodName();
        Method method = ClassUtils.findMethod(ClassUtils.forName(className),
                o -> lambda.getCapturedArgCount() == o.getParameterCount() && o.getName().equals(methodName));
        return method;
    }

    public static SerializedLambda getSerializedLambda(Serializable lambda) {
        return computeSerializedLambda(lambda);
    }

    public static String getLambdaMethodName(Serializable lambda) {
        SerializedLambda serializedLambda = computeSerializedLambda(lambda);
        return getMethodName(serializedLambda);
    }

    public static String getLambdaPropertyName(Serializable lambda) {
        SerializedLambda serializedLambda = computeSerializedLambda(lambda);
        return getPropertyName(serializedLambda);
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

    private static String getMethodName(SerializedLambda serializedLambda) {
        String name = CACHE_LAMBDA_NAME.get(serializedLambda);
        if (null != name) {
            return name;
        }
        String methodName = serializedLambda.getImplMethodName();
        CACHE_LAMBDA_NAME.put(serializedLambda, methodName);
        return methodName;
    }

    private static String getPropertyName(SerializedLambda serializedLambda) {
        String methodName = getMethodName(serializedLambda);
        return methodToPropertyName(methodName);
        //        String name = CACHE_FIELD_NAME.get(serializedLambda);
        //        if (null != name) {
        //            return name;
        //        }
        //        String methodName = serializedLambda.getImplMethodName();
        //        String fieldName = methodToPropertyName(methodName);
        //        CACHE_FIELD_NAME.put(serializedLambda, fieldName);
        //        return fieldName;
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
