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
@Table
public class UserInfo {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    /**
     * get id value
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set id value
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * get user value
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * set user value
     *
     * @param user user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
