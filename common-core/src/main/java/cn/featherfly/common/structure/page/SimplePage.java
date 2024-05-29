
package cn.featherfly.common.structure.page;

/**
 * SimplePage .
 *
 * @author zhongj
 */
public class SimplePage implements Page {

    private int size;

    private int number;

    /**
     * Instantiates a new simple page.
     */
    public SimplePage() {
        super();
    }

    /**
     * Instantiates a new simple page.
     *
     * @param size the size
     * @param number the number
     */
    public SimplePage(int size, int number) {
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
    public SimplePage setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * 设置number.
     *
     * @param number number
     * @return the simple page
     */
    public SimplePage setNumber(int number) {
        this.number = number;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumber() {
        return number;
    }
}
