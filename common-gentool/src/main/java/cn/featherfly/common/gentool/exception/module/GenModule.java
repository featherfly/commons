
package cn.featherfly.common.gentool.exception.module;

/**
 * <p>
 * GenModule
 * </p>
 *
 * @author zhongj
 */
public class GenModule {

    private ExceptionModule exception;

    private ExceptionCodeModule code;

    private String packageName;

    private String author;

    private String name;

    /**
     * 返回exception
     *
     * @return exception
     */
    public ExceptionModule getException() {
        return exception;
    }

    /**
     * 设置exception
     *
     * @param exception exception
     */
    public void setException(ExceptionModule exception) {
        this.exception = exception;
    }

    /**
     * 返回code
     *
     * @return code
     */
    public ExceptionCodeModule getCode() {
        return code;
    }

    /**
     * 设置code
     *
     * @param code code
     */
    public void setCode(ExceptionCodeModule code) {
        this.code = code;
    }

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
     * @param packageName packageName
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
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
     * @param author author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
