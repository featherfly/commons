
package cn.featherfly.common.policy;

import java.util.List;


/**
 * <p>
 * 黑名单策略
 * </p>
 *
 * @param <T> 需要判断的类型
 * @author 钟冀
 */
public interface BlackListPolicy<T> extends AllowPolicy<T>{
    /**
     * <p>
     * 设置策略
     * </p>
     * @param blackList 策略
     */
    void setBlackList(List<T> blackList);
}
