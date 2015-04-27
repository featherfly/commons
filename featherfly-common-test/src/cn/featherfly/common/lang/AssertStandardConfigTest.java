
package cn.featherfly.common.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import cn.featherfly.common.exception.AssertStandardConfig;
import cn.featherfly.common.exception.StandardConfigException;


public class AssertStandardConfigTest {
	@Test
	public void testIsNotNull1(){
		AssertStandardConfig.isNotNull("");
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testIsNotNull2(){
		AssertStandardConfig.isNotNull(null);
	}

	@Test
	public void testArrayIsNotEmpty(){
		String[] ss = {"1","2"};
		AssertStandardConfig.isNotEmpty(ss);
	}

	@Test(expectedExceptions={StandardConfigException.class})
	public void testArrayIsNotEmpty2(){
		String[] ss = null;
		AssertStandardConfig.isNotEmpty(ss);
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testArrayIsNotEmpty3(){
		String[] ss = {};
		AssertStandardConfig.isNotEmpty(ss);
	}

	@Test
	public void testCollectionIsNotEmpty(){
		Collection<String> coll = new ArrayList<String>();
		coll.add("a");
		AssertStandardConfig.isNotEmpty(coll);
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testCollectionIsNoEmpty2(){
		Collection<?> coll = null;
		AssertStandardConfig.isNotEmpty(coll);
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testCollectionIsNoEmpty3(){
		Collection<?> coll = new ArrayList<String>();
		AssertStandardConfig.isNotEmpty(coll);
	}

	@Test
	public void testMapIsNotEmpty(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("k", "v");
		AssertStandardConfig.isNotEmpty(map);
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testMapIsNoEmpty2(){
		Map<String, String> map = new HashMap<String, String>();
		AssertStandardConfig.isNotEmpty(map);
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testMapIsNoEmpty3(){
		Map<String, String> map =null;
		AssertStandardConfig.isNotEmpty(map);
	}

	@Test
	public void testIsNotBlank(){
		AssertStandardConfig.isNotBlank("   sss   ");
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testIsNotBlank2(){
		AssertStandardConfig.isNotBlank("");
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testIsNotBlank3(){
		AssertStandardConfig.isNotBlank(null);
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testIsNotBlank4(){
		AssertStandardConfig.isNotBlank("     ");
	}

	@Test
	public void testIsTree(){
		AssertStandardConfig.isTrue(true, "");
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testIsTree2(){
		AssertStandardConfig.isTrue(false, "");
	}

	@Test
	public void testIsFalse(){
		AssertStandardConfig.isFalse(false, "");
	}
	@Test(expectedExceptions={StandardConfigException.class})
	public void testIsFalse2(){
		AssertStandardConfig.isFalse(true, "");
	}



}
