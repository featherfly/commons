
package cn.featherfly.common.policy;

import java.util.Collection;


/**
 * <p>
 * 白名单策略
 * </p>
 * @param <T> 需要判断的类型
 * @author 钟冀
 */
public interface WhiteListPolicy<T, P extends WhiteListPolicy<T, P>> extends AllowPolicy<T>{
	
	P addWhite(T t);

	P addWhite(@SuppressWarnings("unchecked") T... t);

	P removeWhite(T t);

	/**
	 * <p>
	 * 清除白名单
	 * </p>
	 * @return this
	 */
	P clearWhiteList();
	/**
	 * 返回whiteList
	 * @return whiteList
	 */
	Collection<T> getWhiteList();
    /**
     * <p>
     * 设置策略
     * </p>
     * @param whiteList 策略
     */
    void setWhiteList(Collection<T> whiteList);
}
