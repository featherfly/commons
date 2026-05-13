
package cn.featherfly.common.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * JacksonXmlSerializer .
 *
 * @author zhongj
 */
public class JacksonXmlSerializer extends AbstractSerializer {

    private enum SingleXmlMapper {
        INSTANCE;

        SingleXmlMapper() {
            JacksonXmlModule xmlModule = new JacksonXmlModule();
            xmlModule.setDefaultUseWrapper(false);
            mapper = new XmlMapper(xmlModule);
            mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_EMPTY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        private final XmlMapper mapper;
    }

    private final XmlMapper mapper;

    /**
     * Instantiates a new jackson xml serializer.
     */
    public JacksonXmlSerializer() {
        this(SingleXmlMapper.INSTANCE.mapper);
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
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os, charset);) {
            mapper.writerFor(obj.getClass()).writeValue(writer, obj);
            return os.toByteArray();
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
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
            InputStreamReader reader = new InputStreamReader(is, charset);) {
            return mapper.readerFor(type).readValue(reader);
        } catch (Exception e) {
            throw new SerializationException(
                SerializationExceptionCode.createDeserializeErrorCode(type.getName(), e.getLocalizedMessage()), e);
        }
    }

}
