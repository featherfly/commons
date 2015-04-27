
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.exception.AssertStandardApp;
import cn.featherfly.common.exception.StandardAppException;


public class AssertStandardAppTest {
	@Test
	public void testIsNotNull1(){
		AssertStandardApp.isNotNull("");
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testIsNotNull2(){
		AssertStandardApp.isNotNull(null);
	}

	@Test
	public void testArrayIsNotEmpty(){
		String[] ss = {"1","2"};
		AssertStandardApp.isNotEmpty(ss);
	}

	@Test(expectedExceptions={StandardAppException.class})
	public void testArrayIsNotEmpty2(){
		String[] ss = null;
		AssertStandardApp.isNotEmpty(ss);
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testArrayIsNotEmpty3(){
		String[] ss = {};
		AssertStandardApp.isNotEmpty(ss);
	}

	@Test
	public void testCollectionIsNotEmpty(){
		Collection<String> coll = new ArrayList<String>();
		coll.add("a");
		AssertStandardApp.isNotEmpty(coll);
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testCollectionIsNoEmpty2(){
		Collection<?> coll = null;
		AssertStandardApp.isNotEmpty(coll);
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testCollectionIsNoEmpty3(){
		Collection<?> coll = new ArrayList<String>();
		AssertStandardApp.isNotEmpty(coll);
	}

	@Test
	public void testMapIsNotEmpty(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		AssertStandardApp.isNotEmpty(map);
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testMapIsNoEmpty2(){
		Map<String, String> map = new HashMap<String, String>();
		AssertStandardApp.isNotEmpty(map);
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testMapIsNoEmpty3(){
		Map<String, String> map =null;
		AssertStandardApp.isNotEmpty(map);
	}

	@Test
	public void testIsNotBlank(){
		AssertStandardApp.isNotBlank("   sss   ");
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testIsNotBlank2(){
		AssertStandardApp.isNotBlank("");
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testIsNotBlank3(){
		AssertStandardApp.isNotBlank(null);
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testIsNotBlank4(){
		AssertStandardApp.isNotBlank("     ");
	}

	@Test
	public void testIsTree(){
		AssertStandardApp.isTrue(true, "");
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testIsTree2(){
		AssertStandardApp.isTrue(false, "");
	}

	@Test
	public void testIsFalse(){
		AssertStandardApp.isFalse(false, "");
	}
	@Test(expectedExceptions={StandardAppException.class})
	public void testIsFalse2(){
		AssertStandardApp.isFalse(true, "");
	}



}
