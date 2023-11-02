package cn.featherfly.common.locale;

import java.util.Locale;

/**
 * DefaultLocaleManager.
 *
 * @author zhongj
 */
public class DefaultLocaleManager implements LocaleManager {

    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return Locale.getDefault();
    }

}
