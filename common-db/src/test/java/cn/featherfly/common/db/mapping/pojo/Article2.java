
package cn.featherfly.common.db.mapping.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

/**
 * Article2.
 *
 * @author zhongj
 */
@Table(name = "cms_article")
public class Article2 {

    private Long id;

    private String title;

    private Content[] content;

    private List<Content> content2 = new ArrayList<>();

    private Map<String, Content> content3 = new HashMap<>();

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
     * get content value
     *
     * @return content
     */
    public Content[] getContent() {
        return content;
    }

    /**
     * set content value
     *
     * @param content content
     */
    public void setContent(Content[] content) {
        this.content = content;
    }

    /**
     * get content2 value
     *
     * @return content2
     */
    public List<Content> getContent2() {
        return content2;
    }

    /**
     * set content2 value
     *
     * @param content2 content2
     */
    public void setContent2(List<Content> content2) {
        this.content2 = content2;
    }

    /**
     * get content3 value
     *
     * @return content3
     */
    public Map<String, Content> getContent3() {
        return content3;
    }

    /**
     * set content3 value
     *
     * @param content3 content3
     */
    public void setContent3(Map<String, Content> content3) {
        this.content3 = content3;
    }

}
