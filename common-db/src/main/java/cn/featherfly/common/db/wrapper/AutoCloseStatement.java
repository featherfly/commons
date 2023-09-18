
/*
 * All rights Reserved, Designed By zhongj
 * @Title: AutoClosePreparedStatement.java
 * @Description: AutoClosePreparedStatement
 * @author: zhongj
 * @date: 2023-09-18 15:43:18
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.wrapper;

import java.sql.Statement;

/**
 * The Class AutoCloseStatement.
 *
 * @author zhongj
 */
public class AutoCloseStatement extends ResultSetCreatable<Statement> implements Statement {

    /**
     * Instantiates a new auto close statement.
     *
     * @param stat the stat
     */
    public AutoCloseStatement(Statement stat) {
        super(stat);
    }
}
