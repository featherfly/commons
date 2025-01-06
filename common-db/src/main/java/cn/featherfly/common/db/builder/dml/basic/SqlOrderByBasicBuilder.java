package cn.featherfly.common.db.builder.dml.basic;

import java.util.ArrayList;
import java.util.List;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.db.builder.SqlBuilder;
import cn.featherfly.common.db.builder.model.ColumnElement;
import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.operator.SortOperator;

/**
 * sql order by basic builder
 *
 * @author zhongj
 */
public class SqlOrderByBasicBuilder implements SqlBuilder {

    private Dialect dialect;

    /**
     * @param dialect dialect
     */
    public SqlOrderByBasicBuilder(Dialect dialect) {
        this.dialect = dialect;
    }

    /*
     * 排序参数
     */
    private List<Order> orderParams = new ArrayList<>();

    /**
     * add property to asc list
     *
     * @param name sort property name
     * @param tableAlias tableAlias
     * @param operator SortOperator
     * @return this
     */
    public SqlOrderByBasicBuilder addOrder(String name, String tableAlias, SortOperator operator) {
        addOrderBy(name, tableAlias, operator);
        return this;
    }

    /**
     * add property to asc list
     *
     * @param name sort column name
     * @param tableAlias table alias
     * @return this
     */
    public SqlOrderByBasicBuilder addAsc(String name, String tableAlias) {
        return addOrder(name, tableAlias, SortOperator.ASC);
    }

    /**
     * add property to desc list
     *
     * @param name sort column name
     * @param tableAlias table alias
     * @return this
     */
    public SqlOrderByBasicBuilder addDesc(String name, String tableAlias) {
        return addOrder(name, tableAlias, SortOperator.DESC);
    }

    /**
     * add property to asc list
     *
     * @param name sort column name
     * @return this
     */
    public SqlOrderByBasicBuilder addAsc(String name) {
        return addAsc(name, null);
    }

    /**
     * add property to desc list
     *
     * @param name sort column name
     * @return this
     */
    public SqlOrderByBasicBuilder addDesc(String name) {
        return addDesc(name, null);
    }

    // /**
    // * add property to asc list
    // *
    // * @param names sort property name
    // * @return this
    // */
    // public SqlOrderByBasicBuilder addAsc(String... names) {
    // if (names != null) {
    // for (String name : names) {
    // addOrder(name, null, OrderOperator.ASC);
    // }
    // }
    // return this;
    // }
    //
    // /**
    // * add property to desc list
    // *
    // * @param names sort property name
    // * @return this
    // */
    // public SqlOrderByBasicBuilder addDesc(String... names) {
    // if (names != null) {
    // for (String name : names) {
    // addOrder(name, null, OrderOperator.DESC);
    // }
    // }
    // return this;
    // }

    /**
     * clear all sort properties
     *
     * @return this
     */
    public SqlOrderByBasicBuilder clear() {
        orderParams.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        if (orderParams.size() > 0) {
            //            sb.append(Chars.SPACE).append(dialect.getKeywords().orderBy());
            sb.append(dialect.getKeywords().orderBy());
        }
        for (Order orderParam : orderParams) {
            sb.append(orderParam).append(Chars.COMMA);
        }
        if (orderParams.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getName() + " : " + build();
    }

    // ********************************************************************
    // private method
    // ********************************************************************

    private void addOrderBy(String columnName, String tableAlias, SortOperator orderOperator) {
        Order orderParam = null;
        if (orderParams.isEmpty()) {
            orderParam = new Order(dialect, orderOperator);
            orderParams.add(orderParam);
        } else {
            orderParam = orderParams.get(orderParams.size() - 1);
            if (!orderParam.isOrderOperator(orderOperator)) {
                orderParam = new Order(dialect, orderOperator);
                orderParams.add(orderParam);
            }
        }
        orderParam.addParam(new ColumnElement(dialect, tableAlias, columnName));
    }

    // ********************************************************************
    // 内部类
    // ********************************************************************

    /**
     * <p>
     * 排序参数辅助对象
     * </p>
     *
     * @author zhongj
     */
    public static class Order {

        /**
         * @param dialect dialect
         * @param sortOperator sortOperator
         */
        public Order(Dialect dialect, SortOperator sortOperator) {
            sortOrerator = sortOperator;
            this.dialect = dialect;
        }

        private SortOperator sortOrerator;

        private Dialect dialect;

        private List<ColumnElement> params = new ArrayList<>();

        /**
         * <p>
         * 添加排序参数
         * </p>
         *
         * @param param 排序参数
         */
        public void addParam(String param) {
            params.add(new ColumnElement(dialect, param));
        }

        /**
         * <p>
         * 添加排序参数
         * </p>
         *
         * @param param 排序参数
         */
        public void addParam(ColumnElement param) {
            params.add(param);
        }

        /**
         * 返回是否是传入的操作
         *
         * @param orderOperator orderOperator
         * @return 是否是传入的操作
         */
        public boolean isOrderOperator(SortOperator orderOperator) {
            return sortOrerator == orderOperator;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (ColumnElement column : params) {
                sb.append(Chars.SPACE).append(column.toSql()).append(Chars.SPACE)
                    .append(dialect.getKeyword(sortOrerator)).append(Chars.COMMA);
            }
            if (params.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }

}