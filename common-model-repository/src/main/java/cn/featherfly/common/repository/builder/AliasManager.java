
package cn.featherfly.common.repository.builder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import cn.featherfly.common.lang.AssertIllegalArgument;

/**
 * AliasManager.
 *
 * @author zhongj
 */
public class AliasManager {

    private Map<String, String> nameAlias = new LinkedHashMap<>();

    private Map<String, Integer> nameAliasSize = new ConcurrentHashMap<>(0);

    /**
     * Generate alias.
     *
     * @param name the name
     * @return the string
     */
    public static String generateAlias(String name) {
        return "_" + name;
    }

    /**
     * Put.
     *
     * @param name the name
     * @return the string
     */
    public String put(String name) {
        int size = getNameSize(name);
        String alias = generateAlias(name) + size;
        put(name, alias, size);
        return alias;
    }

    /**
     * Put name and alias.
     *
     * @param name  the name
     * @param alias the alias
     * @return the alias manager
     */
    public AliasManager put(String name, String alias) {
        Integer size = nameAliasSize.get(name);
        if (size == null) {
            size = 0;
        }
        return put(name, alias, size);
    }

    /**
     * Put name and alias.
     *
     * @param tableNames the table names, alias is key, value is name
     * @return the alias manager
     */
    public AliasManager put(Map<String, String> tableNames) {
        for (Entry<String, String> e : tableNames.entrySet()) {
            put(e.getKey(), e.getValue());
        }
        return this;
    }

    private AliasManager put(String name, String alias, Integer size) {
        size++;
        nameAliasSize.put(name, size);
        nameAlias.put(alias, name);
        return this;
    }

    private int getNameSize(String name) {
        Integer size = nameAliasSize.get(name);
        if (size == null) {
            return 0;
        }
        return size;
    }

    /**
     * Gets the name.
     *
     * @param alias the alias
     * @return the name
     */
    public String getName(String alias) {
        return nameAlias.get(alias);
    }

    /**
     * Gets the alias.
     *
     * @param index the index
     * @return the alias
     */
    public String getAlias(int index) {
        AssertIllegalArgument.isGe(index, 0, "index");
        if (index >= nameAlias.size()) {
            throw new BuilderException(
                    BuilderExceptionCode.createIndexOutOffBoundsNameAliasSizeCode(index, nameAlias.size()));
        }
        int size = 0;
        for (Entry<String, String> entry : nameAlias.entrySet()) {
            if (size == index) {
                return entry.getKey();
            }
            size++;
        }
        return null;
    }

    /**
     * Gets the alias.
     *
     * @param name the name
     * @return the alias
     */
    public String getAlias(String name) {
        int size = getNameSize(name);
        if (size > 1) {
            throw new BuilderException(BuilderExceptionCode.createMulitipleNameCode(name));
        }
        for (Entry<String, String> entry : nameAlias.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * get nameAlias value
     *
     * @return nameAlias
     */
    public Map<String, String> getNameAlias() {
        return nameAlias;
    }
}
