package cn.featherfly.common.db.dialect;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.id.DoNothingIdGenerator;
import cn.featherfly.common.lang.ArrayUtils;
import cn.featherfly.common.operator.ComparisonOperator.MatchStrategy;
import cn.featherfly.common.repository.id.IdGenerator;

/**
 * 数据库方言的抽象类. 实现了一些通用内容.
 *
 * @author zhongj
 */
public abstract class AbstractDialect implements Dialect {

    /** The Constant DEFAULT_ID_GENERATOR. */
    protected static final IdGenerator DEFAULT_ID_GENERATOR = new DoNothingIdGenerator(true);

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private Keyworld keyworld;

    private Operator operator;

    /** for update的后置. */
    protected static final String UPDATE_STRING = " for update";

    /** The keywords uppercase. */
    private StringCase keywordCase = StringCase.NONE;

    /** The table and column name uppercase. */
    private StringCase tableAndColumnNameCase = StringCase.NONE;

    /**
     * Instantiates a new abstract dialect.
     */
    protected AbstractDialect() {
        keyworld = new Keyworld(this);
        operator = new Operator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringCase keywordsCase() {
        return keywordCase;
    }

    /**
     * set keywordCase value.
     *
     * @param keywordCase keywordCase
     */
    public void setKeywordCase(StringCase keywordCase) {
        this.keywordCase = keywordCase;
    }

    /**
     * set tableAndColumnNameCase value.
     *
     * @param tableAndColumnNameCase tableAndColumnNameCase
     */
    public void setTableAndColumnNameCase(StringCase tableAndColumnNameCase) {
        this.tableAndColumnNameCase = tableAndColumnNameCase;
    }

    /**
     * 返回tableAndColumnNameUppercase.
     *
     * @return StringConverter
     */
    @Override
    public StringCase tableAndColumnNameCase() {
        return tableAndColumnNameCase;
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
    public Operator getOperators() {
        return operator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKeywordLike(MatchStrategy matchStrategy) {
        if (matchStrategy == null) {
            matchStrategy = MatchStrategy.AUTO;
        }
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                return getKeywordLikeCaseInsensitive(false);
            case CASE_SENSITIVE:
                return getKeywordLikeCaseSensitive(false);
            default:
                return getKeyword(Keywords.LIKE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKeywordNotLike(MatchStrategy matchStrategy) {
        if (matchStrategy == null) {
            matchStrategy = MatchStrategy.AUTO;
        }
        switch (matchStrategy) {
            case CASE_INSENSITIVE:
                return getKeywordLikeCaseInsensitive(true);
            case CASE_SENSITIVE:
                return getKeywordLikeCaseSensitive(true);
            default:
                return getKeyword(Keywords.NOT) + " " + getKeyword(Keywords.LIKE);
        }
    }

    /**
     * Gets the keyword like case insensitive.
     *
     * @param reverse the reverse
     * @return the keyword like case insensitive
     */
    protected String getKeywordLikeCaseInsensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + Chars.SPACE + getKeyword(Keywords.LIKE);
        } else {
            return getKeyword(Keywords.LIKE);
        }
    }

    /**
     * Gets the keyword like case sensitive.
     *
     * @param reverse the reverse
     * @return the keyword like case sensitive
     */
    protected String getKeywordLikeCaseSensitive(boolean reverse) {
        if (reverse) {
            return getKeyword(Keywords.NOT) + Chars.SPACE + getKeyword(Keywords.LIKE);
        } else {
            return getKeyword(Keywords.LIKE);
        }
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
     * .
     *
     * @param sql sql
     * @return sql是否带有使用for update语法
     */
    protected boolean isForUpdate(String sql) {
        return sql.toLowerCase().endsWith(UPDATE_STRING);
    }

}