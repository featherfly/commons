
package cn.featherfly.common.db.mapping;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.metadata.DatabaseMetadata;
import cn.featherfly.common.repository.mapping.MappingFactory;

/**
 * <p>
 * JdbcMappingFactory
 * </p>
 * .
 *
 * @author zhongj
 */
public interface JdbcMappingFactory extends MappingFactory {

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
}
