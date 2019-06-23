package cn.featherfly.common.lang.asserts;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.exception.LocalizedExceptionUtils;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.StringUtils;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出支持国际化消息输出的异常
 * 一般用于检查传入参数是否合法
 * </p>
 *
 * @since 1.7
 * @version 1.7
 * @author 钟冀
 */
public class LocalizedAssert<E extends RuntimeException> implements ILocalizedAssert<E> {
    
    private Class<E> exceptionType;

    /**
     * @param exceptionType 断言失败抛出的异常类型
     * @param <E> 泛型
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
            throwException("#isNotNull", new Object[] {arg});
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotBlank(String text, String arg) {
        if (!StringUtils.isNotBlank(text)) {
            throwException("#isNotBlank", new Object[] {arg});
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object obj, String args) {
        if (!LangUtils.isNotEmpty(obj)) {
            throwException("#isNotEmpty", new Object[] {args});
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(String text, String arg) {
        if (!LangUtils.isNotEmpty(text)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Object[] array, String arg) {
        if (LangUtils.isEmpty(array)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Collection<?> collection, String arg) {
        if (LangUtils.isEmpty(collection)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isNotEmpty(Map<?, ?> map, String arg) {
        if (LangUtils.isEmpty(map)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isExists(File file , String args) {
        if (!LangUtils.isExists(file)) {
            throwException("#isExists", new Object[] {args});
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

    private void throwException(String msg, Object[] args) {
        msg = "@assert" + msg;
        LocalizedExceptionUtils.throwException(exceptionType, msg, args);
//        throw ClassUtils.newInstance(exceptionType, ResourceBundleUtils.getString(msg, args));
    }
}
