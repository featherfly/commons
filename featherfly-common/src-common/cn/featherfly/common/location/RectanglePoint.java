package cn.featherfly.common.location;

/**
 * 矩形点
 */
public class RectanglePoint {

    /* 左上角 */
    private LocationPoint leftTop;
    /* 右上角 */
    private LocationPoint rightTop;
    /* 左下角 */
    private LocationPoint leftBottom;
    /* 右下角 */
    private LocationPoint rightBottom;

    /**
     * Instantiates a new Rectangle point.
     */
    public RectanglePoint() {
    }


    /**
     * Instantiates a new Rectangle point.
     *
     * @param leftTop the left top
     * @param rightTop the right top
     * @param leftBottom the left bottom
     * @param rightBottom the right bottom
     */
    public RectanglePoint(LocationPoint leftTop, LocationPoint rightTop, LocationPoint leftBottom, LocationPoint rightBottom) {
        this.leftTop = leftTop;
        this.rightTop = rightTop;
        this.leftBottom = leftBottom;
        this.rightBottom = rightBottom;
    }

    /**
     * Gets left top.
     *
     * @return the left top
     */
    public LocationPoint getLeftTop() {
        return leftTop;
    }

    /**
     * Sets left top.
     *
     * @param leftTop the left top
     */
    public void setLeftTop(LocationPoint leftTop) {
        this.leftTop = leftTop;
    }

    /**
     * Gets right top.
     *
     * @return the right top
     */
    public LocationPoint getRightTop() {
        return rightTop;
    }

    /**
     * Sets right top.
     *
     * @param rightTop the right top
     */
    public void setRightTop(LocationPoint rightTop) {
        this.rightTop = rightTop;
    }

    /**
     * Gets left bottom.
     *
     * @return the left bottom
     */
    public LocationPoint getLeftBottom() {
        return leftBottom;
    }

    /**
     * Sets left bottom.
     *
     * @param leftBottom the left bottom
     */
    public void setLeftBottom(LocationPoint leftBottom) {
        this.leftBottom = leftBottom;
    }

    /**
     * Gets right bottom.
     *
     * @return the right bottom
     */
    public LocationPoint getRightBottom() {
        return rightBottom;
    }

    /**
     * Sets right bottom.
     *
     * @param rightBottom the right bottom
     */
    public void setRightBottom(LocationPoint rightBottom) {
        this.rightBottom = rightBottom;
    }
}
