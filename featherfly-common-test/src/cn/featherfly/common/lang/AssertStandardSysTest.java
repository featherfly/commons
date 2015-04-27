
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.exception.AssertStandardSys;
import cn.featherfly.common.exception.StandardSysException;


public class AssertStandardSysTest {
	@Test
	public void testIsNotNull1(){
		AssertStandardSys.isNotNull("");
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testIsNotNull2(){
		AssertStandardSys.isNotNull(null);
	}

	@Test
	public void testArrayIsNotEmpty(){
		String[] ss = {"1","2"};
		AssertStandardSys.isNotEmpty(ss);
	}

	@Test(expectedExceptions={StandardSysException.class})
	public void testArrayIsNotEmpty2(){
		String[] ss = null;
		AssertStandardSys.isNotEmpty(ss);
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testArrayIsNotEmpty3(){
		String[] ss = {};
		AssertStandardSys.isNotEmpty(ss);
	}

	@Test
	public void testCollectionIsNotEmpty(){
		Collection<String> coll = new ArrayList<String>();
		coll.add("a");
		AssertStandardSys.isNotEmpty(coll);
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testCollectionIsNoEmpty2(){
		Collection<?> coll = null;
		AssertStandardSys.isNotEmpty(coll);
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testCollectionIsNoEmpty3(){
		Collection<?> coll = new ArrayList<String>();
		AssertStandardSys.isNotEmpty(coll);
	}

	@Test
	public void testMapIsNotEmpty(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		AssertStandardSys.isNotEmpty(map);
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testMapIsNoEmpty2(){
		Map<String, String> map = new HashMap<String, String>();
		AssertStandardSys.isNotEmpty(map);
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testMapIsNoEmpty3(){
		Map<String, String> map =null;
		AssertStandardSys.isNotEmpty(map);
	}

	@Test
	public void testIsNotBlank(){
		AssertStandardSys.isNotBlank("   sss   ");
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testIsNotBlank2(){
		AssertStandardSys.isNotBlank("");
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testIsNotBlank3(){
		AssertStandardSys.isNotBlank(null);
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testIsNotBlank4(){
		AssertStandardSys.isNotBlank("     ");
	}

	@Test
	public void testIsTree(){
		AssertStandardSys.isTrue(true, "");
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testIsTree2(){
		AssertStandardSys.isTrue(false, "");
	}

	@Test
	public void testIsFalse(){
		AssertStandardSys.isFalse(false, "");
	}
	@Test(expectedExceptions={StandardSysException.class})
	public void testIsFalse2(){
		AssertStandardSys.isFalse(true, "");
	}



}
