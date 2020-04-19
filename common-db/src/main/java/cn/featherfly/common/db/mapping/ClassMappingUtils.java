
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
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.Keywords;
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
        BuilderUtils.link(selectSql, dialect.getKeyword(Keywords.SELECT),
                getSelectColumnsSql(classMapping, alias, dialect), dialect.getKeyword(Keywords.FROM),
                dialect.wrapName(classMapping.getRepositoryName()));
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
