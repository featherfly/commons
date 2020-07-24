
package cn.featherfly.common.repository;

import java.util.Arrays;

import cn.featherfly.common.constant.Chars;
import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * <p>
 * Index
 * </p>
 * .
 *
 * @author zhongj
 */
public class Index {

    private String name;

    private String[] columns;

    private boolean unique;

    /**
     * Instantiates a new index.
     *
     * @param columns the columns
     */
    public Index(String[] columns) {
        this(null, columns, false);
    }

    /**
     * Instantiates a new index.
     *
     * @param columns the columns
     * @param unique  the unique
     */
    public Index(String[] columns, boolean unique) {
        this(null, columns, unique);
    }

    /**
     * Instantiates a new index.
     *
     * @param name    the name
     * @param columns the columns
     * @param unique  the unique
     */
    public Index(String name, String[] columns, boolean unique) {
        AssertIllegalArgument.isNotEmpty(columns, "columns");
        if (Lang.isEmpty(name)) {
            StringBuilder sb = new StringBuilder();
            for (String column : columns) {
                sb.append(column).append(Chars.UNDER_LINE);
            }
            sb.append(unique ? "unique" : "index");
            this.name = sb.toString();
        } else {
            this.name = name;
        }
        this.columns = columns;
        this.unique = unique;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(columns);
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (unique ? 1231 : 1237);
        return result;
    }

    /**
     * {@inheritDoc}
     */
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
        Index other = (Index) obj;
        if (!Arrays.equals(columns, other.columns)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (unique != other.unique) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Index [name=" + name + ", columns=" + Arrays.toString(columns) + ", unique=" + unique + "]";
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
     * 返回columns.
     *
     * @return columns
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * 返回unique.
     *
     * @return unique
     */
    public boolean isUnique() {
        return unique;
    }
}
