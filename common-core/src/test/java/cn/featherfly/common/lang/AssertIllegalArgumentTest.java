
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.lang.asserts.IllegalArgumentAssert;
import cn.featherfly.common.lang.vo.User;
import cn.featherfly.common.lang.vo.User2;

public class AssertIllegalArgumentTest {

    IllegalArgumentAssert illegalArgumentAssert = new IllegalArgumentAssert();

    @Test
    public void testIsNotNull1() {
        AssertIllegalArgument.isNotNull("", "string");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotNull2() {
        AssertIllegalArgument.isNotNull(null, "object");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotNull3() {
        User user = new User();
        illegalArgumentAssert.isNotNull(user::getAge);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotNull4() {
        User2 user = new User2();
        illegalArgumentAssert.isNotNull(user::getAge);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testStringIsNotEmpty() {
        User user = new User();
        user.setName("");
        illegalArgumentAssert.isNotEmpty(user::getName);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testListIsNotEmpty() {
        User user = new User();
        user.setList(new ArrayList<>());
        illegalArgumentAssert.isNotEmpty(user::getList);
    }

    @Test
    public void testArrayIsNotEmpty() {
        String[] ss = { "1", "2" };
        AssertIllegalArgument.isNotEmpty(ss, "string[]");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testArrayIsNotEmpty2() {
        String[] ss = null;
        AssertIllegalArgument.isNotEmpty(ss, "string[]");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testArrayIsNotEmpty3() {
        String[] ss = {};
        AssertIllegalArgument.isNotEmpty(ss, "string[]");
    }

    @Test
    public void testCollectionIsNotEmpty() {
        Collection<String> coll = new ArrayList<>();
        coll.add("a");
        AssertIllegalArgument.isNotEmpty(coll, "Collection<String>");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testCollectionIsNoEmpty2() {
        Collection<?> coll = null;
        AssertIllegalArgument.isNotEmpty(coll, "Collection<?>");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testCollectionIsNoEmpty3() {
        Collection<?> coll = new ArrayList<String>();
        AssertIllegalArgument.isNotEmpty(coll, "Collection<?>");
    }

    @Test
    public void testMapIsNotEmpty() {
        Map<String, String> map = new HashMap<>();
        map.put("k", "v");
        AssertIllegalArgument.isNotEmpty(map, "Map<String, String>");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testMapIsNoEmpty2() {
        Map<String, String> map = new HashMap<>();
        AssertIllegalArgument.isNotEmpty(map, "Map<String, String>");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testMapIsNoEmpty3() {
        Map<String, String> map = null;
        AssertIllegalArgument.isNotEmpty(map, "Map<String, String>");
    }

    @Test
    public void testIsNotBlank() {
        AssertIllegalArgument.isNotBlank("   sss   ", "   sss   ");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotBlank2() {
        AssertIllegalArgument.isNotBlank("", "string");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotBlank3() {
        AssertIllegalArgument.isNotBlank(null, "object");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotBlank4() {
        AssertIllegalArgument.isNotBlank("     ", "s      e");
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotBlank5() {
        User user = new User();
        illegalArgumentAssert.isNotBlank(user::getName);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testIsNotBlank6() {
        User user = new User();
        user.setName("     ");
        illegalArgumentAssert.isNotBlank(user::getName);
    }

    @Test
    public void testGt() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isGt(user::getAge, 17);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testGt2() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isGt(user::getAge, 18);
    }

    @Test
    public void testGe() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isGe(user::getAge, 18);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testGe2() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isGe(user::getAge, 19);
    }

    @Test
    public void testLt() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isLt(user::getAge, 19);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testLt2() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isLt(user::getAge, 18);
    }

    @Test
    public void testLe() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isLe(user::getAge, 18);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testLe2() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isLe(user::getAge, 17);
    }

    @Test
    public void testInRange() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isInRange(user::getAge, 1, 30);

        illegalArgumentAssert.isInRange(user::getAge, 1, 18);

        illegalArgumentAssert.isInRange(user::getAge, 18, 30);
    }

    @Test(expectedExceptions = { IllegalArgumentException.class })
    public void testInRange2() {
        User user = new User();
        user.setAge(18);
        illegalArgumentAssert.isInRange(user::getAge, 19, 30);
    }

    //	@Test
    //	public void testIsTree(){
    //		AssertIllegalArgument.isTrue(true, "");
    //	}
    //	@Test(expectedExceptions={IllegalArgumentException.class})
    //	public void testIsTree2(){
    //		AssertIllegalArgument.isTrue(false, "");
    //	}

    //	@Test
    //	public void testIsFalse(){
    //		AssertIllegalArgument.isFalse(false, "");
    //	}
    //	@Test(expectedExceptions={IllegalArgumentException.class})
    //	public void testIsFalse2(){
    //		AssertIllegalArgument.isFalse(true, "");
    //	}

}
