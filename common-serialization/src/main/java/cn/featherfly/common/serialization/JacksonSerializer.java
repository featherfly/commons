
package cn.featherfly.common.serialization;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JacksonSerializer .
 *
 * @author zhongj
 */
public class JacksonSerializer extends AbstractSerializer {

    /** The Constant DEFAULT_MAPPER. */
    public static final ObjectMapper DEFAULT_MAPPER;

    static {
        DEFAULT_MAPPER = new ObjectMapper();
        DEFAULT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        DEFAULT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ObjectMapper mapper;

    /**
     * Instantiates a new jackson serializer.
     */
    public JacksonSerializer() {
        this(DEFAULT_MAPPER);
    }

    /**
     * Instantiates a new jackson serializer.
     *
     * @param mapper the mapper
     */
    public JacksonSerializer(ObjectMapper mapper) {
        super();
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O> byte[] serialize(O obj) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os, charset);
            mapper.writerFor(obj.getClass()).writeValue(writer, obj);
            return os.toByteArray();
            //            return mapper.writerFor(obj.getClass()).writeValueAsBytes(obj);
        } catch (Exception e) {
            throw new SerializationException(SerializationExceptionCode
                    .createSerializeErrorCode(obj.getClass().getName(), e.getLocalizedMessage()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O> O deserialize(byte[] bytes, Class<O> type) {
        try {
            return mapper.readerFor(type).readValue(bytes);
        } catch (Exception e) {
            throw new SerializationException(
                    SerializationExceptionCode.createDeserializeErrorCode(type.getName(), e.getLocalizedMessage()), e);
        }
    }

}
