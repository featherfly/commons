
package cn.featherfly.common.lang;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * Instances
 * </p>
 * .
 *
 * @author zhongj
 */
public class Instances {

    private static final Map<Class<?>, Object> INSTANCES = new ConcurrentHashMap<>();

    /**
     * Singleton.
     *
     * @param <T>  the generic type
     * @param type the type
     * @return the t
     */
    public static final <T> T singleton(Class<T> type) {
        @SuppressWarnings("unchecked")
        T object = (T) INSTANCES.get(type);
        if (object == null) {
            object = ClassUtils.newInstance(type);
            INSTANCES.put(type, object);
        }
        return object;
    }
}
