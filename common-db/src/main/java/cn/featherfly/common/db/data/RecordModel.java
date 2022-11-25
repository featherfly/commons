
package cn.featherfly.common.db.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.featherfly.common.bean.BeanUtils;
import cn.featherfly.common.structure.ChainMapImpl;

/**
 * <p>
 * RecordMetadata. 记录元数据
 * </p>
 * .
 *
 * @author zhongj
 */
public class RecordModel {

    /** The value models. */
    private Map<String, ValueModel> valueModels = new HashMap<>();

    /** The table name. */
    private String tableName;

    /**
     * Instantiates a new record model.
     *
     * @param tableName tableName
     */
    public RecordModel(String tableName) {
        this.tableName = tableName;
    }

    /**
     * <p>
     * 添加ValueModel
     * </p>
     * .
     *
     * @param valueModel the value model
     */
    public void add(ValueModel valueModel) {
        if (valueModel != null) {
            valueModels.put(valueModel.getColumnName(), valueModel);
        }
    }

    /**
     * <p>
     * 返回指定列名的ValueModel
     * </p>
     * .
     *
     * @param columnName 列名
     * @return ValueModel
     */
    public ValueModel getValueMode(String columnName) {
        return valueModels.get(columnName);
    }

    /**
     * <p>
     * 返回全部ValueModel
     * </p>
     * .
     *
     * @return ValueModel集合
     */
    public Collection<ValueModel> getValueModes() {
        return new ArrayList<>(valueModels.values());
    }

    /**
     * <p>
     * 是否存在指定列
     * </p>
     * .
     *
     * @param columnName 列名
     * @return 是否存在
     */
    public boolean isColumnExist(String columnName) {
        return valueModels.containsKey(columnName);
    }

    /**
     * 返回tableName.
     *
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new ChainMapImpl<String, Object>().putChain("tableName", tableName).putChain("values", getValueModes())
                .toString();
    }

    // ********************************************************************
    //
    // ********************************************************************

    /**
     * The Class ValueModel.
     */
    public static class ValueModel {

        /**
         * <p>
         * ValueModel
         * </p>
         * .
         *
         * @param columnName 列名
         * @param type       类型(java.sql.Types定义的类型)
         * @param value      值
         */
        public ValueModel(String columnName, int type, String value) {
            this.columnName = columnName;
            this.value = value;
            this.type = type;
        }

        /** The column name. */
        private String columnName;

        /** The value. */
        private String value;

        /** The type. */
        private int type;

        /**
         * 返回columnName.
         *
         * @return columnName
         */
        public String getColumnName() {
            return columnName;
        }

        /**
         * 返回value.
         *
         * @return value
         */
        public String getValue() {
            return value;
        }

        /**
         * 设置value.
         *
         * @param value value
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 返回type.
         *
         * @return type
         */
        public int getType() {
            return type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return BeanUtils.toMap(this).toString();
        }
    }
}
