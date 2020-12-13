
package cn.featherfly.common.db.mapping.pojo;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Article
 * </p>
 *
 * @author zhongj
 */
@Table(name = "cms_article")
public class Article3 {

    @Id
    private Long id;

    private String title;

    @Embedded
    private Content content2;

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
     * 返回content2
     *
     * @return content2
     */
    public Content getContent2() {
        return content2;
    }

    /**
     * 设置content2
     *
     * @param content2 content2
     */
    public void setContent2(Content content2) {
        this.content2 = content2;
    }
}
