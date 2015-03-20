
package cn.featherfly.common.io.file.rename;


/**
 * <p>
 * 当前时间戳重命名策略
 * </p>
 *
 * @author 钟冀
 */
public class TimestampRenamePolicy extends AbstractExtNameRenamePolicy{

	/**
	 */
	public TimestampRenamePolicy() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String rename(String fileName) {
		return appendExtName(System.currentTimeMillis() + "", fileName);
	}

	// ********************************************************************
	//	property
	// ********************************************************************

}
