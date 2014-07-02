package cn.featherfly.common.validate;

/**
 * @author Zhong Ji
 */
public class FieldError {

    private String field;

    private String message;
    
    private String value;

	/**
	 * 返回field
	 * @return field
	 */
	public String getField() {
		return field;
	}

	/**
	 * 设置field
	 * @param field field
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 返回message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置message
	 * @param message message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 返回value
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置value
	 * @param value value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
