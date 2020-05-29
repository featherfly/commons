
package cn.featherfly.common.structure.page;

/**
 * <p>
 * SimplePage
 * </p>
 * .
 *
 * @author zhongj
 */
public class SimplePage implements Page {

    private Integer size;

    private Integer number;

    /**
     * Instantiates a new simple page.
     */
    public SimplePage() {
        super();
    }

    /**
     * Instantiates a new simple page.
     *
     * @param size   the size
     * @param number the number
     */
    public SimplePage(Integer size, Integer number) {
        super();
        this.size = size;
        this.number = number;
    }

    /**
     * 设置size.
     *
     * @param size size
     * @return the simple page
     */
    public SimplePage setSize(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * 设置number.
     *
     * @param number number
     * @return the simple page
     */
    public SimplePage setNumber(Integer number) {
        this.number = number;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNumber() {
        return number;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public Integer getPageSize() {
        return getSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Deprecated
    public Integer getPageNumber() {
        return getNumber();
    }
}
