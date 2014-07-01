

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Zhong Ji
 */
public class Errors {

    private String message;

    private List<String> errorMessages = new ArrayList<String>();

    private List<FieldError> fieldErrors = new ArrayList<FieldError>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

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
    
    public String toString() {
    	return "message -> " + message + "\n"
    			+ "errorMessages -> \n" + errorMessages.toString() + "\n"
    			+ "fieldErrors -> \n" + fieldErrors.toString() + "\n";
    }
}
