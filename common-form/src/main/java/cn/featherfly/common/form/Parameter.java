
package cn.featherfly.common.form;

import java.util.Collection;
import java.util.Map;

/**
 * Parameter.
 *
 * @author zhongj
 */
public interface Parameter {
    /**
     * 获取参数
     *
     * @param name 参数名称
     * @param type 类型
     * @param <T> 泛型
     * @return 参数
     */
    <T> T get(String name, Class<T> type);

    /**
     * 转换
     *
     * @param type 类型
     * @param <T> 泛型
     * @return 转换后的对象
     */
    <T> T convert(Class<T> type);

    /**
     * 转换
     *
     * @param type 类型
     * @param prefix 前缀
     * @param <T> 泛型
     * @return 转换后的对象
     */
    <T> T convert(Class<T> type, String prefix);

    /**
     * 返回param
     *
     * @param name 参数名
     * @return param
     */
    String getParameter(String name);

    /**
     * 返回params
     *
     * @param name 参数名
     * @return params
     */
    String[] getParameters(String name);

    /**
     * 返回param
     *
     * @return param
     */
    Map<String, String[]> getParameterMap();

    /**
     * 返回参数名集合
     *
     * @return param
     */
    Collection<String> getParameterNames();

    /**
     * 获取变量MAP
     *
     * @return 变量MAP
     */
    Map<String, Object> getVariableMap();

    /**
     * 获取指定变量
     *
     * @param name 变量名称
     * @return 变量
     */
    Object getVariable(String name);

    /**
     * 添加变量
     *
     * @param name 变量名称
     * @param variable 变量值
     */
    void addVariable(String name, Object variable);
}
