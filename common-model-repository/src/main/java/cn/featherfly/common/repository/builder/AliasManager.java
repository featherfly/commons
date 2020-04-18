
package cn.featherfly.common.repository.builder;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * AliasManager
 * </p>
 *
 * @author zhongj
 */
public class AliasManager {

    private Map<String, String> nameAlias = new LinkedHashMap<>();

    private Map<String, Integer> nameAliasSize = new ConcurrentHashMap<>(0);

    public static String generateAlias(String name) {
        return "_" + name;
    }

    public String put(String name) {
        Integer size = nameAliasSize.get(name);
        if (size == null) {
            size = 0;
        }
        String alias = generateAlias(name) + size;
        put(name, alias, size);
        return alias;
    }

    public AliasManager put(String name, String alias) {
        Integer size = nameAliasSize.get(name);
        if (size == null) {
            size = 0;
        }
        return put(name, alias, size);
    }

    private AliasManager put(String name, String alias, Integer size) {
        size++;
        nameAliasSize.put(name, size);
        nameAlias.put(alias, name);
        return this;
    }

    public String getName(String alias) {
        return nameAlias.get(alias);
    }

    public String getAlias(int index) {
        if (index > nameAlias.size()) {
            throw new BuilderException(index + " > name alias size " + nameAlias.size());
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

    public String getAlias(String name) {
        for (Entry<String, String> entry : nameAlias.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

}
