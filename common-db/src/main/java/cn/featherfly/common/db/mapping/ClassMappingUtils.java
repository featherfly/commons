
package cn.featherfly.common.db.mapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.builder.ColumnModel;
import cn.featherfly.common.db.builder.TableModel;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.LangUtils;
import cn.featherfly.common.lang.StringUtils;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.MappingFactory;
import cn.featherfly.common.repository.mapping.PropertyMapping;

/**
 * <p>
 * ClassMappingUtils
 * </p>
 * .
 *
 * @author zhongj
 */
public class ClassMappingUtils {

    /**
     * Gets the table model.
     *
     * @param classMapping          the class mapping
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @return the table model
     */
    public static Table createTable(ClassMapping<?> classMapping, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        // table
        TableModel table = new TableModel();
        table.setName(classMapping.getRepositoryName());
        table.setRemark(classMapping.getRemark());
        // columns
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (LangUtils.isEmpty(propertyMapping.getPropertyMappings())) {
                table.addColumn(createColumn(propertyMapping, sqlTypeMappingManager));
            } else {
                for (PropertyMapping pm : propertyMapping.getPropertyMappings()) {
                    table.addColumn(createColumn(pm, sqlTypeMappingManager));
                }
            }
        }
        return table;
    }

    /**
     * Gets the creates the table sql.
     *
     * @param classMapping          the class mapping
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @return the creates the table sql
     */
    public static String getCreateTableSql(ClassMapping<?> classMapping, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        return dialect.buildCreateTableDDL(createTable(classMapping, dialect, sqlTypeMappingManager));
    }

    /**
     * Creates the column.
     *
     * @param propertyMapping       the property mapping
     * @param sqlTypeMappingManager the sql type mapping manager
     * @return the column model
     */
    @SuppressWarnings("unchecked")
    private static Column createColumn(PropertyMapping propertyMapping, SqlTypeMappingManager sqlTypeMappingManager) {
        ColumnModel column = new ColumnModel();
        column.setName(propertyMapping.getRepositoryFieldName());
        column.setPrimaryKey(propertyMapping.isPrimaryKey());
        column.setAutoincrement(propertyMapping.isAutoincrement());
        column.setDecimalDigits(propertyMapping.getDecimalDigits());
        column.setDefaultValue(propertyMapping.getDefaultValue());
        column.setNullable(propertyMapping.isNullable());
        column.setRemark(propertyMapping.getRemark());
        column.setSize(propertyMapping.getSize());
        column.setSqlType(
                sqlTypeMappingManager.getSqlType((Class<? extends Serializable>) propertyMapping.getPropertyType()));
        return column;
    }

    /**
     * Gets the insert batch sql and param positions.
     *
     * @param insertAmount the insert amount
     * @param classMapping the class mapping
     * @param dialect      the dialect
     * @return the insert batch sql and param positions
     */
    public static Tuple2<String, Map<Integer, String>> getInsertBatchSqlAndParamPositions(int insertAmount,
            ClassMapping<?> classMapping, Dialect dialect) {
        Tuple2<String, Map<Integer, String>> tuple = getInsertSqlAndParamPositions(classMapping, dialect);
        String sql = dialect.buildInsertBatchSql(classMapping.getRepositoryName(),
                tuple.get1().values().toArray(new String[] {}), insertAmount);
        return Tuples.of(sql, tuple.get1());
    }

    /**
     * Gets the insert sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect      the dialect
     * @return the insert sql and param positions
     */
    public static Tuple2<String, Map<Integer, String>> getInsertSqlAndParamPositions(ClassMapping<?> classMapping,
            Dialect dialect) {
        Map<Integer, String> propertyPositions = new LinkedHashMap<>();
        StringBuilder insertSql = new StringBuilder();
        insertSql.append(dialect.getKeywords().insert()).append(Chars.SPACE).append(dialect.getKeywords().into())
                .append(Chars.SPACE).append(dialect.wrapName(classMapping.getRepositoryName())).append(" (");
        List<PropertyMapping> pms = new ArrayList<>();
        for (PropertyMapping pm : classMapping.getPropertyMappings()) {
            if (LangUtils.isEmpty(pm.getPropertyMappings())) {
                if (pm.isInsertable()) {
                    insertSql.append(dialect.wrapName(pm.getRepositoryFieldName())).append(",");
                    pms.add(pm);
                }
            } else {
                for (PropertyMapping pm2 : pm.getPropertyMappings()) {
                    if (pm.isInsertable()) {
                        insertSql.append(dialect.wrapName(pm2.getRepositoryFieldName())).append(",");
                        pms.add(pm2);
                    }
                }
            }
        }
        if (pms.size() > 0) {
            insertSql.deleteCharAt(insertSql.length() - 1);
        }
        insertSql.append(") ").append(dialect.getKeywords().values())
                .append(getInsertValuesSqlPart(pms, propertyPositions));
        return Tuples.of(insertSql.toString(), propertyPositions);
    }

    /**
     * Gets the insert values sql part.
     *
     * @param pms               the pms
     * @param propertyPositions the property positions
     * @return the insert values sql part
     */
    private static String getInsertValuesSqlPart(List<PropertyMapping> pms, Map<Integer, String> propertyPositions) {
        StringBuilder insertSqlPart = new StringBuilder(" (");
        int paramNum = 0;
        for (int i = 0; i < pms.size(); i++) {
            PropertyMapping pm = pms.get(i);
            if (pm.isPrimaryKey() && pm.getDefaultValue() != null && !"null".equalsIgnoreCase(pm.getDefaultValue())) {
                insertSqlPart.append(pm.getDefaultValue()).append(Chars.COMMA);
            } else {
                paramNum++;
                insertSqlPart.append(Chars.QUESTION).append(Chars.COMMA);
                if (pm.getParent() == null) {
                    propertyPositions.put(paramNum, pm.getPropertyName());
                } else {
                    propertyPositions.put(paramNum,
                            pm.getParent().getPropertyName() + Chars.DOT + pm.getPropertyName());
                }
            }
        }
        if (pms.size() > 0) {
            insertSqlPart.deleteCharAt(insertSqlPart.length() - 1);
        }
        insertSqlPart.append(")");
        return insertSqlPart.toString();
    }

    /**
     * Gets the update sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect      the dialect
     * @return the update sql and param positions
     */
    public static Tuple2<String, Map<Integer, String>> getUpdateSqlAndParamPositions(ClassMapping<?> classMapping,
            Dialect dialect) {
        Map<Integer, String> propertyPositions = new LinkedHashMap<>();
        StringBuilder updateSql = new StringBuilder();
        updateSql.append(dialect.getKeywords().update()).append(Chars.SPACE)
                .append(dialect.wrapName(classMapping.getRepositoryName())).append(Chars.SPACE)
                .append(dialect.getKeywords().set()).append(Chars.SPACE);
        int columnNum = 0;

        List<PropertyMapping> pms = new ArrayList<>();
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (LangUtils.isEmpty(propertyMapping.getPropertyMappings())) {
                if (propertyMapping.isPrimaryKey()) {
                    pms.add(propertyMapping);
                } else if (propertyMapping.isUpdatable()) {
                    updateSql.append(dialect.wrapName(propertyMapping.getRepositoryFieldName())).append(" = ?, ");
                    columnNum++;
                    propertyPositions.put(columnNum, propertyMapping.getPropertyName());
                }
            } else {
                for (PropertyMapping subPropertyMapping : propertyMapping.getPropertyMappings()) {
                    if (subPropertyMapping.isPrimaryKey()) {
                        pms.add(subPropertyMapping);
                    } else if (propertyMapping.isUpdatable()) {
                        updateSql.append(dialect.wrapName(subPropertyMapping.getRepositoryFieldName()))
                                .append(" = ?, ");
                        columnNum++;
                        propertyPositions.put(columnNum,
                                propertyMapping.getPropertyName() + Chars.DOT + subPropertyMapping.getPropertyName());
                    }
                }
            }
        }
        if (columnNum > 0) {
            updateSql.deleteCharAt(updateSql.length() - 1).deleteCharAt(updateSql.length() - 1);
        }
        int pkNum = 0;
        updateSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE);
        for (PropertyMapping pm : pms) {
            if (pkNum > 0) {
                updateSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
            }
            updateSql.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
            pkNum++;
            if (pm.getParent() == null) {
                propertyPositions.put(columnNum + pkNum, pm.getPropertyName());
            } else {
                propertyPositions.put(columnNum + pkNum,
                        pm.getParent().getPropertyName() + Chars.DOT + pm.getPropertyName());
            }
        }
        return Tuples.of(updateSql.toString().trim(), propertyPositions);
    }

    /**
     * Gets the delete sql and param positions.
     *
     * @param classMapping the class mapping
     * @param dialect      the dialect
     * @return the delete sql and param positions
     */
    public static Tuple2<String, Map<Integer, String>> getDeleteSqlAndParamPositions(ClassMapping<?> classMapping,
            Dialect dialect) {
        Map<Integer, String> propertyPositions = new LinkedHashMap<>();
        StringBuilder deleteSql = new StringBuilder();
        deleteSql.append(dialect.getKeywords().delete()).append(Chars.SPACE).append(dialect.getKeywords().from())
                .append(Chars.SPACE).append(dialect.wrapName(classMapping.getRepositoryName())).append(Chars.SPACE)
                .append(dialect.getKeywords().where()).append(Chars.SPACE);
        int columnNum = 0;
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (LangUtils.isEmpty(propertyMapping.getPropertyMappings())) {
                if (propertyMapping.isPrimaryKey()) {
                    if (columnNum > 0) {
                        deleteSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
                    }
                    deleteSql.append(dialect.wrapName(propertyMapping.getRepositoryFieldName())).append(" = ? ");
                    columnNum++;
                    propertyPositions.put(columnNum, propertyMapping.getPropertyName());
                }
            } else {
                for (PropertyMapping subPropertyMapping : propertyMapping.getPropertyMappings()) {
                    if (subPropertyMapping.isPrimaryKey()) {
                        if (columnNum > 0) {
                            deleteSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
                        }
                        deleteSql.append(dialect.wrapName(subPropertyMapping.getRepositoryFieldName())).append(" = ? ");
                        columnNum++;
                        propertyPositions.put(columnNum,
                                propertyMapping.getPropertyName() + "." + subPropertyMapping.getPropertyName());
                    }
                }
            }
        }
        return Tuples.of(deleteSql.toString().trim(), propertyPositions);
    }

    /**
     * Gets the merge sql and param positions.
     *
     * @param <T>          the generic type
     * @param entity       the entity
     * @param classMapping the class mapping
     * @param onlyNull     the only null
     * @param dialect      the dialect
     * @return the merge sql and param positions
     */
    public static <T> Tuple2<String, Map<Integer, String>> getMergeSqlAndParamPositions(T entity,
            ClassMapping<?> classMapping, boolean onlyNull, Dialect dialect) {
        LinkedHashMap<Integer, String> propertyPositions = new LinkedHashMap<>();
        StringBuilder updateSql = new StringBuilder();
        updateSql.append(dialect.getKeywords().update()).append(Chars.SPACE)
                .append(dialect.wrapName(classMapping.getRepositoryName())).append(Chars.SPACE)
                .append(dialect.getKeywords().set()).append(Chars.SPACE);
        int columnNum = 0;
        List<PropertyMapping> pkms = new ArrayList<>();
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().isEmpty()) {
                // 如果为空忽略 ignore when null
                if (checkNullOrEmpty(entity, propertyMapping, onlyNull)) {
                    continue;
                }
                if (propertyMapping.isPrimaryKey()) {
                    pkms.add(propertyMapping);
                } else if (propertyMapping.isUpdatable()) {
                    columnNum = set(entity, propertyMapping, updateSql, propertyPositions, columnNum, dialect);
                }
            } else {
                for (PropertyMapping subPropertyMapping : propertyMapping.getPropertyMappings()) {
                    if (checkNullOrEmpty(entity, subPropertyMapping, onlyNull)) {
                        continue;
                    }
                    if (subPropertyMapping.isPrimaryKey()) {
                        pkms.add(subPropertyMapping);
                    } else if (propertyMapping.isUpdatable()) {
                        columnNum = set(entity, subPropertyMapping, updateSql, propertyPositions, columnNum, dialect);
                    }
                }
            }
        }
        if (columnNum > 0) {
            updateSql.deleteCharAt(updateSql.length() - 1);
        }
        int pkNum = 0;
        updateSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE);
        for (PropertyMapping pm : pkms) {
            if (pkNum > 0) {
                updateSql.append(dialect.getKeywords().and()).append(Chars.SPACE);
            }
            updateSql.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
            pkNum++;
            propertyPositions.put(columnNum + pkNum, ClassMappingUtils.getPropertyAliasName(pm));
        }
        return Tuples.of(updateSql.toString().trim(), propertyPositions);
    }

    /**
     * Check null or empty.
     *
     * @param <T>             the generic type
     * @param entity          the entity
     * @param propertyMapping the property mapping
     * @param onlyNull        the only null
     * @return true, if successful
     */
    private static <T> boolean checkNullOrEmpty(T entity, PropertyMapping propertyMapping, boolean onlyNull) {
        String pn = ClassMappingUtils.getPropertyAliasName(propertyMapping);
        if (onlyNull) {
            return BeanUtils.getProperty(entity, pn) == null;
        } else {
            return LangUtils.isEmpty(BeanUtils.getProperty(entity, pn));
        }
    }

    /**
     * Sets the.
     *
     * @param <T>               the generic type
     * @param entity            the entity
     * @param propertyMapping   the property mapping
     * @param updateSql         the update sql
     * @param propertyPositions the property positions
     * @param columnNum         the column num
     * @param dialect           the dialect
     * @return the int
     */
    private static <T> int set(T entity, PropertyMapping propertyMapping, StringBuilder updateSql,
            LinkedHashMap<Integer, String> propertyPositions, int columnNum, Dialect dialect) {
        BuilderUtils.link(updateSql, dialect.wrapName(propertyMapping.getRepositoryFieldName()), " = ?,");
        columnNum++;
        propertyPositions.put(columnNum, ClassMappingUtils.getPropertyAliasName(propertyMapping));
        return columnNum;
    }

    /**
     * Gets the select by id sql.
     *
     * @param classMapping the class mapping
     * @param dialect      the dialect
     * @return the select by id sql
     */
    public static String getSelectByIdSql(ClassMapping<?> classMapping, Dialect dialect) {
        return getSelectByIdSql(classMapping, null, dialect);
    }

    /**
     * Gets the select by id sql.
     *
     * @param classMapping the class mapping
     * @param alias        the alias
     * @param dialect      the dialect
     * @return the select by id sql
     */
    public static String getSelectByIdSql(ClassMapping<?> classMapping, String alias, Dialect dialect) {
        StringBuilder getSql = new StringBuilder();
        LinkedHashMap<Integer, String> propertyPositions = new LinkedHashMap<>();
        getSql.append(getSelectSql(classMapping, alias, dialect));
        List<PropertyMapping> pkPms = new ArrayList<>();
        StringBuilder condition = new StringBuilder();
        int columnNum = 0;
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (propertyMapping.getPropertyMappings().isEmpty()) {
                columnNum = setPk(condition, columnNum, propertyMapping, propertyPositions, pkPms, dialect);
            } else {
                for (PropertyMapping subPropertyMapping : propertyMapping.getPropertyMappings()) {
                    columnNum = setPk(condition, columnNum, subPropertyMapping, propertyPositions, pkPms, dialect);
                }
            }
        }
        BuilderUtils.link(getSql, dialect.getKeywords().where(), condition.toString());
        //        getSql.append(Chars.SPACE).append(dialect.getKeywords().where()).append(Chars.SPACE).append(condition);
        return getSql.toString();
    }

    /**
     * Sets the pk.
     *
     * @param condition         the condition
     * @param columnNum         the column num
     * @param pm                the pm
     * @param propertyPositions the property positions
     * @param pkPms             the pk pms
     * @param dialect           the dialect
     * @return the int
     */
    private static int setPk(StringBuilder condition, int columnNum, PropertyMapping pm,
            Map<Integer, String> propertyPositions, List<PropertyMapping> pkPms, Dialect dialect) {
        if (pm.isPrimaryKey()) {
            if (columnNum > 0) {
                condition.append(dialect.getKeywords().and()).append(Chars.SPACE);
            }
            condition.append(dialect.wrapName(pm.getRepositoryFieldName())).append(" = ? ");
            columnNum++;
            propertyPositions.put(columnNum, ClassMappingUtils.getPropertyAliasName(pm));
            // 设置主键值
            pkPms.add(pm);
        }
        return columnNum;
    }

    /**
     * Gets the select sql.
     *
     * @param classMapping the class mapping
     * @param dialect      the dialect
     * @return the select sql
     */
    public static String getSelectSql(ClassMapping<?> classMapping, Dialect dialect) {
        return getSelectSql(classMapping, null, dialect);
    }

    /**
     * Gets the select sql.
     *
     * @param classMapping the class mapping
     * @param alias        the alias
     * @param dialect      the dialect
     * @return the select sql
     */
    public static String getSelectSql(ClassMapping<?> classMapping, String alias, Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        BuilderUtils.link(selectSql, dialect.getKeywords().select(), getSelectColumnsSql(classMapping, alias, dialect),
                dialect.getKeywords().from(), dialect.wrapName(classMapping.getRepositoryName()));
        return selectSql.toString();
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping the class mapping
     * @param tableAlias   the table alias
     * @param dialect      the dialect
     * @return the select columns sql
     */
    public static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias, Dialect dialect) {
        return getSelectColumnsSql(classMapping, tableAlias, "", dialect);
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping       the class mapping
     * @param tableAlias         the table alias
     * @param prefixPropertyName the prefix property name
     * @param dialect            the dialect
     * @return the select columns sql
     */
    public static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias, String prefixPropertyName,
            Dialect dialect) {
        return getSelectColumnsSql(classMapping, tableAlias, prefixPropertyName, dialect, null, new HashMap<>(0));
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping    the class mapping
     * @param tableAlias      the table alias
     * @param dialect         the dialect
     * @param mappingFactory  the mapping factory
     * @param fetchProperties the fetch properties
     * @return the select columns sql
     */
    public static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias, Dialect dialect,
            MappingFactory mappingFactory, Map<String, String> fetchProperties) {
        return getSelectColumnsSql(classMapping, tableAlias, null, dialect, mappingFactory, fetchProperties);
    }

    /**
     * Gets the select columns sql.
     *
     * @param classMapping       the class mapping
     * @param tableAlias         the table alias
     * @param prefixPropertyName the prefix property name
     * @param dialect            the dialect
     * @param mappingFactory     the mapping factory
     * @param fetchProperties    the fetch properties
     * @return the select columns sql
     */
    private static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias,
            String prefixPropertyName, Dialect dialect, MappingFactory mappingFactory,
            Map<String, String> fetchProperties) {
        StringBuilder selectSql = new StringBuilder();
        int columnNum = 0;
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (LangUtils.isEmpty(propertyMapping.getPropertyMappings())) {
                selectSql.append(
                        getSelectColumnsSql(classMapping, tableAlias, propertyMapping, prefixPropertyName, dialect));
                columnNum++;
            } else {
                boolean fetchAble = false;
                String fetchPropertyTableAlia = null;
                if (LangUtils.isNotEmpty(fetchProperties)) {
                    for (Entry<String, String> fetchPropertyEntry : fetchProperties.entrySet()) {
                        fetchPropertyTableAlia = fetchPropertyEntry.getValue();
                        if (propertyMapping.getPropertyName().equals(fetchPropertyEntry.getKey())) {
                            fetchAble = true;
                            break;
                        }
                    }
                }
                if (fetchAble) {
                    ClassMapping<?> pcm = mappingFactory.getClassMapping(propertyMapping.getPropertyType());
                    for (PropertyMapping pm : pcm.getPropertyMappings()) {
                        selectSql.append(getSelectColumnsSql(classMapping, fetchPropertyTableAlia, pm, propertyMapping,
                                dialect));
                        columnNum++;
                    }

                } else {
                    for (PropertyMapping pm : propertyMapping.getPropertyMappings()) {
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
     * @param classMapping       the class mapping
     * @param tableAlias         the table alias
     * @param propertyMapping    the property mapping
     * @param prefixPropertyName the prefix property name
     * @param dialect            the dialect
     * @return the select columns sql
     */
    private static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias,
            PropertyMapping propertyMapping, String prefixPropertyName, Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        if (StringUtils.isNotBlank(tableAlias)) {
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
     * @param classMapping          the class mapping
     * @param tableAlias            the table alias
     * @param propertyMapping       the property mapping
     * @param nestedPropertyMapping the nested property mapping
     * @param dialect               the dialect
     * @return the select columns sql
     */
    private static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias,
            PropertyMapping propertyMapping, PropertyMapping nestedPropertyMapping, Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        if (StringUtils.isNotBlank(tableAlias)) {
            selectSql.append(tableAlias).append(Chars.DOT);
        }
        selectSql.append(dialect.wrapName(propertyMapping.getRepositoryFieldName())).append(Chars.SPACE)
                .append(getNestedPropertyAliasName(propertyMapping, nestedPropertyMapping, dialect)).append(Chars.COMMA)
                .append(Chars.SPACE);
        return selectSql.toString();
    }

    /**
     * Gets the property alias name.
     *
     * @param propertyMapping the property mapping
     * @return the property alias name
     */
    public static String getPropertyAliasName(PropertyMapping propertyMapping) {
        return getNestedPropertyAliasName(propertyMapping, "");
    }

    /**
     * Gets the nested property alias name.
     *
     * @param propertyMapping       the property mapping
     * @param nestedPropertyMapping the nested property mapping
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(PropertyMapping propertyMapping,
            PropertyMapping nestedPropertyMapping) {
        String prefixPropertyName = null;
        if (nestedPropertyMapping != null) {
            prefixPropertyName = nestedPropertyMapping.getPropertyName();
        }
        return getNestedPropertyAliasName(propertyMapping, prefixPropertyName);
    }

    /**
     * Gets the nested property alias name.
     *
     * @param pm                 the pm
     * @param prefixPropertyName the prefix property name
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(PropertyMapping pm, String prefixPropertyName) {
        PropertyMapping propertyMapping = pm;
        if (!propertyMapping.getPropertyMappings().isEmpty()) {
            propertyMapping = propertyMapping.getPropertyMappings().get(0);
        }
        String propertyName = propertyMapping.getPropertyName();
        if (propertyMapping.getParent() != null) {
            propertyName = propertyMapping.getParent().getPropertyName() + Chars.DOT
                    + propertyMapping.getPropertyName();
        }
        if (LangUtils.isNotEmpty(prefixPropertyName)) {
            propertyName = prefixPropertyName + Chars.DOT + propertyName;
        }
        return propertyName;
    }

    /**
     * Gets the property alias name.
     *
     * @param propertyMapping the property mapping
     * @param dialect         the dialect
     * @return the property alias name
     */
    public static String getPropertyAliasName(PropertyMapping propertyMapping, Dialect dialect) {
        return dialect.wrapName(getPropertyAliasName(propertyMapping));
    }

    /**
     * Gets the nested property alias name.
     *
     * @param propertyMapping    the property mapping
     * @param prefixPropertyName the prefix property name
     * @param dialect            the dialect
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(PropertyMapping propertyMapping, String prefixPropertyName,
            Dialect dialect) {
        return dialect.wrapName(getNestedPropertyAliasName(propertyMapping, prefixPropertyName));
    }

    /**
     * Gets the nested property alias name.
     *
     * @param propertyMapping       the property mapping
     * @param nestedPropertyMapping the nested property mapping
     * @param dialect               the dialect
     * @return the nested property alias name
     */
    private static String getNestedPropertyAliasName(PropertyMapping propertyMapping,
            PropertyMapping nestedPropertyMapping, Dialect dialect) {
        return dialect.wrapName(getNestedPropertyAliasName(propertyMapping, nestedPropertyMapping));
    }

    /**
     * Gets the select columns.
     *
     * @param classMapping the class mapping
     * @return the select columns
     */
    public static Map<String, String> getSelectColumns(ClassMapping<?> classMapping) {
        Map<String, String> columns = new HashMap<>();
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (LangUtils.isEmpty(propertyMapping.getPropertyMappings())) {
                columns.put(propertyMapping.getRepositoryFieldName(), propertyMapping.getPropertyName());
            } else {
                for (PropertyMapping pm : propertyMapping.getPropertyMappings()) {
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
     * @param name         property name or column name
     * @param classMapping classMapping
     * @return Tuple&lt;columnName, propertyName&gt;
     */
    public static Tuple2<String, String> getColumnAndPropertyName(String name, ClassMapping<?> classMapping) {
        PropertyMapping propertyMapping = null;
        if (classMapping != null && LangUtils.isNotEmpty(name)) {
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
     * @param name         property name or column name
     * @param classMapping classMapping
     * @return columnName
     */
    public static String getColumnName(String name, ClassMapping<?> classMapping) {
        if (classMapping != null && LangUtils.isNotEmpty(name)) {
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
     * @param name         the name
     * @param classMapping the class mapping
     * @return the simple mapping
     */
    private static PropertyMapping getSimpleMapping(String name, ClassMapping<?> classMapping) {
        PropertyMapping pm = classMapping.getPropertyMapping(name);
        return pm;
    }

    /**
     * Gets the simple column name.
     *
     * @param name         the name
     * @param classMapping the class mapping
     * @return the simple column name
     */
    private static String getSimpleColumnName(String name, ClassMapping<?> classMapping) {
        PropertyMapping pm = getSimpleMapping(name, classMapping);
        if (pm != null) {
            return pm.getRepositoryFieldName();
        }
        return name;
    }

    /**
     * Gets the nested mapping.
     *
     * @param names        the names
     * @param classMapping the class mapping
     * @return the nested mapping
     */
    private static PropertyMapping getNestedMapping(String[] names, ClassMapping<?> classMapping) {
        PropertyMapping pm = null;
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
     * @param names        the names
     * @param classMapping the class mapping
     * @return the nested column name
     */
    private static String getNestedColumnName(String[] names, ClassMapping<?> classMapping) {
        PropertyMapping pm = getNestedMapping(names, classMapping);
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
     * @param names        property name or column name array
     * @return columnNames
     */
    public static String[] getColumnNames(ClassMapping<?> classMapping, String... names) {
        if (classMapping != null) {
            List<String> newNames = new ArrayList<>();
            for (String name : names) {
                newNames.add(getColumnName(name, classMapping));
            }
            return newNames.toArray(new String[] {});
            // return CollectionUtils.toArray(newNames, String.class);
        }
        return names;
    }

    /**
     * 根据传入name获取对应的columnName array.
     *
     * @param classMapping classMapping
     * @param names        property name or column name collection
     * @return columnNames
     */
    public static String[] getColumnNames(ClassMapping<?> classMapping, Collection<String> names) {
        if (names == null) {
            names = new ArrayList<>();
        }
        return getColumnNames(classMapping, names.toArray(new String[] {}));
    }

}
