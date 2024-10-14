
package cn.featherfly.common.db.mapping;

import java.sql.SQLType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.ColumnModel;
import cn.featherfly.common.db.builder.TableModel;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.CollectionUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;
import cn.featherfly.common.repository.Index;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.MappingFactory;
import cn.featherfly.common.tuple.Tuple2;
import cn.featherfly.common.tuple.Tuple3;
import cn.featherfly.common.tuple.Tuples;

/**
 * JdbcClassMappingUtils.
 *
 * @author zhongj
 */
public final class ClassMappingUtils {

    private ClassMappingUtils() {
    }

    /**
     * Gets the table model.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @return the table model
     */
    public static Table createTable(JdbcClassMapping<?> classMapping, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager) {
        // table
        TableModel table = new TableModel();
        table.setName(classMapping.getRepositoryName());
        table.setRemark(classMapping.getRemark());
        table.setSchema(classMapping.getSchema());
        int i = 1;
        // columns
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(propertyMapping.getPropertyMappings())) {
                table.addColumn(createColumn(propertyMapping, sqlTypeMappingManager, i, dialect));
                i++;
            } else {
                for (JdbcPropertyMapping pm : propertyMapping.getPropertyMappings()) {
                    table.addColumn(createColumn(pm, sqlTypeMappingManager, i, dialect));
                    i++;
                }
            }
        }
        for (Index index : classMapping.getIndexs()) {
            table.addIndex(index);
        }

        //        if (classMapping.getPrivaryKeyJdbcPropertyMappings().size() > 0) {
        //            List<String> pkColumns = new ArrayList<>();
        //            for (JdbcPropertyMapping pm : classMapping.getPrivaryKeyJdbcPropertyMappings()) {
        //                pkColumns.add(pm.getRepositoryFieldName());
        //            }
        //            Index pkIndex = new Index(dialect.getPrimaryKeyIndexName(),
        //                    CollectionUtils.toArray(pkColumns, String.class), true);
        //            table.addIndex(pkIndex);
        //        }

        return table;
    }

    /**
     * Gets the creates the table sql.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @return the creates the table sql
     */
    public static String getCreateTableSql(JdbcClassMapping<?> classMapping, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager) {
        return dialect.ddl().createTable(createTable(classMapping, dialect, sqlTypeMappingManager));
    }

    private static ColumnModel createColumn(JdbcPropertyMapping propertyMapping,
        SqlTypeMappingManager sqlTypeMappingManager, int index, Dialect dialect) {
        ColumnModel column = new ColumnModel();
        JdbcPropertyMapping pm = propertyMapping;
        //        if (propertyMapping.getParent() != null
        //                && Lang.isNotEmpty(propertyMapping.getParent().getRepositoryFieldName())) {
        //            pm = propertyMapping.getParent();
        //        }
        column.setName(pm.getRepositoryFieldName());
        column.setPrimaryKey(pm.isPrimaryKey());
        column.setAutoincrement(pm.isAutoincrement());
        column.setDecimalDigits(pm.getDecimalDigits());
        column.setDefaultValue(pm.getDefaultValue());
        column.setNullable(pm.isNullable());
        column.setRemark(pm.getRemark());
        column.setSize(pm.getSize());
        column.setColumnIndex(index);
        // pm.getPropertyType()是对象，所以使用propertyMapping.getPropertyType()
        SQLType sqlType = sqlTypeMappingManager.getSqlType(propertyMapping.getPropertyType());
        if (sqlType == null) {
            throw new JdbcMappingException("no SqlType found for type " + propertyMapping.getPropertyType());
        }
        column.setSqlType(sqlType);
        column.setTypeName(dialect.getColumnTypeName(column.getSqlType()));
        return column;
    }

    /**
     * Gets the insert batch sql and param positions.
     *
     * @param insertAmount the insert amount
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the insert batch sql and param positions
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getInsertBatchSqlAndMappings(int insertAmount,
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        if (insertAmount == 1) {
            Tuple2<JdbcPropertyMapping[], String[]> tuple = getInsertMappingsAndColumns(classMapping, dialect);
            String sql = dialect.dml().insert(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
                classMapping.isPrimaryKeyAutoincrement());
            return Tuples.of(sql, tuple.get0());
        } else if (insertAmount > 1) {
            Tuple2<JdbcPropertyMapping[], String[]> tuple = getInsertMappingsAndColumns(classMapping, dialect);
            String sql = dialect.dml().insertBatch(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
                insertAmount, classMapping.isPrimaryKeyAutoincrement());
            return Tuples.of(sql, tuple.get0());
        } else {
            throw new JdbcMappingException("insertAmount can not < 1, current value is " + insertAmount);
        }
    }

    /**
     * Gets the insert batch sql and param positions.
     *
     * @param insertAmount the insert amount
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the insert batch sql and param positions
     * @deprecated {@link #getInsertBatchSqlAndMappings(int, JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static Tuple2<String, Map<Integer, JdbcPropertyMapping>> getInsertBatchSqlAndParamPositions(int insertAmount,
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        if (insertAmount == 1) {
            Tuple2<Map<Integer, JdbcPropertyMapping>,
                String[]> tuple = getInsertParamPositionsAndColumns(classMapping, dialect);
            String sql = dialect.dml().insert(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
                classMapping.isPrimaryKeyAutoincrement());
            return Tuples.of(sql, tuple.get0());
        } else if (insertAmount > 1) {
            Tuple2<Map<Integer, JdbcPropertyMapping>,
                String[]> tuple = getInsertParamPositionsAndColumns(classMapping, dialect);
            String sql = dialect.dml().insertBatch(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
                insertAmount, classMapping.isPrimaryKeyAutoincrement());
            return Tuples.of(sql, tuple.get0());
        } else {
            throw new JdbcMappingException("insertAmount can not < 1, current value is " + insertAmount);
        }
    }

    @Deprecated
    private static Tuple2<Map<Integer, JdbcPropertyMapping>, String[]> getInsertParamPositionsAndColumns(
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        Map<Integer, JdbcPropertyMapping> propertyPositions = new LinkedHashMap<>();
        int paramNum = 0;
        List<String> columns = new ArrayList<>();
        for (JdbcPropertyMapping pm : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(pm.getPropertyMappings())) {
                if (pm.isInsertable()) {
                    columns.add(pm.getRepositoryFieldName());
                    propertyPositions.put(++paramNum, pm);
                }
            } else {
                for (JdbcPropertyMapping subPm : pm.getPropertyMappings()) {
                    if (subPm.isInsertable()) {
                        columns.add(subPm.getRepositoryFieldName());
                        propertyPositions.put(++paramNum, subPm);
                    }
                }
            }
        }
        return Tuples.of(propertyPositions, CollectionUtils.toArray(columns, String.class));
    }

    /**
     * Gets the insert sql and mapping array with param order.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the insert sql and mapping array
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getInsertSqlAndMappings(JdbcClassMapping<?> classMapping,
        Dialect dialect) {
        Tuple2<JdbcPropertyMapping[], String[]> tuple = getInsertMappingsAndColumns(classMapping, dialect);
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        String sql = dialect.dml().insert(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
            classMapping.isPrimaryKeyAutoincrement());
        return Tuples.of(sql, tuple.get0());
    }

    private static Tuple2<JdbcPropertyMapping[], String[]> getInsertMappingsAndColumns(JdbcClassMapping<?> classMapping,
        Dialect dialect) {
        List<JdbcPropertyMapping> mappings = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        for (JdbcPropertyMapping pm : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(pm.getPropertyMappings())) {
                if (pm.isInsertable() && !pm.isIgnoreAtInsert()) {
                    columns.add(pm.getRepositoryFieldName());
                    mappings.add(pm);
                }
            } else {
                for (JdbcPropertyMapping subPm : pm.getPropertyMappings()) {
                    if (subPm.isInsertable() && !pm.isIgnoreAtInsert()) {
                        columns.add(subPm.getRepositoryFieldName());
                        mappings.add(subPm);
                    }
                }
            }
        }
        return Tuples.of(CollectionUtils.toArray(mappings, JdbcPropertyMapping.class),
            CollectionUtils.toArray(columns, String.class));
    }

    /**
     * Gets the insert sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the insert sql and param positions
     * @deprecated {@link #getInsertSqlAndMappings(JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static Tuple2<String, Map<Integer, JdbcPropertyMapping>> getInsertSqlAndParamPositions(
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        Tuple2<Map<Integer, JdbcPropertyMapping>,
            String[]> tuple = getInsertParamPositionsAndColumns(classMapping, dialect);
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        String sql = dialect.dml().insert(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
            classMapping.isPrimaryKeyAutoincrement());
        return Tuples.of(sql, tuple.get0());
    }

    /**
     * Gets the upsert batch sql and param positions.
     *
     * @param upsertAmount the upsert amount
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the insert batch sql and param positions
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getUpsertBatchSqlAndMappings(int upsertAmount,
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        Tuple3<JdbcPropertyMapping[], String[], String[]> tuple = getUpsertMappingsColumnsAndIds(classMapping);
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        String sql = dialect.dml().upsertBatch(classMapping.getRepositoryName(), pkColumn, tuple.get1(), tuple.get2(),
            upsertAmount, classMapping.isPrimaryKeyAutoincrement());
        return Tuples.of(sql, tuple.get0());
    }

    /**
     * Gets the upsert batch sql and param positions.
     *
     * @param upsertAmount the upsert amount
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the insert batch sql and param positions
     * @deprecated {@link #getUpsertBatchSqlAndMappings(int, JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static Tuple2<String, Map<Integer, JdbcPropertyMapping>> getUpsertBatchSqlAndParamPositions(int upsertAmount,
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        Tuple3<Map<Integer, JdbcPropertyMapping>, String[],
            String[]> tuple = getUpsertSqlAndParamPositionsColumnsAndIdsAnd(classMapping, dialect);
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        String sql = dialect.dml().upsertBatch(classMapping.getRepositoryName(), pkColumn, tuple.get1(), tuple.get2(),
            upsertAmount, classMapping.isPrimaryKeyAutoincrement());
        return Tuples.of(sql, tuple.get0());
    }

    /**
     * Gets the upsert sql and mappings.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the upsert sql and mappings
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getUpsertSqlAndMappings(JdbcClassMapping<?> classMapping,
        Dialect dialect) {
        Tuple3<JdbcPropertyMapping[], String[], String[]> tuple = getUpsertMappingsColumnsAndIds(classMapping);
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        String upsert = dialect.dml().upsertBatch(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
            tuple.get2(), 1, classMapping.isPrimaryKeyAutoincrement());
        return Tuples.of(upsert, tuple.get0());
    }

    private static Tuple3<JdbcPropertyMapping[], String[], String[]> getUpsertMappingsColumnsAndIds(
        JdbcClassMapping<?> classMapping) {
        List<JdbcPropertyMapping> pkms = new ArrayList<>();
        List<JdbcPropertyMapping> pms = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        List<String> uniques = new ArrayList<>();
        for (JdbcPropertyMapping pm : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(pm.getPropertyMappings())) {
                if (pm.isInsertable()) {
                    columns.add(pm.getRepositoryFieldName());
                    pms.add(pm);
                }
                if (pm.isPrimaryKey()) {
                    uniques.add(pm.getRepositoryFieldName());
                    pkms.add(pm);
                } else if (pm.isUnique()) {
                    uniques.add(pm.getRepositoryFieldName());
                }
            } else {
                for (JdbcPropertyMapping subPm : pm.getPropertyMappings()) {
                    if (subPm.isInsertable()) {
                        columns.add(pm.getRepositoryFieldName());
                        pms.add(subPm);
                    }
                    if (subPm.isPrimaryKey()) {
                        uniques.add(pm.getRepositoryFieldName());
                        pkms.add(subPm);
                    } else if (pm.isUnique()) {
                        uniques.add(pm.getRepositoryFieldName());
                    }
                }
            }
        }
        String[] columnNames = CollectionUtils.toArray(columns);
        String[] uniqueColumns = CollectionUtils.toArray(uniques);
        return Tuples.of(pms.toArray(new JdbcPropertyMapping[pms.size()]), columnNames, uniqueColumns);
    }

    /**
     * Gets the upsert sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the insert sql and param positions
     * @deprecated {@link #getUpsertSqlAndMappings(JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static Tuple2<String, Map<Integer, JdbcPropertyMapping>> getUpsertSqlAndParamPositions(
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        Tuple3<Map<Integer, JdbcPropertyMapping>, String[],
            String[]> tuple = getUpsertSqlAndParamPositionsColumnsAndIdsAnd(classMapping, dialect);
        String pkColumn = null;
        if (classMapping.isPrimaryKeyAutoincrement()) {
            pkColumn = classMapping.getPrimaryKeyPropertyMappings().get(0).getRepositoryFieldName();
        }
        String upsert = dialect.dml().upsertBatch(classMapping.getRepositoryName(), pkColumn, tuple.get1(),
            tuple.get2(), 1, classMapping.isPrimaryKeyAutoincrement());
        return Tuples.of(upsert, tuple.get0());
    }

    @Deprecated
    private static Tuple3<Map<Integer, JdbcPropertyMapping>, String[],
        String[]> getUpsertSqlAndParamPositionsColumnsAndIdsAnd(JdbcClassMapping<?> classMapping, Dialect dialect) {
        List<JdbcPropertyMapping> pkms = new ArrayList<>();
        List<JdbcPropertyMapping> pms = new ArrayList<>();
        List<String> columns = new ArrayList<>();
        List<String> uniques = new ArrayList<>();
        for (JdbcPropertyMapping pm : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(pm.getPropertyMappings())) {
                if (pm.isInsertable()) {
                    columns.add(pm.getRepositoryFieldName());
                    pms.add(pm);
                }
                if (pm.isPrimaryKey()) {
                    uniques.add(pm.getRepositoryFieldName());
                    pkms.add(pm);
                } else if (pm.isUnique()) {
                    uniques.add(pm.getRepositoryFieldName());
                }
            } else {
                for (JdbcPropertyMapping subPm : pm.getPropertyMappings()) {
                    if (subPm.isInsertable()) {
                        columns.add(pm.getRepositoryFieldName());
                        pms.add(subPm);
                    }
                    if (subPm.isPrimaryKey()) {
                        uniques.add(pm.getRepositoryFieldName());
                        pkms.add(subPm);
                    } else if (pm.isUnique()) {
                        uniques.add(pm.getRepositoryFieldName());
                    }
                }
            }
        }
        String[] columnNames = CollectionUtils.toArray(columns);
        String[] uniqueColumns = CollectionUtils.toArray(uniques);
        //        String upsert = dialect.buildUpsertBatchSql(classMapping.getRepositoryName(), columnNames, uniqueColumns, 1);
        return Tuples.of(propertyPositions(pms), columnNames, uniqueColumns);
    }

    @Deprecated
    private static Map<Integer, JdbcPropertyMapping> propertyPositions(List<JdbcPropertyMapping> pms) {
        Map<Integer, JdbcPropertyMapping> propertyPositions = new LinkedHashMap<>();
        int paramNum = 0;
        for (int i = 0; i < pms.size(); i++) {
            JdbcPropertyMapping pm = pms.get(i);
            if (pm.isPrimaryKey() && pm.getDefaultValue() != null && !"null".equalsIgnoreCase(pm.getDefaultValue())) {
            } else {
                paramNum++;
                propertyPositions.put(paramNum, pm);
                //                if (pm.getParent() == null) {
                //                    propertyPositions.put(paramNum, pm.getPropertyName());
                //                } else {
                //                    propertyPositions.put(paramNum,
                //                            pm.getParent().getPropertyName() + Chars.DOT + pm.getPropertyName());
                //                }
            }
        }
        return propertyPositions;
    }

    /**
     * Gets the update sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the update sql and param positions
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getUpdateSqlAndMappings(JdbcClassMapping<?> classMapping,
        Dialect dialect) {
        List<JdbcPropertyMapping> propertyPositions = new ArrayList<>();
        StringBuilder updateSql = new StringBuilder();
        updateSql.append(dialect.getKeywords().update()).append(Chars.SPACE)
            .append(dialect.wrapName(classMapping.getRepositoryName())).append(Chars.SPACE)
            .append(dialect.getKeywords().set()).append(Chars.SPACE);

        List<JdbcPropertyMapping> pms = new ArrayList<>();
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(propertyMapping.getPropertyMappings())) {
                if (propertyMapping.isPrimaryKey()) {
                    pms.add(propertyMapping);
                } else if (propertyMapping.isUpdatable()) {
                    updateSql.append(dialect.wrapName(propertyMapping.getRepositoryFieldName())).append(" = ?, ");
                    propertyPositions.add(propertyMapping);
                }
            } else {
                for (JdbcPropertyMapping subJdbcPropertyMapping : propertyMapping.getPropertyMappings()) {
                    if (subJdbcPropertyMapping.isPrimaryKey()) {
                        pms.add(subJdbcPropertyMapping);
                    } else if (propertyMapping.isUpdatable()) {
                        updateSql.append(dialect.wrapName(subJdbcPropertyMapping.getRepositoryFieldName()))
                            .append(" = ?, ");
                        propertyPositions.add(subJdbcPropertyMapping);
                    }
                }
            }
        }
        if (!propertyPositions.isEmpty()) {
            updateSql.deleteCharAt(updateSql.length() - 1).deleteCharAt(updateSql.length() - 1);
        }
        int pkNum = 0;
        updateSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE);
        for (JdbcPropertyMapping pm : pms) {
            if (pkNum > 0) {
                updateSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
            }
            updateSql.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
            pkNum++;
            propertyPositions.add(pm);
        }
        return Tuples.of(updateSql.toString().trim(),
            propertyPositions.toArray(new JdbcPropertyMapping[propertyPositions.size()]));
    }

    /**
     * Gets the update sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the update sql and param positions
     * @deprecated {@link #getUpdateSqlAndMappings(JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static Tuple2<String, Map<Integer, JdbcPropertyMapping>> getUpdateSqlAndParamPositions(
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        Map<Integer, JdbcPropertyMapping> propertyPositions = new LinkedHashMap<>();
        StringBuilder updateSql = new StringBuilder();
        updateSql.append(dialect.getKeywords().update()).append(Chars.SPACE)
            .append(dialect.wrapName(classMapping.getRepositoryName())).append(Chars.SPACE)
            .append(dialect.getKeywords().set()).append(Chars.SPACE);
        int columnNum = 0;

        List<JdbcPropertyMapping> pms = new ArrayList<>();
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(propertyMapping.getPropertyMappings())) {
                if (propertyMapping.isPrimaryKey()) {
                    pms.add(propertyMapping);
                } else if (propertyMapping.isUpdatable()) {
                    updateSql.append(dialect.wrapName(propertyMapping.getRepositoryFieldName())).append(" = ?, ");
                    columnNum++;
                    propertyPositions.put(columnNum, propertyMapping);
                    //                    propertyPositions.put(columnNum, propertyMapping.getPropertyName());
                }
            } else {
                for (JdbcPropertyMapping subJdbcPropertyMapping : propertyMapping.getPropertyMappings()) {
                    if (subJdbcPropertyMapping.isPrimaryKey()) {
                        pms.add(subJdbcPropertyMapping);
                    } else if (propertyMapping.isUpdatable()) {
                        updateSql.append(dialect.wrapName(subJdbcPropertyMapping.getRepositoryFieldName()))
                            .append(" = ?, ");
                        columnNum++;
                        //                        propertyPositions.put(columnNum, propertyMapping.getPropertyName() + Chars.DOT
                        //                                + subJdbcPropertyMapping.getPropertyName());
                        propertyPositions.put(columnNum, subJdbcPropertyMapping);
                    }
                }
            }
        }
        if (columnNum > 0) {
            updateSql.deleteCharAt(updateSql.length() - 1).deleteCharAt(updateSql.length() - 1);
        }
        int pkNum = 0;
        updateSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE);
        for (JdbcPropertyMapping pm : pms) {
            if (pkNum > 0) {
                updateSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
            }
            updateSql.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
            pkNum++;
            propertyPositions.put(columnNum + pkNum, pm);
            //            if (pm.getParent() == null) {
            //                propertyPositions.put(columnNum + pkNum, pm.getPropertyName());
            //            } else {
            //                propertyPositions.put(columnNum + pkNum,
            //                        pm.getParent().getPropertyName() + Chars.DOT + pm.getPropertyName());
            //            }
        }
        return Tuples.of(updateSql.toString().trim(), propertyPositions);
    }

    /**
     * Gets the delete sql and mappings.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the delete sql and mappings
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getDeleteSqlAndMappings(JdbcClassMapping<?> classMapping,
        Dialect dialect) {
        return getDeleteSqlAndMappings(1, classMapping, dialect);
    }

    /**
     * Gets the delete sql and mappings.
     *
     * @param batchSize the batch size
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the delete sql and mappings
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getDeleteSqlAndMappings(int batchSize,
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        if (batchSize < 1) {
            batchSize = 1;
        }
        List<JdbcPropertyMapping> propertyPositions = new ArrayList<>();
        StringBuilder deleteSql = new StringBuilder();
        deleteSql.append(dialect.getKeywords().delete()).append(Chars.SPACE).append(dialect.getKeywords().from())
            .append(Chars.SPACE).append(dialect.wrapName(classMapping.getRepositoryName())).append(Chars.SPACE)
            .append(dialect.getKeywords().where()).append(Chars.SPACE).append(
                deleteCondition(batchSize, dialect, propertyPositions, classMapping.getPrimaryKeyPropertyMappings()));
        return Tuples.of(deleteSql.toString().trim(),
            propertyPositions.toArray(new JdbcPropertyMapping[propertyPositions.size()]));
    }

    /**
     * Gets the delete sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the delete sql and param positions
     * @deprecated {@link #getDeleteSqlAndMappings(JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static Tuple2<String, Map<Integer, JdbcPropertyMapping>> getDeleteSqlAndParamPositions(
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        return getDeleteSqlAndParamPositions(1, classMapping, dialect);
    }

    /**
     * Gets the delete sql and param positions.
     *
     * @param batchSize the batch size
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the delete sql and param positions
     * @deprecated {@link #getDeleteSqlAndMappings(int, JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static Tuple2<String, Map<Integer, JdbcPropertyMapping>> getDeleteSqlAndParamPositions(int batchSize,
        JdbcClassMapping<?> classMapping, Dialect dialect) {
        if (batchSize < 1) {
            batchSize = 1;
        }
        Map<Integer, JdbcPropertyMapping> propertyPositions = new LinkedHashMap<>();
        StringBuilder deleteSql = new StringBuilder();
        deleteSql.append(dialect.getKeywords().delete()).append(Chars.SPACE).append(dialect.getKeywords().from())
            .append(Chars.SPACE).append(dialect.wrapName(classMapping.getRepositoryName())).append(Chars.SPACE)
            .append(dialect.getKeywords().where()).append(Chars.SPACE).append(
                deleteCondition(batchSize, dialect, propertyPositions, classMapping.getPrimaryKeyPropertyMappings()));
        return Tuples.of(deleteSql.toString().trim(), propertyPositions);
    }

    private static String deleteCondition(int batchSize, Dialect dialect, List<JdbcPropertyMapping> propertyPositions,
        List<JdbcPropertyMapping> pkJdbcPropertyMappings) {
        StringBuilder deleteSqlCondition = new StringBuilder();
        if (pkJdbcPropertyMappings.size() == 1) {
            JdbcPropertyMapping propertyMapping = pkJdbcPropertyMappings.get(0);
            String fieldName = propertyMapping.getRepositoryFieldName();
            if (batchSize == 1) {
                deleteSqlCondition.append(dialect.wrapName(fieldName)).append(" = ? ");
                propertyPositions.add(propertyMapping);
            } else {
                deleteSqlCondition.append(dialect.wrapName(fieldName)).append(Chars.SPACE)
                    .append(dialect.getKeywords().in()).append(Chars.SPACE).append(Chars.PAREN_L);
                for (int i = 0; i < batchSize; i++) {
                    deleteSqlCondition.append(Chars.QUESTION).append(Chars.COMMA);
                    propertyPositions.add(propertyMapping);
                }
                deleteSqlCondition.deleteCharAt(deleteSqlCondition.length() - 1).append(Chars.PAREN_R);
            }
        } else {
            if (batchSize > 1) {
                deleteSqlCondition.append(Chars.SPACE).append(Chars.PAREN_L);
            }
            for (JdbcPropertyMapping propertyMapping : pkJdbcPropertyMappings) {
                String fieldName = propertyMapping.getRepositoryFieldName();
                if (!propertyPositions.isEmpty()) {
                    deleteSqlCondition.append(Chars.SPACE).append(dialect.getKeywords().and()).append(Chars.SPACE);
                }
                deleteSqlCondition.append(dialect.wrapName(fieldName)).append(" = ?");
                propertyPositions.add(propertyMapping);
            }
            if (batchSize > 1) {
                deleteSqlCondition.append(Chars.PAREN_R);
            }
            if (batchSize > 1) {
                String condition = deleteSqlCondition.toString();
                List<JdbcPropertyMapping> conditions = new ArrayList<>(propertyPositions);
                for (int i = 1; i < batchSize; i++) {
                    deleteSqlCondition.append(Chars.SPACE).append(dialect.getKeywords().or()).append(Chars.SPACE)
                        .append(condition);
                    for (JdbcPropertyMapping mapper : conditions) {
                        propertyPositions.add(mapper);
                    }
                }
            }
        }
        return deleteSqlCondition.toString();
    }

    private static String deleteCondition(int batchSize, Dialect dialect,
        Map<Integer, JdbcPropertyMapping> propertyPositions, List<JdbcPropertyMapping> pkJdbcPropertyMappings) {
        StringBuilder deleteSqlCondition = new StringBuilder();
        if (pkJdbcPropertyMappings.size() == 1) {
            JdbcPropertyMapping propertyMapping = pkJdbcPropertyMappings.get(0);
            String fieldName = propertyMapping.getRepositoryFieldName();
            //            String propertyName = propertyMapping.getParent() != null
            //                    ? propertyMapping.getParent().getPropertyName() + "." + propertyMapping.getPropertyName()
            //                    : propertyMapping.getPropertyName();
            if (batchSize == 1) {
                deleteSqlCondition.append(dialect.wrapName(fieldName)).append(" = ? ");
                propertyPositions.put(1, propertyMapping);
            } else {
                deleteSqlCondition.append(dialect.wrapName(fieldName)).append(Chars.SPACE)
                    .append(dialect.getKeywords().in()).append(Chars.SPACE).append(Chars.PAREN_L);
                for (int i = 0; i < batchSize; i++) {
                    deleteSqlCondition.append(Chars.QUESTION).append(Chars.COMMA);
                    propertyPositions.put(i + 1, propertyMapping);
                }
                deleteSqlCondition.deleteCharAt(deleteSqlCondition.length() - 1).append(Chars.PAREN_R);
            }
        } else {
            int columnNum = 0;
            if (batchSize > 1) {
                deleteSqlCondition.append(Chars.SPACE).append(Chars.PAREN_L);
            }
            for (JdbcPropertyMapping propertyMapping : pkJdbcPropertyMappings) {
                String fieldName = propertyMapping.getRepositoryFieldName();
                //                String propertyName = propertyMapping.getParent() != null
                //                        ? propertyMapping.getParent().getPropertyName() + "." + propertyMapping.getPropertyName()
                //                        : propertyMapping.getPropertyName();
                if (columnNum > 0) {
                    deleteSqlCondition.append(Chars.SPACE).append(dialect.getKeywords().and()).append(Chars.SPACE);
                }
                deleteSqlCondition.append(dialect.wrapName(fieldName)).append(" = ?");
                columnNum++;
                propertyPositions.put(columnNum, propertyMapping);
            }
            if (batchSize > 1) {
                deleteSqlCondition.append(Chars.PAREN_R);
            }
            if (batchSize > 1) {
                String condition = deleteSqlCondition.toString();
                Map<Integer, JdbcPropertyMapping> conditionMap = new HashMap<>(propertyPositions);
                for (int i = 1; i < batchSize; i++) {
                    deleteSqlCondition.append(Chars.SPACE).append(dialect.getKeywords().or()).append(Chars.SPACE)
                        .append(condition);
                    for (Entry<Integer, JdbcPropertyMapping> entry : conditionMap.entrySet()) {
                        propertyPositions.put(entry.getKey() + columnNum * i, entry.getValue());
                    }
                }
            }
        }
        return deleteSqlCondition.toString();
    }

    /**
     * Gets the merge sql and param positions.
     *
     * @param <T> the generic type
     * @param entity the entity
     * @param classMapping the class mapping
     * @param onlyNull the only null
     * @param dialect the dialect
     * @return the merge sql and param positions and set value number
     */
    public static <T> Tuple3<String, JdbcPropertyMapping[], Integer> getMergeSqlAndMappings(T entity,
        JdbcClassMapping<?> classMapping, boolean onlyNull, Dialect dialect) {
        List<JdbcPropertyMapping> propertyPositions = new ArrayList<>();
        List<JdbcPropertyMapping> pkms = new ArrayList<>();
        List<JdbcPropertyMapping> setms = new ArrayList<>();

        StringBuilder setSql = new StringBuilder();
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().isEmpty()) {
                // 如果为空忽略 ignore when null
                if (checkNullOrEmpty(entity, propertyMapping, onlyNull)) {
                    continue;
                }
                if (propertyMapping.isPrimaryKey()) {
                    pkms.add(propertyMapping);
                } else if (propertyMapping.isUpdatable()) {
                    setms.add(propertyMapping);
                    add(propertyMapping, setSql, propertyPositions, dialect);
                }
            } else {
                for (JdbcPropertyMapping subJdbcPropertyMapping : propertyMapping.getPropertyMappings()) {
                    if (checkNullOrEmpty(entity, subJdbcPropertyMapping, onlyNull)) {
                        continue;
                    }
                    if (subJdbcPropertyMapping.isPrimaryKey()) {
                        pkms.add(subJdbcPropertyMapping);
                    } else if (propertyMapping.isUpdatable()) {
                        setms.add(propertyMapping);
                        add(subJdbcPropertyMapping, setSql, propertyPositions, dialect);
                    }
                }
            }
        }

        StringBuilder updateSql = new StringBuilder();
        updateSql.append(dialect.getKeywords().update()).append(Chars.SPACE)
            .append(dialect.wrapName(classMapping.getRepositoryName()));
        if (!propertyPositions.isEmpty()) {
            updateSql.append(Chars.SPACE).append(dialect.getKeywords().set()).append(Chars.SPACE).append(setSql);
            updateSql.deleteCharAt(updateSql.length() - 1);
        }
        // where 后面的主键条件
        if (!pkms.isEmpty()) {
            int pkNum = 0;
            updateSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE);
            for (JdbcPropertyMapping pm : pkms) {
                if (pkNum > 0) {
                    updateSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
                }
                updateSql.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
                pkNum++;
                propertyPositions.add(pm);
            }
        }
        return Tuples.of(updateSql.toString().trim(),
            propertyPositions.toArray(new JdbcPropertyMapping[propertyPositions.size()]), setms.size());
    }

    private static void add(JdbcPropertyMapping propertyMapping, StringBuilder updateSql,
        List<JdbcPropertyMapping> propertyPositions, Dialect dialect) {
        BuilderUtils.link(updateSql, dialect.wrapName(propertyMapping.getRepositoryFieldName()), " = ?,");
        propertyPositions.add(propertyMapping);
    }

    /**
     * Gets the merge sql and param positions.
     *
     * @param <T> the generic type
     * @param entity the entity
     * @param classMapping the class mapping
     * @param onlyNull the only null
     * @param dialect the dialect
     * @return the merge sql and param positions and set value number
     * @deprecated {@link #getMergeSqlAndMappings(Object, JdbcClassMapping, boolean, Dialect)}
     */
    @Deprecated
    public static <T> Tuple3<String, Map<Integer, JdbcPropertyMapping>, Integer> getMergeSqlAndParamPositions(T entity,
        JdbcClassMapping<?> classMapping, boolean onlyNull, Dialect dialect) {
        LinkedHashMap<Integer, JdbcPropertyMapping> propertyPositions = new LinkedHashMap<>();
        int columnNum = 0;
        List<JdbcPropertyMapping> pkms = new ArrayList<>();
        List<JdbcPropertyMapping> setms = new ArrayList<>();

        StringBuilder setSql = new StringBuilder();
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().isEmpty()) {
                // 如果为空忽略 ignore when null
                if (checkNullOrEmpty(entity, propertyMapping, onlyNull)) {
                    continue;
                }
                if (propertyMapping.isPrimaryKey()) {
                    pkms.add(propertyMapping);
                } else if (propertyMapping.isUpdatable()) {
                    setms.add(propertyMapping);
                    columnNum = set(propertyMapping, setSql, propertyPositions, columnNum, dialect);
                }
            } else {
                for (JdbcPropertyMapping subJdbcPropertyMapping : propertyMapping.getPropertyMappings()) {
                    if (checkNullOrEmpty(entity, subJdbcPropertyMapping, onlyNull)) {
                        continue;
                    }
                    if (subJdbcPropertyMapping.isPrimaryKey()) {
                        pkms.add(subJdbcPropertyMapping);
                    } else if (propertyMapping.isUpdatable()) {
                        setms.add(propertyMapping);
                        columnNum = set(subJdbcPropertyMapping, setSql, propertyPositions, columnNum, dialect);
                    }
                }
            }
        }

        StringBuilder updateSql = new StringBuilder();
        updateSql.append(dialect.getKeywords().update()).append(Chars.SPACE)
            .append(dialect.wrapName(classMapping.getRepositoryName()));
        if (columnNum > 0) {
            updateSql.append(Chars.SPACE).append(dialect.getKeywords().set()).append(Chars.SPACE).append(setSql);
            updateSql.deleteCharAt(updateSql.length() - 1);
        }
        // where 后面的主键条件
        if (!pkms.isEmpty()) {
            int pkNum = 0;
            updateSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE);
            for (JdbcPropertyMapping pm : pkms) {
                if (pkNum > 0) {
                    updateSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
                }
                updateSql.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
                pkNum++;
                propertyPositions.put(columnNum + pkNum, pm);
            }
        }
        return Tuples.of(updateSql.toString().trim(), propertyPositions, columnNum);
    }

    @Deprecated
    private static <T> int set(JdbcPropertyMapping propertyMapping, StringBuilder updateSql,
        LinkedHashMap<Integer, JdbcPropertyMapping> propertyPositions, int columnNum, Dialect dialect) {
        BuilderUtils.link(updateSql, dialect.wrapName(propertyMapping.getRepositoryFieldName()), " = ?,");
        columnNum++;
        //        propertyPositions.put(columnNum, getPropertyAliasName(propertyMapping));
        propertyPositions.put(columnNum, propertyMapping);
        return columnNum;
    }

    /**
     * Check null or empty.
     *
     * @param <T> the generic type
     * @param entity the entity
     * @param propertyMapping the property mapping
     * @param onlyNull the only null
     * @return true, if successful
     */
    private static <T> boolean checkNullOrEmpty(T entity, JdbcPropertyMapping propertyMapping, boolean onlyNull) {
        String pn = getPropertyAliasName(propertyMapping);
        if (onlyNull) {
            return BeanUtils.getProperty(entity, pn) == null;
        } else {
            return Lang.isEmpty(BeanUtils.getProperty(entity, pn));
        }
    }

    //    private static <T> boolean ignoreProperty(T entity, JdbcPropertyMapping propertyMapping,
    //        BiPredicate<PropertyMapping<?>, Object> ignoreStrategy) {
    //        String pn = getPropertyAliasName(propertyMapping);
    //        Object value = BeanUtils.getProperty(entity, pn);
    //        return ignoreStrategy.test(propertyMapping, value);
    //    }

    /**
     * Gets the select by id sql.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the select by id sql
     * @deprecated {@link #getSelectByPkSql(JdbcClassMapping, Dialect)}
     */
    @Deprecated
    public static String getSelectByIdSql(JdbcClassMapping<?> classMapping, Dialect dialect) {
        return getSelectByPkSql(classMapping, null, dialect);
    }

    /**
     * Gets the select by id sql.
     *
     * @param classMapping the class mapping
     * @param alias the alias
     * @param dialect the dialect
     * @return the select by id sql
     * @deprecated {@link #getSelectByPkSql(JdbcClassMapping, String, Dialect)}
     */
    @Deprecated
    public static String getSelectByIdSql(JdbcClassMapping<?> classMapping, String alias, Dialect dialect) {
        return getSelectByPkSql(classMapping, alias, dialect);
    }

    /**
     * Gets the select by primary key sql.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the select by id sql
     */
    public static String getSelectByPkSql(JdbcClassMapping<?> classMapping, Dialect dialect) {
        return getSelectByPkSql(classMapping, null, dialect);
    }

    /**
     * Gets the select by primary key sql.
     *
     * @param classMapping the class mapping
     * @param alias the alias
     * @param dialect the dialect
     * @return the select by id sql
     */
    public static String getSelectByPkSql(JdbcClassMapping<?> classMapping, String alias, Dialect dialect) {
        StringBuilder getSql = new StringBuilder();
        LinkedHashMap<Integer, String> propertyPositions = new LinkedHashMap<>();
        getSql.append(getSelectSql(classMapping, alias, dialect));
        List<JdbcPropertyMapping> pkPms = new ArrayList<>();
        StringBuilder condition = new StringBuilder();
        int columnNum = 0;
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().isEmpty()) {
                columnNum = setPk(condition, columnNum, propertyMapping, propertyPositions, pkPms, dialect);
            } else {
                for (JdbcPropertyMapping subJdbcPropertyMapping : propertyMapping.getPropertyMappings()) {
                    columnNum = setPk(condition, columnNum, subJdbcPropertyMapping, propertyPositions, pkPms, dialect);
                }
            }
        }
        if (Lang.isEmpty(pkPms)) {
            throw new JdbcMappingException("#table.pk.not.exists", new Object[] { classMapping.getRepositoryName() });
        }
        BuilderUtils.link(getSql, dialect.getKeywords().where(), condition.toString());
        //        getSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE).append(condition);
        return getSql.toString();
    }

    /**
     * Gets the select by primary key sql.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the select by id sql
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getSelectByPkSqlAndMappings(JdbcClassMapping<?> classMapping,
        Dialect dialect) {
        return getSelectByPkSqlAndMappings(classMapping, null, dialect);
    }

    /**
     * Gets the select by primary key sql.
     *
     * @param classMapping the class mapping
     * @param alias the alias
     * @param dialect the dialect
     * @return the select by id sql
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getSelectByPkSqlAndMappings(JdbcClassMapping<?> classMapping,
        String alias, Dialect dialect) {
        StringBuilder getSql = new StringBuilder();
        LinkedHashMap<Integer, String> propertyPositions = new LinkedHashMap<>();
        Tuple2<String, JdbcPropertyMapping[]> tuple = getSelectSqlAndMappings(classMapping, alias, dialect);
        getSql.append(tuple.get0());
        List<JdbcPropertyMapping> pkPms = new ArrayList<>();
        StringBuilder condition = new StringBuilder();
        int columnNum = 0;
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().isEmpty()) {
                columnNum = setPk(condition, columnNum, propertyMapping, propertyPositions, pkPms, dialect);
            } else {
                for (JdbcPropertyMapping subJdbcPropertyMapping : propertyMapping.getPropertyMappings()) {
                    columnNum = setPk(condition, columnNum, subJdbcPropertyMapping, propertyPositions, pkPms, dialect);
                }
            }
        }
        if (Lang.isEmpty(pkPms)) {
            throw new JdbcMappingException("#table.pk.not.exists", new Object[] { classMapping.getRepositoryName() });
        }
        BuilderUtils.link(getSql, dialect.getKeywords().where(), condition.toString());
        return Tuples.of(getSql.toString(), tuple.get1());
    }

    /**
     * Sets the pk.
     *
     * @param condition the condition
     * @param columnNum the column num
     * @param pm the pm
     * @param propertyPositions the property positions
     * @param pkPms the pk pms
     * @param dialect the dialect
     * @return the int
     */
    private static int setPk(StringBuilder condition, int columnNum, JdbcPropertyMapping pm,
        Map<Integer, String> propertyPositions, List<JdbcPropertyMapping> pkPms, Dialect dialect) {
        if (pm.isPrimaryKey()) {
            if (columnNum > 0) {
                condition.append(dialect.getKeywords().and()).append(Chars.SPACE);
            }
            condition.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
            columnNum++;
            propertyPositions.put(columnNum, getPropertyAliasName(pm));
            // 设置主键值
            pkPms.add(pm);
        }
        return columnNum;
    }

    /**
     * Gets the select sql.
     *
     * @param classMapping the class mapping
     * @param dialect the dialect
     * @return the select sql
     */
    public static String getSelectSql(JdbcClassMapping<?> classMapping, Dialect dialect) {
        return getSelectSql(classMapping, null, dialect);
    }

    /**
     * Gets the select sql.
     *
     * @param classMapping the class mapping
     * @param alias the alias
     * @param dialect the dialect
     * @return the select sql
     */
    public static String getSelectSql(JdbcClassMapping<?> classMapping, String alias, Dialect dialect) {
        return getSelectSqlAndMappings(classMapping, alias, dialect).get0();
    }

    /**
     * Gets the select sql.
     *
     * @param classMapping the class mapping
     * @param alias the alias
     * @param dialect the dialect
     * @return the select sql
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getSelectSqlAndMappings(JdbcClassMapping<?> classMapping,
        String alias, Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        Tuple2<String, JdbcPropertyMapping[]> tuple = getSelectColumnsSqlAndMappings(classMapping, alias, dialect);
        BuilderUtils.link(selectSql, dialect.getKeywords().select(), tuple.get0(), dialect.getKeywords().from(),
            dialect.wrapName(classMapping.getRepositoryName()));
        return Tuples.of(selectSql.toString(), tuple.get1());
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param dialect the dialect
     * @return the select columns sql
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getSelectColumnsSqlAndMappings(JdbcClassMapping<?> classMapping,
        String tableAlias, Dialect dialect) {
        return getSelectColumnsSqlAndMappings(classMapping, tableAlias, "", dialect);
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param prefixPropertyName the prefix property name
     * @param dialect the dialect
     * @return the select columns sql
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getSelectColumnsSqlAndMappings(JdbcClassMapping<?> classMapping,
        String tableAlias, String prefixPropertyName, Dialect dialect) {
        return getSelectColumnsSqlAndMappings(classMapping, tableAlias, prefixPropertyName, dialect, null,
            new HashMap<>(0));
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param dialect the dialect
     * @param mappingFactory the mapping factory
     * @param fetchProperties the fetch properties
     * @return the select columns sql
     */
    public static Tuple2<String, JdbcPropertyMapping[]> getSelectColumnsSqlAndMappings(JdbcClassMapping<?> classMapping,
        String tableAlias, Dialect dialect, MappingFactory<JdbcPropertyMapping> mappingFactory,
        Map<String, String> fetchProperties) {
        return getSelectColumnsSqlAndMappings(classMapping, tableAlias, null, dialect, mappingFactory, fetchProperties);
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param prefixPropertyName the prefix property name
     * @param dialect the dialect
     * @param mappingFactory the mapping factory
     * @param fetchProperties the fetch properties
     * @return the select columns sql
     */
    private static Tuple2<String, JdbcPropertyMapping[]> getSelectColumnsSqlAndMappings(
        JdbcClassMapping<?> classMapping, String tableAlias, String prefixPropertyName, Dialect dialect,
        MappingFactory<JdbcPropertyMapping> mappingFactory, Map<String, String> fetchProperties) {
        List<JdbcPropertyMapping> jdbcPropertyMappings = new ArrayList<>();
        StringBuilder selectSql = new StringBuilder();
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(propertyMapping.getPropertyMappings())) {
                selectSql.append(
                    getSelectColumnsSql(classMapping, tableAlias, propertyMapping, prefixPropertyName, dialect));
                jdbcPropertyMappings.add(propertyMapping);
            } else {
                boolean fetchAble = false;
                String fetchPropertyTableAlia = null;
                if (Lang.isNotEmpty(fetchProperties)) {
                    for (Entry<String, String> fetchPropertyEntry : fetchProperties.entrySet()) {
                        fetchPropertyTableAlia = fetchPropertyEntry.getValue();
                        if (propertyMapping.getPropertyName().equals(fetchPropertyEntry.getKey())) {
                            fetchAble = true;
                            break;
                        }
                    }
                }
                if (fetchAble) {
                    ClassMapping<?,
                        JdbcPropertyMapping> pcm = mappingFactory.getClassMapping(propertyMapping.getPropertyType());
                    for (JdbcPropertyMapping pm : pcm.getPropertyMappings()) {
                        selectSql.append(
                            getSelectColumnsSql(classMapping, fetchPropertyTableAlia, pm, propertyMapping, dialect));
                        jdbcPropertyMappings.add(pm);
                    }

                } else {
                    for (JdbcPropertyMapping pm : propertyMapping.getPropertyMappings()) {
                        selectSql
                            .append(getSelectColumnsSql(classMapping, tableAlias, pm, prefixPropertyName, dialect));
                        jdbcPropertyMappings.add(pm);
                    }
                }
                fetchAble = false;
            }
        }
        if (!jdbcPropertyMappings.isEmpty()) {
            selectSql.delete(selectSql.length() - 2, selectSql.length());
        }
        return Tuples.of(selectSql.toString(),
            jdbcPropertyMappings.toArray(new JdbcPropertyMapping[jdbcPropertyMappings.size()]));
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param dialect the dialect
     * @return the select columns sql
     */
    public static String getSelectColumnsSql(JdbcClassMapping<?> classMapping, String tableAlias, Dialect dialect) {
        return getSelectColumnsSql(classMapping, tableAlias, "", dialect);
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param prefixPropertyName the prefix property name
     * @param dialect the dialect
     * @return the select columns sql
     */
    public static String getSelectColumnsSql(JdbcClassMapping<?> classMapping, String tableAlias,
        String prefixPropertyName, Dialect dialect) {
        return getSelectColumnsSql(classMapping, tableAlias, prefixPropertyName, dialect, null, new HashMap<>(0));
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param dialect the dialect
     * @param mappingFactory the mapping factory
     * @param fetchProperties the fetch properties
     * @return the select columns sql
     */
    public static String getSelectColumnsSql(JdbcClassMapping<?> classMapping, String tableAlias, Dialect dialect,
        MappingFactory<JdbcPropertyMapping> mappingFactory, Map<String, String> fetchProperties) {
        return getSelectColumnsSql(classMapping, tableAlias, null, dialect, mappingFactory, fetchProperties);
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param prefixPropertyName the prefix property name
     * @param dialect the dialect
     * @param mappingFactory the mapping factory
     * @param fetchProperties the fetch properties
     * @return the select columns sql
     */
    private static String getSelectColumnsSql(JdbcClassMapping<?> classMapping, String tableAlias,
        String prefixPropertyName, Dialect dialect, MappingFactory<JdbcPropertyMapping> mappingFactory,
        Map<String, String> fetchProperties) {
        int columnNum = 0;
        StringBuilder selectSql = new StringBuilder();
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(propertyMapping.getPropertyMappings())) {
                selectSql.append(
                    getSelectColumnsSql(classMapping, tableAlias, propertyMapping, prefixPropertyName, dialect));
                columnNum++;
            } else {
                boolean fetchAble = false;
                String fetchPropertyTableAlia = null;
                if (Lang.isNotEmpty(fetchProperties)) {
                    for (Entry<String, String> fetchPropertyEntry : fetchProperties.entrySet()) {
                        fetchPropertyTableAlia = fetchPropertyEntry.getValue();
                        if (propertyMapping.getPropertyName().equals(fetchPropertyEntry.getKey())) {
                            fetchAble = true;
                            break;
                        }
                    }
                }
                if (fetchAble) {
                    ClassMapping<?,
                        JdbcPropertyMapping> pcm = mappingFactory.getClassMapping(propertyMapping.getPropertyType());
                    for (JdbcPropertyMapping pm : pcm.getPropertyMappings()) {
                        selectSql.append(
                            getSelectColumnsSql(classMapping, fetchPropertyTableAlia, pm, propertyMapping, dialect));
                        columnNum++;
                    }

                } else {
                    for (JdbcPropertyMapping pm : propertyMapping.getPropertyMappings()) {
                        selectSql
                            .append(getSelectColumnsSql(classMapping, tableAlias, pm, prefixPropertyName, dialect));
                        columnNum++;
                    }
                }
                fetchAble = false;
            }
        }
        if (columnNum > 0) {
            selectSql.delete(selectSql.length() - 2, selectSql.length());
        }
        return selectSql.toString();
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param propertyMapping the property mapping
     * @param prefixPropertyName the prefix property name
     * @param dialect the dialect
     * @return the select columns sql
     */
    private static String getSelectColumnsSql(JdbcClassMapping<?> classMapping, String tableAlias,
        JdbcPropertyMapping propertyMapping, String prefixPropertyName, Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        if (Strings.isNotBlank(tableAlias)) {
            selectSql.append(tableAlias).append(Chars.DOT);
        }
        selectSql.append(dialect.wrapName(propertyMapping.getRepositoryFieldName())).append(Chars.SPACE)
            .append(getNestedPropertyAliasName(propertyMapping, prefixPropertyName, dialect)).append(Chars.COMMA)
            .append(Chars.SPACE);
        return selectSql.toString();
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias the table alias
     * @param propertyMapping the property mapping
     * @param nestedJdbcPropertyMapping the nested property mapping
     * @param dialect the dialect
     * @return the select columns sql
     */
    private static String getSelectColumnsSql(JdbcClassMapping<?> classMapping, String tableAlias,
        JdbcPropertyMapping propertyMapping, JdbcPropertyMapping nestedJdbcPropertyMapping, Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        if (Strings.isNotBlank(tableAlias)) {
            selectSql.append(tableAlias).append(Chars.DOT);
        }
        selectSql.append(dialect.wrapName(propertyMapping.getRepositoryFieldName())).append(Chars.SPACE)
            .append(getNestedPropertyAliasName(propertyMapping, nestedJdbcPropertyMapping, dialect)).append(Chars.COMMA)
            .append(Chars.SPACE);
        return selectSql.toString();
    }

    /**
     * Gets the property alias name.
     *
     * @param propertyMapping the property mapping
     * @return the property alias name
     */
    public static String getPropertyAliasName(JdbcPropertyMapping propertyMapping) {
        return getNestedPropertyAliasName(propertyMapping, "");
    }

    /**
     * Gets the nested property alias name.
     *
     * @param propertyMapping the property mapping
     * @param nestedJdbcPropertyMapping the nested property mapping
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(JdbcPropertyMapping propertyMapping,
        JdbcPropertyMapping nestedJdbcPropertyMapping) {
        String prefixPropertyName = null;
        if (nestedJdbcPropertyMapping != null) {
            prefixPropertyName = nestedJdbcPropertyMapping.getPropertyName();
        }
        return getNestedPropertyAliasName(propertyMapping, prefixPropertyName);
    }

    /**
     * Gets the nested property alias name.
     *
     * @param pm the pm
     * @param prefixPropertyName the prefix property name
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(JdbcPropertyMapping pm, String prefixPropertyName) {
        JdbcPropertyMapping propertyMapping = pm;
        if (!propertyMapping.getPropertyMappings().isEmpty()) {
            propertyMapping = propertyMapping.getPropertyMappings().get(0);
        }
        String propertyName = propertyMapping.getPropertyName();
        if (propertyMapping.getParent() != null) {
            propertyName = propertyMapping.getParent().getPropertyName() + Chars.DOT
                + propertyMapping.getPropertyName();
        }
        if (Lang.isNotEmpty(prefixPropertyName)) {
            propertyName = prefixPropertyName + Chars.DOT + propertyName;
        }
        return propertyName;
    }

    /**
     * Gets the property alias name.
     *
     * @param propertyMapping the property mapping
     * @param dialect the dialect
     * @return the property alias name
     */
    public static String getPropertyAliasName(JdbcPropertyMapping propertyMapping, Dialect dialect) {
        return dialect.wrapName(getPropertyAliasName(propertyMapping));
    }

    /**
     * Gets the nested property alias name.
     *
     * @param propertyMapping the property mapping
     * @param prefixPropertyName the prefix property name
     * @param dialect the dialect
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(JdbcPropertyMapping propertyMapping, String prefixPropertyName,
        Dialect dialect) {
        return dialect.wrapName(getNestedPropertyAliasName(propertyMapping, prefixPropertyName));
    }

    /**
     * Gets the nested property alias name.
     *
     * @param propertyMapping the property mapping
     * @param nestedJdbcPropertyMapping the nested property mapping
     * @param dialect the dialect
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(JdbcPropertyMapping propertyMapping,
        JdbcPropertyMapping nestedJdbcPropertyMapping, Dialect dialect) {
        return dialect.wrapName(getNestedPropertyAliasName(propertyMapping, nestedJdbcPropertyMapping));
    }

    /**
     * Gets the select columns.
     *
     * @param classMapping the class mapping
     * @return the select columns
     */
    public static Map<String, String> getSelectColumns(JdbcClassMapping<?> classMapping) {
        Map<String, String> columns = new HashMap<>();
        for (JdbcPropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (Lang.isEmpty(propertyMapping.getPropertyMappings())) {
                columns.put(propertyMapping.getRepositoryFieldName(), propertyMapping.getPropertyName());
            } else {
                for (JdbcPropertyMapping pm : propertyMapping.getPropertyMappings()) {
                    columns.put(pm.getRepositoryFieldName(),
                        propertyMapping.getPropertyName() + Chars.DOT + pm.getPropertyName());
                }
            }
        }
        return columns;
    }

    /**
     * 根据传入name获取对应的columnName,propertyName.
     *
     * @param name property name or column name
     * @param classMapping classMapping
     * @return Tuple&lt;columnName, propertyName&gt;
     */
    public static Tuple2<String, String> getColumnAndPropertyName(String name, JdbcClassMapping<?> classMapping) {
        JdbcPropertyMapping propertyMapping = null;
        if (classMapping != null && Lang.isNotEmpty(name)) {
            if (name.contains(Chars.DOT)) {
                String[] names = name.split("\\.");
                propertyMapping = getNestedMapping(names, classMapping);
            } else {
                propertyMapping = getSimpleMapping(name, classMapping);
            }
        }
        if (propertyMapping != null) {
            return Tuples.of(propertyMapping.getRepositoryFieldName(), propertyMapping.getPropertyName());
        } else {
            return Tuples.of(name, Chars.EMPTY_STR);
        }
    }

    /**
     * 根据传入name获取对应的columnName.
     *
     * @param name property name or column name
     * @param classMapping classMapping
     * @return columnName
     */
    public static String getColumnName(String name, JdbcClassMapping<?> classMapping) {
        if (classMapping != null && Lang.isNotEmpty(name)) {
            if (name.contains(Chars.DOT)) {
                String[] names = name.split("\\.");
                return getNestedColumnName(names, classMapping);
            } else {
                return getSimpleColumnName(name, classMapping);
            }
        }
        return name;
    }

    /**
     * Gets the simple mapping.
     *
     * @param name the name
     * @param classMapping the class mapping
     * @return the simple mapping
     */
    private static JdbcPropertyMapping getSimpleMapping(String name, JdbcClassMapping<?> classMapping) {
        JdbcPropertyMapping pm = classMapping.getPropertyMapping(name);
        return pm;
    }

    /**
     * Gets the simple column name.
     *
     * @param name the name
     * @param classMapping the class mapping
     * @return the simple column name
     */
    public static String getSimpleColumnName(String name, JdbcClassMapping<?> classMapping) {
        JdbcPropertyMapping pm = getSimpleMapping(name, classMapping);
        if (pm != null) {
            return pm.getRepositoryFieldName();
        }
        return name;
    }

    /**
     * Gets the nested mapping.
     *
     * @param names the names
     * @param classMapping the class mapping
     * @return the nested mapping
     */
    private static JdbcPropertyMapping getNestedMapping(String[] names, JdbcClassMapping<?> classMapping) {
        JdbcPropertyMapping pm = null;
        for (String n : names) {
            if (pm == null) {
                pm = classMapping.getPropertyMapping(n);
            } else {
                pm = pm.getPropertyMapping(n);
            }
        }
        return pm;
    }

    /**
     * Gets the nested column name.
     *
     * @param names the names
     * @param classMapping the class mapping
     * @return the nested column name
     */
    private static String getNestedColumnName(String[] names, JdbcClassMapping<?> classMapping) {
        JdbcPropertyMapping pm = getNestedMapping(names, classMapping);
        if (pm != null) {
            return pm.getRepositoryFieldName();
        } else {
            return null;
        }
    }

    /**
     * 根据传入name获取对应的columnName array.
     *
     * @param classMapping classMapping
     * @param names property name or column name array
     * @return columnNames
     */
    public static String[] getColumnNames(JdbcClassMapping<?> classMapping, String... names) {
        if (classMapping != null) {
            List<String> newNames = new ArrayList<>();
            for (String name : names) {
                newNames.add(getColumnName(name, classMapping));
            }
            return newNames.toArray(new String[newNames.size()]);
        }
        return names;
    }

    /**
     * 根据传入name获取对应的columnName array.
     *
     * @param classMapping classMapping
     * @param names property name or column name collection
     * @return columnNames
     */
    public static String[] getColumnNames(JdbcClassMapping<?> classMapping, Collection<String> names) {
        if (names == null) {
            names = new ArrayList<>();
        }
        return getColumnNames(classMapping, names.toArray(new String[names.size()]));
    }

}
