
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.lang.AssertIllegalArgument;
import org.testng.annotations.Test;


public class AssertIllegalArgumentTest {
	@Test
	public void testIsNotNull1(){
		AssertIllegalArgument.isNotNull("", "string");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotNull2(){
		AssertIllegalArgument.isNotNull(null, "object");
	}

	@Test
	public void testArrayIsNotEmpty(){
		String[] ss = {"1","2"};
		AssertIllegalArgument.isNotEmpty(ss, "string[]");
	}

	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testArrayIsNotEmpty2(){
		String[] ss = null;
		AssertIllegalArgument.isNotEmpty(ss, "string[]");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testArrayIsNotEmpty3(){
		String[] ss = {};
		AssertIllegalArgument.isNotEmpty(ss, "string[]");
	}

	@Test
	public void testCollectionIsNotEmpty(){
		Collection<String> coll = new ArrayList<String>();
		coll.add("a");
		AssertIllegalArgument.isNotEmpty(coll, "Collection<String>");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testCollectionIsNoEmpty2(){
		Collection<?> coll = null;
		AssertIllegalArgument.isNotEmpty(coll, "Collection<?>");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testCollectionIsNoEmpty3(){
		Collection<?> coll = new ArrayList<String>();
		AssertIllegalArgument.isNotEmpty(coll, "Collection<?>");
	}

	@Test
	public void testMapIsNotEmpty(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		AssertIllegalArgument.isNotEmpty(map, "Map<String, String>");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testMapIsNoEmpty2(){
		Map<String, String> map = new HashMap<String, String>();
		AssertIllegalArgument.isNotEmpty(map, "Map<String, String>");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testMapIsNoEmpty3(){
		Map<String, String> map =null;
		AssertIllegalArgument.isNotEmpty(map, "Map<String, String>");
	}

	@Test
	public void testIsNotBlank(){
		AssertIllegalArgument.isNotBlank("   sss   ", "   sss   ");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotBlank2(){
		AssertIllegalArgument.isNotBlank("", "string");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotBlank3(){
		AssertIllegalArgument.isNotBlank(null, "object");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotBlank4(){
		AssertIllegalArgument.isNotBlank("     ", "s      e");
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
