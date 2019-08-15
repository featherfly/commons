
package cn.featherfly.common.lang;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.asserts.LocalizedAssert;
import cn.featherfly.common.lang.vo.User;

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

    @Test
    public void testIsExsits() {
        asserts.isExists(new File("src"), "src");
    }

    @Test
    public void testIsFile() {
        asserts.isFile(new File("build.gradle"), "build.gradle");
    }

    @Test
    public void testIsDir() {
        asserts.isDirectory(new File("src"), "src");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsExsits2() {
        asserts.isExists(new File("src11"), "src11");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsFile2() {
        asserts.isFile(new File("build.gradle11"), "build.gradle11");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsDir2() {
        asserts.isDirectory(new File("src11"), "src11");
    }

    @Test
    public void testIsNotInterface() {
        asserts.isNotInterface(User.class);
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void testIsNotInterface2() {
        asserts.isNotInterface(List.class);
    }

    @Test
    public void isInRange() {
        asserts.isInRange(5, 1, 12, "month");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void isInRange2() {
        asserts.isInRange(0, 1, 12, "month");
    }

    @Test
    public void isGt() {
        asserts.isGt(5, 1, "month");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void isGt2() {
        asserts.isGt(5, 5, "month");
    }

    @Test
    public void isGe() {
        asserts.isGe(5, 5, "month");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void isGe2() {
        asserts.isGe(5, 12, "month");
    }

    @Test
    public void isLt() {
        asserts.isLt(5, 12, "month");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void isLt2() {
        asserts.isLt(5, 5, "month");
    }

    @Test
    public void isLe() {
        asserts.isLe(5, 5, "month");
    }

    @Test(expectedExceptions = { RuntimeException.class })
    public void isLe2() {
        asserts.isLe(5, 1, "month");
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
