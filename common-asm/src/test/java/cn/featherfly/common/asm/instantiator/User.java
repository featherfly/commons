
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:41:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.asm.instantiator;

/**
 * Role.
 *
 * @author zhongj
 */
public class User {

    private int id;

    private String usernmae;

    private String password;

    private String descp;

    /**
     * Instantiates a new role.
     */
    public User() {
        descp = "new User()";
    }

    /**
     * Instantiates a new role.
     *
     * @param id the id
     */
    public User(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsernmae() {
        return usernmae;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
