
package cn.featherfly.common.spring.cache;

/**
 * <p>
 * User
 * </p>
 * <p>
 * 2019-08-08
 * </p>
 *
 * @author zhongj
 */
public class User {

    private Integer id;

    private String name;

    private String username;

    private String email;

    private String token;

    private String descp;

    private Integer age;

    /**
     */
    public User() {
    }

    /**
     * 返回token
     *
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置token
     *
     * @param token token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 返回descp
     *
     * @return descp
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置descp
     *
     * @param descp descp
     */
    public void setDescp(String descp) {
        this.descp = descp;
    }

    /**
     * 返回age
     *
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age
     *
     * @param age age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 返回id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 返回name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 返回username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置username
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 返回email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", token=" + token
                + ", descp=" + descp + ", age=" + age + "]";
    }

}
