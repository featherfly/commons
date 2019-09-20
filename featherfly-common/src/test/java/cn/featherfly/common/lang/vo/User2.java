
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
public class User2 extends User {

    /**
     */
    public User2() {
    }

    /**
     * @param name
     * @param age
     */
    public User2(String name, Integer age) {
        super();
        this.name = name;
        this.age = age;
    }

    /**
     * @param list
     */
    public User2(List<String> list) {
        super();
        this.list = list;
        construct = "List";
    }

    /**
     * @param list
     */
    public User2(LinkedList<String> list) {
        super();
        this.list = list;
        construct = "LinkedList";
    }

    /**
     * @param name
     * @param age
     * @param list
     */
    public User2(String name, Integer age, List<String> list) {
        super();
        this.name = name;
        this.age = age;
        this.list = list;
    }

}
