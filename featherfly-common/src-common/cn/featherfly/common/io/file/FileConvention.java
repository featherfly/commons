
package cn.featherfly.common.io.file;

import java.io.File;

/**
 * <p>
 * 文件转换器.
 * </p>
 *
 *
 * @author 钟冀
 */
public interface FileConvention {

	/**
	 * <p>
	 * 将文件转换为指定对象.
	 * </p>
	 * @param <E> 对象
	 * @param file 文件
	 * @return 对象
	 */
	<E> E convert(File file);
}
