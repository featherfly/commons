
package cn.featherfly.common.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * JacksonSerializer
 * </p>
 *
 * @author zhongj
 */
public class JacksonSerializer implements Serializer {

    private ObjectMapper mapper;

    /**
     */
    public JacksonSerializer() {
        mapper = new ObjectMapper();
    }

    /**
     * @param mapper
     */
    protected JacksonSerializer(ObjectMapper mapper) {
        super();
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O> byte[] serialize(O obj) {
        try {
            return mapper.writerFor(obj.getClass()).writeValueAsBytes(obj);
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
