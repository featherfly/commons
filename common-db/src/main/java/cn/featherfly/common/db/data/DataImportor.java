
package cn.featherfly.common.db.data;

import java.io.Reader;

/**
 * <p>
 * 数据导入
 * </p>
 *
 * @author zhongj
 */
public interface DataImportor {
	/**
	 * <p>
	 * 导入.
	 * </p>
	 * @param reader reader
	 */
	void imp(Reader reader);
	/**
	 * <p>
	 * 数据存在时的策略.
	 * </p>
	 *
	 * @author zhongj
	 */
	public static enum ExistPolicy {
		/**
		 * 异常
		 */
		exception,
		/**
		 * 忽略
		 */
		ignore,
		/**
		 * 更新
		 */
		update
	}

	/**
	 * <p>
	 * 事务策略.
	 * </p>
	 *
	 * @author zhongj
	 */
	public static enum TransactionPolicy {
		/**
		 * 不提交，用于使用统一的事物管理的情形
		 */
		none,
		/**
		 * 导入单张表数据后提交一次
		 */
		table,
		/**
		 * 导入全部后统一提交
		 */
		all
	}
}
