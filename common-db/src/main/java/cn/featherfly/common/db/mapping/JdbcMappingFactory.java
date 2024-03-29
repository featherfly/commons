
package cn.featherfly.common.db.mapping;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.repository.mapping.MappingFactory;

/**
 * JdbcMappingFactory .
 *
 * @author zhongj
 */
public interface JdbcMappingFactory extends MappingFactory<JdbcPropertyMapping> {

    /**
     * The Enum MappingMode.
     */
    public enum MappingMode {
        /**
         * compatible mode. 如果对象的属性没有明确的jpa注释表示映射，则使用数据库元数据反向映射对象属性.
         * 可能存在数据库列没有映射属性以及对象属性没有映射数据库列的情况.
         */
        COMPATIBLE_MODE,
        /**
         * strict mode. 使用对象属性进行映射，如果对象属性映射的数据库列不存在，则抛出异常.
         * 如果有属性不需要映射，使用javax.persistence.Transient注解注释该属性.
         */
        STRICT_MODE;
    }

    /**
     * get dialect.
     *
     * @return dialect
     */
    Dialect getDialect();

    /**
     * get metadata.
     *
     * @return metadata
     */
    DatabaseMetadata getMetadata();

    /**
     * Gets the manager.
     *
     * @return the manager
     */
    SqlTypeMappingManager getSqlTypeMappingManager();

    /**
     * {@inheritDoc}
     */
    @Override
    <T> JdbcClassMapping<T> getClassMapping(Class<T> type);
}
