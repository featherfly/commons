package cn.featherfly.common.repository;

import java.util.function.Predicate;

import cn.featherfly.common.lang.Lang;

/**
 * The Enum IgnorePolicy.
 *
 * @author zhongj
 */
public enum IgnorePolicy implements Predicate<Object> {

    /** not ignore. */
    NONE,

    /** ignore null. */
    NULL,

    /** ignore null and empty (String Array Collection Map size = 0). */
    EMPTY;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test(Object t) {
        switch (this) {
            case NULL:
                return t == null;
            case EMPTY:
                return Lang.isEmpty(t);
            default:
                return false;
        }
    }
}