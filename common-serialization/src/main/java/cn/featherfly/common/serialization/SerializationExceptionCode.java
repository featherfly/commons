
package cn.featherfly.common.serialization;

import java.util.Locale;

/**
 * <p>
 * SerializationExceptionCode
 * </p>
 * 
 * @author zhongj
 */
public class SerializationExceptionCode extends cn.featherfly.common.exception.SimpleLocalizedExceptionCode {

    private static final String MODULE = "EASYSOCKET";
    
    /**
     * @param num num
     * @param key key
     */
    public SerializationExceptionCode(Integer num, String key) {
        this(num, key, new Object[] {});
    }
    /**
     * @param num num
     * @param key key
     * @param argus argus
     */
    public SerializationExceptionCode(Integer num, String key, Object[] argus) {
        this(num, key, null, argus);
    }
    
    /**
     * @param num num
     * @param key  key
     * @param locale locale
     */
    public SerializationExceptionCode(Integer num, String key, Locale locale) {
        this(num, key, locale, new Object[]{});
    }
    
    /**
     * @param num num
     * @param key  key
     * @param locale locale
     * @param argus argus
     */
    public SerializationExceptionCode(Integer num, String key, Locale locale, Object[] argus) {
        super(MODULE, num, key, locale, argus);
    }
    
    public enum SerializationExceptionCodes {
        
        EASYSOCKET10001("serialize_error", 10001),
        EASYSOCKET10002("deserialize_error", 10002),
        EASYSOCKET10003("no_serializer_for_mime_type", 10003);
                
        private String key;
        
        private Integer num;
        
        /**
         * @param key key
         * @param num num
         */
        private SerializationExceptionCodes(String key, Integer num) {
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
     * create SerializeErrorCode
     * </p>
     * @param className className
     * @param errorMessage errorMessage
     * 
     * @return SerializeErrorCode
     */
    public static SerializationExceptionCode createSerializeErrorCode(
        java.lang.String className, java.lang.String errorMessage) {
        return new SerializationExceptionCode(SerializationExceptionCodes.EASYSOCKET10001.num
                , SerializationExceptionCodes.EASYSOCKET10001.key
                , new Object[] {className, errorMessage});
    }
    
    /**
     * <p>
     * create DeserializeErrorCode
     * </p>
     * @param className className
     * @param errorMessage errorMessage
     * 
     * @return DeserializeErrorCode
     */
    public static SerializationExceptionCode createDeserializeErrorCode(
        java.lang.String className, java.lang.String errorMessage) {
        return new SerializationExceptionCode(SerializationExceptionCodes.EASYSOCKET10002.num
                , SerializationExceptionCodes.EASYSOCKET10002.key
                , new Object[] {className, errorMessage});
    }
    
    /**
     * <p>
     * create NoSerializerForMimeTypeCode
     * </p>
     * @param mimeType mimeType
     * 
     * @return NoSerializerForMimeTypeCode
     */
    public static SerializationExceptionCode createNoSerializerForMimeTypeCode(
        java.lang.String mimeType) {
        return new SerializationExceptionCode(SerializationExceptionCodes.EASYSOCKET10003.num
                , SerializationExceptionCodes.EASYSOCKET10003.key
                , new Object[] {mimeType});
    }
    
}
