/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-18 21:52:18
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.structure.ChainMap;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * Test.
 *
 * @author zhongj
 */
public class TestKey {

    private Map<String, Object> map;
    private Map<String, Object> map2;

    private String[] keyContain = new String[] { "name0", "age0", "password0", "gender0", "name1", "age1", "password1",
        "gender1", "name2", "age2", "password2", "gender2", "name3", "age3", "password3", "gender3" };

    private String[] keyContain2;

    private int max = 1000000;

    private char[][] keys;

    private boolean eq(char c, int index, char[][] keys) {
        for (char ds : keys[index]) {
            if (ds == c) {
                return true;
            }
        }
        return false;
    }

    private boolean contain(String key) {
        //        if (key.length() > keys.length) {
        //            return false;
        //        }
        for (int i = 0; i < key.length(); i++) {
            if (eq(key.charAt(i), i, keys)) {
                return true;
            }
        }
        return false;
    }

    private boolean contain2(String key) {
        for (int i = 0; i < key.length(); i++) {
            if (eq(key.charAt(i), i, keys)) {
                return true;
            }
        }
        return false;
    }

    @BeforeClass
    public void beforeClass() {
        keyContain2 = "In the current implementation, the Java compiler included in Javassist has several limitations with respect to the language that the compiler can accept"
            .split(" ");
        System.out.println(ArrayUtils.toString(keyContain2));

        ChainMap<String, Object> chain = new ChainMapImpl<>();
        for (int i = 0; i < 3; i++) {
            chain.putChain("name" + i, "yufei").putChain("age" + i, 18).putChain("password" + i, "123456")
                .putChain("gender" + i, "MALE");
        }
        map = chain;

        map2 = new HashMap<>();
        for (String key2 : keyContain2) {
            map2.put(key2, "value of " + key2);
        }

        Optional<String> lenMax = Arrays.stream(keyContain).sorted((s1, s2) -> {
            if (s1.length() < s2.length()) {
                return 1;
            } else if (s1.length() > s2.length()) {
                return -1;
            } else {
                return 0;
            }
        }).findFirst();

        keys = new char[lenMax.get().length()][];
        for (int i = 0; i < lenMax.get().length(); i++) {
            Set<Character> chars = new HashSet<>();
            for (String key : keyContain) {
                if (key.length() <= i) {
                    //                    System.out.println(Strings.format("key.length() < i  ---- {} < {}", i, key.length()));
                    continue;
                }
                //                System.out.println(Strings.format("index[{}]   {}.length {}", i, key, key.length()));
                chars.add(key.charAt(i));
            }
            char[] cs = new char[chars.size()];
            Lang.each(chars, (e, index) -> cs[index] = e);
            keys[i] = cs;
        }
    }

    @Test
    public void testContain() {
        for (int i = 0; i < max; i++) {
            for (String key : keyContain) {
                contain(key);
            }
        }
    }

    @Test
    public void testContain2() {
        for (int i = 0; i < max; i++) {
            for (String key : keyContain2) {
                contain2(key);
            }
        }
    }

    @Test
    public void testMapContain() {
        for (int i = 0; i < max; i++) {
            for (String key : keyContain) {
                map.containsKey(key);
                //                System.out.println(key + " " + map.containsKey(key));
            }
        }
    }

    @Test
    public void testMapContain2() {
        for (int i = 0; i < max; i++) {
            for (String key : keyContain2) {
                map2.containsKey(key);
                //                System.out.println(key + " " + map.containsKey(key));
            }
        }
    }

    //    @Test
    //    public void testMapContainPart() {
    //        for (int i = 0; i < max; i++) {
    //            for (String key : keyContainPart) {
    //                map.containsKey(key);
    //                //                System.out.println(key + " " + map.containsKey(key));
    //            }
    //        }
    //    }
}
