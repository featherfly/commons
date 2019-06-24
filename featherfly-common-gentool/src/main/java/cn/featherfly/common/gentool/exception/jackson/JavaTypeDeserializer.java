
package cn.featherfly.common.gentool.exception.jackson;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import cn.featherfly.common.lang.ClassUtils;
import cn.featherfly.common.structure.HashChainMap;

/**
 * <p>
 * JavaTypeDeserializer
 * </p>
 * 
 * @author zhongj
 */
public class JavaTypeDeserializer extends JsonDeserializer<Class<?>> {

    private static final Map<String, Class<?>> TYPES = new HashChainMap<>();

    static {
        TYPES.put(String.class.getSimpleName(), String.class);
        TYPES.put(String.class.getName(), String.class);
        TYPES.put(Integer.class.getSimpleName(), Integer.class);
        TYPES.put(Integer.class.getName(), Integer.class);
        TYPES.put(Long.class.getSimpleName(), Long.class);
        TYPES.put(Long.class.getName(), Long.class);
        TYPES.put(Double.class.getSimpleName(), Double.class);
        TYPES.put(Double.class.getName(), Double.class);
        TYPES.put(BigDecimal.class.getSimpleName(), BigDecimal.class);
        TYPES.put(BigDecimal.class.getName(), BigDecimal.class);
        TYPES.put(BigInteger.class.getSimpleName(), BigInteger.class);
        TYPES.put(BigInteger.class.getName(), BigInteger.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String text = p.getText();
        Class<?> type = TYPES.get(text);
        if (type != null) {
            return type;
        } else {
            return ClassUtils.forName(text);
        }
    }

}
