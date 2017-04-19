
package cn.featherfly.common.policy;

import java.util.Collection;


/**
 * <p>
 * 黑名单策略
 * </p>
 *
 * @param <T> 需要判断的类型
 * @author 钟冀
 */
public interface BlackListPolicy<T, P extends BlackListPolicy<T, P>> extends AllowPolicy<T>{
	
	P addBlack(T t);

	P addBlack(@SuppressWarnings("unchecked") T... t);

	P removeBlack(T t);
	
	/**
	 * <p>
	 * 清除黑名单
	 * </p>
	 * @return this
	 */
	P clearBlackList();
	
	Collection<T> getBlackList();	
    /**
     * <p>
     * 设置策略
     * </p>
     * @param blackList 策略
     */
    P setBlackList(Collection<T> blackList);
}
