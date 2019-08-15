
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", list=" + list + ", construct=" + construct + "]";
    }

}
