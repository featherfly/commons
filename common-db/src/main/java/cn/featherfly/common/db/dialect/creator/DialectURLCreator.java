
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-10-10 16:16:10
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.creator;

import java.util.function.Function;

import cn.featherfly.common.db.dialect.Dialect;

/**
 * The Class DialectURLCreator.
 *
 * @author zhongj
 */
public abstract class DialectURLCreator implements Function<String, Dialect> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Dialect apply(String url) {
        for (String urlPrefix : getJdbcUrlPrefixes()) {
            if (url.startsWith(urlPrefix)) {
                return createDialect();
            }
        }
        return null;
    }

    /**
     * Gets the jdbc url prefixes.
     *
     * @return the jdbc url prefixes
     */
    protected abstract String[] getJdbcUrlPrefixes();

    /**
     * Creates the dialect.
     *
     * @return the dialect
     */
    protected abstract Dialect createDialect();
}
