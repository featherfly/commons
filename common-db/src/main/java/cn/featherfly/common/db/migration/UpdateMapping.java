
package cn.featherfly.common.db.migration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.repository.Index;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * <p>
 * UpdateMapping
 * </p>
 *
 * @author zhongj
 */
public class UpdateMapping {
    // FIXME 差表基本信息修改

    Map<ClassMapping<?>, Table> newClassMappings = new HashMap<>();

    Set<Table> noMappingTables = new HashSet<>();

    ModifyTables modifyTables = new ModifyTables();

    UpdateMapping() {
    }
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
    public ModifyTable(Table table, ClassMapping<?> classMapping) {
        this.table = table;
        this.classMapping = classMapping;
    }

    Table table;

    ClassMapping<?> classMapping;

    Map<PropertyMapping, Column> newColumns = new LinkedHashMap<>();

    Map<PropertyMapping, Column> modifyColumns = new LinkedHashMap<>();

    Set<Column> noMappingColumns = new LinkedHashSet<>();

    Set<Index> dropIndexs = new LinkedHashSet<>();

    Set<Index> addIndexs = new LinkedHashSet<>();

    Set<Index> noMappingIndexs = new LinkedHashSet<>();

}
