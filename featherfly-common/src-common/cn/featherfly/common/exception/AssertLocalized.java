package cn.featherfly.common.exception;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.StringUtils;

/**
 * <p>
 * 断言工具类，对于满足断言的情况，抛出支持国际化消息输出的异常
 * 一般用于检查传入参数是否合法
 * </p>
 *
 * @author 钟冀
 * @since 1.3
 * @version 1.3
 */
public class AssertLocalized {
    
    private Class<?> exceptionType;

    /**
     * @param exceptionType 断言失败抛出的异常类型
     * @param <E> 泛型
     */
    public <E extends LocalizedException> AssertLocalized(Class<E> exceptionType) {
        this.exceptionType = exceptionType;
    }

    /**
     * <p>
     * 判断不为空，如果为空，抛出指定异常
     * </p>
     * @param object 判断的对象
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    public void isNotNull(Object object, String arg) {
        if (object == null) {
            throwException("#isNotNull", new Object[] {arg});
        }
    }

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    public void isNotBlank(String text, String arg) {
        if (!StringUtils.isNotBlank(text)) {
            throwException("#isNotBlank", new Object[] {arg});
        }
    }

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * @param obj 判断的对象
     * @param args 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    public void isNotEmpty(Object obj, String args) {
        if (!LangUtils.isNotEmpty(obj)) {
            throwException("#isNotEmpty", new Object[] {args});
        }
    }

    /**
     * <p>
     * 判断不为空或空串，判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    public void isNotEmpty(String text, String arg) {
        if (!LangUtils.isNotEmpty(text)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param array 需要判断的数组
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    public void isNotEmpty(Object[] array, String arg) {
        if (LangUtils.isEmpty(array)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param collection 判断的集合
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    public void isNotEmpty(Collection<?> collection, String arg) {
        if (LangUtils.isEmpty(collection)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param map 判断的集合
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    public void isNotEmpty(Map<?, ?> map, String arg) {
        if (LangUtils.isEmpty(map)) {
            throwException("#isNotEmpty", new Object[] {arg});
        }
    }

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常
     * </p>
     * @param file 判断的文件对象
     * @param args 出错时对参数的描述信息，例如：uploadFile等等
     * 
     */
    public void isExists(File file , String args) {
        if (!LangUtils.isExists(file)) {
            throwException("#isExists", new Object[] {args});
        }
    }

    @SuppressWarnings("unchecked")
    private void throwException(String msg, Object[] args) {
        msg = "@assert" + msg;
        throw ClassUtils.newInstance((Class<LocalizedException>) exceptionType, msg, args);
    }
}
