
package cn.featherfly.common.bean.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import cn.featherfly.common.bean.BeanUtils;

// @javax.xml.ws.BindingType("sys_user")
public class User {

    public static void main(String[] args) {
        User u = new User();
        u.setBirthDay(new Date());
        System.out.println(BeanUtils.getProperty(u, "birthDay").getClass());
    }

    private String name;

    private Integer age;

    private Boolean available;

    private Address address;

    private Object object;

    private Date birthDay;

    private List<Address> addresses = new ArrayList<>();

    private List<String> names = new ArrayList<>();

    private Optional<String> username;

    /**
     * 返回addresses
     *
     * @return addresses
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * 设置addresses
     *
     * @param addresses addresses
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * 返回names
     *
     * @return names
     */
    public List<String> getNames() {
        return names;
    }

    /**
     * 设置names
     *
     * @param names names
     */
    public void setNames(List<String> names) {
        this.names = names;
    }

    /**
     * 返回birthDay
     *
     * @return birthDay
     */
    public Date getBirthDay() {
        return birthDay;
    }

    /**
     * 设置birthDay
     *
     * @param birthDay birthDay
     */
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * @return 返回object
     */
    public Object getObject() {
        return object;
    }

    /**
     * @param object 设置object
     */
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * @return 返回address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address 设置address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return 返回name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 设置name
     */
    public void setName(String name) {
        //		System.out.println("set user name : " + name);
        this.name = name;
    }

    /**
     * @return 返回age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age 设置age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return 返回available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * @param available 设置available
     */
    public void isAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * 返回username
     *
     * @return username
     */
    public Optional<String> getUsername() {
        return username;
    }

    /**
     * 设置available
     *
     * @param available available
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * 设置username
     *
     * @param username username
     */
    public void setUsername(Optional<String> username) {
        this.username = username;
    }

}
