
/*
 * All rights Reserved, Designed By zhongj
 * @Title: StringLinker.java
 * @Package cn.featherfly.common.lang.string
 * @Description: StringLinker
 * @author: zhongj
 * @date: 2022-10-14 14:23:14
 * @Copyright: 2022 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.lang.string;

import cn.featherfly.common.lang.Lang;
import cn.featherfly.common.lang.Strings;

/**
 * StringLinker.
 *
 * @author zhongj
 */
public class StringLinker {

    private boolean ignoreDuplicate = true;

    private boolean ignoreEmpty = true;

    private String separator;

    private StringBuilder value = new StringBuilder();

    /**
     * Instantiates a new string linker.
     *
     * @param separator the separator
     */
    public StringLinker(String separator) {
        this(separator, true, true);
    }

    /**
     * Instantiates a new string linker.
     *
     * @param separator       the separator
     * @param ignoreDuplicate the ignore duplicate
     */
    public StringLinker(String separator, boolean ignoreDuplicate) {
        this(separator, ignoreDuplicate, true);
    }

    /**
     * Instantiates a new string linker.
     *
     * @param separator       the separator
     * @param ignoreDuplicate the ignore duplicate
     * @param ignoreEmpty     the ignore empty
     */
    public StringLinker(String separator, boolean ignoreDuplicate, boolean ignoreEmpty) {
        super();
        this.separator = separator;
        this.ignoreDuplicate = ignoreDuplicate;
        this.ignoreEmpty = ignoreEmpty;
    }

    public StringLinker link(String... values) {
        if (values.length == 1) {
            value.append(values[0]);
        } else {
            value.append(ignoreDuplicate ? trimEndSeparator(values[0]) : values[0]);
            for (int i = 1; i < values.length - 1; i++) {
                if (ignoreEmpty && Lang.isEmpty(values[i])) {
                    continue;
                }
                value.append(separator).append(ignoreDuplicate ? trimStartAndEndSeparator(values[i]) : values[i]);
            }
            if (ignoreEmpty && Lang.isEmpty(values[values.length - 1])) {
                return this;
            }
            value.append(separator).append(
                    ignoreDuplicate ? trimStartSeparator(values[values.length - 1]) : values[values.length - 1]);
        }
        return this;
    }

    public String getValue() {
        return value.toString();
    }

    @Override
    public String toString() {
        return getValue();
    }

    private String trimStartSeparator(String str) {
        return Strings.trimBegin(str, separator);
    }

    private String trimEndSeparator(String str) {
        return Strings.trimEnd(str, separator);
    }

    private String trimStartAndEndSeparator(String str) {
        return Strings.trimBeginEnd(str, separator);
    }
}
