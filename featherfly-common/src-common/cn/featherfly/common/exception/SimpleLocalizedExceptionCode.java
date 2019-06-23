
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.ResourceBundleUtils;

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
    
    private Class<? extends Throwable> forExceptionType;
    
    private Locale locale;

    /**
     * 
     * @param forExceptionType forExceptionType 
     * @param module module
     * @param num num 
     * @param key resource key
     * @param argus argus
     */
    public SimpleLocalizedExceptionCode(Class<? extends Throwable> forExceptionType
            , String module, Integer num, String key, Object...argus) {
        this(forExceptionType, module, num, key, null, new Object[] {});
    }
    
    /**
     * 
     * @param forExceptionType forExceptionType
     * @param module module
     * @param num num 
     * @param key resource key
     * @param locale locale
     * @param argus argus
     */
    public SimpleLocalizedExceptionCode(Class<? extends Throwable> forExceptionType
            , String module, Integer num, String key, Locale locale , Object...argus) {
        super(module, num, null);
        this.argus = argus;
        this.key = key;
        this.forExceptionType = forExceptionType;
        this.locale = locale;
        setMessage();
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
     * 返回forExceptionType
     * @return forExceptionType
     */
    public Class<? extends Throwable> getForExceptionType() {
        return forExceptionType;
    }

    /**
     * 返回locale
     * @return locale
     */
    public Locale getLocale() {
        return locale;
    }

    private void setMessage() {
        message = ResourceBundleUtils.getString(forExceptionType, key, argus, locale);
    }
}
