
/*
 * Thgk-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;

import static org.testng.Assert.*;

import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.lang.ClassUtils;
import org.testng.annotations.Test;


public class ClassUtilsTest {


    @Test
    public void testFormart(){
    	assertTrue(ClassUtils.isParent(Map.class, HashMap.class));
    	assertFalse(ClassUtils.isParent(HashMap.class, Map.class));
    }

}
