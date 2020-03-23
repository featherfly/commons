package cn.featherfly.common.validate;

public class ValidateCode {

    private String show;

    private String valid;

    /**
    	 */
    public ValidateCode() {
    }

    /**
     * @param show  str to show
     * @param valid str to valid
     */
    public ValidateCode(String show, String valid) {
        super();
        this.show = show;
        this.valid = valid;
    }

    /**
     * 返回show
     *
     * @return show
     */
    public String getShow() {
        return show;
    }

    /**
     * 设置show
     *
     * @param show show
     */
    public void setShow(String show) {
        this.show = show;
    }

    /**
     * 返回valid
     *
     * @return valid
     */
    public String getValid() {
        return valid;
    }

    /**
     * 设置valid
     *
     * @param valid valid
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ValidateCode [show=" + show + ", valid=" + valid + "]";
    }
}