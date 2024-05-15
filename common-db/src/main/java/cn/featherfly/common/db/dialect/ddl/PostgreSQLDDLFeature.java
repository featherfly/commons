package cn.featherfly.common.db.dialect.ddl;

import java.sql.JDBCType;
import java.sql.SQLType;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.AbstractDDLFeature;
import cn.featherfly.common.db.dialect.DialectException;
import cn.featherfly.common.db.dialect.Keywords;
import cn.featherfly.common.db.dialect.PostgreSQLDialect;
import cn.featherfly.common.exception.UnsupportedException;
import cn.featherfly.common.lang.Lang;

/**
 * PostgreSQLDialect DDL feature.
 *
 * @author zhongj
 */
public class PostgreSQLDDLFeature extends AbstractDDLFeature<PostgreSQLDialect> {

    /**
     * Instantiates a new postgre SQLDDL feature.
     *
     * @param dialect the dialect
     */
    public PostgreSQLDDLFeature(PostgreSQLDialect dialect) {
        super(dialect);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createTable(Table table) {
        final StringBuilder comments = new StringBuilder();
        for (Column column : table.getColumns()) {
            if (Lang.isNotEmpty(column.getRemark())) {
                StringBuilder comment = new StringBuilder();
                String columnName = dialect.wrapName(table.getName()) + Chars.DOT + dialect.wrapName(column.getName());
                if (Lang.isNotEmpty(table.getSchema())) {
                    columnName = dialect.wrapName(table.getSchema()) + Chars.DOT + columnName;
                }
                BuilderUtils.link(comment, dialect.getKeyword(Keywords.COMMENT), dialect.getKeyword(Keywords.ON),
                        dialect.getKeyword(Keywords.COLUMN), columnName, dialect.getKeyword(Keywords.IS),
                        Chars.QM + column.getRemark() + Chars.QM);
                comment.append(Chars.SEMI).append(Chars.NEW_LINE);
                comments.append(comment);
            }
        }
        String result = super.createTable(table);
        if (comments.length() > 0) {
            comments.deleteCharAt(comments.length() - 1);
            result += Chars.SEMI + Chars.NEW_LINE + comments.toString();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String autoIncrement(Column column) {
        throw new UnsupportedException();
    }

    @Override
    protected String tableComment(Table table) {
        // COMMENT ON TABLE "p"."user4" IS 'user用户表';
        return Lang.isEmpty(table.getRemark()) ? ""
                : BuilderUtils.link(Chars.SEMI + Chars.NEW_LINE + dialect.getKeyword(Keywords.COMMENT),
                        dialect.getKeyword(Keywords.ON), dialect.getKeyword(Keywords.TABLE),
                        Lang.ifEmpty(table.getSchema(), () -> dialect.wrapName(table.getName()),
                                () -> dialect.wrapName(table.getSchema()) + Chars.DOT
                                        + dialect.wrapName(table.getName())),
                        dialect.getKeyword(Keywords.IS), Chars.QM + table.getRemark() + Chars.QM);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String primaryKey(Table table) {
        StringBuilder result = new StringBuilder(Chars.PAREN_L);
        for (Column column : table.getColumns()) {
            if (column.isPrimaryKey()) {
                result.append(dialect.wrapName(column.getName())).append(Chars.DOT);
            }
        }
        result.deleteCharAt(result.length() - 1);
        result.append(Chars.PAREN_R);
        return BuilderUtils.link(dialect.getKeyword(Keywords.CONSTRAINT),
                dialect.wrapName(table.getName() + "_" + "pkey"), dialect.getKeyword(Keywords.PRIMARY),
                dialect.getKeyword(Keywords.KEY), result.toString());
    }

    @Override
    protected String column(Column column) {
        // FIXME 注释要用单独语句，不能放在列语句中
        if (column.isAutoincrement()) {
            return BuilderUtils.link(dialect.wrapName(column.getName()), getSerial(column.getSqlType()),
                    columnNotNull(column));
        } else {
            return BuilderUtils.link(dialect.wrapName(column.getName()), columnType(column), columnNotNull(column),
                    defaultValue(column));
        }
    }

    private String getSerial(SQLType sqlType) {
        JDBCType type = JDBCType.valueOf(sqlType.getVendorTypeNumber());
        switch (type) {
            case SMALLINT:
                return "SERIAL2";
            case INTEGER:
                return "SERIAL4";
            case BIGINT:
                return "SERIAL8";
            default:
                throw new DialectException(
                        "serial only support for JDBCType.BIGINT, JDBCType.INTEGER, JDBCType.SMALLINT");
        }
    }

}
