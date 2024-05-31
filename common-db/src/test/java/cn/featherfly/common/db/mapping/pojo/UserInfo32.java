package cn.featherfly.common.db.mapping.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserInfo.
 *
 * @author zhongj
 */
@Entity
@Table(name = "user_info_3")
public class UserInfo32 {

    @Id
    private Long id;

    private String userName3;

    @ManyToOne
    @Column(name = "user")
    private User user;

    /**
     * get id value.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id value.
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the user name 3.
     *
     * @return the user name 3
     */
    public String getUserName3() {
        return userName3;
    }

    /**
     * Sets the user name 3.
     *
     * @param userName3 the new user name 3
     */
    public void setUserName3(String userName3) {
        this.userName3 = userName3;
    }

    /**
     * get user value.
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * set user value.
     *
     * @param user user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
