
package cn.featherfly.common.db.mapping.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * User
 * </p>
 *
 * @author zhongj
 */
@Entity
@Table
public class User2 {

    @Id
    private Long id;

    private String name2;

    private String descp;

    /**
     * 返回id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get name2 value
     *
     * @return name2
     */
    public String getName2() {
        return name2;
    }

    /**
     * set name2 value
     *
     * @param name2 name2
     */
    public void setName2(String name2) {
        this.name2 = name2;
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

}
