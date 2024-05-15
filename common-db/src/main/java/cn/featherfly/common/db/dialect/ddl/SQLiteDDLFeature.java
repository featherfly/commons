package cn.featherfly.common.db.dialect.ddl;

import java.sql.JDBCType;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.Column;
import cn.featherfly.common.db.Table;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.dialect.AbstractDDLFeature;
import cn.featherfly.common.db.dialect.SQLiteDialect;
import cn.featherfly.common.exception.NotImplementedException;
import cn.featherfly.common.lang.Lang;

/**
 * SQLite DDL feature.
 *
 * @author zhongj
 */
public class SQLiteDDLFeature extends AbstractDDLFeature<SQLiteDialect> {

    /**
     * Instantiates a new SQ lite DDL feature.
     *
     * @param dialect the dialect
     */
    public SQLiteDDLFeature(SQLiteDialect dialect) {
        super(dialect);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableAddColumn(String schema, String tableName, Column... columns) {
        // TODO 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, String... columnNames) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableDropColumn(String schema, String tableName, Column... columns) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String alterTableModifyColumn(String schema, String tableName, Column... columns) {
        // NOIMPL 未实现
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String tableColumns(Table table) {
        StringBuilder ddl = new StringBuilder();
        int pkNo = 0;
        for (Column column : table.getColumns()) {
            if (column.isPrimaryKey()) {
                pkNo++;
            }
        }
        boolean mulityPk = pkNo > 1;
        int i = 0;
        int size = table.getColumns().size();
        for (Column column : table.getColumns()) {
            i++;
            if (mulityPk) {
                BuilderUtils.link(ddl, column(column));
                ddl.append(Chars.COMMA);
            } else {
                BuilderUtils.link(ddl, dialect.wrapName(column.getName()), columnType(column), columnNotNull(column),
                        defaultValue(column), autoIncrement(column));
                if (i < size) {
                    ddl.append(Chars.COMMA);
                }
            }
            BuilderUtils.link(ddl, columnComment(column));
            ddl.append(Chars.NEW_LINE);
        }
        if (mulityPk) {
            BuilderUtils.link(ddl, primaryKey(table));
        }
        return ddl.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String tableComment(Table table) {
        return Lang.isEmpty(table.getRemark()) ? "" : BuilderUtils.link("--", table.getRemark());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String columnComment(Column column) {
        return Lang.isEmpty(column.getRemark()) ? "" : BuilderUtils.link("--", column.getRemark());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String column(Column column) {
        return BuilderUtils.link(dialect.wrapName(column.getName()), columnType(column), columnNotNull(column),
                defaultValue(column), autoIncrement(column));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String autoIncrement(Column column) {
        if (column.isAutoincrement()) {
            return "PRIMARY KEY AUTOINCREMENT";
        } else {
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String columnType(Column column, String extra) {
        int size = column.getSize();
        int decimalDigits = column.getDecimalDigits();
        String result = dialect.getColumnTypeName(column.getSqlType());
        if (size > 0 && (result.equals(JDBCType.REAL.getName()) || result.equals(SQLiteDialect.TEXT_TYPE))) {
            result += Chars.PAREN_L + size;
            if (decimalDigits > 0) {
                result += Chars.COMMA + decimalDigits;
            }
            result += Chars.PAREN_R;
        }
        if (Lang.isNotEmpty(extra)) {
            result = BuilderUtils.link(result, extra);
        }
        return result;
    }

}
