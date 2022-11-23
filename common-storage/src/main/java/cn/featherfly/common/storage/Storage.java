package cn.featherfly.common.storage;

import cn.featherfly.common.lang.asserts.LocalizedAssert;

/**
 * <p>
 * 存储
 * </p>
 *
 * @param <E>  存储的对象类型
 * @param <ID> 存储后产生的唯一标示，用于取回该对象
 * @author zhongj
 */
public interface Storage<E, ID> {

    LocalizedAssert<StorageException> ASSERT = new LocalizedAssert<>(StorageException.class);

    /**
     * 存储对象.
     *
     * @param e 对象
     * @return 存储后产生的唯一标示，用于取回
     */
    ID store(E e);

    /**
     * 获得对象.
     *
     * @param id 对象唯一标示
     * @return 对象
     */
    E retrieve(ID id);

    /**
     * 删除对象.
     *
     * @param id 对象唯一标示
     * @return 是否删除成功
     */
    boolean delete(ID id);

    /**
     * 判断是否存在.
     *
     * @param id 对象唯一标示
     * @return 是否存在
     */
    boolean exists(ID id);
}
