/*
 *
 */

package cn.featherfly.common.db.dialect;

/**
 * Keywords .
 *
 * @author zhongj
 */
public enum Keywords {

    /**
     * The table.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    TABLE,

    /**
     * The column.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    COLUMN,

    /**
     * The view.
     * reserved word: PostgreSQL Oracle SQLServer.
     * keyword: MySQL SQLite.
     */
    VIEW,

    /**
     * The index.
     * reserved word: SQLServer Oracle.
     * keyword: PostgreSQL MySQL SQLite.
     */
    INDEX,

    /**
     * The select.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    SELECT,

    /**
     * The from.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    FROM,

    /**
     * The where.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    WHERE,

    /**
     * The insert.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    INSERT,

    /**
     * The into.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    INTO,

    /**
     * The values.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    VALUES,

    /**
     * The update.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    UPDATE,

    /**
     * The set.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    SET,

    /**
     * The delete.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    DELETE,

    /**
     * The join.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     * keyword: Oracle.
     */
    JOIN,

    /**
     * The on.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     * keyword: Oracle.
     */
    ON,

    /**
     * The inner.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     * keyword: Oracle.
     */
    INNER,

    /**
     * The left.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     * keyword: Oracle.
     */
    LEFT,

    /**
     * The right.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     * keyword: Oracle.
     */
    RIGHT,

    /**
     * The outer.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     * keyword: Oracle.
     */
    OUTER,

    /**
     * The full.
     * reserved word: PostgreSQL Oracle SQLServer.
     * keyword: MySQL SQLite.
     */
    FULL,

    /**
     * The corss.
     * reserved word: MySQL SQLite Oracle SQLServer.
     * keyword: PostgreSQL.
     */
    CORSS,

    /**
     * The create.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    CREATE,

    /**
     * The drop.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    DROP,

    /**
     * The after.
     * keyword: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    AFTER,

    /**
     * The add.
     * reserved word: Oracle SQLServer.
     * keyword: MySQL PostgreSQL SQLite.
     */
    ADD,

    /**
     * The modify.
     * reserved word: Oracle.
     * keyword: MySQL.
     */
    MODIFY,

    /**
     * The truncate.
     * keyword: MySQL SQLite PostgreSQL Oracle.
     * reserved word: SQLServer.
     */
    TRUNCATE,

    /**
     * The order.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    ORDER,

    /**
     * The by.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    BY,

    /**
     * The desc.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    DESC,

    /**
     * The asc.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    ASC,

    /**
     * The like.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    LIKE,

    /**
     * The in.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    IN,

    /**
     * The is.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    IS,

    /**
     * The null.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    NULL,

    /**
     * The not.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    NOT,

    /**
     * The between.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    BETWEEN,

    /**
     * The union.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    UNION,

    /**
     * The intersect.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    INTERSECT,

    /**
     * The except.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    EXCEPT,

    /**
     * The and.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    AND,

    /**
     * The or.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    OR,

    /**
     * The all.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    ALL,

    /**
     * The as.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    AS,

    /**
     * The count.
     * keyword: MySQL PostgreSQL SQLite.
     */
    COUNT,

    /**
     * The sum.
     * keyword: MySQL PostgreSQL SQLite.
     */
    SUM,

    /**
     * The max.
     * keyword: MySQL SQLite.
     */
    MAX,

    /**
     * The min.
     * keyword: MySQL SQLite.
     */
    MIN,

    /**
     * The avg.
     * keyword: MySQL.
     */
    AVG,

    /**
     * The distinct.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    DISTINCT,

    /**
     * The database.
     * reserved word: SQLServer.
     * keyword: MySQL SQLite.
     */
    DATABASE,

    /**
     * The alter.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    ALTER,

    /**
     * The if.
     * keyword: MySQL SQLite SQLServer.
     */
    IF,

    /**
     * The exists.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    EXISTS,

    /**
     * The comment.
     * keyword: MySQL Oracle.
     */
    COMMENT,

    /**
     * The primary.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     */
    PRIMARY,

    /**
     * The key.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     */
    KEY,

    /**
     * The constraint.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    CONSTRAINT,

    /**
     * The default.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    DEFAULT,

    /**
     * The cascade.
     * reserved word: MySQL PostgreSQL SQLite SQLServer.
     */
    CASCADE,

    /**
     * The schema.
     * reserved word: SQLServer.
     */
    SCHEMA,

    /**
     * The unique.
     * reserved word: MySQL PostgreSQL SQLite Oracle SQLServer.
     */
    UNIQUE,

    /**
     * The collate.
     * keyword: MySQL SQLite
     * reserved word: PostgreSQL SQLServer Oracle.
     */
    COLLATE,

    /**
     * The binary.
     * reserved word: MySQL PostgreSQL.
     */
    BINARY,

    /**
     * The duplicate.
     * kyeword: MySQL.
     */
    DUPLICATE,

    /**
     * The conflict.
     * keyword: PostgreSQL SQLite.
     */
    CONFLICT,

    /**
     * The do.
     * reserved word: MySQL PostgreSQL Oracle.
     * keyword: SQLite.
     */
    DO,
    /**
     * The excluded.
     * keyword: PostgreSQL (pseudo-table for ON CONFLICT DO UPDATE).
     */
    EXCLUDED;
}