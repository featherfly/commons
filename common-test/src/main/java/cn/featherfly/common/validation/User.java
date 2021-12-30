
/*
 * All rights Reserved, Designed By zhongj
 * @Title: User.java
 * @Package cn.featherfly.common.validation
 * @Description: TODO (用一句话描述该文件做什么)
 * @author: zhongj
 * @date: 2021-11-25 16:47:25
 * @Copyright: 2021 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.validation;

/**
 * User.
 *
 * @author zhongj
 */
public class User {

    private String name;

    private int age;

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
    public int getAge() {
        return age;
    }

    /**
     * set age value
     *
     * @param age age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [name=" + name + ", age=" + age + "]";
    }
}
