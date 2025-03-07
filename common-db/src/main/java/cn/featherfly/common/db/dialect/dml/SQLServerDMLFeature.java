
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 02:57:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.dialect.dml;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.model.SqlElement;
import cn.featherfly.common.db.dialect.AbstractDMLFeature;
import cn.featherfly.common.db.dialect.DialectException;
import cn.featherfly.common.db.dialect.SQLServerDialect;
import cn.featherfly.common.exception.NotImplementedException;
import cn.featherfly.common.operator.ComparisonOperator;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.operator.DateFunction;

/**
 * SQLServer DML feature
 *
 * @author zhongj
 */
public class SQLServerDMLFeature extends AbstractDMLFeature<SQLServerDialect> {

    /**
     * Instantiates a new SQL server DML feature.
     *
     * @param dialect the dialect
     */
    public SQLServerDMLFeature(SQLServerDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String upsertBatch(String tableName, String pkColumnName, String[] columnNames, String[] uniqueColumns,
        int insertAmount, boolean autoIncrement) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String inOrNotInExpression(boolean isIn, String name, Object values, MatchStrategy matchStrategy) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, Object values,
        MatchStrategy matchStrategy) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String betweenOrNotBetweenExpression(boolean isBetween, String name, SqlElement min, SqlElement max,
        MatchStrategy matchStrategy) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String compareExpression0(ComparisonOperator comparisonOperator, String name, Object values,
        MatchStrategy matchStrategy) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    @Override
    protected String compareExpression0(ComparisonOperator comparisonOperator, String name, SqlElement values,
        MatchStrategy matchStrategy) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String preparePrimaryKeyColumnForInsert(String tableName, String columnName, boolean autoIncrement) {
        return Chars.QUESTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String column(boolean distinct, String tableAlias, String columnName,
        String columnAlias, DateFunction function, Object... argus) {
        // NOIMPL 未实现
        throw new DialectException("未实现的 function" + function.getClass().getName());
    }

}
