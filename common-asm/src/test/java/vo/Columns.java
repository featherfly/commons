
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2024-05-25 15:56:25
 * @Copyright: 2024 www.featherfly.cn Inc. All rights reserved.
 */
package vo;

/**
 * TestColumns.
 *
 * @author zhongj
 */
public abstract class Columns {
    private Integer id;

    /**
     * get id value
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * set id value
     *
     * @param id id
     */
    public Columns setId(Integer id) {
        this.id = id;
        return this;
    }

}
