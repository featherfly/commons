package cn.featherfly.common.locale;

import java.util.Locale;

/**
 * <p>
 * SimpleLocaleManager
 * </p>
 * 
 * @author 钟冀
 */
public class DefaultLocaleManager implements LocaleManager{

    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return Locale.getDefault();
    }
   
}
