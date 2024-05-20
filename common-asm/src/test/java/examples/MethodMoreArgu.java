
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-21 14:53:21
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package examples;

import java.util.Collection;
import java.util.Map;

/**
 * Methods.
 *
 * @author zhongj
 */
public class MethodMoreArgu {

    public void a2(String s, Integer i) {
    }

    public void a3(String s, Integer i, Object o) {
    }

    public void a4(String s, Integer i, Object o, Collection<String> coll) {
    }

    public void a5(String s, Integer i, Object o, Collection<String> coll, Map<String, Object> map) {
    }

    public String ra2(String s, Integer i) {
        return "ra2(String s, Integer i) return";
    }

    public Integer ra3(String s, Integer i, Object o) {
        return Integer.valueOf(1);
    }

    public Integer ra4(String s, Integer i, Object o, Collection<String> coll) {
        return 1;
    }

    public Collection<Object> ra5(String s, Integer i, Object o, Collection<String> coll, Map<String, Object> map) {
        return null;
    }
}
