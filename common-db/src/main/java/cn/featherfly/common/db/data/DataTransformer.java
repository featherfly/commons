
package cn.featherfly.common.db.data;

/**
 * DataTransformer 数据转换器，用于导入或导出数据时对一些特殊数据进行处理.
 *
 * @author zhongj
 */
public interface DataTransformer {
    /**
     * <p>
     * 数据变换
     * </p>
     *
     * @param currentRecordModel  当前数据记录模型，即已经经过转换器转换过的
     * @param originalRecordModel 原始数据记录模型
     * @return 返回处理后的数据记录模型
     */
    RecordModel transform(RecordModel currentRecordModel, RecordModel originalRecordModel);
}
