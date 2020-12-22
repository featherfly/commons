
package cn.featherfly.common.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * <p>
 * JacksonSerializer
 * </p>
 * .
 *
 * @author zhongj
 */
public class JacksonXmlSerializer implements Serializer {

    /** The Constant DEFAULT_MAPPER. */
    public static final XmlMapper DEFAULT_MAPPER;

    static {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        DEFAULT_MAPPER = new XmlMapper(xmlModule);
        DEFAULT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        DEFAULT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private XmlMapper mapper;

    /**
     * Instantiates a new jackson xml serializer.
     */
    public JacksonXmlSerializer() {
        this(DEFAULT_MAPPER);
    }

    /**
     * Instantiates a new jackson xml serializer.
     *
     * @param mapper the mapper
     */
    public JacksonXmlSerializer(XmlMapper mapper) {
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
