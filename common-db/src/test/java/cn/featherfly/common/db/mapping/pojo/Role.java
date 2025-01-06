
package cn.featherfly.common.db.mapping.pojo;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Role.
 *
 * @author zhongj
 */
@Table
public class Role implements Serializable {

    private static final long serialVersionUID = 2291141112997009736L;

    @Id
    private Long id;

    private String name;

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
