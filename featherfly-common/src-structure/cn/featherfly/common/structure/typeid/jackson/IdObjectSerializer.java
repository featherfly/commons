package cn.featherfly.common.structure.typeid.jackson;

import java.io.IOException;

import cn.featherfly.common.structure.typeid.IdObject;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class IdObjectSerializer<ID extends IdObject> extends JsonSerializer<ID> {    
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(ID value, JsonGenerator gen,
            SerializerProvider serializers) throws IOException,
            JsonProcessingException {
        if (value.getValue() instanceof Number) {
            gen.writeRawValue(value.toString());
        } else {
            gen.writeString(value.toString());
        }
    }
}