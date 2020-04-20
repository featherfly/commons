
package cn.featherfly.common.db.data;

import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.data.query.SimpleQuery;
import cn.featherfly.common.db.data.query.TableQuery;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.repository.Query;
import cn.featherfly.common.repository.builder.dml.ConditionBuilder;

/**
 * <p>
 * 抽象导出器
 * </p>
 * .
 *
 * @author zhongj
 */
public abstract class AbstractDataExportor extends AbstractDataImpExp implements DataExportor {

    /**
     * Instantiates a new abstract data exportor.
     *
     * @param dialect the dialect
     */
    protected AbstractDataExportor(Dialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportDatabase(Writer writer) {
        Collection<TableQuery> tableQuerys = new ArrayList<>();
        DatabaseMetadata databaseMetadata = getDatabaseMetadata();
        Collection<Table> tableMetadatas = databaseMetadata.getTables();
        for (Table tableMetadata : tableMetadatas) {
            tableQuerys.add(new TableQuery(getDialect(), tableMetadata.getName()));
        }
        exportTables(writer, tableQuerys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportTable(String tableName, Writer writer) {
        exportTable(new TableQuery(getDialect(), tableName), writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportTable(Writer writer, Collection<String> tableNames) {
        Collection<TableQuery> tableQuerys = new ArrayList<>();
        if (LangUtils.isEmpty(tableNames)) {
            tableNames = new ArrayList<>();
        }
        for (String tableName : tableNames) {
            tableQuerys.add(new TableQuery(getDialect(), tableName));
        }
        exportTables(writer, tableQuerys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportTable(Writer writer, String... tableNames) {
        exportTable(writer, Arrays.asList(tableNames));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportTable(TableQuery tableQuery, Writer writer) {
        exportTables(writer, tableQuery);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportTables(Writer writer, TableQuery... querys) {
        exportData(writer, querys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportTables(Writer writer, Collection<TableQuery> querys) {
        exportTables(writer, querys.toArray(new TableQuery[] {}));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportData(String querySql, Writer writer) {
        exportData(writer, new SimpleQuery(querySql, new Object[] {}));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportData(Collection<String> querySqls, Writer writer) {
        exportData(writer, querySqls.toArray(new String[] {}));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportData(Writer writer, String... querySqls) {
        Collection<Query> querys = new ArrayList<>();
        if (LangUtils.isNotEmpty(querySqls)) {
            for (String querySql : querySqls) {
                querys.add(new SimpleQuery(querySql, new Object[] {}));
            }
        }
        exportData(writer, querys);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportData(String tableName, ConditionBuilder conditionBuilder, Writer os) {
        String querySql = getTableQuerySql(tableName);
        if (conditionBuilder != null) {
            querySql += " " + conditionBuilder.build();
        }
        exportData(querySql, os);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportData(Writer writer, Query... querys) {
        exportData(writer, Arrays.asList(querys));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exportData(Query query, Writer writer) {
        exportData(writer, query);
    }
}
