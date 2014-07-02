
package cn.featherfly.common.storage;


/**
 * <p>
 * 存储
 * </p>
 *
 * @param <E> 存储的对象类型
 * @param <ID> 存储后产生的唯一标示，用于取回该对象
 * @author 钟冀
 *
 */
public interface Storage<E, ID> {
	/**
	 * <p>
	 * 存储对象
	 * </p>
	 * @param e 对象
	 * @return 存储后产生的唯一标示，用于取回
	 */
	ID store(E e);
	/**
	 * <p>
	 * 获得对象
	 * </p>
	 * @param id 对象唯一标示
	 * @return 对象
	 */
	E retrieve(ID id);
}
