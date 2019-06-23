
package cn.featherfly.common.form;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * Parameter
 * </p>
 *
 * @author 钟冀
 */
public interface Parameter {
    /**
     * <p>
     * 获取参数
     * </p>
     * @param name 参数名称
     * @param type 类型
     * @param <T> 泛型
     * @return 参数
     */
    <T> T get(String name, Class<T> type);
    /**
     * <p>
     * 转换
     * </p>
     * @param type 类型
     * @param <T> 泛型
     * @return 转换后的对象
     */
    <T> T convert(Class<T> type);
    /**
     * <p>
     * 转换
     * </p>
     * @param type 类型
     * @param prefix 前缀
     * @param <T> 泛型
     * @return 转换后的对象
     */
    <T> T convert(Class<T> type, String prefix);
    /**
     * 返回param
     * @param name 参数名
     * @return param
     */
    String getParameter(String name);
    /**
     * 返回params
     * @param name 参数名
     * @return params
     */
    String[] getParameters(String name);
    /**
     * 返回param
     * @return param
     */
    Map<String, String[]> getParameterMap();
    /**
     * 返回参数名集合
     * @return param
     */
    Collection<String> getParameterNames();
    /**
     * <p>
     * 获取变量MAP
     * </p>
     * @return 变量MAP
     */
    Map<String, Object> getVariableMap();
    /**
     * <p>
     * 获取指定变量
     * </p>
     * @param name 变量名称
     * @return 变量
     */
    Object getVariable(String name);
    /**
     * <p>
     * 添加变量
     * </p>
     * @param name 变量名称
     * @param variable 变量值
     */
    void addVariable(String name, Object variable);
}
