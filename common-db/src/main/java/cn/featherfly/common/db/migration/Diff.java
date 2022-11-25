
package cn.featherfly.common.db.migration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.mapping.JdbcClassMapping;
import cn.featherfly.common.db.mapping.JdbcPropertyMapping;
import cn.featherfly.common.repository.Index;

/**
 * <p>
 * UpdateMapping
 * </p>
 * .
 *
 * @author zhongj
 */
public class Diff {
    // FIXME 差表基本信息修改

    /** The new tables. */
    Set<TableMapping> newTables = new HashSet<>();

    /** The no exits tables. */
    Set<Table> notExistTables = new HashSet<>();

    /** The modify tables. */
    ModifyTables modifyTables = new ModifyTables();

    /**
     * Instantiates a new diff.
     */
    Diff() {
    }
}

class TableMapping {
    /**
     * Instantiates a new modify table.
     *
     * @param table        the table
     * @param classMapping the class mapping
     */
    TableMapping(Table table, JdbcClassMapping<?> classMapping) {
        this.table = table;
        this.classMapping = classMapping;
    }

    Table table;

    JdbcClassMapping<?> classMapping;
}

class ModifyTables {

    Map<Table, ModifyTable> modifyTableMap = new HashMap<>();

    public ModifyTable getModifyTable(Table table) {
        return modifyTableMap.get(table);
    }

    public ModifyTable put(ModifyTable modifyTable) {
        return modifyTableMap.put(modifyTable.table, modifyTable);
    }

    /**
     * Checks for table.
     *
     * @param table the table
     * @return true, if successful
     */
    public boolean contains(Table table) {
        return modifyTableMap.containsKey(table);
    }
}

class ModifyTable {

    /**
     * Instantiates a new modify table.
     *
     * @param table        the table
     * @param classMapping the class mapping
     */
    public ModifyTable(Table table, JdbcClassMapping<?> classMapping) {
        this.table = table;
        this.classMapping = classMapping;
    }

    Table table;

    JdbcClassMapping<?> classMapping;

    Map<JdbcPropertyMapping, Column> newColumns = new LinkedHashMap<>();

    Map<JdbcPropertyMapping, Column> modifyColumns = new LinkedHashMap<>();

    Set<Column> noMappingColumns = new LinkedHashSet<>();

    Set<Index> dropIndexs = new LinkedHashSet<>();

    Set<Index> addIndexs = new LinkedHashSet<>();

    Set<Index> notExistIndexs = new LinkedHashSet<>();

}
