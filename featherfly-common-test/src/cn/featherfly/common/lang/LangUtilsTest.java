
/*
 * Thgk-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 21, 2010 9:42:54 AM
 */
package cn.featherfly.common.lang;
import static org.testng.Assert.*;

import cn.featherfly.common.lang.LangUtils;
import org.testng.annotations.Test;


public class LangUtilsTest {
	@Test
	public void testPickFirst(){
		String s = "yufei";
		String result = LangUtils.pickFirst(null,s);
		assertEquals(s, result);

		result = LangUtils.pickFirst(s,null);
		assertEquals(s, result);

		result = LangUtils.pickFirst(null,null,s);
		assertEquals(s, result);

		result = LangUtils.pickFirst(null,s,null);
		assertEquals(s, result);

		result = LangUtils.pickFirst(null,s,"yi",null);
		assertEquals(s, result);

		result = LangUtils.pickFirst(null,"yi",s,null);
		assertFalse(s.equals(result));
	}
	@Test
	public void testPickLast(){
		String s = "yufei";
		String result = LangUtils.pickLast(null,s);
		assertEquals(s, result);

		result = LangUtils.pickLast(s,null);
		assertEquals(s, result);

		result = LangUtils.pickLast(s,null,null);
		assertEquals(s, result);

		result = LangUtils.pickLast(null,s,null);
		assertEquals(s, result);

		result = LangUtils.pickLast(null,s,"yi",null);
		assertFalse(s.equals(result));

		result = LangUtils.pickLast(null,"yi",s,null);
		assertEquals(s, result);
	}
}
