
package cn.featherfly.common.db.mapping.pojo;

import javax.persistence.Table;

/**
 * <p>
 * Article
 * </p>
 *
 * @author zhongj
 */
@Table(name = "cms_article")
public class Article {

    private Long id;

    private String title;

    private Long[] content;

    /**
     * 返回id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

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
     * 返回content
     *
     * @return content
     */
    public Long[] getContent() {
        return content;
    }

    /**
     * 设置content
     *
     * @param content content
     */
    public void setContent(Long[] content) {
        this.content = content;
    }

}
