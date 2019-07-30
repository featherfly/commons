
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.asserts.LocalizedAssert;

public class LocalizedAssertTest {

    LocalizedAssert<RuntimeException> asserts = new LocalizedAssert<>(RuntimeException.class);

    @Test
    public void testIsNotNull1() {
        asserts.isNotNull("", "string");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsNotNull2() {
        asserts.isNotNull(null, "object");
    }

    @Test
    public void testArrayIsNotEmpty() {
        String[] ss = { "1", "2" };
        asserts.isNotEmpty(ss, "string[]");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testArrayIsNotEmpty2() {
        String[] ss = null;
        asserts.isNotEmpty(ss, "string[]");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testArrayIsNotEmpty3() {
        String[] ss = {};
        asserts.isNotEmpty(ss, "string[]");
    }

    @Test
    public void testCollectionIsNotEmpty() {
        Collection<String> coll = new ArrayList<>();
        coll.add("a");
        asserts.isNotEmpty(coll, "Collection<String>");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testCollectionIsNoEmpty2() {
        Collection<?> coll = null;
        asserts.isNotEmpty(coll, "Collection<?>");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testCollectionIsNoEmpty3() {
        Collection<?> coll = new ArrayList<String>();
        asserts.isNotEmpty(coll, "Collection<?>");
    }

    @Test
    public void testMapIsNotEmpty() {
        Map<String, String> map = new HashMap<>();
        map.put("k", "v");
        asserts.isNotEmpty(map, "Map<String, String>");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testMapIsNoEmpty2() {
        Map<String, String> map = new HashMap<>();
        asserts.isNotEmpty(map, "Map<String, String>");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testMapIsNoEmpty3() {
        Map<String, String> map = null;
        asserts.isNotEmpty(map, "Map<String, String>");
    }

    @Test
    public void testIsNotBlank() {
        asserts.isNotBlank("   sss   ", "   sss   ");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsNotBlank2() {
        asserts.isNotBlank("", "string");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsNotBlank3() {
        asserts.isNotBlank(null, "object");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsNotBlank4() {
        asserts.isNotBlank("     ", "s      e");
    }

    //	@Test
    //	public void testIsTree(){
    //		asserts.isTrue(true, "");
    //	}
    //	@Test(expectedExceptions={RuntimeException.class})
    //	public void testIsTree2(){
    //		asserts.isTrue(false, "");
    //	}

    //	@Test
    //	public void testIsFalse(){
    //		asserts.isFalse(false, "");
    //	}
    //	@Test(expectedExceptions={RuntimeException.class})
    //	public void testIsFalse2(){
    //		asserts.isFalse(true, "");
    //	}

}
