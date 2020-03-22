
package com.thgk.common.net.ftp;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

/**
 * <p>
 * 类的说明放这里
 * </p>
 * <p>
 * 创建时间 2011-8-28 - 上午11:06:25
 * </p>
 * <blockquote>
 * <h4>历史修改记录</h4>
 * <ul>
 * <li>修改人 修改时间 修改描述
 * </ul>
 * </blockquote>
 * <p>
 * copyright featherfly 2010-2020, all rights reserved.
 * </p>
 *
 * @author zhongj
 * @author cdpws r&d
 * @since 1.0
 * @version 1.0
 */
public class FtpClientTest {
	@Test
	public void testMakeDirectory() {
		FtpClient f = new FtpClient("localhost",2121,"admin","admin");
		assertTrue(f.makeDirectory("/aaa"));
		assertTrue(f.makeDirectory("/aaa/bbb"));
		assertTrue(f.makeDirectory("/aaa/bbb/ccc"));
		f.setRemoteDir("/aaa/bbb/ccc");
		assertTrue(f.makeDirectory("ddd"));
		assertTrue(f.isDirectoryExist("ddd"));
		assertTrue(f.isDirectoryExist("/aaa/bbb/ccc/ddd"));
	}
	@Test
	public void testIsDirectory() {
		FtpClient f = new FtpClient("localhost",2121,"admin","admin");
		assertTrue(f.isDirectoryExist("user1"));
		assertTrue(f.isDirectoryExist("aaa"));
		assertTrue(f.isDirectoryExist("/aaa"));
		assertTrue(f.isDirectoryExist("/aaa/bbb"));
		assertTrue(f.isDirectoryExist("aaa/bbb"));
		assertTrue(f.isDirectoryExist("aaa/bbb/ccc"));
		f.setRemoteDir("aaa");
		assertTrue(f.isDirectoryExist("bbb"));
		assertFalse(f.isDirectoryExist("aaa"));
		assertTrue(f.isDirectoryExist("/aaa/bbb"));
		assertFalse(f.isDirectoryExist("aaa/bbb"));
		assertTrue(f.isDirectoryExist("bbb/ccc"));
		f.setRemoteDir("bbb");
		assertTrue(f.isDirectoryExist("ccc"));
		assertTrue(f.isDirectoryExist("/aaa/bbb"));
		assertTrue(f.isDirectoryExist("/aaa/bbb/ccc"));
		f.setRemoteDir("/aaa/bbb/ccc");
		assertTrue(f.isDirectoryExist("ddd"));
		assertTrue(f.isDirectoryExist("/aaa/bbb/ccc/ddd"));
		f.setRemoteDir("/");
		assertTrue(f.isDirectoryExist("user1"));
		assertTrue(f.isDirectoryExist("aaa"));
		assertTrue(f.isDirectoryExist("/aaa"));
		assertTrue(f.isDirectoryExist("/aaa/bbb"));
		assertTrue(f.isDirectoryExist("aaa/bbb"));
		assertTrue(f.isDirectoryExist("aaa/bbb/ccc"));
		assertTrue(f.isDirectoryExist("/aaa/bbb/ccc"));
		assertFalse(f.isDirectoryExist("ccc"));
		assertFalse(f.isDirectoryExist("bbb"));
	}

//	@Test
//	public void testDelete() {
//		FtpClient f = new FtpClient("localhost",2121,"admin","admin");
//		f.delete("/aaa/bbb/ccc/ddd/1.txt");
//		f.setRemoteDir("/aaa/bbb/ccc");
//		System.out.println(f.deleteDirectory("ddd"));
//		System.out.println(f.deleteDirectory("/aaa/bbb/ccc/ddd/"));
//	}
}
