
package cn.featherfly.common.db.data.format;

import java.io.Writer;

import cn.featherfly.common.db.data.DataFormat;
import cn.featherfly.common.db.data.DataFormatFactory;
import cn.featherfly.common.db.dialect.Dialect;

/**
 * <p>
 * JsonDataFormatFactory
 * </p>
 * 
 * @author zhongj
 */
public class JsonDataFormatFactory implements DataFormatFactory{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataFormat createDataFormat(Writer writer, Dialect dialect) {
		return new JsonDataFormat(writer);
	}

}
