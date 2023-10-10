
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 16:16:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.creator;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.db.dialect.OracleDialect;

/**
 * MysqlDialectURLCreator.
 *
 * @author zhongj
 */
public class OracleDialectURLCreator extends DialectURLCreator {

    /** The Constant PREFIXES. */
    public static final String[] PREFIXES = new String[] { "jdbc:oracle:", "jdbc:log4jdbc:oracle:" };

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
        return new OracleDialect();
    }
}
