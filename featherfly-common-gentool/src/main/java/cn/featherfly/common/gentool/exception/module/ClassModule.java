
package cn.featherfly.common.gentool.exception.module;

/**
 * <p>
 * ClassModule
 * </p>
 * 
 * @author 钟冀
 */
public class ClassModule {

    private Class<?> parent;

    private String packageName;

    private String name;

    private String descp;

    private String author;

    /**
     * 返回packageName
     * 
     * @return packageName
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 设置packageName
     * 
     * @param packageName
     *            packageName
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 返回name
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * 
     * @param name
     *            name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param descp
     *            descp
     */
    public void setDescp(String descp) {
        this.descp = descp;
    }

    /**
     * 返回author
     * 
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置author
     * 
     * @param author
     *            author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 返回parent
     * 
     * @return parent
     */
    public Class<?> getParent() {
        return parent;
    }

    /**
     * 设置parent
     * 
     * @param parent
     *            parent
     */
    public void setParent(Class<?> parent) {
        this.parent = parent;
    }

}
