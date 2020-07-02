
package cn.featherfly.common.db.mapping;

import java.io.Serializable;

/**
 * <p>
 * Article
 * </p>
 *
 * @author zhongj
 */
public class Content implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8784165418053178812L;

    private String title;

    private String descp;

    private String img;

    /**
     * 返回title
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置title
     *
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 返回descp
     *
     * @return descp
     */
    public String getDescp() {
        return descp;
    }

    /**
     * 设置descp
     *
     * @param descp descp
     */
    public void setDescp(String descp) {
        this.descp = descp;
    }

    /**
     * 返回img
     *
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置img
     *
     * @param img img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Content [title=" + title + ", descp=" + descp + ", img=" + img + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (descp == null ? 0 : descp.hashCode());
        result = prime * result + (img == null ? 0 : img.hashCode());
        result = prime * result + (title == null ? 0 : title.hashCode());
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
        Content other = (Content) obj;
        if (descp == null) {
            if (other.descp != null) {
                return false;
            }
        } else if (!descp.equals(other.descp)) {
            return false;
        }
        if (img == null) {
            if (other.img != null) {
                return false;
            }
        } else if (!img.equals(other.img)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        return true;
    }

}
