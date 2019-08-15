
package cn.featherfly.common.compress;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import cn.featherfly.common.compress.zip.ZipCompressProvider;
import cn.featherfly.common.lang.ServiceLoaderUtils;
import cn.featherfly.common.lang.StringUtils;

/**
 * <p>
 * 压缩、解压工具类
 * </p>
 *
 *
 * @author 钟冀
 */
public final class CompressUtils {
	
	private static final Set<CompressProvider> COMPRESS_PROVIDERS = new HashSet<CompressProvider>();
	
	static {
		COMPRESS_PROVIDERS.add(new ZipCompressProvider());
		COMPRESS_PROVIDERS.addAll(ServiceLoaderUtils.loadAll(CompressProvider.class));
	}

	private CompressUtils() {
	}
	
	/**
	 * <p>
	 * 根据支持的压缩算法聪明的自动查找实现.
	 * 如果是没有提供实现的压缩算法，择抛出异常.
	 * </p>
	 * @param compressFile 压缩文件
	 * @param decompressDir 解压的目标
	 */
	public static void decompress(File compressFile, File decompressDir) {
		for (CompressProvider provider : COMPRESS_PROVIDERS) {
			if (provider.matchExtName(compressFile)) {
				provider.decompress(compressFile, decompressDir);
				return;
			}
		}
		for (CompressProvider provider : COMPRESS_PROVIDERS) {
			if (provider.matchContent(compressFile)) {
				provider.decompress(compressFile, decompressDir);
				return;
			}
		}
		throw new CompressException(
				StringUtils.format("there is no CompressProvider can work with this file #1 in #2", new String[]{
					compressFile.getAbsolutePath(), COMPRESS_PROVIDERS.toString() 
				})
		);
	}	
}
