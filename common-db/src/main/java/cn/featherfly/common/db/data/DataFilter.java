
package cn.featherfly.common.db.data;

import java.sql.Connection;

/**
 * <p>
 * DataFilter
 * 数据过滤器，用于在导入与导出时进行过滤
 * </p>
 *
 * @author zhongj
 */
public interface DataFilter {
	/**
	 * <p>
	 * 是否过滤指定数据，返回true表示过滤当前验证的数据
	 * </p>
	 * @param recordModel 数据记录模型
	 * @param connection 数据库连接.不要在扩展实现里进行事务提交，此连接只为查询所用
	 * @return 是否过滤
	 */
	boolean filter(RecordModel recordModel, Connection connection);
}
