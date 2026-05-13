
package cn.featherfly.common.serialization;

import java.util.HashMap;
import java.util.Map;

import javax.activation.MimeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.ClassUtils;

/**
 * Serialization.
 *
 * @author zhongj
 */
public class Serialization {

    private static final Logger LOG = LoggerFactory.getLogger(Serialization.class);

    private static final SerializableStrategy DEFAULT_STRATEGY = init();

    /** The Constant MIME_TYPE_JSON. */
    public static final String MIME_TYPE_JSON = "application/json";

    /** The Constant MIME_TYPE_XML. */
    public static final String MIME_TYPE_XML = "application/xml";

    /** The Constant MIME_TYPE_KRYO. */
    public static final String MIME_TYPE_KRYO = "application/kryo";

    /** The Constant MIME_TYPE_PROTOBUFF. */
    public static final String MIME_TYPE_PROTOBUFF = "application/protobuff";

    private Serialization() {
    }

    private static SerializableStrategy init() {
        Serializer defaultSerializer = new JacksonSerializer();
        Map<String, Serializer> serializers = new HashMap<>();
        serializers.put(MIME_TYPE_JSON, defaultSerializer);
        try {
            Class.forName("com.fasterxml.jackson.dataformat.xml.XmlMapper");
            serializers.put(MIME_TYPE_XML, (Serializer) ClassUtils
                .newInstance(ClassUtils.forName("cn.featherfly.common.serialization.JacksonXmlSerializer")));
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }
        try {
            Class.forName("io.protostuff.ProtostuffIOUtil");
            serializers.put(MIME_TYPE_PROTOBUFF, (Serializer) ClassUtils
                .newInstance(ClassUtils.forName("cn.featherfly.common.serialization.ProtostuffSerializer")));
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }
        try {
            Class.forName("com.esotericsoftware.kryo.Kryo");
            serializers.put(MIME_TYPE_KRYO, (Serializer) ClassUtils
                .newInstance(ClassUtils.forName("cn.featherfly.common.serialization.KryoSerializer")));
        } catch (Exception e) {
            LOG.warn(e.getMessage());
        }
        return new SerializableStrategy(defaultSerializer, serializers);
    }

    /**
     * Gets the default.
     *
     * @return the default
     */
    public static SerializableStrategy getDefault() {
        return DEFAULT_STRATEGY;
    }

    /**
     * Serialize.
     *
     * @param <O> the generic type
     * @param obj the obj
     * @param mimeType the mime type
     * @return the byte[]
     */
    public static <O> byte[] serialize(O obj, MimeType mimeType) {
        return DEFAULT_STRATEGY.serialize(obj, mimeType.getBaseType());
    }

    /**
     * Serialize.
     *
     * @param <O> the generic type
     * @param obj the obj
     * @param mimeType the mime type
     * @return the byte[]
     */
    public static <O> byte[] serialize(O obj, String mimeType) {
        return DEFAULT_STRATEGY.serialize(obj, mimeType);
    }

    /**
     * Deserialize.
     *
     * @param <O> the generic type
     * @param bytes the bytes
     * @param type the type
     * @param mimeType the mime type
     * @return the o
     */
    public static <O> O deserialize(byte[] bytes, Class<O> type, String mimeType) {
        return DEFAULT_STRATEGY.deserialize(bytes, type, mimeType);
    }

    /**
     * Deserialize.
     *
     * @param <O> the generic type
     * @param bytes the bytes
     * @param type the type
     * @param mimeType the mime type
     * @return the o
     */
    public static <O> O deserialize(byte[] bytes, Class<O> type, MimeType mimeType) {
        return DEFAULT_STRATEGY.deserialize(bytes, type, mimeType);
    }

    /**
     * Serialize.
     *
     * @param <O> the generic type
     * @param obj the obj
     * @return the byte[]
     */
    public static <O> byte[] serialize(O obj) {
        return DEFAULT_STRATEGY.serialize(obj);
    }

    /**
     * Deserialize.
     *
     * @param <O> the generic type
     * @param bytes the bytes
     * @param type the type
     * @return the o
     */
    public static <O> O deserialize(byte[] bytes, Class<O> type) {
        return DEFAULT_STRATEGY.deserialize(bytes, type);
    }
}
