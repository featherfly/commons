
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.ResourceBundleUtils;

/**
 * <p>
 * LoadedMessageLocalizedExceptionCode
 * </p>
 * 
 * @author zhongj
 * @since 1.7
 * @version 1.7
 */
public class LoadedMessageLocalizedExceptionCode extends SimpleLocalizedExceptionCode {
        
    private Class<? extends Throwable> forExceptionType;

    /**
     * 
     * @param forExceptionType forExceptionType 
     * @param module module
     * @param num num 
     * @param key resource key
     */
    public LoadedMessageLocalizedExceptionCode(Class<? extends Throwable> forExceptionType
            , String module, Integer num, String key) {
        this(forExceptionType, module, num, key, new Object[] {});
    }
    /**
     * 
     * @param forExceptionType forExceptionType 
     * @param module module
     * @param num num 
     * @param key resource key
     * @param argus argus
     */
    public LoadedMessageLocalizedExceptionCode(Class<? extends Throwable> forExceptionType
            , String module, Integer num, String key, Object[] argus) {
        this(forExceptionType, module, num, key, null, argus);
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
    public LoadedMessageLocalizedExceptionCode(Class<? extends Throwable> forExceptionType
            , String module, Integer num, String key, Locale locale) {
        this(forExceptionType, module, num, key, locale, new Object[] {});
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
    public LoadedMessageLocalizedExceptionCode(Class<? extends Throwable> forExceptionType
            , String module, Integer num, String key, Locale locale , Object[] argus) {
        super(module, num, key, locale, argus);
        this.forExceptionType = forExceptionType;
        setMessage();
    }
    
    /**
     * 返回forExceptionType
     * @return forExceptionType
     */
    public Class<? extends Throwable> getForExceptionType() {
        return forExceptionType;
    }

    private void setMessage() {
        message = ResourceBundleUtils.getString(forExceptionType, getKey(), getArgus(), getLocale());
    }
}
