
package cn.featherfly.common.lang.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * User
 * </p>
 * <p>
 * 2019-08-15
 * </p>
 *
 * @author zhongj
 */
public class User {

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
     * @param list
     */
    public User(List<String> list) {
        super();
        this.list = list;
        construct = "List";
    }

    /**
     * @param list
     */
    public User(LinkedList<String> list) {
        super();
        this.list = list;
        construct = "LinkedList";
    }

    /**
     * @param name
     * @param age
     * @param list
     */
    public User(String name, Integer age, List<String> list) {
        super();
        this.name = name;
        this.age = age;
        this.list = list;
    }

    public String name;

    public Integer age;

    public List<String> list;

    public String construct;

    private boolean locked;

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
     * 返回list
     *
     * @return list
     */
    public List<String> getList() {
        return list;
    }

    /**
     * 设置list
     *
     * @param list list
     */
    public void setList(List<String> list) {
        this.list = list;
    }

    /**
     * 返回construct
     *
     * @return construct
     */
    public String getConstruct() {
        return construct;
    }

    /**
     * 设置construct
     *
     * @param construct construct
     */
    public void setConstruct(String construct) {
        this.construct = construct;
    }

    /**
     * 返回locked
     * 
     * @return locked
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * 设置locked
     * 
     * @param locked locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", list=" + list + ", construct=" + construct + "]";
    }

}
