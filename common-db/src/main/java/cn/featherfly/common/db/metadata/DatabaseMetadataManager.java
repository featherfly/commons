package cn.featherfly.common.db.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.JdbcException;
import cn.featherfly.common.db.JdbcUtils;
import cn.featherfly.common.lang.StringUtils;

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
     * @param dataBase   具体库
     * @return 已经初始化的数据库元数据对象
     */
    public DatabaseMetadata create(Connection connection, String dataBase) {
        return create(connection, dataBase, false);
    }

    /**
     * <p>
     * 创建数据库元数据，会初始化表和列元数据.
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
     * 创建数据库元数据，会初始化表和列元数据， 不管元数据对象是否存在，都创建，原来的会被替换.
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
        return create(connection, dataBase, true);
    }

    // ********************************************************************
    //	private method
    // ********************************************************************

    private String getDatabase(Connection connection) {
        String catalog = JdbcUtils.getCatalog(connection);
        if (StringUtils.isEmpty(catalog)) {
            throw new DatabaseMetadataException("#driver.not.support.catalog");
            //			throw new DatabaseMetadataException("数据库驱动不支持从连接对象获取当前连接的具体库，请使用带具体库名称的方法显示创建！");
        }
        return catalog;
    }

    private synchronized DatabaseMetadata create(Connection connection, String dataBase, boolean reCreate) {
        try {
            DatabaseMetadata databaseMetadata = getDatabaseMetadata(dataBase);
            if (databaseMetadata != null && !reCreate) {
                return databaseMetadata;
            }
            databaseMetadata = new DatabaseMetadata();
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTableTypes();
            // 得到表信息
            rs = metaData.getTables(dataBase, null, null, new String[] { TableType.TABLE.toString() });
            //            rs = metaData.getTables(dataBase, dataBase, null, new String[] { TableType.TABLE.toString() });
            //			rs = metaData.getTables("","", null, new String[]{TableType.TABLE.toString()});
            final String remarks = "REMARKS";
            final String columnName = "COLUMN_NAME";
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
                if (tableCat != null) {
                    tableMetadata.setCatalog(tableCat);
                } else {
                    tableMetadata.setCatalog(dataBase);
                }
                tableMetadata.setRemark(rs.getString(remarks));
                databaseMetadata.addTable(tableMetadata);
                // 得到主键信息
                ResultSet rp = metaData.getPrimaryKeys(dataBase, null, tableMetadata.getName());
                Set<String> pkColumnNames = new HashSet<>();
                while (rp.next()) {
                    // 主键列名称
                    pkColumnNames.add(rp.getString(columnName));
                }
                rp.close();
                // 得到表信息
                ResultSet rc = metaData.getColumns(dataBase, null, tableMetadata.getName(), null);
                while (rc.next()) {
                    ColumnMetadata columnMetadata = new ColumnMetadata(tableMetadata);
                    // 列名
                    columnMetadata.setName(rc.getString(columnName));
                    // 列类型
                    columnMetadata.setType(rc.getInt("DATA_TYPE"));
                    // 类型名称
                    columnMetadata.setTypeName(rc.getString("TYPE_NAME"));
                    // 注释
                    columnMetadata.setRemark(rc.getString(remarks));
                    // 长度
                    columnMetadata.setSize(rc.getInt("COLUMN_SIZE"));
                    // 默认值
                    columnMetadata.setDefaultValue(rc.getString("COLUMN_DEF"));
                    // 是否空
                    int nullable = rc.getInt("NULLABLE");
                    if (Chars.ZERO == nullable) {
                        columnMetadata.setNullable(false);
                    } else {
                        columnMetadata.setNullable(true);
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
            rs.close();
            if (!hasDatabase) {
                throw new DatabaseMetadataException("#driver.not.find.database", new Object[] { dataBase });
                //				throw new DatabaseMetadataException(String.format(
                //						"没有在数据源中找到具体库%s（mysql为库，oracle为表空间）", dataBase));
            }
            databasemetadataPool.put(dataBase, databaseMetadata);
            databaseMetadata.setName(dataBase);
            return databaseMetadata;
        } catch (SQLException e) {
            throw new JdbcException(e);
        } finally {
            JdbcUtils.closeQuietly(connection);
        }
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
