
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
		AssertIllegalArgument.isNotNull("");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotNull2(){
		AssertIllegalArgument.isNotNull(null);
	}

	@Test
	public void testArrayIsNotEmpty(){
		String[] ss = {"1","2"};
		AssertIllegalArgument.isNotEmpty(ss);
	}

	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testArrayIsNotEmpty2(){
		String[] ss = null;
		AssertIllegalArgument.isNotEmpty(ss);
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testArrayIsNotEmpty3(){
		String[] ss = {};
		AssertIllegalArgument.isNotEmpty(ss);
	}

	@Test
	public void testCollectionIsNotEmpty(){
		Collection<String> coll = new ArrayList<String>();
		coll.add("a");
		AssertIllegalArgument.isNotEmpty(coll);
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testCollectionIsNoEmpty2(){
		Collection<?> coll = null;
		AssertIllegalArgument.isNotEmpty(coll);
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testCollectionIsNoEmpty3(){
		Collection<?> coll = new ArrayList<String>();
		AssertIllegalArgument.isNotEmpty(coll);
	}

	@Test
	public void testMapIsNotEmpty(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		AssertIllegalArgument.isNotEmpty(map);
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testMapIsNoEmpty2(){
		Map<String, String> map = new HashMap<String, String>();
		AssertIllegalArgument.isNotEmpty(map);
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testMapIsNoEmpty3(){
		Map<String, String> map =null;
		AssertIllegalArgument.isNotEmpty(map);
	}

	@Test
	public void testIsNotBlank(){
		AssertIllegalArgument.isNotBlank("   sss   ");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotBlank2(){
		AssertIllegalArgument.isNotBlank("");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotBlank3(){
		AssertIllegalArgument.isNotBlank(null);
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsNotBlank4(){
		AssertIllegalArgument.isNotBlank("     ");
	}

	@Test
	public void testIsTree(){
		AssertIllegalArgument.isTrue(true, "");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsTree2(){
		AssertIllegalArgument.isTrue(false, "");
	}

	@Test
	public void testIsFalse(){
		AssertIllegalArgument.isFalse(false, "");
	}
	@Test(expectedExceptions={IllegalArgumentException.class})
	public void testIsFalse2(){
		AssertIllegalArgument.isFalse(true, "");
	}



}
