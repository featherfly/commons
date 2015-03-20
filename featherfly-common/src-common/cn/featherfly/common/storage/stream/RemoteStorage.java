
package cn.featherfly.common.storage.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.storage.Storage;


/**
 * <p>
 * 远程存储
 * </p>
 * @param <E> 存储的对象类型
 * @param <ID> 存储后产生的唯一标示，用于取回该对象
 * @author 钟冀
 */
public abstract class RemoteStorage<E, ID> implements Storage<E, ID>{

	/**
	 * 日志
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 */
	public RemoteStorage() {
	}

	// ********************************************************************
	//	protected method
	// ********************************************************************

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getProtocol() + "://" + getHost() + ":" + getPort();
	}

	/**
	 * <p>
	 * 获取协议
	 * </p>
	 * @return 协议
	 */
	protected abstract String getProtocol();

	// ********************************************************************
	//	property
	// ********************************************************************

	private String host;

	private Integer port;

	/**
	 * 返回host
	 * @return host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 设置host
	 * @param host host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 返回port
	 * @return port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * 设置port
	 * @param port port
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
}
