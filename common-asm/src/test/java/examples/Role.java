
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-16 15:41:16
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package examples;

/**
 * Role.
 *
 * @author zhongj
 */
public class Role {

    private int id;

    private String name;

    private String descp;

    /**
     * Instantiates a new role.
     */
    public Role() {
        descp = "new Role()";
    }

    /**
     * Instantiates a new role.
     *
     * @param id the id
     */
    public Role(int id) {
        super();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
