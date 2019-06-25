
package cn.featherfly.common.gentool.exception.gencode;

import java.util.Locale;

/**
 * <p>
 * HelloExceptionCode
 * </p>
 * 
 * @author featherfly
 */
public class GenExceptionCode extends cn.featherfly.common.exception.SimpleLocalizedExceptionCode {

    private static final String MODULE = "HE";
    
    /**
     * @param num num
     * @param key key
     * @param argus argus
     */
    public GenExceptionCode(Integer num, String key) {
        this(num, key, new Object[] {});
    }
    /**
     * @param num num
     * @param key key
     * @param argus argus
     */
    public GenExceptionCode(Integer num, String key, Object[] argus) {
        this(num, key, null, argus);
    }
    
    /**
     * @param num num
     * @param key  key
     * @param locale locale
     * @param argus argus
     */
    public GenExceptionCode(Integer num, String key, Locale locale) {
        this(num, key, locale, new Object[]{});
    }
    
    /**
     * @param num num
     * @param key  key
     * @param locale locale
     * @param argus argus
     */
    public GenExceptionCode(Integer num, String key, Locale locale, Object[] argus) {
        super(MODULE, num, key, locale, argus);
    }
    
    public enum GenExceptionCodes {
        
        HE10000("no_property", 10000),
        HE10001("property_access", 10001),
        HE10002("not_null", 10002);
                
        private String key;
        
        private Integer num;
        
        /**
         * @param key key
         * @param num num
         */
        private GenExceptionCodes(String key, Integer num) {
            this.key = key;
            this.num = num;
        }
        /**
         * get key
         * @return key
         */
        public String getKey() {
            return key;
        }
        /**
         * get num
         * @return num
         */
        public Integer getNum() {
            return num;
        }
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
    public static GenExceptionCode createNoPropertyCode(
        java.lang.String className, java.lang.String propertyName) {
        return new GenExceptionCode(GenExceptionCodes.HE10000.num
                , GenExceptionCodes.HE10000.key
                , new Object[] {className, propertyName});
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
    public static GenExceptionCode createPropertyAccessCode(
        java.lang.String className, java.lang.String propertyName) {
        return new GenExceptionCode(GenExceptionCodes.HE10001.num
                , GenExceptionCodes.HE10001.key
                , new Object[] {className, propertyName});
    }
    
    /**
     * <p>
     * create NotNullCode
     * </p>
     * 
     * @return NotNullCode
     */
    public static GenExceptionCode createNotNullCode(
        ) {
        return new GenExceptionCode(GenExceptionCodes.HE10002.num
                , GenExceptionCodes.HE10002.key);
    }    
}
