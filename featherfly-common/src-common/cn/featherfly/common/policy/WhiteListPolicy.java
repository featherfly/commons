
package cn.featherfly.common.policy;

import java.util.List;


/**
 * <p>
 * 白名单策略
 * </p>
 * @param <T> 需要判断的类型
 * @author 钟冀
 */
public interface WhiteListPolicy<T> extends AllowPolicy<T>{
    /**
     * <p>
     * 设置策略
     * </p>
     * @param whiteList 策略
     */
    void setWhiteList(List<T> whiteList);
}
