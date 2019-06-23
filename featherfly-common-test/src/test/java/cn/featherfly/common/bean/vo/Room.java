
package cn.featherfly.common.bean.vo;




public class Room<P extends Person> {
	
    private P person;
    
    private String code;

    /**
     * 返回code
     * @return code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * 设置code
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 返回person
     * @return person
     */
    public P getPerson() {
        return person;
    }

    /**
     * 设置person
     * @param person person
     */
    public void setPerson(P person) {
        this.person = person;
    }    
}
