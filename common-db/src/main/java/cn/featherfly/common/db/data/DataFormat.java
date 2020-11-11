
package cn.featherfly.common.db.data;

import java.sql.ResultSet;

import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.metadata.DatabaseMetadata;

/**
 * <p>
 * DataFomart
 * </p>
 * .
 *
 * @author zhongj
 */
public interface DataFormat {

    /**
     * Write data start.
     *
     * @param databaseMetadata the database metadata
     * @throws Exception the exception
     */
    void writeDataStart(DatabaseMetadata databaseMetadata) throws Exception;

    /**
     * Write data end.
     *
     * @param databaseMetadata the database metadata
     * @throws Exception the exception
     */
    void writeDataEnd(DatabaseMetadata databaseMetadata) throws Exception;

    /**
     * Write table start.
     *
     * @param tableMetadata the table metadata
     * @throws Exception the exception
     */
    void writeTableStart(Table tableMetadata) throws Exception;

    /**
     * Write table end.
     *
     * @param tableMetadata the table metadata
     * @throws Exception the exception
     */
    void writeTableEnd(Table tableMetadata) throws Exception;

    /**
     * Write row.
     *
     * @param tableMetadata the table metadata
     * @param res           the res
     * @throws Exception the exception
     */
    void writeRow(Table tableMetadata, ResultSet res) throws Exception;

    //	void readDataStart() throws Exception;
    //	void readDataEnd() throws Exception;
    //	void readTableStart() throws Exception;
    //	void readTableEnd() throws Exception;
    //	void readRow() throws Exception;
}
