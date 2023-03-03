package cn.featherfly.common.db.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.wrapper.ConnectionWrapper;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.repository.Index;

/**
 * <p>
 * 数据库元数据管理器
 * </p>
 *
 * @author zhongj
 */
public class DatabaseMetadataManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseMetadataManager.class);

    private final Map<String, DatabaseMetadata> databasemetadataPool = new HashMap<>(0);

    private static final DatabaseMetadataManager DEFAULT_MANAGER = new DatabaseMetadataManager();

    private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("[a-zA-Z-_0-9]+");

    private static final String COLUMN_NAME = "COLUMN_NAME";
    private static final String REMARKS = "REMARKS";

    /**
     */
    public DatabaseMetadataManager() {
    }

    /**
     * <p>
     * 返回的元数据，首先查找已经受管理的元数据对象， 如果没有找到，则基于传入数据库连接创建并纳入管理.
     * </p>
     *
     * @param dataBase 具体库名称
     * @return 元数据对象
     */
    public DatabaseMetadata getDatabaseMetadata(String dataBase) {
        return databasemetadataPool.get(dataBase);
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据.
     * </p>
     *
     * @param dataSource 数据源
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata create(DataSource dataSource) {
        return create(JdbcUtils.getConnection(dataSource));
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 如果元数据对象已经存在，则直接返回.
     * </p>
     *
     * @param connection 数据库连接
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata create(Connection connection) {
        return create(connection, getDatabase(connection));
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 如果元数据对象已经存在，则直接返回.
     * </p>
     *
     * @param connection 数据库连接
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata create(ConnectionWrapper connection) {
        return create(connection.getConnection());
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 如果元数据对象已经存在，则直接返回.
     * </p>
     *
     * @param connection 数据库连接
     * @param dataBase   具体库
     * @return 已经初始化的数据库元数据对象
     */
    public DatabaseMetadata create(Connection connection, String dataBase) {
        return create(connection, dataBase, null);
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 如果元数据对象已经存在，则直接返回.
     * </p>
     *
     * @param connection 数据库连接
     * @param dataBase   具体库
     * @param schema     the schema
     * @return 已经初始化的数据库元数据对象
     */
    public DatabaseMetadata create(Connection connection, String dataBase, String schema) {
        return create(connection, dataBase, schema, false);
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 如果元数据对象已经存在，则直接返回.
     * </p>
     *
     * @param dataSource 数据源
     * @param dataBase   具体库
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata create(DataSource dataSource, String dataBase) {
        return create(JdbcUtils.getConnection(dataSource), dataBase);
    }

    /**
     * <p>
     * 重新创建数据库元数据，会初始化表和列元数据， 不管元数据对象是否存在，都创建，原来的会被替换.
     * </p>
     *
     * @param connection 数据库连接
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata reCreate(Connection connection) {
        return reCreate(connection, getDatabase(connection));
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 不管元数据对象是否存在，都创建，原来的会被替换.
     * </p>
     *
     * @param dataSource 数据源
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata reCreate(DataSource dataSource) {
        return reCreate(JdbcUtils.getConnection(dataSource));
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 不管元数据对象是否存在，都创建，原来的会被替换.
     * </p>
     *
     * @param dataSource 数据源
     * @param dataBase   具体库
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata reCreate(DataSource dataSource, String dataBase) {
        return reCreate(JdbcUtils.getConnection(dataSource), dataBase);
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 不管元数据对象是否存在，都创建，原来的会被替换.
     * </p>
     *
     * @param connection 数据库连接
     * @param dataBase   具体库
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata reCreate(Connection connection, String dataBase) {
        return reCreate(connection, dataBase, null);
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据， 不管元数据对象是否存在，都创建，原来的会被替换.
     * </p>
     *
     * @param connection 数据库连接
     * @param dataBase   具体库
     * @param schema     the schema
     * @return 已经初始化的数据库元数据对象
     */
    public synchronized DatabaseMetadata reCreate(Connection connection, String dataBase, String schema) {
        return create(connection, dataBase, schema, true);
    }

    // ********************************************************************
    //	private method
    // ********************************************************************

    private String getDatabase(Connection connection) {
        String catalog = JdbcUtils.getCatalog(connection);
        if (Strings.isEmpty(catalog)) {
            throw new DatabaseMetadataException("#driver.not.support.catalog");
            //			throw new DatabaseMetadataException("数据库驱动不支持从连接对象获取当前连接的具体库，请使用带具体库名称的方法显示创建！");
        }
        return catalog;
    }

    private synchronized DatabaseMetadata create(Connection connection, String dataBase, String schema,
            boolean reCreate) {
        try {
            DatabaseMetadata databaseMetadata = getDatabaseMetadata(dataBase);
            if (databaseMetadata != null && !reCreate) {
                return databaseMetadata;
            }
            databaseMetadata = new DatabaseMetadata();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = null;
            //            rs = metaData.getTableTypes();
            // 得到表信息
            rs = metaData.getTables(dataBase, schema, null, new String[] { TableType.TABLE.toString() });
            //            rs = metaData.getTables(dataBase, dataBase, null, new String[] { TableType.TABLE.toString() });
            //			rs = metaData.getTables("","", null, new String[]{TableType.TABLE.toString()});

            boolean hasDatabase = false;
            while (rs.next()) {
                hasDatabase = true;
                TableMetadata tableMetadata = new TableMetadata(databaseMetadata);
                // 表名
                String tableName = rs.getString("TABLE_NAME");
                tableMetadata.setName(tableName);
                // 不符合标准
                if (!TABLE_NAME_PATTERN.matcher(tableName).matches()) {
                    LOGGER.debug("{} 不是用户表, 忽略！", tableName);
                    continue;
                }
                // 表类型
                tableMetadata.setType(rs.getString("TABLE_TYPE"));
                // 库（表空间）
                String tableCat = rs.getString("TABLE_CAT");
                if (Lang.isNotEmpty(tableCat)) {
                    tableMetadata.setCatalog(tableCat);
                } else {
                    tableMetadata.setCatalog(dataBase);
                }
                // schema
                String tableSchema = rs.getString("TABLE_SCHEM");
                if (Lang.isNotEmpty(tableSchema)) {
                    tableMetadata.setSchema(tableSchema);
                } else {
                    // TODO 这里是否需要
                    tableMetadata.setSchema(tableMetadata.getCatalog());
                }
                tableMetadata.setRemark(rs.getString(REMARKS));
                tableMetadata.addIndex(
                        createIndexs(metaData, tableName, tableMetadata.getCatalog(), tableMetadata.getSchema()));
                addColumns(metaData, tableMetadata);
                databaseMetadata.addTable(tableMetadata);

            }
            rs.close();
            if (!hasDatabase && !dataBase.equals(connection.getCatalog())) {
                throw new DatabaseMetadataException("#driver.not.find.database", new Object[] { dataBase });
            }
            databasemetadataPool.put(dataBase, databaseMetadata);
            databaseMetadata.setName(dataBase);
            databaseMetadata.setProductName(metaData.getDatabaseProductName());
            databaseMetadata.setProductVersion(metaData.getDatabaseProductVersion());
            databaseMetadata.setMajorVersion(metaData.getDatabaseMajorVersion());
            databaseMetadata.setMinorVersion(metaData.getDatabaseMinorVersion());
            databaseMetadata.setSupportsBatchUpdate(metaData.supportsBatchUpdates());
            return databaseMetadata;
        } catch (SQLException e) {
            throw new JdbcException(e);
        } finally {
            JdbcUtils.closeQuietly(connection);
        }
    }

    private void addColumns(DatabaseMetaData metaData, TableMetadata tableMetadata) throws SQLException {
        // 得到主键信息
        ResultSet rp = metaData.getPrimaryKeys(tableMetadata.getCatalog(), tableMetadata.getSchema(),
                tableMetadata.getName());
        Set<String> pkColumnNames = new HashSet<>();
        while (rp.next()) {
            // 主键列名称
            pkColumnNames.add(rp.getString(COLUMN_NAME));
        }
        rp.close();

        // 得到表信息
        ResultSet rc = metaData.getColumns(tableMetadata.getCatalog(), tableMetadata.getSchema(),
                tableMetadata.getName(), null);
        while (rc.next()) {
            ColumnMetadata columnMetadata = new ColumnMetadata(tableMetadata);
            // 列名
            columnMetadata.setName(rc.getString(COLUMN_NAME));
            // 列类型
            columnMetadata.setType(rc.getInt("DATA_TYPE"));
            // 类型名称
            columnMetadata.setTypeName(rc.getString("TYPE_NAME"));
            // 注释
            columnMetadata.setRemark(rc.getString(REMARKS));
            // 长度
            columnMetadata.setSize(rc.getInt("COLUMN_SIZE"));
            // 小数位数
            columnMetadata.setDecimalDigits(rc.getInt("DECIMAL_DIGITS"));
            // 默认值
            columnMetadata.setDefaultValue(rc.getString("COLUMN_DEF"));
            // 是否空
            int nullable = rc.getInt("NULLABLE");
            if (DatabaseMetaData.columnNullable == nullable) {
                columnMetadata.setNullable(true);
            } else {
                columnMetadata.setNullable(false);
            }
            // 是否空
            String isAutoincrement = rc.getString("IS_AUTOINCREMENT");
            if ("YES".equals(isAutoincrement)) {
                columnMetadata.setAutoincrement(true);
            } else {
                columnMetadata.setAutoincrement(false);
            }
            // 列的位置
            columnMetadata.setColumnIndex(rc.getInt("ORDINAL_POSITION"));
            // 设置是否主键
            if (pkColumnNames.contains(columnMetadata.getName())) {
                columnMetadata.setPrimaryKey(true);
            }
            tableMetadata.addColumn(columnMetadata);
        }
        rc.close();
    }

    private List<Index> createIndexs(DatabaseMetaData metaData, String tableName, String catalog, String schema)
            throws SQLException {
        List<Index> indexList = new ArrayList<>();
        ResultSet ri = metaData.getIndexInfo(catalog, schema, tableName, false, false);
        Map<String, List<String>> indexColumnMap = new LinkedHashMap<>();
        Set<String> uniques = new HashSet<>();
        while (ri.next()) {
            String indexName = ri.getString("INDEX_NAME");
            if (Dialect.PRIMARY_KEY_INDEX_NAME.equals(indexName)) {
                continue;
            }
            List<String> columnNames = indexColumnMap.get(indexName);
            String columnName = ri.getString("COLUMN_NAME");
            if (columnNames == null) {
                columnNames = new ArrayList<>();
            }
            columnNames.add(columnName);
            indexColumnMap.put(indexName, columnNames);
        }
        ri = metaData.getIndexInfo(catalog, schema, tableName, true, false);
        while (ri.next()) {
            String indexName = ri.getString("INDEX_NAME");
            uniques.add(indexName);
        }
        indexColumnMap.forEach((name, columns) -> {
            indexList.add(new Index(name, Lang.toArray(columns, String.class), uniques.contains(name)));
        });
        return indexList;
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    /**
     * 返回dEFAULT_MANAGER
     *
     * @return dEFAULT_MANAGER
     */
    public static DatabaseMetadataManager getDefaultManager() {
        return DEFAULT_MANAGER;
    }
}
