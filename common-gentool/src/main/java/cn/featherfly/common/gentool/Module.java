
package cn.featherfly.common.gentool;

import java.util.HashSet;
import java.util.Set;

/**
 * GenModule.
 *
 * @author zhongj
 */
public class Module {

    private String packageName;

    private String moduleName;

    private String author;

    private Set<String> includes = new HashSet<>();

    private Set<String> excludes = new HashSet<>();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getIncludes() {
        return includes;
    }

    public void setIncludes(Set<String> includes) {
        this.includes = includes;
    }

    public Set<String> getExcludes() {
        return excludes;
    }

    public void setExcludes(Set<String> excludes) {
        this.excludes = excludes;
    }
}
