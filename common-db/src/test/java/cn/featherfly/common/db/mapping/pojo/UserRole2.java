
package cn.featherfly.common.db.mapping.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserRole2
 *
 * @author zhongj
 */
@Table(name = "user_role")
public class UserRole2 {

    @Id
    @GeneratedValue(generator = "assign")
    @ManyToOne
    @Column(name = "user_id")
    private User user;

    @Id
    @GeneratedValue(generator = "assign")
    @ManyToOne
    @Column(name = "role_id")
    private Role role;

    private String descp;
    private String descp2;

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

    /**
     * 返回user
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置user
     *
     * @param user user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 返回role
     *
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * 设置role
     *
     * @param role role
     */
    public void setRole(Role role) {
        this.role = role;
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "UserRole2 [user=" + user + ", role=" + role + ", descp=" + descp + ", descp2=" + descp2 + "]";
    }

}
