
package cn.featherfly.common.compress.zip;

import java.io.File;

import cn.featherfly.common.compress.CompressException;
import cn.featherfly.common.compress.CompressProvider;
import cn.featherfly.common.io.FileUtils;

/**
 * <p>
 * Zip压缩实现
 * </p>
 * 
 * @author 钟冀
 */
public class ZipCompressProvider implements CompressProvider{
	
	private static final String EXT_NAME = "zip";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean matchExtName(File compressFile) {
		return EXT_NAME.equalsIgnoreCase(FileUtils.getFileExtName(compressFile));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean matchContent(File compressFile) {
		try {
			new ZipFile(compressFile);
			return true;
		} catch (CompressException e) {
			return false;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void decompress(File compressFile, File decompressDir) {
		new ZipFile(compressFile).decompress(decompressDir);
	}

}
