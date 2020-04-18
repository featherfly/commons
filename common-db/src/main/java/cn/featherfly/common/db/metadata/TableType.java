
package cn.featherfly.common.db.metadata;

/**
 * <p>
 * 数据表类型
 * </p>
 * @author zhongj
 */
public enum TableType {
	/**
	 * 表
	 */
	TABLE,
	/**
	 * 视图
	 */
	VIEW,
	/**
	 * 系统表
	 */
	SYSTEM_TABLE,
	/**
	 * 全局临时表
	 */
	GLOBAL_TEMPORARY,
	/**
	 * 本地临时表
	 */
	LOCAL_TEMPORARY,
	/**
	 *
	 */
	ALIAS,
	/**
	 *
	 */
	SYNONYM;
}
