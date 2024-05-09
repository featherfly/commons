
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 16:16:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.creator;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.PostgreSQLDialect;

/**
 * The Class PostgreSQLDialectURLCreator.
 *
 * @author zhongj
 */
public class PostgreSQLDialectURLCreator extends DialectURLCreator {

    /** The Constant PREFIXES. */
    protected static final String[] PREFIXES = new String[] { "jdbc:postgresql:", "jdbc:log4jdbc:postgresql:" };

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getJdbcUrlPrefixes() {
        return PREFIXES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dialect createDialect() {
        return new PostgreSQLDialect();
    }
}
