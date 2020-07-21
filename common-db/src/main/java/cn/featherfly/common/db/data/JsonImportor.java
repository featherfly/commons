/**
 * @author zhongj - yufei Mar 12, 2009
 */
package cn.featherfly.common.db.data;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.data.RecordModel.ValueModel;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * 导出为JSON格式的数据导入
 * </p>
 * .
 *
 * @author zhongj
 */
public class JsonImportor extends AbstractDataImportor {

    /** The factory. */
    private JsonFactory factory;

    /**
     * Instantiates a new json importor.
     *
     * @param dialect the dialect
     */
    public JsonImportor(Dialect dialect) {
        super(dialect);
        factory = new JsonFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void imp(Reader reader) {
        Connection conn = null;
        try {
            JsonParser parser = factory.createParser(reader);

            conn = getDataSource().getConnection();

            conn.setAutoCommit(false);

            if (!isFkCheck()) {
                addPrepareSql(getDialect().getInitSqlHeader());
            }

            if (Lang.isNotEmpty(getPrepareSqls())) {
                for (String prepareSql : getPrepareSqls()) {
                    logger.debug("prepareSql: {}", prepareSql);
                    conn.createStatement().execute(prepareSql);
                }
            }

            while (parser.nextToken() != null) {
                // 读取表信息
                System.out.print("token: " + parser.getCurrentToken().asString());
                System.out.print(" \t name : " + parser.getCurrentName());
                System.out.println(" \t " + parser.getCurrentToken());

                if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
                    System.out.print("token == JsonToken.START_ARRAY: " + parser.getCurrentToken().asString());
                    System.out.println(" \t name: " + parser.getCurrentName());
                    readTable(parser, conn);
                } else if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
                    // TODO
                } else if ("name".equals(parser.getCurrentName())) {
                    // TODO
                } else if ("tables".equals(parser.getCurrentName())) {
                    // TODO
                }
            }
            if (getTransactionPolicy() == TransactionPolicy.all) {
                conn.commit();
            }
            JdbcUtils.closeQuietly(conn);
        } catch (Exception e) {
            JdbcUtils.rollbackAndCloseQuietly(conn);
            throw new ImportException(e);
        }
    }

    /**
     * Read table.
     *
     * @param parser the parser
     * @param conn   the conn
     * @throws Exception the exception
     */
    private void readTable(JsonParser parser, Connection conn) throws Exception {
        //		System.out.println("readTable - name : " + parser.getCurrentName() + " " + parser.getCurrentToken());
        Statement statement = conn.createStatement();
        String tableName = null;
        String pkMapping = null;
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            System.out.print(" readTable token: " + parser.getCurrentToken().asString());
            System.out.println(" \t name: " + parser.getCurrentName());
            if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
                readRows(parser, statement, tableName, pkMapping);
            } else if ("name".equals(parser.getCurrentName())) {
                parser.nextToken();
                tableName = parser.getText();
            } else if ("pkMapping".equals(parser.getCurrentName())) {
                parser.nextToken();
                pkMapping = parser.getText();
            }
        }
        statement.executeBatch();
        statement.close();
        if (getTransactionPolicy() == TransactionPolicy.table) {
            conn.commit();
        }
    }

    /**
     * Read rows.
     *
     * @param parser    the parser
     * @param statement the statement
     * @param tableName the table name
     * @param pkMapping the pk mapping
     * @throws Exception the exception
     */
    private void readRows(JsonParser parser, Statement statement, String tableName, String pkMapping) throws Exception {
        String[] pkMappings = pkMapping.split(",");
        StringBuilder selectSql = new StringBuilder();
        selectSql.append("select count(*) as num from ").append(tableName).append(" where ");
        for (int i = 0; i < pkMappings.length; i++) {
            String pkName = pkMappings[i];
            if (i > 0) {
                selectSql.append(" and ");
            }
            selectSql.append(pkName).append(" = ? ");
        }
        logger.debug("selectSql : {}", selectSql.toString());

        PreparedStatement prep = statement.getConnection().prepareStatement(selectSql.toString());
        while (parser.nextToken() != JsonToken.END_ARRAY) {
            if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
                System.out.print(" readTable token: " + parser.getCurrentToken().asString());
                System.out.println(" \t name: " + parser.getCurrentName());
                readRow(parser, statement, tableName, pkMappings, prep);
            }
        }
    }

    /**
     * Read row.
     *
     * @param parser     the parser
     * @param statement  the statement
     * @param tableName  the table name
     * @param pkMappings the pk mappings
     * @param prep       the prep
     * @throws Exception the exception
     */
    private void readRow(JsonParser parser, Statement statement, String tableName, String[] pkMappings,
            PreparedStatement prep) throws Exception {
        //		System.out.println("readRow - name : " + parser.getCurrentName() + " " + parser.getCurrentToken());
        Map<String, Map<String, String>> columnsMap = new HashMap<>();
        while (parser.nextToken() != JsonToken.END_OBJECT) {
            if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                Map<String, String> column = new HashMap<>();
                columnsMap.put(parser.getCurrentName(), column);
                parser.nextToken();

                while (parser.nextToken() != JsonToken.END_OBJECT) {
                    if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
                        parser.nextToken();
                        column.put(parser.getCurrentName(), parser.getText());
                    }
                }
            }
            //			if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
            //				Map<String, String> column = new HashMap<String, String>();
            //				// 读取列
            //				while(parser.nextToken() != JsonToken.END_OBJECT) {
            //					if (parser.getCurrentToken() == JsonToken.FIELD_NAME) {
            //						parser.nextToken();
            //						column.put(parser.getCurrentName(), parser.getText());
            //					}
            //				}
            //			} else if (parser.getCurrentToken() == JsonToken.FIELD_NAME){
            //				columnsMap.put(parser.getCurrentName(), column);
            //			}
        }
        //		System.out.println("row:" + columnsMap);

        if (ExistPolicy.exception == getExistPolicy()) {
            // 行记录
            insert(columnsMap, tableName, statement);
        } else {
            int num = 0;
            String msg = "table " + tableName + " {}, {}";
            // 行记录
            // 处理主键
            for (int i = 0; i < pkMappings.length; i++) {
                String pkName = pkMappings[i];
                String value = columnsMap.get(pkName).get("value");
                JdbcUtils.setParameter(prep, i + 1, value);
            }
            ResultSet res = prep.executeQuery();
            if (res.next()) {
                if (res.getLong(1) > 0) {
                    logger.debug(msg, "exits", num++);
                    if (ExistPolicy.update == getExistPolicy()) {
                        update(columnsMap, tableName, statement, pkMappings);
                    }
                } else {
                    logger.debug(msg, "not exits", num++);
                    insert(columnsMap, tableName, statement);
                }
            }
        }
    }

    /**
     * Update.
     *
     * @param columnsMap the columns map
     * @param tableName  the table name
     * @param statement  the statement
     * @param pkMappings the pk mappings
     * @throws SQLException the SQL exception
     */
    private void update(Map<String, Map<String, String>> columnsMap, String tableName, Statement statement,
            String[] pkMappings) throws SQLException {
        StringBuilder updateSql = new StringBuilder();
        StringBuilder condition = new StringBuilder();
        StringBuilder values = new StringBuilder();
        if (columnsMap.size() == pkMappings.length) {
            logger.trace("列数量与主键列数量一致，不进行更新");
            return;
        }

        // 准备 recordModel
        RecordModel recordModel = new RecordModel(tableName);
        for (Entry<String, Map<String, String>> entry : columnsMap.entrySet()) {
            ValueModel valueModel = new ValueModel(entry.getKey(), getType(entry.getValue().get("type")),
                    entry.getValue().get("value"));
            recordModel.add(valueModel);
        }

        // 过滤数据
        if (filtdate(recordModel, statement.getConnection())) {
            return;
        }

        // 数据处理
        recordModel = transform(recordModel);

        // 列值
        for (Entry<String, Map<String, String>> entry : columnsMap.entrySet()) {
            String columnName = entry.getKey();
            ValueModel vm = recordModel.getValueMode(columnName);
            int type = vm.getType();
            String value = vm.getValue();

            boolean isPkColumn = false;
            for (int i = 0; i < pkMappings.length; i++) {
                String pkName = pkMappings[i];
                if (columnName.equals(pkName)) {
                    if (condition.length() > 0) {
                        condition.append(" and ");
                    }
                    condition.append(getDialect().wrapName(columnName.toUpperCase())).append(" = ")
                            .append(getValueToSql(value, type, entry.getValue().get("null")));
                    isPkColumn = true;
                }
            }

            // 列名大写
            if (!isPkColumn) {
                values.append(" ").append(getDialect().wrapName(columnName.toUpperCase())).append(" = ")
                        .append(getValueToSql(value, type, entry.getValue().get("null"))).append(",");
            }
        }
        if (values.length() > 0 && values.lastIndexOf(",") + 1 == values.length()) {
            values.deleteCharAt(values.length() - 1);
        }
        if (condition.length() > 0 && condition.lastIndexOf(",") + 1 == condition.length()) {
            condition.deleteCharAt(condition.length() - 1);
        }
        // 表名大写
        updateSql.append("UPDATE ").append(getDialect().wrapName(tableName.toUpperCase())).append(" SET ")
                .append(values).append(" WHERE ").append(condition);
        logger.trace("update sql : {}", updateSql.toString());
        statement.addBatch(updateSql.toString());
    }

    /**
     * Insert.
     *
     * @param columnsMap the columns map
     * @param tableName  the table name
     * @param statement  the statement
     * @throws SQLException the SQL exception
     */
    private void insert(Map<String, Map<String, String>> columnsMap, String tableName, Statement statement)
            throws SQLException {
        // 准备 recordModel
        RecordModel recordModel = new RecordModel(tableName);
        for (Entry<String, Map<String, String>> entry : columnsMap.entrySet()) {
            ValueModel valueModel = new ValueModel(entry.getKey(), getType(entry.getValue().get("type")),
                    entry.getValue().get("value"));
            recordModel.add(valueModel);
        }

        // 过滤数据
        if (filtdate(recordModel, statement.getConnection())) {
            return;
        }

        // 数据处理
        recordModel = transform(recordModel);

        StringBuilder insertSql = new StringBuilder();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        values.append(" VALUES ( ");
        columns.append(" ( ");
        // 列值
        for (Entry<String, Map<String, String>> entry : columnsMap.entrySet()) {
            String columnName = entry.getKey();
            ValueModel vm = recordModel.getValueMode(columnName);
            int type = vm.getType();
            String value = vm.getValue();

            // 列名大写
            columns.append(getDialect().wrapName(columnName.toUpperCase())).append(",");
            values.append(getValueToSql(value, type, entry.getValue().get("null"))).append(",");
        }
        if (columns.lastIndexOf(",") + 1 == columns.length()) {
            columns.deleteCharAt(columns.length() - 1);
        }
        columns.append(" )");
        if (values.lastIndexOf(",") + 1 == values.length()) {
            values.deleteCharAt(values.length() - 1);
        }
        values.append(" )");
        columns.append(values.toString());
        // 表名大写
        insertSql.append("INSERT INTO ").append(getDialect().wrapName(tableName.toUpperCase())).append(columns)
                .append(";");
        logger.trace("insert sql : {}", insertSql.toString());
        statement.addBatch(insertSql.toString());
    }
}