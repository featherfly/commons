package cn.featherfly.common.repository.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 属性与列的映射对象
 * </p>
 * .
 *
 * @author zhongj
 * @version 0.1.0
 * @since 0.1.0
 */
public class PropertyMapping<P extends PropertyMapping<P>> {

    public enum Mode {
        SINGLE, EMBEDDED, MANY_TO_ONE, ONE_TO_MANY
    }

    /**
     * Instantiates a new property mapping.
     */
    public PropertyMapping() {

    }

    private String propertyName;

    private String repositoryFieldName;

    private Class<?> propertyType;

    private boolean primaryKey;

    private String defaultValue;

    private Map<String, P> propertyMappings = new HashMap<>(0);

    protected P parent;

    private int size;

    private String remark = "";

    private boolean nullable = true;

    private boolean insertable = true;

    private boolean updatable = true;

    private boolean unique;

    private Mode mode = Mode.SINGLE;

    /**
     * 小数位数
     */
    private int decimalDigits;

    private boolean autoincrement;

    private int index = 0;

    /**
     * 返回index.
     *
     * @return index
     */
    public int getIndex() {
        return index;
    }

    /**
     * 设置index.
     *
     * @param index index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 返回unique.
     *
     * @return unique
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * 设置unique.
     *
     * @param unique unique
     */
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    /**
     * 返回size.
     *
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * 设置size.
     *
     * @param size size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * 返回remark.
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark.
     *
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 返回nullable.
     *
     * @return nullable
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * 设置nullable.
     *
     * @param nullable nullable
     */
    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    /**
     * 返回insertable.
     *
     * @return insertable
     */
    public boolean isInsertable() {
        return insertable;
    }

    /**
     * 设置insertable.
     *
     * @param insertable insertable
     */
    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    /**
     * 返回updatable.
     *
     * @return updatable
     */
    public boolean isUpdatable() {
        return updatable;
    }

    /**
     * 设置updatable.
     *
     * @param updatable updatable
     */
    public void setUpdatable(boolean updatable) {
        this.updatable = updatable;
    }

    /**
     * 返回decimalDigits.
     *
     * @return decimalDigits
     */
    public int getDecimalDigits() {
        return decimalDigits;
    }

    /**
     * 设置decimalDigits.
     *
     * @param decimalDigits decimalDigits
     */
    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * 返回autoincrement.
     *
     * @return autoincrement
     */
    public boolean isAutoincrement() {
        return autoincrement;
    }

    /**
     * 设置autoincrement.
     *
     * @param autoincrement autoincrement
     */
    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    /**
     * Gets the property type.
     *
     * @return 返回propertyType
     */
    public Class<?> getPropertyType() {
        return propertyType;
    }

    /**
     * Sets the property type.
     *
     * @param propertyType 设置propertyType
     */
    public void setPropertyType(Class<?> propertyType) {
        this.propertyType = propertyType;
    }

    /**
     * Checks if is primary key.
     *
     * @return 返回primaryKey
     */
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the primary key.
     *
     * @param primaryKey 设置primaryKey
     */
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * Gets the property name.
     *
     * @return 返回propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Gets the property full name.
     *
     * @return the property full name
     */
    public String getPropertyFullName() {
        if (parent != null) {
            return parent.getPropertyFullName() + "." + getPropertyName();
        } else {
            return getPropertyName();
        }
    }

    /**
     * Sets the property name.
     *
     * @param propertyName 设置propertyName
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * Gets the repository field name.
     *
     * @return 返回repositoryFiledName
     */
    public String getRepositoryFieldName() {
        if (repositoryFieldName == null) {
            StringBuilder name = new StringBuilder();
            for (Map.Entry<String, P> entry : propertyMappings.entrySet()) {
                name.append(entry.getKey()).append(",");
            }
            if (name.length() > 0) {
                name.deleteCharAt(name.length() - 1);
            }
            return name.toString();
        } else {
            return repositoryFieldName;
        }
    }

    /**
     * Sets the repository field name.
     *
     * @param repositoryFieldName 设置repositoryFieldName
     */
    public void setRepositoryFieldName(String repositoryFieldName) {
        this.repositoryFieldName = repositoryFieldName;
    }

    /**
     * get mode value
     *
     * @return mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * set mode value
     *
     * @param mode mode
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * 返回defaultValue.
     *
     * @return defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置defaultValue.
     *
     * @param defaultValue defaultValue
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * 返回parent.
     *
     * @return parent
     */
    public P getParent() {
        return parent;
    }

    /**
     * 返回PropertyMappings.
     *
     * @return PropertyMappings
     */
    public List<P> getPropertyMappings() {
        return new ArrayList<>(propertyMappings.values());
    }

    /**
     * <p>
     * 返回指定属性名称的属性映射. 没有找到返回null.
     * </p>
     *
     * @param propertyName 属性名称
     * @return 属性映射对象
     */
    public P getPropertyMapping(String propertyName) {
        for (P pm : propertyMappings.values()) {
            if (pm.getPropertyName().equals(propertyName)) {
                return pm;
            }
        }
        return null;
    }

    /**
     * <p>
     * 通过持久化字段（数据库字段）的名称返回指定属性映射. 没有找到返回null.
     * </p>
     *
     * @param repositoryFiledName 持久化字段（数据库字段）
     * @return PropertyMapping
     */
    public P getPropertyMappingByPersitField(String repositoryFiledName) {
        return propertyMappings.get(repositoryFiledName);
    }

    /**
     * add nested property mapping.
     *
     * @param propertyMapping propertyMapping
     * @return this
     */
    @SuppressWarnings("unchecked")
    public P add(P propertyMapping) {
        propertyMapping.parent = (P) this;
        propertyMappings.put(propertyMapping.getRepositoryFieldName(), propertyMapping);
        return (P) this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PropertyMapping [propertyName=" + propertyName + ", repositoryFieldName=" + repositoryFieldName
                + ", propertyType=" + propertyType + ", primaryKey=" + primaryKey + ", defaultValue=" + defaultValue
                + ", mode=" + mode + ", propertyMappings=" + propertyMappings + ", parent="
                + (parent == null ? "" : parent.getPropertyName()) + "]";
    }
}
