
package cn.featherfly.common.db.builder.model;

import java.util.Arrays;

import cn.featherfly.common.db.dialect.Dialect;
import cn.featherfly.common.operator.CalculationOperator;
import cn.featherfly.common.operator.Function;

/**
 * ColumnElement.
 *
 * @author zhongj
 */
public class ColumnElement extends AbstractSqlElement {

    /** The name. */
    protected String name;

    /** The table alias. */
    protected String tableAlias;

    /** The function. */
    protected Function function;

    /** The argus. */
    protected Object[] arguments;

    /**
     * Instantiates a new column element.
     *
     * @param dialect dialect
     * @param name name
     */
    public ColumnElement(Dialect dialect, String name) {
        this(dialect, null, name);
    }

    /**
     * Instantiates a new column element.
     *
     * @param dialect dialect
     * @param tableAlias tableAlias
     * @param name name
     */
    public ColumnElement(Dialect dialect, String tableAlias, String name) {
        this(dialect, tableAlias, name, null);
    }

    /**
     * Instantiates a new column element.
     *
     * @param dialect dialect
     * @param name name
     * @param function the function
     * @param arguments the arguments
     */
    public ColumnElement(Dialect dialect, String name, Function function, Object... arguments) {
        this(dialect, null, name, function, arguments);
    }

    /**
     * Instantiates a new column element.
     *
     * @param dialect the dialect
     * @param tableAlias the table alias
     * @param name the name
     * @param function the function
     * @param arguments the arguments
     */
    public ColumnElement(Dialect dialect, String tableAlias, String name, Function function, Object... arguments) {
        super(dialect);
        this.name = name;
        this.tableAlias = tableAlias;
        this.function = function;
        this.arguments = arguments;
    }

    /**
     * 返回name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 返回tableAlias.
     *
     * @return tableAlias
     */
    public String getTableAlias() {
        return tableAlias;
    }

    /**
     * 设置name.
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置tableAlias.
     *
     * @param tableAlias tableAlias
     */
    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    /**
     * Gets the function.
     *
     * @return the function
     */
    public Function getFunction() {
        return function;
    }

    /**
     * Sets the function.
     *
     * @param function the new function
     */
    public void setFunction(Function function) {
        this.function = function;
    }

    /**
     * Gets the arguments.
     *
     * @return the arguments
     */
    public Object[] getArguments() {
        return arguments;
    }

    /**
     * Sets the arguments.
     *
     * @param arguments the new arguments
     */
    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public ColumnElement function(Function function, Object... arguments) {
        this.function = function;
        this.arguments = arguments;
        return this;
    }

    /**
     * Operate.
     *
     * @param calculationOperator the calculation operator
     * @param sqlElement the sql element
     * @return the arithmetic column element
     */
    public ArithmeticColumnElement operate(CalculationOperator calculationOperator, SqlElement sqlElement) {
        return new ArithmeticColumnElement(this, calculationOperator, sqlElement);
    }

    /**
     * Operate.
     *
     * @param calculationOperator the calculation operator
     * @param param the param
     * @return the arithmetic column element
     */
    public ArithmeticColumnElement operate(CalculationOperator calculationOperator, Object param) {
        return new ArithmeticColumnElement(this, calculationOperator, param);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSql() {
        if (function == null) {
            return dialect.dml().column(tableAlias, name);
        } else {
            return dialect.dml().column(tableAlias, name, function, arguments);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(arguments);
        result = prime * result + (function == null ? 0 : function.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (tableAlias == null ? 0 : tableAlias.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ColumnElement other = (ColumnElement) obj;
        if (!Arrays.deepEquals(arguments, other.arguments)) {
            return false;
        }
        if (function == null) {
            if (other.function != null) {
                return false;
            }
        } else if (!function.equals(other.function)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (tableAlias == null) {
            if (other.tableAlias != null) {
                return false;
            }
        } else if (!tableAlias.equals(other.tableAlias)) {
            return false;
        }
        return true;
    }

}
