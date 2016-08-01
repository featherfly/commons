package cn.featherfly.common.structure.typevalue.jackson;

import java.io.IOException;

import cn.featherfly.common.structure.typevalue.TypeValue;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class TypeValueSerializer<T extends TypeValue<?>> extends JsonSerializer<T> {    
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(T type, JsonGenerator gen,
            SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        Object value = type.getValue();
        if (value == null) {
            gen.writeString("");
        } else {
            if (value instanceof Number) {
                gen.writeRawValue(value.toString());
            } else {
                gen.writeString(value.toString());
            }            
        }
    }
}