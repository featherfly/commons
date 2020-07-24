
package cn.featherfly.common.db.mapping.pojo;

import javax.persistence.Id;

/**
 * <p>
 * UserRole
 * </p>
 *
 * @author zhongj
 */
public class UserRole {
    @Id
    private Integer userId;

    @Id
    private Integer roleId;

    private String descp;

    private String descp2;

    /**
     * 返回userId
     *
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置userId
     *
     * @param userId userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 返回roleId
     *
     * @return roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置roleId
     *
     * @param roleId roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
     * 返回descp2
     * 
     * @return descp2
     */
    public String getDescp2() {
        return descp2;
    }

    /**
     * 设置descp2
     * 
     * @param descp2 descp2
     */
    public void setDescp2(String descp2) {
        this.descp2 = descp2;
    }

}
