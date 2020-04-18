package cn.featherfly.common.db.dialect;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.BuilderUtils;
import cn.featherfly.common.db.metadata.Column;
import cn.featherfly.common.db.metadata.Table;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.LangUtils;

/**
 * <p>
 * 数据库方言的抽象类. 实现了一些通用内容.
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractDialect implements Dialect {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Keyworld keyworld;

    /**
     * for update的后置
     */
    protected static final String UPDATE_STRING = " for update";

    private boolean keywordsUppercase = true;

    private boolean tableAndColumnNameUppercase = false;

    /**
     */
    public AbstractDialect() {
        keyworld = new Keyworld(this);
    }

    /**
     * 返回keywordsUppercase
     *
     * @return keywordsUppercase
     */
    @Override
    public boolean isKeywordsUppercase() {
        return keywordsUppercase;
    }

    /**
     * 设置keywordsUppercase
     *
     * @param keywordsUppercase keywordsUppercase
     */
    public void setKeywordsUppercase(boolean keywordsUppercase) {
        this.keywordsUppercase = keywordsUppercase;
    }

    /**
     * 返回tableAndColumnNameUppercase
     *
     * @return tableAndColumnNameUppercase
     */
    @Override
    public boolean isTableAndColumnNameUppercase() {
        return tableAndColumnNameUppercase;
    }

    /**
     * 设置tableAndColumnNameUppercase
     *
     * @param tableAndColumnNameUppercase tableAndColumnNameUppercase
     */
    public void setTableAndColumnNameUppercase(boolean tableAndColumnNameUppercase) {
        this.tableAndColumnNameUppercase = tableAndColumnNameUppercase;
    }

    /**
     * Builds the create table sql.
     *
     * @param dataBaseName the data base name
     * @param table        the table
     * @return the string
     */
    @Override
    public String buildCreateTableSql(String dataBaseName, Table table) {
        boolean formatSql = true;
        AssertIllegalArgument.isNotEmpty(table, "table");
        StringBuilder sql = new StringBuilder();
        String tableName = LangUtils.isEmpty(dataBaseName) ? wrapName(table.getName())
                : wrapName(dataBaseName) + Chars.DOT + wrapName(table.getName());
        BuilderUtils.link(sql, getKeyword(Keywords.CREATE), getKeyword(Keywords.TABLE), tableName, Chars.PAREN_L);
        if (formatSql) {
            sql.append(Chars.NEW_LINE);
        }

        for (Column column : table.getColumns()) {
            BuilderUtils.link(sql, wrapName(column.getName()), getColumnTypeDDL(column), getColumnNotNull(column),
                    getDefaultValue(column), getAutoIncrement(column), getColumnComment(column));
            sql.append(Chars.COMMA);
            if (formatSql) {
                sql.append(Chars.NEW_LINE);
            }
        }
        BuilderUtils.link(sql, getPrimaryKeyDDL(table));
        if (formatSql) {
            sql.append(Chars.NEW_LINE);
        }
        BuilderUtils.link(sql, Chars.PAREN_R, LangUtils.isEmpty(table.getRemark()) ? ""
                : BuilderUtils.link(getKeyword(Keywords.COMMENT), "'" + table.getRemark() + "'"));
        return sql.toString();
    }

    protected abstract String getPrimaryKeyDDL(Table table);

    protected String getDefaultValue(Column column) {
        if (LangUtils.isEmpty(column.getDefaultValue())) {
            return "";
        } else {
            return BuilderUtils.link(getKeyword(Keywords.DEFAULT), "'" + column.getDefaultValue() + "'");
        }
    }

    protected String getAutoIncrement(Column column) {
        return "";
    }

    protected String getColumnComment(Column column) {
        return LangUtils.isEmpty(column.getRemark()) ? ""
                : BuilderUtils.link(getKeyword(Keywords.COMMENT), "'" + column.getRemark() + "'");
    }

    protected String getColumnNotNull(Column column) {
        if (column.isNullable()) {
            return "";
        } else {
            return BuilderUtils.link(getKeyword(Keywords.NOT), getKeyword(Keywords.NULL));
        }
    }

    protected String getColumnTypeDDL(Column column) {
        return getColumnTypeDDL(column, null);
    }

    protected String getColumnTypeDDL(Column column, String extra) {
        int size = column.getSize();
        int decimalDigits = column.getDecimalDigits();
        String result = column.getSqlType().getName();
        if (size > 0) {
            result += Chars.PAREN_L + size;
            if (decimalDigits > 0) {
                result += Chars.DOT + decimalDigits;
            }
            result += Chars.PAREN_R;
        }
        if (LangUtils.isNotEmpty(extra)) {
            result = BuilderUtils.link(result, extra);
        }
        return result;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Keyworld getKeywords() {
        return keyworld;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getPaginationSqlParameter(Object[] params, int start, int limit) {
        Object[] pagingParams = null;
        if (start > 0) {
            logger.debug("start > 0 , use start {}", start);
            pagingParams = new Object[2];
            pagingParams[0] = Integer.valueOf(start);
        } else {
            logger.debug("start < 0 , don't use start");
            pagingParams = new Object[1];
        }
        if (limit > Chars.ZERO) {
            logger.debug("limit > 0 , use limit {}", limit);
        } else if (limit == Chars.ZERO) {
            logger.debug("limit = 0 , use default limit {}", DEFAULT_LIMIT);
            limit = DEFAULT_LIMIT;
        } else {
            logger.debug("limit < 0 , don't use limit");
            limit = Integer.MAX_VALUE;
        }
        pagingParams[pagingParams.length - 1] = limit;
        return (Object[]) ArrayUtils.concat(params, pagingParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getPaginationSqlParameter(Map<String, Object> params, int start, int limit) {
        if (start > 0) {
            logger.debug("start > 0 , use start {}", start);
            params.put(START_PARAM_NAME, start);
        } else {
            logger.debug("start < 0 , don't use start");
        }
        if (limit > Chars.ZERO) {
            logger.debug("limit > 0 , use limit {}", limit);
        } else if (limit == Chars.ZERO) {
            logger.debug("limit = 0 , use default limit {}", DEFAULT_LIMIT);
            limit = DEFAULT_LIMIT;
        } else {
            logger.debug("limit < 0 , don't use limit");
            limit = Integer.MAX_VALUE;
        }
        params.put(LIMIT_PARAM_NAME, limit);
        return params;
    }

    /**
     * <p>
     * 判断传入sql是否带有使用for update语法
     * </p>
     *
     * @param sql sql
     * @return sql是否带有使用for update语法
     */
    protected boolean isForUpdate(String sql) {
        return sql.toLowerCase().endsWith(UPDATE_STRING);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String valueToSql(Object value, int sqlType) {
        return convertValueToSql(value, sqlType);
    }

    /**
     * <p>
     * 转换值为字符串
     * </p>
     *
     * @param value   value
     * @param sqlType sqlType
     * @return 字符串
     */
    protected abstract String convertValueToSql(Object value, int sqlType);
}