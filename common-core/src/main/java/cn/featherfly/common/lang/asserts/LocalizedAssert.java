package cn.featherfly.common.lang.asserts;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.exception.LocalizedExceptionUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LambdaUtils;
import cn.featherfly.common.lang.LambdaUtils.SerializedLambdaInfo;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.lang.function.SerializableSupplier;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出支持国际化消息输出的异常 一般用于检查传入参数是否合法
 * </p>
 *
 * @since 1.7
 * @version 1.7
 * @author zhongj
 */
public class LocalizedAssert<E extends RuntimeException> implements ILocalizedAssert<E> {

    private Class<E> exceptionType;

    /**
     * @param exceptionType 断言失败抛出的异常类型
     */
    public LocalizedAssert(Class<E> exceptionType) {
        this.exceptionType = exceptionType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotNull(Object object, String arg) {
        if (object == null) {
            throwException("#isNotNull", new Object[] { arg });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotBlank(String text, String arg) {
        if (!Strings.isNotBlank(text)) {
            throwException("#isNotBlank", new Object[] { arg });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object obj, String args) {
        if (!Lang.isNotEmpty(obj)) {
            throwException("#isNotEmpty", new Object[] { args });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(String text, String arg) {
        if (!Lang.isNotEmpty(text)) {
            throwException("#isNotEmpty", new Object[] { arg });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object[] array, String arg) {
        if (Lang.isEmpty(array)) {
            throwException("#isNotEmpty", new Object[] { arg });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Collection<?> collection, String arg) {
        if (Lang.isEmpty(collection)) {
            throwException("#isNotEmpty", new Object[] { arg });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Map<?, ?> map, String arg) {
        if (Lang.isEmpty(map)) {
            throwException("#isNotEmpty", new Object[] { arg });
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
        LocalizedExceptionUtils.throwException(exceptionType, msg, args);
        //        throw ClassUtils.newInstance(exceptionType, ResourceBundleUtils.getString(msg, args));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isInRange(int value, int min, int max, String arguDescp) {
        if (value < min || value > max) {
            throwException("#isInRange", value, min, max, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isGt(int value, int min, String arguDescp) {
        if (value <= min) {
            throwException("#isGt", value, min, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isGe(int value, int min, String arguDescp) {
        if (value < min) {
            throwException("#isGe", value, min, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isLt(int value, int max, String arguDescp) {
        if (value >= max) {
            throwException("#isLt", value, max, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isLe(int value, int max, String arguDescp) {
        if (value > max) {
            throwException("#isLe", value, max, arguDescp);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void isNotNull(SerializableSupplier<T> propertySupplier) {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(propertySupplier);
        Object value = BeanUtils.getProperty(info.getSerializedLambda().getCapturedArg(0), info.getPropertyName());
        isNotNull(value, createDescp(info));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotBlank(SerializableSupplier<String> propertySupplier) {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(propertySupplier);
        Object value = BeanUtils.getProperty(info.getSerializedLambda().getCapturedArg(0), info.getPropertyName());
        String descp = createDescp(info);
        isNotNull(value, descp);
        isNotBlank(value.toString(), descp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void isNotEmpty(SerializableSupplier<T> propertySupplier) {
        SerializedLambdaInfo info = LambdaUtils.getLambdaInfo(propertySupplier);
        Object value = BeanUtils.getProperty(info.getSerializedLambda().getCapturedArg(0), info.getPropertyName());
        isNotEmpty(value, createDescp(info));
    }

    private String createDescp(SerializedLambdaInfo info) {
        return org.apache.commons.lang3.StringUtils.substringAfterLast(info.getMethodInstanceClassName(), ".") + "."
                + info.getPropertyName();
    }
}
