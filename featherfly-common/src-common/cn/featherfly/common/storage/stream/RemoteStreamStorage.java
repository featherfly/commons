
package cn.featherfly.common.storage.stream;

import java.io.InputStream;



/**
 * <p>
 * 远程流存储
 * </p>
 * @author 钟冀
 */
public abstract class RemoteStreamStorage extends RemoteStorage<InputStream, String>
	implements StreamStorage {

	/**
	 */
	public RemoteStreamStorage() {
	}
}
