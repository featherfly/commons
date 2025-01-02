
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2025-01-02 16:37:02
 * @Copyright: 2025 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.bean;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.testng.annotations.Test;

/**
 * ListInstantiatorTest.
 *
 * @author zhongj
 */
public class ReflectionListInstantiatorTest {

    @Test
    void testDefaultType() {
        ReflectionListInstantiator<String> instantiator = new ReflectionListInstantiator<>();
        List<String> list = instantiator.instantiate();

        assertEquals(list.getClass(), ArrayList.class);
    }

    @Test
    void testAssignType() {
        ReflectionListInstantiator<String> instantiator = new ReflectionListInstantiator<>(LinkedList.class);
        List<String> list = instantiator.instantiate();

        assertEquals(list.getClass(), LinkedList.class);
    }

    @Test
    void testInterfaceType() {
        ReflectionListInstantiator<String> instantiator = new ReflectionListInstantiator<>(List.class);
        List<String> list = instantiator.instantiate();

        assertEquals(list.getClass(), ArrayList.class);
    }

}
