
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * <p>
 * 集合类工具
 * </p>
 * @author 钟冀
 */
public final class CollectionUtils {

	private CollectionUtils() {
	}
	/**
	 * <p>
	 * 返回传入集合是否为空（是null或size=0）
	 * </p>
	 * @param collection
	 *            传入的集合
	 * @return 传入集合是否为空
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return LangUtils.isEmpty(collection);
	}
	/**
	 * <p>
	 * 返回传入集合是否不为空（不是null或size>0）
	 * </p>
	 * @param collection
	 *            传入的集合
	 * @return 传入集合是否不为空
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}
	
	/**
	 * <p>
	 * 批量添加元素到集合，如果collection==null返回false,或者elements为空返回false，其他情况请参考{@link #java.util.Collections.addAll}
	 * </p>
	 * @param collection 集合
	 * @param elements 需要批量添加的元素
	 * @return 是否添加
	 */
	public static <T> boolean addAll(Collection<? super T> collection, T... elements) {
		if (collection == null) {
			return false;
		}
		if (LangUtils.isEmpty(elements)) {
			return false;
		}
		return Collections.addAll(collection, elements);
	}
	
	/**
	 * <p>
	 * 转换为数组.
	 * 如果传入集合为空（null或者size=0），返回长度为0的数组（不会返回null）.
	 * </p>
	 * @param collection 集合
	 * @return 数组
	 */
	public static <A> A[] toArray(Collection<A> collection, Class<A> type) {
		AssertIllegalArgument.isNotNull(type, "type不能为空");
		A[] results = null;
		if (collection == null) {
			collection = new ArrayList<A>();
		}
		results = ArrayUtils.create(type, collection.size());
		int i = 0;
		for (A a : collection) {
			results[i] = a;
			i++;
		}
		return results;
	}
}
