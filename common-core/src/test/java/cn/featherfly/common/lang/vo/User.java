
package cn.featherfly.common.lang.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    private Optional<String> optional;

    private Optional<?> obj;

    private Optional<? extends Number> obj1;

    private Optional<? super User> obj2;

    private Optional obj3;

    private Map<String, Integer> map;

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
    public User setAge(Integer age) {
        this.age = age;
        return this;
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

    public void setObject(Object o) {
        System.out.println("setObject " + o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", list=" + list + ", construct=" + construct + "]";
    }

    /**
     * 返回optional
     *
     * @return optional
     */
    public Optional<String> getOptional() {
        return optional;
    }

    /**
     * 设置optional
     *
     * @param optional optional
     */
    public void setOptional(Optional<String> optional) {
        this.optional = optional;
    }

    /**
     * 返回obj
     *
     * @return obj
     */
    public Optional<?> getObj() {
        return obj;
    }

    /**
     * 设置obj
     *
     * @param obj obj
     */
    public void setObj(Optional<?> obj) {
        this.obj = obj;
    }

    /**
     * 返回obj1
     *
     * @return obj1
     */
    public Optional<? extends Number> getObj1() {
        return obj1;
    }

    /**
     * 设置obj1
     *
     * @param obj1 obj1
     */
    public void setObj1(Optional<? extends Number> obj1) {
        this.obj1 = obj1;
    }

    /**
     * 返回obj2
     *
     * @return obj2
     */
    public Optional<? super User> getObj2() {
        return obj2;
    }

    /**
     * 设置obj2
     *
     * @param obj2 obj2
     */
    public void setObj2(Optional<? super User> obj2) {
        this.obj2 = obj2;
    }

    /**
     * 返回obj3
     *
     * @return obj3
     */
    public Optional getObj3() {
        return obj3;
    }

    /**
     * 设置obj3
     *
     * @param obj3 obj3
     */
    public void setObj3(Optional obj3) {
        this.obj3 = obj3;
    }

    /**
     * 返回map
     *
     * @return map
     */
    public Map<String, Integer> getMap() {
        return map;
    }

    /**
     * 设置map
     *
     * @param map map
     */
    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public void set(String name, Integer age) {
        setName(name);
        setAge(age);
    }

    public String get() {
        return "name = " + name + " age = " + age;
    }

    public void setDescp(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getDescp(String name) {
        return "name = " + name + " age = " + age;
    }

    public void setAgeInt(int age) {
        this.age = age;
    }

    public int getAgeInt() {
        return age;
    }

    public static void setStatic(String n) {

    }

    public static String getStatic() {
        return null;
    }

}
