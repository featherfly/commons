
package cn.featherfly.common.lang.asserts;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * IAssertLocalized
 * </p>
 * @since 1.7
 * @version 1.7
 * @author zhongj
 */
public interface ILocalizedAssert<E extends RuntimeException> {

    /**
     * <p>
     * 判断不为空，如果为空，抛出指定异常
     * </p>
     * @param object 判断的对象
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    void isNotNull(Object object, String arg);

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    void isNotBlank(String text, String arg);

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * @param obj 判断的对象
     * @param args 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    void isNotEmpty(Object obj, String args);

    /**
     * <p>
     * 判断不为空或空串，判断失败抛出指定异常
     * </p>
     * @param text 判断的字符串
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    void isNotEmpty(String text, String arg);

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param array 需要判断的数组
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    void isNotEmpty(Object[] array, String arg);

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param collection 判断的集合
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    void isNotEmpty(Collection<?> collection, String arg);

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出指定异常
     * </p>
     * @param map 判断的集合
     * @param arg 出错时对参数的描述信息，例如：user.id、username等等
     * 
     */
    void isNotEmpty(Map<?, ?> map, String arg);

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常
     * </p>
     * @param file 判断的文件对象
     * @param args 出错时对参数的描述信息，例如：uploadFile等等
     * 
     */
    void isExists(File file, String args);
    
    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常
     * </p>
     * @param clazz 类型
     * @param obj 对象
     */
    void isInstanceOf(Class<?> clazz, Object obj);
    
    /**
     * <p>
     * 判断类型（第一个参数）是指定类型（第二个参数）的父类（包括接口实现和类继承），判断失败抛出指定异常
     * </p>
     * @param parentType 父类型
     * @param subType 子类型
     */
    void isParent(Class<?> parentType, Class<?> subType);
}