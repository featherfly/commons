package cn.featherfly.common.validate;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Errors.
 *
 * @author Zhong Ji
 */
public class Errors {

    /**
     * Instantiates a new errors.
     */
    public Errors() {
    }

    private String message;

    private String code;

    private List<String> errorMessages = new ArrayList<>();

    private List<FieldError> fieldErrors = new ArrayList<>();

    /**
     * 返回code.
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置code.
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 返回message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message.
     *
     * @param message message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回errorMessages.
     *
     * @return errorMessages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * 设置errorMessages.
     *
     * @param errorMessages errorMessages
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * 返回fieldErrors.
     *
     * @return fieldErrors
     */
    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    /**
     * <p>
     * 设置fieldErrors
     * </p>
     * .
     *
     * @param fieldErrors fieldErrors
     */
    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    /**
     * <p>
     * set errorMessage
     * </p>
     * .
     *
     * @param errorMessage errorMessage
     */
    public void addErrorMessage(String errorMessage) {
        errorMessages.add(errorMessage);
    }

    /**
     * <p>
     * set fieldError
     * </p>
     * .
     *
     * @param fieldError fieldError
     */
    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }
}
