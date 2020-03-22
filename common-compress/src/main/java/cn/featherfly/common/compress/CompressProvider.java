
package cn.featherfly.common.compress;

import java.io.File;

/**
 * <p>
 * CompressProvider
 * </p>
 * 
 * @author 钟冀
 */
public interface CompressProvider {
	/**
	 * <p>
	 * 匹配扩展名
	 * </p>
	 * @param compressFile 文件
	 * @return 是否匹配
	 */
	boolean matchExtName(File compressFile);
	/**
	 * <p>
	 * 匹配文件内容
	 * </p>
	 * @param compressFile 文件
	 * @return 是否匹配
	 */
	boolean matchContent(File compressFile);
	/**
	 * <p>
	 * 解压文件到指定目录.
	 * </p>
	 * @param compressFile 压缩文件
	 * @param decompressDir 解压的目标目录
	 */
	void decompress(File compressFile, File decompressDir);
}
