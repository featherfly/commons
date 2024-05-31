package cn.featherfly.common.db.mapping;

import java.util.List;

import cn.featherfly.common.bean.PropertyAccessorFactory;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.repository.id.IdGeneratorManager;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;

/**
 * MapperFactory.
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
     * @param dialect dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param idGeneratorManager the id generator manager
     * @param propertyAccessorFactory the property accessor factory
     */
    public JdbcMappingFactoryImpl(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, IdGeneratorManager idGeneratorManager,
        PropertyAccessorFactory propertyAccessorFactory) {
        this(metadata, dialect, sqlTypeMappingManager, idGeneratorManager, null, null, propertyAccessorFactory);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param metadata DatabaseMetadata
     * @param dialect dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param idGeneratorManager the id generator manager
     * @param classNameConversions classNameConversions
     * @param propertyNameConversions propertyNameConversions
     * @param propertyAccessorFactory the property accessor factory
     */
    public JdbcMappingFactoryImpl(DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, IdGeneratorManager idGeneratorManager,
        List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions,
        PropertyAccessorFactory propertyAccessorFactory) {
        this(MappingMode.COMPATIBLE_MODE, metadata, dialect, sqlTypeMappingManager, idGeneratorManager,
            classNameConversions, propertyNameConversions, propertyAccessorFactory);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param mappingMode the mapping mode
     * @param metadata DatabaseMetadata
     * @param dialect dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param idGeneratorManager the id generator manager
     * @param propertyAccessorFactory the property accessor factory
     */
    public JdbcMappingFactoryImpl(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, IdGeneratorManager idGeneratorManager,
        PropertyAccessorFactory propertyAccessorFactory) {
        this(mappingMode, metadata, dialect, sqlTypeMappingManager, idGeneratorManager, null, null,
            propertyAccessorFactory);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param mappingMode the mapping mode
     * @param metadata DatabaseMetadata
     * @param dialect dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     * @param idGeneratorManager the id generator manager
     * @param classNameConversions classNameConversions
     * @param propertyNameConversions propertyNameConversions
     * @param propertyAccessorFactory the property accessor factory
     */
    public JdbcMappingFactoryImpl(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
        SqlTypeMappingManager sqlTypeMappingManager, IdGeneratorManager idGeneratorManager,
        List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions,
        PropertyAccessorFactory propertyAccessorFactory) {
        super();
        if (mappingMode == MappingMode.STRICT_MODE) {
            factory = new StrictJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager, idGeneratorManager,
                classNameConversions, propertyNameConversions, propertyAccessorFactory);
        } else {
            factory = new CompatibleJdbcMappingFactory(metadata, dialect, sqlTypeMappingManager, idGeneratorManager,
                classNameConversions, propertyNameConversions, propertyAccessorFactory);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> JdbcClassMapping<T> getClassMapping(Class<T> type) {
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
