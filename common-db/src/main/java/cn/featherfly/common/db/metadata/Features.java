
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2023-11-01 16:04:01
 * @Copyright: 2023 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.db.metadata;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.db.JdbcException;

/**
 * Features.
 *
 * @author zhongj
 */
public class Features {

    private Map<Integer, Boolean> supportsResultSetType = new HashMap<>();
    private Map<Integer, Boolean> supportsResultSetHoldability = new HashMap<>();
    private Map<String, Boolean> supportsResultSetConcurrency = new HashMap<>();

    private boolean supportsBatchUpdates;

    private boolean supportsSelectForUpdate;

    private boolean supportsLikeEscapeClause;

    private boolean supportsOuterJoins;

    private boolean supportsFullOuterJoins;

    private boolean supportsLimitedOuterJoins;

    private boolean supportsNamedParameters;

    private boolean supportsGetGeneratedKeys;

    private boolean supportsPositionedUpdate;

    private boolean supportsPositionedDelete;

    DatabaseMetaData metaData;

    public Features(DatabaseMetaData databaseMetaData) {
        reload(databaseMetaData);
    }

    public void reload(DatabaseMetaData databaseMetaData) {
        try {
            for (ResultSetType type : ResultSetType.values()) {
                supportsResultSetType.put(type.value(), databaseMetaData.supportsResultSetType(type.value()));

                for (ResultSetConcurrency concurrency : ResultSetConcurrency.values()) {
                    supportsResultSetConcurrency.put(generateKey(type.value(), concurrency.value()),
                            databaseMetaData.supportsResultSetConcurrency(type.value(), concurrency.value()));
                }
            }

            for (ResultSetHoldability holdability : ResultSetHoldability.values()) {
                supportsResultSetHoldability.put(holdability.value(),
                        databaseMetaData.supportsResultSetType(holdability.value()));
            }

            supportsBatchUpdates = databaseMetaData.supportsBatchUpdates();

            supportsSelectForUpdate = databaseMetaData.supportsSelectForUpdate();

            supportsLikeEscapeClause = databaseMetaData.supportsLikeEscapeClause();

            supportsOuterJoins = databaseMetaData.supportsOuterJoins();

            supportsFullOuterJoins = databaseMetaData.supportsFullOuterJoins();

            supportsLimitedOuterJoins = databaseMetaData.supportsLimitedOuterJoins();

            supportsNamedParameters = databaseMetaData.supportsNamedParameters();

            supportsGetGeneratedKeys = databaseMetaData.supportsGetGeneratedKeys();

            supportsPositionedUpdate = databaseMetaData.supportsPositionedUpdate();

            supportsPositionedDelete = databaseMetaData.supportsPositionedDelete();

        } catch (SQLException e) {
            throw new JdbcException(e);
        }
    }

    private String generateKey(int type, int concurrency) {
        return type + "." + concurrency;
    }

    public boolean supportsBatchUpdates() {
        return supportsBatchUpdates;
    }

    public boolean supportsSelectForUpdate() {
        return supportsSelectForUpdate;
    }

    public boolean supportsResultSetType(int type) {
        return supportsResultSetType.getOrDefault(type, false);
    }

    public boolean supportsResultSetType(ResultSetType type) {
        return supportsResultSetType(type.value());
    }

    public boolean supportsResultSetConcurrency(int type, int concurrency) {
        return supportsResultSetConcurrency.get(generateKey(type, concurrency));
    }

    public boolean supportsResultSetConcurrency(ResultSetType type, ResultSetConcurrency concurrency) {
        return supportsResultSetConcurrency(type.value(), concurrency.value());
    }

    public boolean supportsResultSetHoldability(int holdability) {
        return supportsResultSetHoldability.getOrDefault(holdability, false);
    }

    public boolean supportsResultSetHoldability(ResultSetHoldability holdability) {
        return supportsResultSetHoldability(holdability.value());
    }

    public boolean supportsLikeEscapeClause() {
        return supportsLikeEscapeClause;
    }

    public boolean supportsOuterJoins() {
        return supportsOuterJoins;
    }

    public boolean supportsFullOuterJoins() {
        return supportsFullOuterJoins;
    }

    public boolean supportsLimitedOuterJoins() {
        return supportsLimitedOuterJoins;
    }

    public boolean supportsNamedParameters() {
        return supportsNamedParameters;
    }

    public boolean supportsGetGeneratedKeys() {
        return supportsGetGeneratedKeys;
    }

    public boolean supportsPositionedUpdate() {
        return supportsPositionedUpdate;
    }

    public boolean supportsPositionedDelete() {
        return supportsPositionedDelete;
    }
}
