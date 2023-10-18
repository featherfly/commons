
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 16:16:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.creator;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.exception.NotImplementedException;

/**
 * The Class HSQLDBDialectURLCreator.
 *
 * @author zhongj
 */
public class HSQLDBDialectURLCreator extends DialectURLCreator {

    /** The Constant PREFIXES. */
    public static final String[] PREFIXES = new String[] { "jdbc:hsqldb:", "jdbc:log4jdbc:hsqldb:" };

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
        throw new NotImplementedException();
    }
}
