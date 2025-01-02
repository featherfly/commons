
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-01-02 16:37:02
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.testng.annotations.Test;

/**
 * MapInstantiatorTest.
 *
 * @author zhongj
 */
public class ReflectionMapInstantiatorTest {

    @Test
    void testDefaultType() {
        ReflectionMapInstantiator<String, String> instantiator = new ReflectionMapInstantiator<>();
        Map<String, String> map = instantiator.instantiate();

        assertEquals(map.getClass(), HashMap.class);
    }

    @Test
    void testAssignType() {
        ReflectionMapInstantiator<String, String> instantiator = new ReflectionMapInstantiator<>(LinkedHashMap.class);
        Map<String, String> map = instantiator.instantiate();

        assertEquals(map.getClass(), LinkedHashMap.class);

        instantiator = new ReflectionMapInstantiator<>(ConcurrentHashMap.class);
        map = instantiator.instantiate();

        assertEquals(map.getClass(), ConcurrentHashMap.class);
    }

    @Test
    void testInterfaceType() {
        ReflectionMapInstantiator<String, String> instantiator = new ReflectionMapInstantiator<>(Map.class);
        Map<String, String> map = instantiator.instantiate();

        assertEquals(map.getClass(), HashMap.class);

        instantiator = new ReflectionMapInstantiator<>(ConcurrentMap.class);
        map = instantiator.instantiate();

        assertEquals(map.getClass(), ConcurrentHashMap.class);
    }

}
