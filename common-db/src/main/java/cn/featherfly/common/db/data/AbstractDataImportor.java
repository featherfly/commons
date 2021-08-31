
package cn.featherfly.common.db.data;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * 抽象导入器
 * </p>
 * .
 *
 * @author zhongj
 */
public abstract class AbstractDataImportor extends AbstractDataImpExp implements DataImportor {

    /**
     * Instantiates a new abstract data importor.
     *
     * @param dialect the dialect
     */
    protected AbstractDataImportor(Dialect dialect) {
        super(dialect);
    }

    /**
     * <p>
     * 添加预执行sql
     * </p>
     * .
     *
     * @param prepareSql prepareSql
     */
    public void addPrepareSql(String prepareSql) {
        if (Lang.isNotEmpty(prepareSql)) {
            prepareSqls.add(prepareSql);
        }
    }

    /**
     * <p>
     * 添加预执行sql
     * </p>
     * .
     *
     * @param prepareSqls prepareSqls
     */
    public void addPrepareSql(String... prepareSqls) {
        if (Lang.isNotEmpty(prepareSqls)) {
            for (String prepareSql : prepareSqls) {
                this.prepareSqls.add(prepareSql);
            }
        }
    }

    /**
     * <p>
     * 添加过滤器
     * </p>
     * .
     *
     * @param filter filter
     */
    public void addFilter(DataFilter filter) {
        if (Lang.isNotEmpty(filter)) {
            filters.add(filter);
        }
    }

    /**
     * <p>
     * 添加过滤器
     * </p>
     * .
     *
     * @param filters dataFilters
     */
    public void addFilter(DataFilter... filters) {
        if (Lang.isNotEmpty(filters)) {
            for (DataFilter filter : filters) {
                this.filters.add(filter);
            }
        }
    }

    /**
     * <p>
     * 添加数据变换器
     * </p>
     * .
     *
     * @param transformer transformer
     */
    public void addTransformers(DataTransformer transformer) {
        if (Lang.isNotEmpty(transformer)) {
            transformers.add(transformer);
        }
    }

    /**
     * <p>
     * 添加数据变换器
     * </p>
     * .
     *
     * @param transformers transformer
     */
    public void addTransformers(DataTransformer... transformers) {
        if (Lang.isNotEmpty(filters)) {
            for (DataTransformer transformer : transformers) {
                this.transformers.add(transformer);
            }
        }
    }

    /**
     * <p>
     * 数据过滤
     * </p>
     * .
     *
     * @param recordModel recordModel
     * @param conn        conn
     * @return RecordModel
     */
    protected boolean filtdate(RecordModel recordModel, Connection conn) {
        // 过滤
        if (Lang.isNotEmpty(filters)) {
            for (DataFilter filter : filters) {
                if (filter.filter(recordModel, conn)) {
                    logger.debug("过滤该条数据， {}", recordModel);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <p>
     * 数据变换
     * </p>
     * .
     *
     * @param recordModel recordModel
     * @return RecordModel
     */
    protected RecordModel transform(RecordModel recordModel) {
        // 数据转换
        RecordModel currentRecordModel = recordModel;
        if (Lang.isNotEmpty(transformers)) {
            for (DataTransformer transformer : transformers) {
                currentRecordModel = transformer.transform(currentRecordModel, recordModel);
            }
        }
        return currentRecordModel;
    }

    // ********************************************************************
    //	peroperty
    // ********************************************************************

    /** The fk check. */
    private boolean fkCheck;

    /** The exist policy. */
    private ExistPolicy existPolicy = ExistPolicy.exception;

    /** The transaction policy. */
    private TransactionPolicy transactionPolicy = TransactionPolicy.all;

    /** The prepare sqls. */
    private List<String> prepareSqls = new ArrayList<>();

    /** The filters. */
    private List<DataFilter> filters = new ArrayList<>();

    /** The transformers. */
    private List<DataTransformer> transformers = new ArrayList<>();

    /**
     * 返回prepareSqls.
     *
     * @return prepareSqls
     */
    public List<String> getPrepareSqls() {
        return prepareSqls;
    }

    /**
     * 设置prepareSqls.
     *
     * @param prepareSqls prepareSqls
     */
    public void setPrepareSqls(List<String> prepareSqls) {
        this.prepareSqls = prepareSqls;
    }

    /**
     * 返回transformers.
     *
     * @return transformers
     */
    public List<DataTransformer> getTransformers() {
        return transformers;
    }

    /**
     * 设置transformers.
     *
     * @param transformers transformers
     */
    public void setTransformers(List<DataTransformer> transformers) {
        this.transformers = transformers;
    }

    /**
     * 返回existPolicy.
     *
     * @return existPolicy
     */
    public ExistPolicy getExistPolicy() {
        return existPolicy;
    }

    /**
     * 设置existPolicy.
     *
     * @param existPolicy existPolicy
     */
    public void setExistPolicy(ExistPolicy existPolicy) {
        this.existPolicy = existPolicy;
    }

    /**
     * 返回transactionPolicy.
     *
     * @return transactionPolicy
     */
    public TransactionPolicy getTransactionPolicy() {
        return transactionPolicy;
    }

    /**
     * 设置transactionPolicy.
     *
     * @param transactionPolicy transactionPolicy
     */
    public void setTransactionPolicy(TransactionPolicy transactionPolicy) {
        this.transactionPolicy = transactionPolicy;
    }

    /**
     * 返回fkCheck.
     *
     * @return fkCheck
     */
    public boolean isFkCheck() {
        return fkCheck;
    }

    /**
     * 设置fkCheck.
     *
     * @param fkCheck fkCheck
     */
    public void setFkCheck(boolean fkCheck) {
        this.fkCheck = fkCheck;
    }
}
