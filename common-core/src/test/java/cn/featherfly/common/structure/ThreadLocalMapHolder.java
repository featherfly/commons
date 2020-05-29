
package cn.featherfly.common.structure;

/**
 * <p>
 * ThreadLocalMapHolder
 * </p>
 *
 * @author zhongj
 */
public class ThreadLocalMapHolder {

    static ThreadLocalMap<String, Object> t = new ThreadLocalMap<>();

    static void set(String k, Object v) {
        t.put(k, v);
    }

    static Object get(String k) {
        return t.get(k);
    }
}
