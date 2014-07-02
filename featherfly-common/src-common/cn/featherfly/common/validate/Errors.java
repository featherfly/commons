package cn.featherfly.common.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhong Ji
 */
public class Errors {

    private String message;
    
    private int code = -1;

    private List<String> errorMessages = new ArrayList<String>();

    private List<FieldError> fieldErrors = new ArrayList<FieldError>();
    
    /**
	 * 返回code
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置code
	 * @param code code
	 */
	public void setCode(int code) {
		this.code = code;
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
	 * 返回errorMessages
	 * @return errorMessages
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * 设置errorMessages
	 * @param errorMessages errorMessages
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	/**
	 * 返回fieldErrors
	 * @return fieldErrors
	 */
	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorMessages.add(errorMessage);
    }
    public void addFieldError(FieldError fieldError) {
        this.fieldErrors.add(fieldError);
    }
}
