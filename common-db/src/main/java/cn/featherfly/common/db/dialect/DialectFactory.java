
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 15:53:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

/**
 * DialectFactory.
 *
 * @author zhongj
 */
public interface DialectFactory {

    /**
     * Creates Dialect.
     *
     * @param  dataSource the data source
     * @return            the dialect or null.
     */
    Dialect create(DataSource dataSource);

    /**
     * Creates Dialect. The caller closes connection by himself.
     *
     * @param  connection the connection.
     * @return            the dialect or null.
     */
    Dialect create(Connection connection);

    /**
     * Creates Dialect.
     *
     * @param  metadata the metadata
     * @return          the dialect or null.
     */
    Dialect create(DatabaseMetaData metadata);
}
