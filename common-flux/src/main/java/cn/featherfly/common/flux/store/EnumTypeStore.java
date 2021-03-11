
/*
 * All rights Reserved, Designed By zhongj
 * @Title: SimpleStore.java
 * @Package cn.featherfly.common.flux.store
 * @Description: todo (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-03-11 13:40:11
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.flux.store;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.flux.action.Action;
import cn.featherfly.common.flux.action.Action.Type;

/**
 * EnumTypeStore.
 *
 * @author zhongj
 * @param <A> the generic type extends Action
 * @param <T> the generic type extends Type, must be a Enum implements Type
 */
public class EnumTypeStore<A extends Action<?>, T extends Enum<T>> extends Store<A> {

    private Class<T> actionTypeClass;

    private final Map<T, Object> storeDatas = new HashMap<>();

    /**
     * Instantiates a new enum type store.
     */
    protected EnumTypeStore() {
        if (!Type.class.isAssignableFrom(actionTypeClass) || !actionTypeClass.isEnum()) {
            throw new IllegalArgumentException("第二个泛型参数必须是实现了" + Type.class.getName() + "接口的枚举类型");
        }
    }

    @SuppressWarnings("unchecked")
    private Class<T> getActionTypeClass() {
        int index = 1;
        // 得到泛型父类
        try {
            java.lang.reflect.Type genType = this.getClass().getGenericSuperclass();
            // 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
            if (!(genType instanceof ParameterizedType)) {
                throw new IllegalArgumentException(
                        "请先完成泛型的具体化 YourType extends EnumTypeStore<YourAction, YourTypeEnum>");
            }
            // 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
            // DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
            java.lang.reflect.Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index >= params.length || index < 0) {
                throw new IllegalArgumentException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
            }
            if (!(params[index] instanceof Class)) {
                throw new IllegalArgumentException("泛型参数不是Class类型");
            }
            return (Class<T>) params[index];
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<Type, StoreAction<A>> initStoreActions() {
        actionTypeClass = getActionTypeClass();
        final Map<Type, StoreAction<A>> storeActions = new HashMap<>();
        for (T actionType : actionTypeClass.getEnumConstants()) {
            storeActions.put((Type) actionType, action -> {
                storeDatas.put(actionType, action.getData());
                storeChange(action);
            });
        }
        return storeActions;
    }

    /**
     * Gets the data.
     *
     * @param <D>  the generic type
     * @param type the type
     * @return the data
     */
    @SuppressWarnings("unchecked")
    public <D> D getData(T type) {
        return (D) storeDatas.get(type);
    }

}
