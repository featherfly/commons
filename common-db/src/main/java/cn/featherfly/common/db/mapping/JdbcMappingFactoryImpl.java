package cn.featherfly.common.db.mapping;

import java.util.List;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;

/**
 * <p>
 * MapperFactory
 * </p>
 * .
 *
 * @author zhongj
 */
public class JdbcMappingFactoryImpl implements JdbcMappingFactory {

    /** The factory. */
    private AbstractJdbcMappingFactory factory;

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param metadata DatabaseMetadata
     * @param dialect  dialect
     */
    public JdbcMappingFactoryImpl(DatabaseMetadata metadata, Dialect dialect) {
        this(metadata, dialect, new SqlTypeMappingManager());
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param metadata              DatabaseMetadata
     * @param dialect               dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    public JdbcMappingFactoryImpl(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        this(metadata, dialect, sqlTypeMappingManager, null, null);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public JdbcMappingFactoryImpl(DatabaseMetadata metadata, Dialect dialect,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        this(metadata, dialect, new SqlTypeMappingManager(), classNameConversions, propertyNameConversions);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param sqlTypeMappingManager   the sql type mapping manager
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public JdbcMappingFactoryImpl(DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
            List<PropertyNameConversion> propertyNameConversions) {
        this(MappingMode.COMPATIBLE_MODE, metadata, dialect, sqlTypeMappingManager, classNameConversions,
                propertyNameConversions);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param mappingMode the mapping mode
     * @param metadata    DatabaseMetadata
     * @param dialect     dialect
     */
    public JdbcMappingFactoryImpl(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect) {
        this(mappingMode, metadata, dialect, new SqlTypeMappingManager());
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param mappingMode           the mapping mode
     * @param metadata              DatabaseMetadata
     * @param dialect               dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    public JdbcMappingFactoryImpl(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager) {
        this(mappingMode, metadata, dialect, sqlTypeMappingManager, null, null);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param mappingMode             the mapping mode
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public JdbcMappingFactoryImpl(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        this(mappingMode, metadata, dialect, new SqlTypeMappingManager(), classNameConversions,
                propertyNameConversions);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param mappingMode             the mapping mode
     * @param metadata                DatabaseMetadata
     * @param dialect                 dialect
     * @param sqlTypeMappingManager   the sql type mapping manager
     * @param classNameConversions    classNameConversions
     * @param propertyNameConversions propertyNameConversions
     */
    public JdbcMappingFactoryImpl(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
            List<PropertyNameConversion> propertyNameConversions) {
        super();
        if (mappingMode == MappingMode.STRICT_MODE) {
            factory = new StrictJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager, classNameConversions,
                    propertyNameConversions);
        } else {
            factory = new CompatibleJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager, classNameConversions,
                    propertyNameConversions);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> ClassMapping<T> getClassMapping(Class<T> type) {
        return factory.getClassMapping(type);
    }

    /**
     * 返回dialect.
     *
     * @return dialect
     */
    @Override
    public Dialect getDialect() {
        return factory.getDialect();
    }

    /**
     * 返回metadata.
     *
     * @return metadata
     */
    @Override
    public DatabaseMetadata getMetadata() {
        return factory.getMetadata();
    }

    /**
     * 返回sqlTypeMappingManager.
     *
     * @return sqlTypeMappingManager
     */
    @Override
    public SqlTypeMappingManager getSqlTypeMappingManager() {
        return factory.getSqlTypeMappingManager();
    }
}
