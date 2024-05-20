package cn.featherfly.common.lang.asserts;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import cn.featherfly.common.exception.LocalizedExceptionUtils;
import cn.featherfly.common.function.serializable.SerializableNumberSupplier;
import cn.featherfly.common.function.serializable.SerializableSupplier;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LambdaUtils;
import cn.featherfly.common.lang.LambdaUtils.SerializableSupplierLambdaInfo;
import cn.featherfly.common.lang.LambdaUtils.SerializedLambdaInfo;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.NumberUtils;
import cn.featherfly.common.lang.Strings;

/**
 * 断言工具类，对于满足断言的情况，抛出支持国际化消息输出的异常 一般用于检查传入参数是否合法.
 *
 * @since 1.7
 * @version 1.7
 * @author zhongj
 */
public class LocalizedAssert<E extends RuntimeException> implements ILocalizedAssert {

    private static final String IS_NOT_EMPTY = "#isNotEmpty";
    private static final String MIN = "min";
    private static final String MAX = "max";
    private static final String VALUE = "value";

    private Function<String, E> newException;

    private Class<E> exceptionType;

    /**
     * Instantiates a new localized assert.
     *
     * @param exceptionType the exception type
     */
    public LocalizedAssert(Class<E> exceptionType) {
        this(exceptionType, null);
    }

    /**
     * Instantiates a new localized assert.
     *
     * @param exceptionType the exception type
     * @param newException create new exception function
     */
    public LocalizedAssert(Class<E> exceptionType, Function<String, E> newException) {
        this.exceptionType = exceptionType;
        if (newException == null) {
            this.newException = msg -> ClassUtils.newInstance(exceptionType, msg);
        } else {
            this.newException = newException;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotNull(Object object, String arg) {
        if (object == null) {
            throwException("#isNotNull", arg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotNull(Object object, Supplier<String> arg) {
        isNotNull(object, nullSafeGet(arg));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotBlank(String text, String arg) {
        if (!Strings.isNotBlank(text)) {
            throwException("#isNotBlank", arg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object obj, String args) {
        if (!Lang.isNotEmpty(obj)) {
            throwException(IS_NOT_EMPTY, args);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(String text, String arg) {
        if (!Lang.isNotEmpty(text)) {
            throwException(IS_NOT_EMPTY, arg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object[] array, String arg) {
        if (Lang.isEmpty(array)) {
            throwException(IS_NOT_EMPTY, arg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Collection<?> collection, String arg) {
        if (Lang.isEmpty(collection)) {
            throwException(IS_NOT_EMPTY, arg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Map<?, ?> map, String arg) {
        if (Lang.isEmpty(map)) {
            throwException(IS_NOT_EMPTY, arg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isExists(File file, String args) {
        if (file == null) {
            throwException("#isExists", args, "null");
        } else if (!file.exists()) {
            throwException("#isExists", args, file.getAbsolutePath());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isFile(File file, String args) {
        if (file == null) {
            throwException("#isFile", args, "null");
        } else if (!file.isFile()) {
            throwException("#isFile", args, file.getAbsolutePath());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isDirectory(File file, String args) {
        if (file == null) {
            throwException("#isDirectory", args, "null");
        } else if (!file.isDirectory()) {
            throwException("#isDirectory", args, file.getAbsolutePath());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isInstanceOf(Class<?> clazz, Object obj) {
        Object[] args = new Object[2];
        if (clazz == null) {
            args[0] = "null";
        } else {
            args[0] = clazz.getName();
        }
        if (obj == null) {
            args[1] = "null";
        } else {
            args[1] = obj.getClass().getName();
        }
        if (clazz == null || !clazz.isInstance(obj)) {
            throwException("#isInstanceOf", args);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isParent(Class<?> parentType, Class<?> subType) {
        Object[] args = new Object[2];
        if (parentType == null) {
            args[0] = "null";
        } else {
            args[0] = parentType.getName();
        }
        if (subType == null) {
            args[1] = "null";
        } else {
            args[1] = subType.getName();
        }
        if (!ClassUtils.isParent(parentType, subType)) {
            throwException("#isParent", args);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotInterface(Class<?> classType) {
        if (classType != null && classType.isInterface()) {
            throwException("#isNotInterface", classType.getName());
        }
    }

    private void throwException(String msg, Object... args) {
        msg = "@assert" + msg;
        LocalizedExceptionUtils.throwException(newException, exceptionType, msg, args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isInRange(N value, N min, N max, String arguDescp) {
        isNotNull(min, MIN);
        isNotNull(max, MAX);
        isNotNull(value, VALUE);
        int result = NumberUtils.compare(value, min);
        if (result < 0) {
            throwException("#isInRange", value, min, max, arguDescp);
        }
        result = NumberUtils.compare(value, max);
        if (result > 0) {
            throwException("#isInRange", value, min, max, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isInRange(SerializableNumberSupplier<N> value, N min, N max) {
        SerializableSupplierLambdaInfo<N> info = LambdaUtils.getSerializableSupplierLambdaInfo(value);
        isInRange(info.getValue(), min, max, createDescp(info.getSerializedLambdaInfo()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isGt(N value, N min, String arguDescp) {
        isNotNull(min, MIN);
        isNotNull(value, VALUE);
        int result = NumberUtils.compare(value, min);
        if (result == -1 || result == 0) {
            throwException("#isGt", value, min, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isGt(SerializableNumberSupplier<N> value, N min) {
        SerializableSupplierLambdaInfo<N> info = LambdaUtils.getSerializableSupplierLambdaInfo(value);
        isGt(info.getValue(), min, createDescp(info.getSerializedLambdaInfo()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isGe(N value, N min, String arguDescp) {
        isNotNull(min, MIN);
        isNotNull(value, VALUE);
        if (NumberUtils.compare(value, min) == -1) {
            throwException("#isGe", value, min, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isGe(SerializableNumberSupplier<N> value, N min) {
        SerializableSupplierLambdaInfo<N> info = LambdaUtils.getSerializableSupplierLambdaInfo(value);
        isGe(info.getValue(), min, createDescp(info.getSerializedLambdaInfo()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isLt(N value, N max, String arguDescp) {
        isNotNull(max, MAX);
        isNotNull(value, VALUE);
        int result = NumberUtils.compare(value, max);
        if (result == 0 || result == 1) {
            throwException("#isLt", value, max, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isLt(SerializableNumberSupplier<N> value, N max) {
        SerializableSupplierLambdaInfo<N> info = LambdaUtils.getSerializableSupplierLambdaInfo(value);
        isLt(info.getValue(), max, createDescp(info.getSerializedLambdaInfo()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isLe(N value, N max, String arguDescp) {
        isNotNull(max, MAX);
        isNotNull(value, VALUE);
        if (NumberUtils.compare(value, max) == 1) {
            throwException("#isLe", value, max, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isLe(SerializableNumberSupplier<N> value, N max) {
        SerializableSupplierLambdaInfo<N> info = LambdaUtils.getSerializableSupplierLambdaInfo(value);
        isLe(info.getValue(), max, createDescp(info.getSerializedLambdaInfo()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void isNotNull(SerializableSupplier<T> propertySupplier) {
        SerializableSupplierLambdaInfo<T> info = LambdaUtils.getSerializableSupplierLambdaInfo(propertySupplier);
        isNotNull(info.getValue(), createDescp(info.getSerializedLambdaInfo()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotBlank(SerializableSupplier<String> propertySupplier) {
        SerializableSupplierLambdaInfo<String> info = LambdaUtils.getSerializableSupplierLambdaInfo(propertySupplier);
        String descp = createDescp(info.getSerializedLambdaInfo());
        isNotNull(info.getValue(), descp);
        isNotBlank(info.getValue(), descp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void isNotEmpty(SerializableSupplier<T> propertySupplier) {
        SerializableSupplierLambdaInfo<T> info = LambdaUtils.getSerializableSupplierLambdaInfo(propertySupplier);
        isNotEmpty(info.getValue(), createDescp(info.getSerializedLambdaInfo()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotBlank(String text, Supplier<String> arguDescp) {
        isNotBlank(text, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object obj, Supplier<String> arguDescps) {
        isNotEmpty(obj, nullSafeGet(arguDescps));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(String text, Supplier<String> arguDescp) {
        isNotEmpty(text, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object[] array, Supplier<String> arguDescp) {
        isNotEmpty(array, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Collection<?> collection, Supplier<String> arguDescp) {
        isNotEmpty(collection, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Map<?, ?> map, Supplier<String> arguDescp) {
        isNotEmpty(map, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isExists(File file, Supplier<String> arguDescps) {
        isExists(file, nullSafeGet(arguDescps));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isFile(File file, Supplier<String> arguDescps) {
        isFile(file, nullSafeGet(arguDescps));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isDirectory(File file, Supplier<String> arguDescps) {
        isDirectory(file, nullSafeGet(arguDescps));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isInRange(N value, N min, N max, Supplier<String> arguDescp) {
        isInRange(value, min, max, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isGt(N value, N min, Supplier<String> arguDescp) {
        isGt(value, min, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isGe(N value, N min, Supplier<String> arguDescp) {
        isGe(value, min, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isLt(N value, N max, Supplier<String> arguDescp) {
        isLt(value, max, nullSafeGet(arguDescp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <N extends Number> void isLe(N value, N max, Supplier<String> arguDescp) {
        isLe(value, max, nullSafeGet(arguDescp));
    }

    private String createDescp(SerializedLambdaInfo info) {
        return org.apache.commons.lang3.StringUtils.substringAfterLast(info.getMethodInstanceClassName(), ".") + "."
            + info.getPropertyName();
    }

    private static String nullSafeGet(Supplier<String> messageSupplier) {
        return messageSupplier != null ? messageSupplier.get() : null;
    }
}
