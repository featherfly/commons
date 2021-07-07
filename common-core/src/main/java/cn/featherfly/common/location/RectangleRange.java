package cn.featherfly.common.location;

/**
 * 矩形范围
 */
public class RectangleRange {

    /* 左上角 */
    private LocationPoint leftTop;
    /* 右上角 */
    private LocationPoint rightTop;
    /* 左下角 */
    private LocationPoint leftBottom;
    /* 右下角 */
    private LocationPoint rightBottom;

    /**
     * Instantiates a new Rectangle Range.
     */
    public RectangleRange() {
    }

    /**
     * Instantiates a new Rectangle Range.
     *
     * @param leftTop     the left top
     * @param rightTop    the right top
     * @param leftBottom  the left bottom
     * @param rightBottom the right bottom
     */
    public RectangleRange(LocationPoint leftTop, LocationPoint rightTop, LocationPoint leftBottom,
            LocationPoint rightBottom) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RectangleRange [leftTop=" + leftTop + ", rightTop=" + rightTop + ", leftBottom=" + leftBottom
                + ", rightBottom=" + rightBottom + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (leftBottom == null ? 0 : leftBottom.hashCode());
        result = prime * result + (leftTop == null ? 0 : leftTop.hashCode());
        result = prime * result + (rightBottom == null ? 0 : rightBottom.hashCode());
        result = prime * result + (rightTop == null ? 0 : rightTop.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RectangleRange other = (RectangleRange) obj;
        if (leftBottom == null) {
            if (other.leftBottom != null) {
                return false;
            }
        } else if (!leftBottom.equals(other.leftBottom)) {
            return false;
        }
        if (leftTop == null) {
            if (other.leftTop != null) {
                return false;
            }
        } else if (!leftTop.equals(other.leftTop)) {
            return false;
        }
        if (rightBottom == null) {
            if (other.rightBottom != null) {
                return false;
            }
        } else if (!rightBottom.equals(other.rightBottom)) {
            return false;
        }
        if (rightTop == null) {
            if (other.rightTop != null) {
                return false;
            }
        } else if (!rightTop.equals(other.rightTop)) {
            return false;
        }
        return true;
    }
}
