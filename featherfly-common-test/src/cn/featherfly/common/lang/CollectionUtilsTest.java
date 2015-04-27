
/*
 * Thgk-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 21, 2010 3:46:26 PM
 */
package cn.featherfly.common.lang;
import static org.testng.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.testng.annotations.Test;



public class CollectionUtilsTest {


    @Test
    public void testIsParent(){
    	Collection<Object> c = CollectionUtils.newInstance(Collection.class);
    	
    	assertTrue(c instanceof ArrayList);
    	
    	c = CollectionUtils.newInstance(List.class);
    	assertTrue(c instanceof ArrayList);
    	
    	c = CollectionUtils.newInstance(Set.class);
    	assertTrue(c instanceof HashSet);
    	c = CollectionUtils.newInstance(Queue.class);
    	assertTrue(c instanceof ArrayDeque);
    }

}
