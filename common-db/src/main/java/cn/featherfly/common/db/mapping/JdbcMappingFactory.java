package cn.featherfly.common.db.mapping;

import java.util.List;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.repository.mapping.ClassMapping;
import cn.featherfly.common.repository.mapping.ClassNameConversion;
import cn.featherfly.common.repository.mapping.MappingFactory;
import cn.featherfly.common.repository.mapping.PropertyNameConversion;

/**
 * <p>
 * MapperFactory
 * </p>
 * .
 *
 * @author zhongj
 */
public class JdbcMappingFactory implements MappingFactory {

    /**
     * The Enum MappingMode.
     */
    public enum MappingMode {

        /**
         * obj db mixed. 如果对象的属性没有明确的jpa注释表示映射，则使用数据库元数据反向映射对象属性.
         * 可能存在数据库列没有映射属性以及对象属性没有映射数据库列的情况.
         */
        OBJ_DB_MIXED,
        /**
         * The obj to db strict. 使用对象属性进行映射，如果对象属性映射的数据库列不存在，则抛出异常.
         * 如果有属性不需要映射，使用javax.persistence.Transient注解注释该属性
         */
        OBJ_TO_DB;
    }

    /** The factory. */
    private MappingFactory factory;

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param metadata DatabaseMetadata
     * @param dialect  dialect
     */
    public JdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect) {
        this(metadata, dialect, new SqlTypeMappingManager());
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param metadata              DatabaseMetadata
     * @param dialect               dialect
     * @param sqlTypeMappingManager the sql type mapping manager
     */
    public JdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect, SqlTypeMappingManager sqlTypeMappingManager) {
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
    public JdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect,
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
    public JdbcMappingFactory(DatabaseMetadata metadata, Dialect dialect, SqlTypeMappingManager sqlTypeMappingManager,
            List<ClassNameConversion> classNameConversions, List<PropertyNameConversion> propertyNameConversions) {
        this(MappingMode.OBJ_DB_MIXED, metadata, dialect, sqlTypeMappingManager, classNameConversions,
                propertyNameConversions);
    }

    /**
     * Instantiates a new jdbc mapping factory.
     *
     * @param mappingMode the mapping mode
     * @param metadata    DatabaseMetadata
     * @param dialect     dialect
     */
    public JdbcMappingFactory(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect) {
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
    public JdbcMappingFactory(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
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
    public JdbcMappingFactory(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
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
    public JdbcMappingFactory(MappingMode mappingMode, DatabaseMetadata metadata, Dialect dialect,
            SqlTypeMappingManager sqlTypeMappingManager, List<ClassNameConversion> classNameConversions,
            List<PropertyNameConversion> propertyNameConversions) {
        super();
        if (mappingMode == MappingMode.OBJ_TO_DB) {
            factory = new ObjectToDbMappingFactory(metadata, dialect, sqlTypeMappingManager, classNameConversions,
                    propertyNameConversions);
        } else {
            factory = new ObjectDbMixedMappingFactory(metadata, dialect, sqlTypeMappingManager, classNameConversions,
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
}
