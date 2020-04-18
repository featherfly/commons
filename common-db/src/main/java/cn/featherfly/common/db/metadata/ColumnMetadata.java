
package cn.featherfly.common.db.metadata;

/**
 * <p>
 * 列元数据
 * </p>
 * @author zhongj
 */
public class ColumnMetadata {

	/**
	 * @param tableMetadata 表元数据
	 */
	public ColumnMetadata(TableMetadata tableMetadata) {
		this.tableMetadata = tableMetadata;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hashCode = 0;
		if (getName() != null && getName().length() > 0) {
			byte[] bs = getName().getBytes();
			for (byte b : bs) {
				hashCode += b;
			}
		}
		return hashCode;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (this.getClass().isInstance(obj)) {
			ColumnMetadata cm = (ColumnMetadata) obj;
			if (this.getTableMetadata() == null
					|| !this.getTableMetadata().equals(cm.getTableMetadata())) {
				return false;
			}
			return this.getName().equals(cm.getName());
		}
		return false;
	}

	// ********************************************************************
	//	property
	// ********************************************************************

	private TableMetadata tableMetadata;

	private String name;

	private int type;

	private String typeName;

	private int size;

	private String remark;

	private String defaultValue;

	private boolean nullable;

	private int columnIndex;

	private boolean primaryKey;

	/**
	 * @return 返回primaryKey
	 */
	public boolean isPrimaryKey() {
		return primaryKey;
	}

	/**
	 * @param primaryKey 设置primaryKey
	 */
	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	/**
	 * @return 返回type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type 设置type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return 返回columnIndex
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	/**
	 * @param columnIndex 设置columnIndex
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	/**
	 * @return 返回size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size 设置size
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return 返回tableMetadata
	 */
	public TableMetadata getTableMetadata() {
		return tableMetadata;
	}

	/**
	 * @return 返回name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 设置name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 返回typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName 设置typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return 返回remarks
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark 设置remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return 返回defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue 设置defaultValue
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return 返回nullable
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * @param nullable 设置nullable
	 */
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

}
