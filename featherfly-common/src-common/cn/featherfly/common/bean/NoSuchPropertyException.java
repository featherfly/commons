
package cn.featherfly.common.bean;


/**
 * <p>
 * 没有找到相应属性的异常.
 * 如果一个成员变量既没有set方法也没有get方法就会抛出该异常.
 * </p>
 *
 * @author 钟冀
 */
public class NoSuchPropertyException extends RuntimeException{

	private static final long serialVersionUID = -8041655239720325546L;

	private static final String MSG_START = "%s没有这样的属性：[%s]";

	private static final String MSG_SPLITOR = " , ";

	/**
	 *
	 * @param clazz 类型
	 * @param propertyName 属性名
	 */
	public NoSuchPropertyException(Class<?> clazz, String propertyName) {
    	super(String.format(MSG_START, clazz.getName(), propertyName));
	}

	/**
	 *
	 * @param clazz 类型
	 * @param propertyName 属性名
	 * @param message 信息
	 */
	public NoSuchPropertyException(Class<?> clazz, String propertyName, String message) {
    	super(String.format(MSG_START, clazz.getName(), propertyName) + MSG_SPLITOR + message);
	}

    /**
	 *
	 * @param clazz 类型
	 * @param propertyName 属性名
	 * @param message 信息
	 * @param cause 异常
	 */
	public NoSuchPropertyException(Class<?> clazz, String propertyName, String message, Throwable cause) {
    	super(String.format(MSG_START, clazz.getName(), propertyName) + MSG_SPLITOR + message , cause);
	}

    /**
	 *
	 * @param clazz 类型
	 * @param propertyName 属性名
	 * @param cause 异常
	 */
	public NoSuchPropertyException(Class<?> clazz, String propertyName, Throwable cause) {
		super(String.format(MSG_START, clazz.getName(), propertyName), cause);
	}

    // ********************************************************************
	// property
	// ********************************************************************

	private String propertyName;

	/**
	 * @return 返回propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName 设置propertyName
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
}
