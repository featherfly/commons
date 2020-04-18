
package cn.featherfly.common.db.data;

import java.io.Writer;

import cn.featherfly.common.db.dialect.Dialect;



/**
 * <p>
 * DataFormatFactory
 * </p>
 * 
 * @author zhongj
 */
public interface DataFormatFactory {
	/**
	 * <p>
	 * 创建DataFormat
	 * </p>
	 * @param writer writer
	 * @param dialect dialect
	 * @return DataFormat
	 */
	DataFormat createDataFormat(Writer writer, Dialect dialect);
}
