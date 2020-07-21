
package cn.featherfly.common.db.metadata;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.db.Table;

/**
 * <p>
 * 库元数据
 * </p>
 *
 * @author zhongj
 */
public class DatabaseMetadata {
    // FIXME 多个schema内有相同名称表的会出错
    // TODO 后续加入多个schema管理，当前的方法都不该，只是代理到default schema去获取
    /**
     */
    DatabaseMetadata() {
    }

    /**
     * <p>
     * 返回指定名称的表元数据对象. 没有找到返回null.
     * </p>
     *
     * @param tableName 表名称
     * @return 表元数据对象
     */
    public Table getTable(String tableName) {
        return tableMap.get(tableName.toUpperCase());
    }

    /**
     * <p>
     * 返回所有表元数据对象的集合.
     * </p>
     *
     * @return 所有表元数据对象的集合
     */
    public Collection<Table> getTables() {
        return tableMap.values();
    }

    /**
     * <p>
     * 返回所有表元数据对象的MAP.
     * </p>
     *
     * @return 所有表元数据对象的MAP
     */
    public Map<String, Table> getTableMap() {
        return tableMap;
    }

    /**
     * <p>
     * 添加表元数据.
     * </p>
     *
     * @param table 表元数据对象
     */
    void addTable(Table table) {
        tableMap.put(table.getName().toUpperCase(), table);
    }

    /**
     * <p>
     * 添加表元数据.
     * </p>
     *
     * @param tables 表元数据对象数组
     */
    void addTable(Table... tables) {
        for (Table Table : tables) {
            addTable(Table);
        }
    }

    /**
     * <p>
     * 添加表元数据.
     * </p>
     *
     * @param tables 表元数据对象集合
     */
    void addTable(Collection<Table> tables) {
        for (Table Table : tables) {
            addTable(Table);
        }
    }

    // ********************************************************************
    //	property
    // ********************************************************************

    private Map<String, Table> tableMap = new HashMap<>(0);

    private String name;

    private String productName;

    private String productVersion;

    private Integer majorVersion;

    private Integer minorVersion;

    /**
     * 返回name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * 返回productName
     *
     * @return productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 返回productVersion
     *
     * @return productVersion
     */
    public String getProductVersion() {
        return productVersion;
    }

    /**
     * 设置productName
     *
     * @param productName productName
     */
    void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 设置productVersion
     *
     * @param productVersion productVersion
     */
    void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    /**
     * 返回majorVersion
     *
     * @return majorVersion
     */
    public Integer getMajorVersion() {
        return majorVersion;
    }

    /**
     * 设置majorVersion
     *
     * @param majorVersion majorVersion
     */
    void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    /**
     * 返回minorVersion
     *
     * @return minorVersion
     */
    public Integer getMinorVersion() {
        return minorVersion;
    }

    /**
     * 设置minorVersion
     *
     * @param minorVersion minorVersion
     */
    void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }

}
