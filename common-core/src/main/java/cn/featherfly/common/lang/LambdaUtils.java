
package cn.featherfly.common.lang;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.apache.commons.lang3.StringUtils;

import cn.featherfly.common.exception.ReflectException;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.function.PrimitiveSupplier;
import cn.featherfly.common.function.ToPrimitiveFunction;
import cn.featherfly.common.function.serializable.SerializableConsumer;
import cn.featherfly.common.function.serializable.SerializableDoubleSupplier;
import cn.featherfly.common.function.serializable.SerializableIntSupplier;
import cn.featherfly.common.function.serializable.SerializableLongSupplier;
import cn.featherfly.common.function.serializable.SerializableSupplier;

/**
 * LambdaUtils.
 *
 * @author zhongj
 */
public class LambdaUtils {

    private static final Map<Serializable, SerializedLambdaInfo> CACHE_LAMBDA_INFO = new ConcurrentHashMap<>(8);

    private static final Map<Serializable, SerializedLambda> CACHE_LAMBDA = new ConcurrentHashMap<>(8);

    // private static final Map<SerializedLambda, String> CACHE_FIELD_NAME = new
    // ConcurrentHashMap<>(8);

    /**
     * The Class SerializedLambdaInfo.
     *
     * @author zhongj
     */
    public static class SerializedLambdaInfo {

        private String methodName;

        private String methodDeclaredClassName;

        private String methodInstanceClassName;

        private String propertyName;

        private Class<?> propertyType;

        private Method method;

        private SerializedLambda serializedLambda;

        /**
         * 返回propertyName.
         *
         * @return propertyName
         */
        public String getPropertyName() {
            return propertyName;
        }

        /**
         * 返回methodName.
         *
         * @return methodName
         */
        public String getMethodName() {
            return methodName;
        }

        /**
         * 返回methodDeclaredClassName.
         *
         * @return methodDeclaredClassName
         */
        public String getMethodDeclaredClassName() {
            return methodDeclaredClassName;
        }

        /**
         * 返回methodInstanceClassName.
         *
         * @return methodInstanceClassName
         */
        public String getMethodInstanceClassName() {
            return methodInstanceClassName;
        }

        /**
         * 返回serializedLambda.
         *
         * @return serializedLambda
         */
        public SerializedLambda getSerializedLambda() {
            return serializedLambda;
        }

        /**
         * 返回method.
         *
         * @return method
         */
        public Method getMethod() {
            return method;
        }

        /**
         * 返回propertyType.
         *
         * @return propertyType
         */
        public Class<?> getPropertyType() {
            return propertyType;
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

    /**
     * The Class InstanceLambdaInfo.
     *
     * @author zhongj
     */
    public static abstract class InstanceLambdaInfo {

        private SerializedLambdaInfo serializedLambdaInfo;

        private Object instance;

        /**
         * Instantiates a new instance lambda info.
         *
         * @param serializedLambdaInfo the serialized lambda info
         * @param instance             the instance
         */
        public InstanceLambdaInfo(SerializedLambdaInfo serializedLambdaInfo, Object instance) {
            super();
            this.serializedLambdaInfo = serializedLambdaInfo;
            this.instance = instance;
        }

        /**
         * 返回serializedLambdaInfo.
         *
         * @return serializedLambdaInfo
         */
        public SerializedLambdaInfo getSerializedLambdaInfo() {
            return serializedLambdaInfo;
        }

        /**
         * 返回instance.
         *
         * @return instance
         */
        public Object getInstance() {
            return instance;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " [serializedLambdaInfo=" + serializedLambdaInfo + ", instance="
                    + instance + "]";
        }

    }

    /**
     * The Class SerializableConsumerLambdaInfo.
     *
     * @author zhongj
     * @param <T> the generic type
     */
    public static class SerializableConsumerLambdaInfo<T> extends InstanceLambdaInfo implements Consumer<T> {

        private Consumer<T> consumer;

        private SerializableConsumerLambdaInfo(SerializedLambdaInfo serializedLambdaInfo, Consumer<T> consumer) {
            super(serializedLambdaInfo, serializedLambdaInfo.getSerializedLambda().getCapturedArg(0));
            this.consumer = consumer;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void accept(T t) {
            consumer.accept(t);
        }
    }

    /**
     * The Class SerializableSupplierLambdaInfo.
     *
     * @author zhongj
     * @param <T> the generic type
     */
    public static class SerializableSupplierLambdaInfo<T> extends InstanceLambdaInfo implements Supplier<T> {

        private T value;

        private Supplier<T> supplier;

        private boolean init;

        private SerializableSupplierLambdaInfo(SerializedLambdaInfo serializedLambdaInfo, Supplier<T> supplier) {
            super(serializedLambdaInfo, serializedLambdaInfo.getSerializedLambda().getCapturedArg(0));
            this.supplier = supplier;
        }

        /**
         * 返回value.
         *
         * @return value
         */
        public T getValue() {
            if (!init) {
                value = get();
                init = true;
            }
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " [value=" + getValue() + ", serializedLambdaInfo="
                    + getSerializedLambdaInfo() + ", instance=" + getInstance() + "]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T get() {
            return supplier.get();
        }
    }

    /**
     * The Class SerializableIntSupplierLambdaInfo.
     *
     * @author zhongj
     */
    public static class SerializableIntSupplierLambdaInfo extends InstanceLambdaInfo implements IntSupplier {

        private int value;

        private IntSupplier supplier;

        private boolean init;

        private SerializableIntSupplierLambdaInfo(SerializedLambdaInfo serializedLambdaInfo, IntSupplier supplier) {
            super(serializedLambdaInfo, serializedLambdaInfo.getSerializedLambda().getCapturedArg(0));
            this.supplier = supplier;
        }

        /**
         * 返回value.
         *
         * @return value
         */
        public int getValue() {
            if (!init) {
                value = getAsInt();
                init = true;
            }
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " [value=" + getValue() + ", serializedLambdaInfo="
                    + getSerializedLambdaInfo() + ", instance=" + getInstance() + "]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getAsInt() {
            return supplier.getAsInt();
        }
    }

    /**
     * The Class SerializableLongSupplierLambdaInfo.
     *
     * @author zhongj
     */
    public static class SerializableLongSupplierLambdaInfo extends InstanceLambdaInfo implements LongSupplier {
        private long value;

        private LongSupplier supplier;

        private boolean init;

        private SerializableLongSupplierLambdaInfo(SerializedLambdaInfo serializedLambdaInfo, LongSupplier supplier) {
            super(serializedLambdaInfo, serializedLambdaInfo.getSerializedLambda().getCapturedArg(0));
            this.supplier = supplier;
        }

        /**
         * 返回value.
         *
         * @return value
         */
        public long getValue() {
            if (!init) {
                value = getAsLong();
                init = true;
            }
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " [value=" + getValue() + ", serializedLambdaInfo="
                    + getSerializedLambdaInfo() + ", instance=" + getInstance() + "]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long getAsLong() {
            return supplier.getAsLong();
        }
    }

    /**
     * The Class SerializableDoubleSupplierLambdaInfo.
     *
     * @author zhongj
     */
    public static class SerializableDoubleSupplierLambdaInfo extends InstanceLambdaInfo implements DoubleSupplier {
        private double value;

        private DoubleSupplier supplier;

        private boolean init;

        private SerializableDoubleSupplierLambdaInfo(SerializedLambdaInfo serializedLambdaInfo,
                DoubleSupplier supplier) {
            super(serializedLambdaInfo, serializedLambdaInfo.getSerializedLambda().getCapturedArg(0));
            this.supplier = supplier;
        }

        /**
         * 返回value.
         *
         * @return value
         */
        public double getValue() {
            if (!init) {
                value = getAsDouble();
                init = true;
            }
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + " [value=" + getValue() + ", serializedLambdaInfo="
                    + getSerializedLambdaInfo() + ", instance=" + getInstance() + "]";
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double getAsDouble() {
            return supplier.getAsDouble();
        }
    }

    /**
     * Gets the lambda info.
     *
     * @param lambda the lambda
     * @return the lambda info
     */
    public static SerializedLambdaInfo getLambdaInfo(Serializable lambda) {
        SerializedLambdaInfo info = CACHE_LAMBDA_INFO.get(lambda);
        if (null != info) {
            return info;
        }
        SerializedLambda serializedLambda = getSerializedLambda(lambda);
        SerializedLambdaInfo info2 = new SerializedLambdaInfo();
        info2.serializedLambda = serializedLambda;
        info2.methodDeclaredClassName = serializedLambda.getImplClass().replaceAll("/", ".");
        info2.methodName = serializedLambda.getImplMethodName();
        info2.propertyName = methodToPropertyName(info2.methodName);
        if (lambda instanceof Function // R getXx()    T::getXx
                || lambda instanceof BiConsumer //   setXx(U)   T::setXx
                || lambda instanceof ToIntFunction // int getXx()
                || lambda instanceof ToLongFunction // long getXx()
                || lambda instanceof ToDoubleFunction // double getXx()
                || lambda instanceof Predicate // ToBooleanFunction boolean isXx()
                || lambda instanceof ToPrimitiveFunction // char|byte|short getXx()
                || lambda instanceof ObjDoubleConsumer // setXx(double)
                || lambda instanceof ObjIntConsumer // setXx(int)
                || lambda instanceof ObjLongConsumer // setXx(long)
                || lambda instanceof BiFunction // R getXx(U)  T::getXx
        ) {
            Class<?>[] pts = getParamaeterTypes(serializedLambda.getImplMethodSignature());
            info2.method = ClassUtils.getMethod(ClassUtils.forName(info2.methodDeclaredClassName), info2.methodName,
                    pts);
            if (Lang.isEmpty(pts)) {
                info2.propertyType = info2.method.getReturnType();
            } else {
                info2.propertyType = pts[0];
            }
            if (serializedLambda.getCapturedArgCount() > 0) {
                info2.methodInstanceClassName = serializedLambda.getCapturedArg(0).getClass().getName();
            } else {
                info2.methodInstanceClassName = StringUtils
                        .substringBefore(serializedLambda.getInstantiatedMethodType(), ";").substring(2)
                        .replaceAll("/", ".");
            }
        } else if (lambda instanceof Supplier // R getXx()   T t; t::getXx
                || lambda instanceof IntSupplier // int getXx()   T t; t::getXx
                || lambda instanceof LongSupplier // long getXx()   T t; t::getXx
                || lambda instanceof DoubleSupplier// double getXx()   T t; t::getXx
                || lambda instanceof BooleanSupplier // boolean getXx()   T t; t::getXx
                || lambda instanceof PrimitiveSupplier // char|byte|short getXx()   T t; t::getXx
        //                || lambda instanceof CharSupplier // char getXx()   T t; t::getXx
        //                || lambda instanceof ByteSupplier// byte getXx()   T t; t::getXx
        //                || lambda instanceof ShortSupplier // short getXx()   T t; t::getXx
        ) {
            Class<?> obj = serializedLambda.getCapturedArg(0).getClass();
            info2.methodInstanceClassName = obj.getName();
            info2.method = ClassUtils.getMethod(ClassUtils.forName(info2.methodDeclaredClassName), info2.methodName,
                    ArrayUtils.EMPTY_CLASS_ARRAY);
            info2.propertyType = info2.method.getReturnType();
        } else if (lambda instanceof Consumer //   setXx(U)   T t; t::setXx
                || lambda instanceof IntConsumer //   setXx(int)   T t; t::setXx
                || lambda instanceof LongConsumer //   setXx(long)   T t; t::setXx
                || lambda instanceof DoubleConsumer //   setXx(double)   T t; t::setXx
        ) {
            info2.methodInstanceClassName = serializedLambda.getCapturedArg(0).getClass().getName();
            Class<?>[] pts = getParamaeterTypes(serializedLambda.getImplMethodSignature());
            info2.method = ClassUtils.getMethod(ClassUtils.forName(info2.methodDeclaredClassName), info2.methodName,
                    pts);
            info2.propertyType = pts[0];
        } else {
            throw new UnsupportedException("unsupported for " + lambda.getClass().getName());
        }
        CACHE_LAMBDA_INFO.put(lambda, info2);
        return info2;
    }

    //    private static Class<?> getPropertyType(String methodSignature) {
    //        Class<?> type = getReturnType(methodSignature);
    //        if (type == Void.TYPE) {
    //            Class<?>[] types = getParamaeterTypes(methodSignature);
    //            if (Lang.isNotEmpty(types)) {
    //                type = types[0];
    //            }
    //        }
    //        return type;
    //    }
    //    private static Class<?> getReturnType(String methodSignature) {
    //        // ()Ljava/lang/Integer;
    //        String str = StringUtils.substringAfterLast(methodSignature, ")");
    //        if (str.equals("V")) {
    //            return Void.TYPE;
    //        } else {
    //            str = StringUtils.substringAfter(str, "L");
    //            return ClassUtils.forName(str.replaceAll("/", "."));
    //        }
    //    }

    private static Class<?>[] getParamaeterTypes(String methodSignature) {
        //        "(Ljava/lang/String;)V"
        String str = StringUtils.substringAfter(methodSignature, "(");
        str = StringUtils.substringBefore(str, ")");
        if (Lang.isEmpty(str)) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        List<Class<?>> types = new ArrayList<>();
        for (int i = 0; i < str.length();) {
            char c = str.charAt(i);
            if (c == 'L') {
                int start = i + 1;
                for (int j = start; j < str.length(); j++) {
                    char e = str.charAt(j);
                    if (e == ';') {
                        String type = str.substring(start, j);
                        type = type.replaceAll("/", ".");
                        types.add(ClassUtils.forName(type));
                        i = j;
                        break;
                    }
                }
            } else if (c == 'I') {
                types.add(Integer.TYPE);
            } else if (c == 'J') {
                types.add(Long.TYPE);
            } else if (c == 'F') {
                types.add(Float.TYPE);
            } else if (c == 'D') {
                types.add(Double.TYPE);
            } else if (c == 'Z') {
                types.add(Boolean.TYPE);
            } else if (c == 'B') {
                types.add(Byte.TYPE);
            } else if (c == 'S') {
                types.add(Short.TYPE);
            } else if (c == 'C') {
                types.add(Character.TYPE);
            }
            i++;
        }
        return types.toArray(new Class[types.size()]);
    }

    /**
     * Gets the serializable supplier lambda info.
     *
     * @param <T>    the generic type
     * @param lambda the lambda
     * @return the serializable supplier lambda info
     */
    public static <
            T> SerializableSupplierLambdaInfo<T> getSerializableSupplierLambdaInfo(SerializableSupplier<T> lambda) {
        SerializedLambdaInfo info = getLambdaInfo(lambda);
        //        Object value = BeanUtils.getProperty(info.getSerializedLambda().getCapturedArg(0), info.getPropertyName());
        return new SerializableSupplierLambdaInfo<>(info, lambda);
    }

    /**
     * Gets the serializable int supplier lambda info.
     *
     * @param lambda the lambda
     * @return the serializable supplier lambda info
     */
    public static SerializableIntSupplierLambdaInfo getSerializableIntSupplierLambdaInfo(
            SerializableIntSupplier lambda) {
        SerializedLambdaInfo info = getLambdaInfo(lambda);
        return new SerializableIntSupplierLambdaInfo(info, lambda);
    }

    /**
     * Gets the serializable long supplier lambda info.
     *
     * @param lambda the lambda
     * @return the serializable supplier lambda info
     */
    public static SerializableLongSupplierLambdaInfo getSerializableLongSupplierLambdaInfo(
            SerializableLongSupplier lambda) {
        SerializedLambdaInfo info = getLambdaInfo(lambda);
        return new SerializableLongSupplierLambdaInfo(info, lambda);
    }

    /**
     * Gets the serializable double supplier lambda info.
     *
     * @param lambda the lambda
     * @return the serializable supplier lambda info
     */
    public static SerializableDoubleSupplierLambdaInfo getSerializableDoubleSupplierLambdaInfo(
            SerializableDoubleSupplier lambda) {
        SerializedLambdaInfo info = getLambdaInfo(lambda);
        return new SerializableDoubleSupplierLambdaInfo(info, lambda);
    }

    /**
     * Gets the serializable supplier lambda info.
     *
     * @param <T>    the generic type
     * @param lambda the lambda
     * @return the serializable supplier lambda info
     */
    public static <
            T> SerializableConsumerLambdaInfo<T> getSerializableConsumerLambdaInfo(SerializableConsumer<T> lambda) {
        SerializedLambdaInfo info = getLambdaInfo(lambda);
        return new SerializableConsumerLambdaInfo<>(info, lambda);
    }

    /**
     * Gets the serialized lambda.
     *
     * @param lambda the lambda
     * @return the serialized lambda
     */
    public static SerializedLambda getSerializedLambda(Serializable lambda) {
        SerializedLambda sl = CACHE_LAMBDA.get(lambda);
        if (sl == null) {
            sl = computeSerializedLambda(lambda);
            CACHE_LAMBDA.put(lambda, sl);
        }
        return sl;
    }

    /**
     * Gets the lambda method.
     *
     * @param lambda the lambda
     * @return the lambda method
     */
    public static Method getLambdaMethod(SerializedLambda lambda) {
        String className = lambda.getImplClass().replaceAll("/", ".");
        String methodName = lambda.getImplMethodName();
        Method method = ClassUtils.findMethod(ClassUtils.forName(className),
                o -> lambda.getCapturedArgCount() == o.getParameterCount() && o.getName().equals(methodName));
        return method;
    }

    /**
     * Gets the lambda method.
     *
     * @param lambda the lambda
     * @return the lambda method
     */
    public static Method getLambdaMethod(Serializable lambda) {
        return getLambdaMethod(getSerializedLambda(lambda));
    }

    /**
     * Gets the lambda method name.
     *
     * @param lambda the lambda
     * @return the lambda method name
     */
    public static String getLambdaMethodName(Serializable lambda) {
        return getLambdaMethodName(getSerializedLambda(lambda));
    }

    /**
     * Gets the lambda method name.
     *
     * @param lambda the lambda
     * @return the lambda method name
     */
    public static String getLambdaMethodName(SerializedLambda lambda) {
        return lambda.getImplMethodName();
    }

    /**
     * Gets the lambda property name.
     *
     * @param lambda the lambda
     * @return the lambda property name
     */
    public static String getLambdaPropertyName(Serializable lambda) {
        return getLambdaPropertyName(getSerializedLambda(lambda));
    }

    /**
     * Gets the lambda property name.
     *
     * @param lambda the lambda
     * @return the lambda property name
     */
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
        } else if (methodName.startsWith("set")) {
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
