
package cn.featherfly.common.structure.page;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * Limit.
 *
 * @author zhongj
 */
public class Limit {

    private Integer offset;

    private Integer limit;

    /**
     * @param offset offset
     * @param limit  limit
     */
    public Limit(Integer offset, Integer limit) {
        super();
        if (offset == null) {
            this.offset = 0;
        } else {
            this.offset = offset;
        }
        AssertIllegalArgument.isNotNull(limit, "Integer limit");
        this.limit = limit;
    }

    /**
     * @param page page
     */
    public Limit(Page page) {
        super();
        AssertIllegalArgument.isNotNull(page, "Page page");
        Integer pageNumber = page.getNumber();
        Integer pageSize = page.getSize();
        if (pageNumber == null) {
            pageNumber = 1;
        } else if (pageNumber < 1) {
            pageNumber = 1;
        }
        AssertIllegalArgument.isNotNull(pageSize, "page.pageSize");
        limit = pageSize;
        offset = (pageNumber - 1) * pageSize;
    }

    /**
     * 返回offset
     *
     * @return offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     * 返回limit
     *
     * @return limit
     */
    public Integer getLimit() {
        return limit;
    }
}
