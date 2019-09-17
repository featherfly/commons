
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

    public static String getLambdaMethodName(Serializable lambda) {
        SerializedLambda serializedLambda = computeSerializedLambda(lambda);
        return getMethodName(serializedLambda);
    }

    public static String getLambdaPropertyName(Serializable lambda) {
        SerializedLambda serializedLambda = computeSerializedLambda(lambda);
        return getPropertyName(serializedLambda);
    }

    private static SerializedLambda computeSerializedLambda(Serializable lambda) {
        Class<?> cl = lambda.getClass();
        try {
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
