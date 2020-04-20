
package cn.featherfly.common.gentool;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * GenModule
 * </p>
 *
 * @author zhongj
 */
public class Module {

    public String packageName;

    public String moduleName;

    public String author;

    public Set<String> includes = new HashSet<>();

    public Set<String> excludes = new HashSet<>();
}
