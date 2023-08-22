
package cn.featherfly.common.lang.asserts;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import cn.featherfly.common.lang.function.SerializableNumberSupplier;
import cn.featherfly.common.lang.function.SerializableSupplier;

/**
 * <p>
 * IAssertLocalized
 * </p>
 * .
 *
 * @author zhongj
 * @version 1.7
 * @param <E> the element type
 * @since 1.7
 */
public interface ILocalizedAssert<E extends RuntimeException> {

    /**
     * <p>
     * 判断不为空，如果为空，抛出指定异常
     * </p>
     * .
     *
     * @param object    判断的对象
     * @param arguDescp 出错时对参数的描述信息，例如：user.id、username等等
     */
    void isNotNull(Object object, String arguDescp);

    /**
     * <p>
     * if null, throw exception
     * </p>
     * .
     *
     * @param <T>              the generic type
     * @param propertySupplier object property lambda
     */
    <T> void isNotNull(SerializableSupplier<T> propertySupplier);

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * .
     *
     * @param text      判断的字符串
     * @param arguDescp 出错时对参数的描述信息，例如：user.id、username等等
     */
    void isNotBlank(String text, String arguDescp);

    /**
     * <p>
     * 判断不为空或空串（包括只有空字符的串），判断失败抛出指定异常
     * </p>
     * .
     *
     * @param propertySupplier object property lambda
     */
    void isNotBlank(SerializableSupplier<String> propertySupplier);

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * .
     *
     * @param obj        判断的对象
     * @param arguDescps 出错时对参数的描述信息，例如：user.id、username等等
     */
    void isNotEmpty(Object obj, String arguDescps);

    /**
     * <p>
     * 判断不为空（String,Collection,Map,Array还要判断长度是否为0），判断失败抛出指定异常
     * </p>
     * .
     *
     * @param <T>              the generic type
     * @param propertySupplier object property lambda
     */
    <T> void isNotEmpty(SerializableSupplier<T> propertySupplier);

    /**
     * <p>
     * 判断不为空或空串，判断失败抛出指定异常
     * </p>
     * .
     *
     * @param text      判断的字符串
     * @param arguDescp 出错时对参数的描述信息，例如：user.id、username等等
     */
    void isNotEmpty(String text, String arguDescp);

    /**
     * <p>
     * 判断数组不为null或size不为0，判断失败抛出指定异常
     * </p>
     * .
     *
     * @param array     需要判断的数组
     * @param arguDescp 出错时对参数的描述信息，例如：user.id、username等等
     */
    void isNotEmpty(Object[] array, String arguDescp);

    /**
     * <p>
     * 判断集合不为null或size不为0，判断失败抛出指定异常
     * </p>
     * .
     *
     * @param collection 判断的集合
     * @param arguDescp  出错时对参数的描述信息，例如：user.id、username等等
     */
    void isNotEmpty(Collection<?> collection, String arguDescp);

    /**
     * <p>
     * 判断MAP不为null或size不为0，判断失败抛出指定异常
     * </p>
     * .
     *
     * @param map       判断的集合
     * @param arguDescp 出错时对参数的描述信息，例如：user.id、username等等
     */
    void isNotEmpty(Map<?, ?> map, String arguDescp);

    /**
     * <p>
     * 判断传入文件对象代表的物理文件是否存在，判断失败抛出指定异常
     * </p>
     * .
     *
     * @param file       判断的文件对象
     * @param arguDescps 出错时对参数的描述信息，例如：uploadFile等等
     */
    void isExists(File file, String arguDescps);

    /**
     * if arguDescpu file is not a file, throw exception.
     *
     * @param file       判断的文件对象
     * @param arguDescps 出错时对参数的描述信息，例如：uploadFile等等
     */
    void isFile(File file, String arguDescps);

    /**
     * if arguDescpu file is not directory, throw exception.
     *
     * @param file       判断的文件对象
     * @param arguDescps 出错时对参数的描述信息，例如：uploadFile等等
     */
    void isDirectory(File file, String arguDescps);

    /**
     * <p>
     * 判断对象（第二个参数）是指定类型（第一个参数）的实例，判断失败抛出指定异常
     * </p>
     * .
     *
     * @param clazz 类型
     * @param obj   对象
     */
    void isInstanceOf(Class<?> clazz, Object obj);

    /**
     * <p>
     * 判断类型（第一个参数）是指定类型（第二个参数）的父类（包括接口实现和类继承），判断失败抛出指定异常
     * </p>
     * .
     *
     * @param parentType 父类型
     * @param subType    子类型
     */
    void isParent(Class<?> parentType, Class<?> subType);

    /**
     * if arguDescpu classType is interface, throw exception.
     *
     * @param classType the class type
     */
    void isNotInterface(Class<?> classType);

    /**
     * if value is &lt;= min or &gt;= max, throw exception.
     *
     * @param <N>       the number type
     * @param value     value
     * @param min       min
     * @param max       max
     * @param arguDescp arguDescp
     */
    <N extends Number> void isInRange(N value, N min, N max, String arguDescp);

    /**
     * if value is &lt;= min or &gt;= max, throw exception.
     *
     * @param <N>   the number type
     * @param value the value
     * @param min   the min
     * @param max   the max
     */
    <N extends Number> void isInRange(SerializableNumberSupplier<N> value, N min, N max);

    /**
     * if value is &lt;= min throw exception.
     *
     * @param <N>       the number type
     * @param value     value
     * @param min       min
     * @param arguDescp arguDescp
     */
    <N extends Number> void isGt(N value, N min, String arguDescp);

    /**
     * if value is &lt;= min throw exception.
     *
     * @param <N>   the number type
     * @param value value
     * @param min   min
     */
    <N extends Number> void isGt(SerializableNumberSupplier<N> value, N min);

    /**
     * if value is &lt; min throw exception.
     *
     * @param <N>       the number type
     * @param value     value
     * @param min       min
     * @param arguDescp arguDescp
     */
    <N extends Number> void isGe(N value, N min, String arguDescp);

    /**
     * if value is &lt; min throw exception.
     *
     * @param <N>   the number type
     * @param value value
     * @param min   min
     */
    <N extends Number> void isGe(SerializableNumberSupplier<N> value, N min);

    /**
     * if value is &gt;= max, throw exception.
     *
     * @param <N>       the number type
     * @param value     value
     * @param max       max
     * @param arguDescp arguDescp
     */
    <N extends Number> void isLt(N value, N max, String arguDescp);

    /**
     * if value is &gt;= max, throw exception.
     *
     * @param <N>   the number type
     * @param value value
     * @param max   max
     */
    <N extends Number> void isLt(SerializableNumberSupplier<N> value, N max);

    /**
     * if value is &gt; max, throw exception.
     *
     * @param <N>       the number type
     * @param value     value
     * @param max       max
     * @param arguDescp arguDescp
     */
    <N extends Number> void isLe(N value, N max, String arguDescp);

    /**
     * if value is &gt; max, throw exception.
     *
     * @param <N>   the number type
     * @param value value
     * @param max   max
     */
    <N extends Number> void isLe(SerializableNumberSupplier<N> value, N max);
}