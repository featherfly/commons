
package cn.featherfly.common.db;

import java.util.HashMap;
import java.util.Map;

/**
 * Test2.
 *
 * @author zhongj
 */
public class Test2 {
    public void query(String sql, Object... ars) {
        System.out.println("query(String sql, Object...ars)");
    }

    public void query(String sql, Map<String, Object> map) {
        System.out.println("query(String sql, Map<String, Object> map)");
    }

    public static void main(String[] args) {
        Test2 t = new Test2();
        t.query("", "");
        t.query("", new HashMap<>());
    }

}
