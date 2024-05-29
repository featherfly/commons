
package cn.featherfly.common.structure.page;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * Limit.
 *
 * @author zhongj
 */
public class Limit {

    private int offset;

    private int limit;

    /**
     * @param offset offset
     * @param limit limit
     */
    public Limit(Integer offset, int limit) {
        super();
        if (offset == null) {
            this.offset = 0;
        } else {
            this.offset = offset;
        }
        AssertIllegalArgument.isGe(limit, 0, "limit");
        this.limit = limit;
    }

    /**
     * @param page page
     */
    public Limit(Page page) {
        super();
        AssertIllegalArgument.isNotNull(page, "Page page");
        int pageNumber = page.getNumber();
        int pageSize = page.getSize();
        if (pageNumber < 1) {
            pageNumber = 1;
        }
        AssertIllegalArgument.isGt(pageSize, 0, "page.pageSize");
        limit = pageSize;
        offset = (pageNumber - 1) * pageSize;
    }

    /**
     * 返回offset
     *
     * @return offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * 返回limit
     *
     * @return limit
     */
    public int getLimit() {
        return limit;
    }
}
