
package cn.featherfly.common.db.data;

import java.io.Writer;
import java.util.Collection;

import cn.featherfly.common.db.data.query.TableQuery;
import cn.featherfly.common.repository.Query;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;

/**
 * <p>
 * 数据导出
 * </p>
 *
 * @author zhongj
 */
public interface DataExportor {
    /**
     * <p>
     * 导出整库
     * </p>
     *
     * @param writer writer
     */
    void exportDatabase(Writer writer);

    /**
     * <p>
     * 导出某张表
     * </p>
     *
     * @param tableName 表名称
     * @param writer    writer
     */
    void exportTable(String tableName, Writer writer);

    /**
     * <p>
     * 导出某几张表
     * </p>
     *
     * @param writer     writer
     * @param tableNames 表名称集合
     */
    void exportTable(Writer writer, Collection<String> tableNames);

    /**
     * <p>
     * 导出某几张表
     * </p>
     *
     * @param writer     writer
     * @param tableNames 表名称集合
     */
    void exportTable(Writer writer, String... tableNames);

    /**
     * <p>
     * 导出某张表
     * </p>
     *
     * @param tableQuery 表查询
     * @param writer     writer
     */
    void exportTable(TableQuery tableQuery, Writer writer);

    /**
     * <p>
     * 导出某几张表
     * </p>
     *
     * @param writer writer
     * @param querys 表查询集合
     */
    void exportTables(Writer writer, Collection<TableQuery> querys);

    /**
     * <p>
     * 导出某几张表
     * </p>
     *
     * @param writer writer
     * @param querys 表查询数组
     */
    void exportTables(Writer writer, TableQuery... querys);

    /**
     * <p>
     * 导出结果集
     * </p>
     *
     * @param querySql 查询sql
     * @param writer   writer
     */
    void exportData(String querySql, Writer writer);

    /**
     * <p>
     * 导出结果集
     * </p>
     *
     * @param querySqls 查询sql集合
     * @param writer    writer
     */
    void exportData(Collection<String> querySqls, Writer writer);

    /**
     * <p>
     * 导出结果集
     * </p>
     *
     * @param writer    writer
     * @param querySqls 查询sql可变参数
     */
    void exportData(Writer writer, String... querySqls);

    /**
     * <p>
     * 导出结果集
     * </p>
     * 
     * @param conditionBuilder 查询条件
     * @param tableName        表名称
     * @param writer           writer
     */
    void exportData(String tableName, ConditionBuilder conditionBuilder, Writer writer);

    /**
     * <p>
     * 导出结果集
     * </p>
     *
     * @param writer writer
     * @param querys 查询对象可变参数
     */
    void exportData(Writer writer, Query... querys);

    /**
     * <p>
     * 导出结果集
     * </p>
     *
     * @param writer writer
     * @param querys 查询对象集合
     */
    void exportData(Writer writer, Collection<Query> querys);

    /**
     * <p>
     * 导出结果集
     * </p>
     *
     * @param query  查询
     * @param writer writer
     */
    void exportData(Query query, Writer writer);
}
