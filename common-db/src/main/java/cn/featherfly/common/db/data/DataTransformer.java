
package cn.featherfly.common.db.data;

/**
 * <p>
 * DataTransformer
 * 数据变换器，用于导入或导出数据时对一些特殊数据进行处理
 * </p>
 *
 * @author zhongj
 */
public interface DataTransformer {
	/**
	 * <p>
	 * 数据变换
	 * </p>
	 * @param recordModel 数据记录模型
	 * @return 返回处理后的数据记录模型
	 */
	RecordModel transform(RecordModel recordModel);
}
