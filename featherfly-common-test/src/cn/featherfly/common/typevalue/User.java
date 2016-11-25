package cn.featherfly.common.typevalue;

import cn.featherfly.common.structure.typevalue.TypeLong;
import cn.featherfly.common.structure.typevalue.TypeString;

class User {
    UserId id;
    
    TypeLong age;
    
    TypeString name;

    /**
     * 返回id
     * @return id
     */
    public UserId getId() {
        return id;
    }

    /**
     * 设置id
     * @param id id
     */
    public void setId(UserId id) {
        this.id = id;
    }

    /**
     * 返回age
     * @return age
     */
    public TypeLong getAge() {
        return age;
    }

    /**
     * 设置age
     * @param age age
     */
    public void setAge(TypeLong age) {
        this.age = age;
    }

    /**
     * 返回name
     * @return name
     */
    public TypeString getName() {
        return name;
    }

    /**
     * 设置name
     * @param name name
     */
    public void setName(TypeString name) {
        this.name = name;
    }
}