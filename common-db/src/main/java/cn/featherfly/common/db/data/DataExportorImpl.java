
/**
 * @author zhongj - yufei Mar 12, 2009
 */
package cn.featherfly.common.db.data;

import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Collection;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.data.query.SimpleQuery;
import cn.featherfly.common.db.data.query.TableQuery;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.db.metadata.TableMetadata;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.repository.Query;

/**
 * <p>
 * 通用数据导出器，使用DataFormatFactory格式化数据
 * </p>
 *
 * @author zhongj
 */
public class DataExportorImpl extends AbstractDataExportor {

    private DataFormatFactory facotry;

    /**
     * @param factory DataFormatFactory
     */
    public DataExportorImpl(DataFormatFactory factory) {
        facotry = factory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportData(Writer writer, Collection<Query> querys) {
        try {
            DatabaseMetadata databaseMetadata = getDatabaseMetadata();
            DataFormat dataFormat = facotry.createDataFormat(writer, getDialect());
            dataFormat.writeDataStart(databaseMetadata);
            for (Query query : querys) {
                exportData(query, dataFormat);
            }
            dataFormat.writeDataEnd(databaseMetadata);
        } catch (Exception e) {
            throw new ExportException(e);
        }
    }

    private void exportData(Query query, DataFormat dataFormat) throws Exception {
        //得到字段信息
        Connection conn = getDataSource().getConnection();
        PreparedStatement prep = conn.prepareStatement(query.getExecution());
        if (LangUtils.isNotEmpty(query.getParams())) {
            JdbcUtils.setParameters(prep, query.getParams());
        }
        ResultSet res = prep.executeQuery();
        ResultSetMetaData rsmd = res.getMetaData();
        String name = null;
        if (query instanceof SimpleQuery) {
            name = ((SimpleQuery) query).getName();
        } else if (query instanceof TableQuery) {
            name = ((TableQuery) query).getName();
        }
        if (LangUtils.isEmpty(name)) {
            name = rsmd.getTableName(1);
            if (LangUtils.isEmpty(name)) {
                throw new ExportException("#driver.resultset.tablename");
                //				throw new ExportException("自动获取表名称失败，当前数据库驱动不支持从结果集获取表名称！");
            } else {
                logger.debug("自动从结果集第一列获取表名称：{}", name);
            }
        }
        TableMetadata tableMetadata = getDatabaseMetadata().getTable(name);

        dataFormat.writeTableStart(tableMetadata);
        while (res.next()) {
            dataFormat.writeRow(tableMetadata, res);
        }

        dataFormat.writeTableEnd(tableMetadata);

        JdbcUtils.closeQuietly(conn, prep, res);
    }

    // ********************************************************************
    //	property
    // ********************************************************************
}