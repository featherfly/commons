
package cn.featherfly.common.io.file.rename;

import cn.featherfly.common.lang.UUIDGenerator;

/**
 * <p>
 * UUID重命名策略
 * </p>
 *
 * @author 钟冀
 */
public class UUIDRenamePolicy extends AbstractExtNameRenamePolicy{

	/**
	 */
	public UUIDRenamePolicy() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String rename(String name) {
		return appendExtName(UUIDGenerator.generateUUID32(), name);
	}
}
