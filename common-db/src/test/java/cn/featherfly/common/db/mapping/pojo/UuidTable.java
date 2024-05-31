package cn.featherfly.common.db.mapping.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserInfo.
 *
 * @author zhongj
 */
@Entity
@Table
public class UuidTable {

    @GeneratedValue(generator = "uuid")
    @Id
    private String id;

    private String descp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
