
package cn.featherfly.common.db.data;

import java.sql.ResultSet;

import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.Table;

/**
 * <p>
 * DataFomart
 * </p>
 *
 * @author zhongj
 */
public interface DataFormat {
    void writeDataStart(DatabaseMetadata databaseMetadata) throws Exception;

    void writeDataEnd(DatabaseMetadata databaseMetadata) throws Exception;

    void writeTableStart(Table tableMetadata) throws Exception;

    void writeTableEnd(Table tableMetadata) throws Exception;

    void writeRow(Table tableMetadata, ResultSet res) throws Exception;

    //	void readDataStart() throws Exception;
    //	void readDataEnd() throws Exception;
    //	void readTableStart() throws Exception;
    //	void readTableEnd() throws Exception;
    //	void readRow() throws Exception;
}
