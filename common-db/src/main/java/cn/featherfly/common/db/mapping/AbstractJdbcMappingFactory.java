
package cn.featherfly.common.db.mapping;

import java.math.BigDecimal;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.bean.BeanProperty;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.jpa.ColumnDefault;
import cn.featherfly.common.db.jpa.Comment;
import cn.featherfly.common.db.mapping.operator.BasicOperators;
import cn.featherfly.common.db.mapping.operator.DefaultTypesSqlTypeOperator;
import cn.featherfly.common.db.mapping.operator.EnumSqlTypeOperator;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.SystemPropertyUtils;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.ClassNameJpaConversion;
import cn.featherfly.common.repository.mapping.ClassNameUnderscoreConversion;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;
import cn.featherfly.common.repository.mapping.PropertyNameJpaConversion;
import cn.featherfly.common.repository.mapping.PropertyNameUnderscoreConversion;

/**
 * <p>
 * AbstractMappingFactory
 * </p>
 * .
 *
 * @author zhongj
 */
public abstract class AbstractJdbcMappingFactory implements JdbcMappingFactory {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /** The mapped types. */
    protected final Map<Class<?>, JdbcClassMapping<?>> mappedTypes = new HashMap<>();

    /** The metadata. */
    protected DatabaseMetadata metadata;

    /** The dialect. */
    protected Dialect dialect;

    /** The class name conversions. */
    protected List<ClassNameConversion> classNameConversions = new ArrayList<>();

    /** The property name conversions. */
    protected List<PropertyNameConversion> propertyNameConversions = new ArrayList<>();

    /** The sql type mapping manager. */
    protected SqlTypeMappingManager sqlTypeMappingManager;

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata the metadata
     * @param dialect  the dialect
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect) {
        this(metadata, dialect, null);
    }

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata              the metadata
     * @param dialect               the dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        this(metadata, dialect, sqlTypeMappingManager, null, null);
    }

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata                the metadata
     * @param dialect                 the dialect
     * @param classNameConversions    the class name conversions
     * @param propertyNameConversions the property name conversions
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        this(metadata, dialect, new SqlTypeMappingManager(), classNameConversions, propertyNameConversions);
    }

    /**
     * Instantiates a new abstract mapping factory.
     *
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param sqlTypeMappingManager   the sql type mapping manager
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    protected AbstractJdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
            List<PropertyNameConversion> propertyNameConversions) {
        super();
        this.metadata = metadata;
        this.dialect = dialect;

        if (Lang.isEmpty(classNameConversions)) {
            this.classNameConversions.add(new ClassNameJpaConversion());
            this.classNameConversions.add(new ClassNameUnderscoreConversion());
        } else {
            this.classNameConversions.addAll(classNameConversions);
        }

        if (Lang.isEmpty(propertyNameConversions)) {
            this.propertyNameConversions.add(new PropertyNameJpaConversion());
            this.propertyNameConversions.add(new PropertyNameUnderscoreConversion());
        } else {
            this.propertyNameConversions.addAll(propertyNameConversions);
        }
        if (sqlTypeMappingManager == null) {
            this.sqlTypeMappingManager = new SqlTypeMappingManager();
        } else {
            this.sqlTypeMappingManager = sqlTypeMappingManager;
        }
    }

    /**
     * Sets the column mapping.
     *
     * @param mapping      the mapping
     * @param beanProperty the bean property
     */
    protected void setColumnMapping(JdbcPropertyMapping mapping, BeanProperty<?, ?> beanProperty) {
        boolean isPk = beanProperty.hasAnnotation(Id.class);
        Column column = beanProperty.getAnnotation(Column.class);
        if (column != null) {
            mapping.setSize(
                    ClassUtils.isParent(Number.class, beanProperty.getType()) ? column.precision() : column.length());
            mapping.setDecimalDigits(column.scale());
            mapping.setInsertable(column.insertable());
            mapping.setUpdatable(column.updatable());
            mapping.setUnique(column.unique());
            mapping.setNullable(column.nullable());
        } else {
            // @Column length的默认值
            if (beanProperty.getType() == String.class) {
                mapping.setSize(255);
            } else if (beanProperty.getType() == Double.class || beanProperty.getType() == Double.TYPE
                    || beanProperty.getType() == Float.class || beanProperty.getType() == Float.TYPE
                    || beanProperty.getType() == BigDecimal.class) {
                mapping.setSize(12);
                mapping.setDecimalDigits(2);
            }
        }
        if (mapping.getSize() == 0) {
            SQLType sqlType = sqlTypeMappingManager.getSqlType(beanProperty.getType());
            mapping.setSize(dialect.getDefaultSize(sqlType));
        }
        ColumnDefault columnDefault = beanProperty.getAnnotation(ColumnDefault.class);
        if (columnDefault != null) {
            mapping.setDefaultValue(columnDefault.value());
        }
        Comment comment = beanProperty.getAnnotation(Comment.class);
        if (comment != null) {
            mapping.setRemark(comment.value());
        }
        if (beanProperty.getType().isEnum()) {
            if (!sqlTypeMappingManager.isEnumWithOrdinal()) {
                mapping.setSize(dialect.getDefaultSize(sqlTypeMappingManager.getEnumOrdinalType()));
            }
        }

        if (isPk) {
            mapping.setNullable(false);
            GeneratedValue generatedValue = beanProperty.getAnnotation(GeneratedValue.class);
            if (generatedValue != null) {
                if (generatedValue.strategy() == GenerationType.IDENTITY
                        || generatedValue.strategy() == GenerationType.AUTO) {
                    mapping.setAutoincrement(true);
                } else {
                    // TODO 其他实现，后续慢慢添加-_-
                    throw new JdbcMappingException("只实现了IDENTITY, AUTO时使用数据库的自增长的策略");
                }
            } else {
                mapping.setAutoincrement(true);
            }
        }
    }

    protected void setJavaSqlTypeMapper(JdbcPropertyMapping mapping, BeanProperty<?, ?> beanProperty) {
        JavaSqlTypeMapper<?> mapper = sqlTypeMappingManager.getJavaSqlTypeMapper(beanProperty);
        if (mapper != null) {
            mapping.setJavaTypeSqlTypeOperator(mapper);
        } else {
            if (beanProperty.getClass().isEnum()) {
                @SuppressWarnings({ "rawtypes", "unchecked" })
                Class<? extends Enum<?>> t = (Class) beanProperty.getClass();
                mapping.setJavaTypeSqlTypeOperator(new EnumSqlTypeOperator<>(t));
            } else {
                // YUFEI_TODO 后续来优化打开检查，主要是现在父JdbcPropertyMapping（非具体映射）也调用了此方法，造成检查不通过
                //                mapping.setJavaTypeSqlTypeOperator(new DefaultTypesSqlTypeOperator<>(beanProperty.getType(), true));
                JavaTypeSqlTypeOperator<?> operator = BasicOperators.getOperator(beanProperty.getType());
                if (operator != null) {
                    mapping.setJavaTypeSqlTypeOperator(operator);
                } else {
                    mapping.setJavaTypeSqlTypeOperator(new DefaultTypesSqlTypeOperator<>(beanProperty.getType()));
                }
            }
        }
    }

    /**
     * Checks if is transient.
     *
     * @param beanProperty the bean property
     * @param logInfo      the log info
     * @return true, if is transient
     */
    protected boolean isTransient(BeanProperty<?, ?> beanProperty, StringBuilder logInfo) {
        boolean result = beanProperty.hasAnnotation(java.beans.Transient.class)
                || beanProperty.hasAnnotation(javax.persistence.Transient.class);
        if (result && logger.isDebugEnabled()) {
            logInfo.append(String.format("%s###\t%s is annotated with @Transient, ignore",
                    SystemPropertyUtils.getLineSeparator(), beanProperty.getName()));
        }
        return result;
    }

    /**
     * 检查tableMapping是否有问题
     *
     * @param tableMapping tableMapping
     */
    protected void checkTableMapping(Map<String, JdbcPropertyMapping> tableMapping) {
        Map<String, JdbcPropertyMapping> columns = new HashMap<>();
        for (JdbcPropertyMapping pm : tableMapping.values()) {
            if (Lang.isEmpty(pm.getPropertyMappings())) {
                if (pm.isInsertable()) {
                    //                    column.name.mapped = 列{0}已经映射{1},不能再映射{2}
                    if (columns.containsKey(pm.getRepositoryFieldName())) {
                        //                        throw new JdbcMappingException("duplicate mapping column name " + pm.getRepositoryFieldName());
                        JdbcPropertyMapping _pm = columns.get(pm.getRepositoryFieldName());
                        throw new JdbcMappingException("#column.name.mapped", new Object[] {
                                pm.getRepositoryFieldName(), _pm.getPropertyName(), pm.getPropertyName() });
                    }
                    columns.put(pm.getRepositoryFieldName(), pm);
                }
            } else {
                for (JdbcPropertyMapping subPm : pm.getPropertyMappings()) {
                    if (pm.isInsertable()) {
                        if (columns.containsKey(subPm.getRepositoryFieldName())) {
                            //                            throw new JdbcMappingException(
                            //                                    "duplicate mapping column name " + pm.getRepositoryFieldName());
                            JdbcPropertyMapping _pm = columns.get(subPm.getRepositoryFieldName());
                            throw new JdbcMappingException("#column.name.mapped", new Object[] {
                                    subPm.getRepositoryFieldName(), _pm.getPropertyName(), subPm.getPropertyName() });
                        }
                        columns.put(subPm.getRepositoryFieldName(), subPm);
                    }
                }
            }
        }
    }

    /**
     * 返回dialect.
     *
     * @return dialect
     */
    @Override
    public Dialect getDialect() {
        return dialect;
    }

    /**
     * 返回sqlTypeMappingManager.
     *
     * @return sqlTypeMappingManager
     */
    @Override
    public SqlTypeMappingManager getSqlTypeMappingManager() {
        return sqlTypeMappingManager;
    }

    /**
     * 返回classNameConversions.
     *
     * @return classNameConversions
     */
    public List<ClassNameConversion> getClassNameConversions() {
        return classNameConversions;
    }

    /**
     * 设置classNameConversions.
     *
     * @param classNameConversions classNameConversions
     */
    public void setClassNameConversions(List<ClassNameConversion> classNameConversions) {
        this.classNameConversions = classNameConversions;
    }

    /**
     * 返回propertyNameConversions.
     *
     * @return propertyNameConversions
     */
    public List<PropertyNameConversion> getPropertyNameConversions() {
        return propertyNameConversions;
    }

    /**
     * 设置propertyNameConversions.
     *
     * @param propertyNameConversions propertyNameConversions
     */
    public void setPropertyNameConversions(List<PropertyNameConversion> propertyNameConversions) {
        this.propertyNameConversions = propertyNameConversions;
    }

    /**
     * 返回metadata.
     *
     * @return metadata
     */
    @Override
    public DatabaseMetadata getMetadata() {
        return metadata;
    }
}
