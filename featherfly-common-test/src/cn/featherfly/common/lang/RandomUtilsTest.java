
/*
 * Thgk-Component-Commons
 * copyright featherfly 2010-2020, all rights reserved.
 * created on May 20, 2010 6:06:31 PM
 */
package cn.featherfly.common.lang;


import cn.featherfly.common.lang.RandomUtils;
import cn.featherfly.common.lang.RandomUtils.CharType;
import org.testng.annotations.Test;


public class RandomUtilsTest {
	
	@Test
	public void testBoolean(){
		System.out.println("testBoolean -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomBoolean());
		}
	}
	@Test
	public void testGaussian(){
		System.out.println("testGaussian -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomGaussian());
		}
	}
	@Test
	public void testDouble(){
		System.out.println("testDouble -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomDouble());
		}
	}
	
	@Test
	public void testLetter(){
		System.out.println("testLetter -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomLetter());
		}
	}
	@Test
	public void testLetterUpperCase(){
		System.out.println("testLetterUpperCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomLetterUpperCase());
		}
	}
	@Test
	public void testLetterLowerCase(){
		System.out.println("testLetterLowerCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomLetterLowerCase());
		}
	}
	@Test
	public void testNumber(){
		System.out.println("testNumber -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomNumber());
		}
	}
	@Test
	public void testWord(){
		System.out.println("testWord -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomWord());
		}
	}
	@Test
	public void testString(){
		System.out.println("testString -------------------------------");
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomUtils.getRandomString(i+5));
		}
	}
	@Test
	public void testStringLowerCase(){
		System.out.println("testStringLowerCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			String result = RandomUtils.getRandomString(i+5,
					new CharType[]{CharType.lowerCase}
			);
			System.out.println(result);
		}
	}
	@Test
	public void testStringUpperCase(){
		System.out.println("testStringUpperCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			String result = RandomUtils.getRandomString(i+5,
					new CharType[]{CharType.upperCase}
			);
			System.out.println(result);
		}
	}
	@Test
	public void testStringNumberCase(){
		System.out.println("testStringNumberCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			String result = RandomUtils.getRandomString(i+5,
					new CharType[]{CharType.numberCase}
			);
			System.out.println(result);
		}
	}
	@Test
	public void testStringLowerCaseNumberCase(){
		System.out.println("testStringLowerCaseNumberCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			String result = RandomUtils.getRandomString(i+5,
					new CharType[]{CharType.numberCase,CharType.lowerCase}
			);
			System.out.println(result);
		}
	}
	@Test
	public void testStringUpperCaseNumberCase(){
		System.out.println("testStringUpperCaseNumberCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			String result = RandomUtils.getRandomString(i+5,
					new CharType[]{CharType.numberCase,CharType.upperCase}
			);
			System.out.println(result);
		}
	}
	@Test
	public void testStringUpperCaseLowerCase(){
		System.out.println("testStringUpperCaseNumberCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			String result = RandomUtils.getRandomString(i+5,
					new CharType[]{CharType.lowerCase,CharType.upperCase}
			);
			System.out.println(result);
		}
	}
	@Test
	public void testStringUpperCaseLowerCaseNumberCase(){
		System.out.println("testStringUpperCaseNumberCase -------------------------------");
		for (int i = 0; i < 10; i++) {
			String result = RandomUtils.getRandomString(i+5,
					new CharType[]{CharType.lowerCase,CharType.upperCase,CharType.numberCase}
			);
			System.out.println(result);
		}
	}
}
