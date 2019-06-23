
package cn.featherfly.common.exception;

import java.util.Locale;

/**
 * <p>
 * SimpleLocalizedExceptionCode
 * </p>
 * 
 * @author 钟冀
 * @since 1.7
 * @version 1.7
 */
public class SimpleLocalizedExceptionCode extends SimpleExceptionCode
        implements LocalizedExceptionCode {

    private Object[] argus;
    
    private String key;
    
    private Locale locale;

    /**
     * 
     * @param module module
     * @param num num 
     * @param key resource key
     * @param argus argus
     */
    public SimpleLocalizedExceptionCode(String module, Integer num, String key, Object...argus) {
        this(module, num, key, null, new Object[] {});
    }
    
    /**
     * 
     * @param module module
     * @param num num 
     * @param key resource key
     * @param locale locale
     * @param argus argus
     */
    public SimpleLocalizedExceptionCode(String module, Integer num, String key, Locale locale , Object...argus) {
        super(module, num, null);
        this.argus = argus;
        this.key = key;
        this.locale = locale;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] getArgus() {
        return argus;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey() {
        return key;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Locale getLocale() {
        return locale;
    }
}
