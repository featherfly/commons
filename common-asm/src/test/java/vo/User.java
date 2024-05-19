
package vo;

/**
 * User.
 *
 * @author zhongj
 */
public class User {

    public String name;

    public Integer age;

    private String username;

    private String gender;

    private boolean locked;

    /**
     */
    public User() {
    }

    /**
     * @param name
     * @param age
     */
    public User(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * get name value
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set name value
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get age value
     *
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * set age value
     *
     * @param age age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * get username value
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username value
     *
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get gender value
     *
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * set gender value
     *
     * @param gender gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * get locked value
     *
     * @return locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * set locked value
     *
     * @param locked locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
