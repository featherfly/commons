

/**
 * @author Zhong Ji
 */
public class FieldError {

    private String field;

    private String message;
    
    
    /**
	 * 
	 */
	public FieldError() {
		super();
	}

	/**
	 * @param field
	 * @param message
	 */
	public FieldError(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String toString() {
    	return "field -> " + field + "\n"
    			+ "message -> " + message + "\n";
    }
}
