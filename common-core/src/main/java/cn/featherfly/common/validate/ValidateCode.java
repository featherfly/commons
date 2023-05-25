package cn.featherfly.common.validate;

/**
 * The Class ValidateCode.
 *
 * @author zhongj
 */
public class ValidateCode {

    private String show;

    private String valid;

    /**
     * Instantiates a new validate code.
     */
    public ValidateCode() {
    }

    /**
     * Instantiates a new validate code.
     *
     * @param show  str to show
     * @param valid str to valid
     */
    public ValidateCode(String show, String valid) {
        super();
        this.show = show;
        this.valid = valid;
    }

    /**
     * 返回show.
     *
     * @return show
     */
    public String getShow() {
        return show;
    }

    /**
     * 设置show.
     *
     * @param show show
     */
    public void setShow(String show) {
        this.show = show;
    }

    /**
     * 返回valid.
     *
     * @return valid
     */
    public String getValid() {
        return valid;
    }

    /**
     * 设置valid.
     *
     * @param valid valid
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

    /**
     * Validate.
     *
     * @param validCode the valid code
     * @return true, if successful
     */
    public boolean validate(String validCode) {
        return validate(validCode, false);
    }

    /**
     * Validate.
     *
     * @param validCode     the valid code
     * @param caseSensitive the case sensitive
     * @return true, if successful
     */
    public boolean validate(String validCode, boolean caseSensitive) {
        if (validCode == null) {
            return false;
        }
        if (caseSensitive) {
            return valid.equals(validCode);
        } else {
            return valid.equalsIgnoreCase(validCode);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ValidateCode [show=" + show + ", valid=" + valid + "]";
    }
}