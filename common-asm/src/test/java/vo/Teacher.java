
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-07-02 15:41:02
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

/**
 * Teacher.
 *
 * @author zhongj
 */
public class Teacher {
    private Integer id;

    private String no;

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        System.out.println("id".hashCode());
        System.out.println("no".hashCode());
        System.out.println("user".hashCode());
    }
}
