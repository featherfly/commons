
package cn.featherfly.common.exception;

import java.util.Locale;

/**
 * <p>
 * PracticeExceptionCode
 * </p>
 * 
 * @author featherfly
 */
public class PracticeExceptionCode extends cn.featherfly.common.exception.SimpleLocalizedExceptionCode {

    private static final String MODULE = "PA";
    
    /**
     * @param num num
     * @param key key
     * @param argus argus
     */
    public PracticeExceptionCode(Integer num, String key) {
        this(num, key, new Object[] {});
    }
    /**
     * @param num num
     * @param key key
     * @param argus argus
     */
    public PracticeExceptionCode(Integer num, String key, Object[] argus) {
        this(num, key, null, argus);
    }
    
    /**
     * @param num num
     * @param key  key
     * @param locale locale
     * @param argus argus
     */
    public PracticeExceptionCode(Integer num, String key, Locale locale) {
        this(num, key, locale, new Object[]{});
    }
    
    /**
     * @param num num
     * @param key  key
     * @param locale locale
     * @param argus argus
     */
    public PracticeExceptionCode(Integer num, String key, Locale locale, Object[] argus) {
        super(MODULE, num, key, locale, argus);
    }
   
    
    /**
     * <p>
     * create NoPropertyCode
     * </p>
     * @param className className
     * @param propertyName propertyName
     * 
     * @return NoPropertyCode
     */
    public static PracticeExceptionCode createNoPropertyCode(java.lang.String className, java.lang.String propertyName) {
        return new PracticeExceptionCode(10000, "no_property",
                new Object[] {className, propertyName});
    }
    
    /**
     * <p>
     * create PropertyAccessCode
     * </p>
     * @param className className
     * @param propertyName propertyName
     * 
     * @return PropertyAccessCode
     */
    public static PracticeExceptionCode createPropertyAccessCode(java.lang.String className, java.lang.String propertyName) {
        return new PracticeExceptionCode(10001, "property_access",
                new Object[] {className, propertyName});
    }
    
    /**
     * <p>
     * create NotNullCode
     * </p>
     * 
     * @return NotNullCode
     */
    public static PracticeExceptionCode createNotNullCode() {
        return new PracticeExceptionCode(10002, "not_null");
    }
    
}
