
package cn.featherfly.common.db.mapping;

/**
 * <p>
 * Address
 * </p>
 *
 * @author zhongj
 */
public class Address {

    private String city;

    private String location;

    private Integer no;

    /**
     * 返回no
     * 
     * @return no
     */
    public Integer getNo() {
        return no;
    }

    /**
     * 设置no
     * 
     * @param no no
     */
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * 返回city
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置city
     *
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 返回location
     *
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置location
     *
     * @param location location
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
