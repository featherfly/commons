
package cn.featherfly.common.db.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.speedment.common.tuple.Tuple2;
import com.speedment.common.tuple.Tuples;

import cn.featherfly.common.constant.Chars;
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
 *
 * @author zhongj
 */
public class ClassMappingUtils {
    public static String getSelectSql(ClassMapping<?> classMapping, Dialect dialect) {
        return getSelectSql(classMapping, null, dialect);
    }

    public static String getSelectSql(ClassMapping<?> classMapping, String alias, Dialect dialect) {
        StringBuilder selectSql = new StringBuilder();
        selectSql.append("select ");
        selectSql.append(getSelectColumnsSql(classMapping, alias, dialect));
        selectSql.append(" from ").append(classMapping.getRepositoryName());
        return selectSql.toString();
    }

    public static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias, Dialect dialect) {
        return getSelectColumnsSql(classMapping, tableAlias, "", dialect);
    }

    public static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias, String prefixPropertyName,
            Dialect dialect) {
        return getSelectColumnsSql(classMapping, tableAlias, prefixPropertyName, dialect, null, new HashMap<>(0));
    }

    public static String getSelectColumnsSql(ClassMapping<?> classMapping, String tableAlias, Dialect dialect,
            MappingFactory mappingFactory, Map<String, String> fetchProperties) {
        return getSelectColumnsSql(classMapping, tableAlias, null, dialect, mappingFactory, fetchProperties);
    }

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

    public static String getPropertyAliasName(PropertyMapping propertyMapping) {
        return getNestedPropertyAliasName(propertyMapping, "");
    }

    private static String getNestedPropertyAliasName(PropertyMapping propertyMapping,
            PropertyMapping nestedPropertyMapping) {
        String prefixPropertyName = null;
        if (nestedPropertyMapping != null) {
            prefixPropertyName = nestedPropertyMapping.getPropertyName();
        }
        return getNestedPropertyAliasName(propertyMapping, prefixPropertyName);
    }

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

    public static String getPropertyAliasName(PropertyMapping propertyMapping, Dialect dialect) {
        return dialect.wrapName(getPropertyAliasName(propertyMapping));
    }

    private static String getNestedPropertyAliasName(PropertyMapping propertyMapping, String prefixPropertyName,
            Dialect dialect) {
        return dialect.wrapName(getNestedPropertyAliasName(propertyMapping, prefixPropertyName));
    }

    private static String getNestedPropertyAliasName(PropertyMapping propertyMapping,
            PropertyMapping nestedPropertyMapping, Dialect dialect) {
        return dialect.wrapName(getNestedPropertyAliasName(propertyMapping, nestedPropertyMapping));
    }

    public static Map<String, String> getSelectColumns(ClassMapping<?> classMapping) {
        Map<String, String> columns = new HashMap<>();
        for (PropertyMapping propertyMapping : classMapping.getPropertyMappings()) {
            if (LangUtils.isEmpty(propertyMapping.getPropertyMappings())) {
                columns.put(propertyMapping.getRepositoryFieldName(), propertyMapping.getPropertyName());
            } else {
                for (PropertyMapping pm : propertyMapping.getPropertyMappings()) {
                    columns.put(pm.getRepositoryFieldName(),
                            propertyMapping.getPropertyName() + "." + pm.getPropertyName());
                }
            }
        }
        return columns;
    }

    /**
     * 根据传入name获取对应的columnName,propertyName
     *
     * @param name         property name or column name
     * @param classMapping classMapping
     * @return Tuple&lt;columnName, propertyName&gt;
     */
    public static Tuple2<String, String> getColumnAndPropertyName(String name, ClassMapping<?> classMapping) {
        PropertyMapping propertyMapping = null;
        if (classMapping != null && LangUtils.isNotEmpty(name)) {
            if (name.contains(".")) {
                String[] names = name.split("\\.");
                propertyMapping = getNestedMapping(names, classMapping);
            } else {
                propertyMapping = getSimpleMapping(name, classMapping);
            }
        }
        if (propertyMapping != null) {
            return Tuples.of(propertyMapping.getRepositoryFieldName(), propertyMapping.getPropertyName());
        } else {
            return Tuples.of(name, "");
        }
    }

    /**
     * 根据传入name获取对应的columnName
     *
     * @param name         property name or column name
     * @param classMapping classMapping
     * @return columnName
     */
    public static String getColumnName(String name, ClassMapping<?> classMapping) {
        if (classMapping != null && LangUtils.isNotEmpty(name)) {
            if (name.contains(".")) {
                String[] names = name.split("\\.");
                return getNestedColumnName(names, classMapping);
            } else {
                return getSimpleColumnName(name, classMapping);
            }
        }
        return name;
    }

    private static PropertyMapping getSimpleMapping(String name, ClassMapping<?> classMapping) {
        PropertyMapping pm = classMapping.getPropertyMapping(name);
        return pm;
    }

    private static String getSimpleColumnName(String name, ClassMapping<?> classMapping) {
        PropertyMapping pm = getSimpleMapping(name, classMapping);
        if (pm != null) {
            return pm.getRepositoryFieldName();
        }
        return name;
    }

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

    private static String getNestedColumnName(String[] names, ClassMapping<?> classMapping) {
        PropertyMapping pm = getNestedMapping(names, classMapping);
        if (pm != null) {
            return pm.getRepositoryFieldName();
        } else {
            return null;
        }
    }

    /**
     * 根据传入name获取对应的columnName array
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
     * 根据传入name获取对应的columnName array
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
