
package cn.featherfly.common.serialization;

import java.util.HashMap;
import java.util.Map;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Serialization.
 *
 * @author zhongj
 */
public class Serialization {

    private static final Logger LOG = LoggerFactory.getLogger(Serialization.class);

    private Map<String, Serializer> serializers = new HashMap<>();

    /** The Constant DEFAULT_SERIALIZER. */
    private static final Serializer DEFAULT_SERIALIZER = new JacksonSerializer();

    /** The Constant DEFAULT. */
    private static final Serialization DEFAULT;

    /** The Constant MIME_TYPE_JSON. */
    public static final String MIME_TYPE_JSON = "application/json";

    /** The Constant MIME_TYPE_KRYO. */
    public static final String MIME_TYPE_KRYO = "application/kryo";

    /** The Constant MIME_TYPE_PROTOBUFF. */
    public static final String MIME_TYPE_PROTOBUFF = "application/protobuff";

    /** The Constant MIME_TYPE_XML. */
    public static final String MIME_TYPE_XML = "application/xml";

    static {
        DEFAULT = new Serialization();
        DEFAULT.serializers.put(MIME_TYPE_JSON, DEFAULT_SERIALIZER);
        try {
            Class.forName("io.protostuff.ProtostuffIOUtil");
            DEFAULT.serializers.put(MIME_TYPE_PROTOBUFF, new ProtostuffSerializer());
        } catch (ClassNotFoundException e) {
            LOG.warn(e.getMessage());
        }
        try {
            Class.forName("com.esotericsoftware.kryo.Kryo");
            DEFAULT.serializers.put(MIME_TYPE_KRYO, new KryoSerializer());
        } catch (ClassNotFoundException e) {
            LOG.warn(e.getMessage());
        }
    }

    /**
     * Instantiates a new serialization.
     */
    public Serialization() {

    }

    /**
     * Instantiates a new serialization.
     *
     * @param serializers the serializers
     */
    public Serialization(Map<String, Serializer> serializers) {
        this.serializers.putAll(serializers);
    }

    /**
     * Gets the default.
     *
     * @return the default
     */
    public static Serialization getDefault() {
        return DEFAULT;
    }

    private Serializer _getSerializer(String mimeType) {
        Serializer serializer = serializers.get(mimeType);
        if (serializer == null) {
            throw new SerializationException(SerializationExceptionCode.createNoSerializerForMimeTypeCode(mimeType));
        }
        return serializer;
    }

    /**
     * Gets the serializer.
     *
     * @param mimeType the mime type
     * @return the serializer
     */
    public Serializer getSerializer(String mimeType) {
        return serializers.get(mimeType);
    }

    /**
     * Gets the serializer.
     *
     * @param mimeType the mime type
     * @return the serializer
     */
    public Serializer getSerializer(MimeType mimeType) {
        return serializers.get(mimeType.getBaseType());
    }

    /**
     * Serialize.
     *
     * @param <O>      the generic type
     * @param obj      the obj
     * @param mimeType the mime type
     * @return the byte[]
     */
    public static <O> byte[] serialize(O obj, MimeType mimeType) {
        return serialize(obj, mimeType.getBaseType());
    }

    /**
     * Serialize.
     *
     * @param <O>      the generic type
     * @param obj      the obj
     * @param mimeType the mime type
     * @return the byte[]
     */
    public static <O> byte[] serialize(O obj, String mimeType) {
        Serializer serializer = DEFAULT._getSerializer(mimeType);
        return serializer.serialize(obj);
    }

    /**
     * Deserialize.
     *
     * @param <O>      the generic type
     * @param bytes    the bytes
     * @param type     the type
     * @param mimeType the mime type
     * @return the o
     */
    public static <O> O deserialize(byte[] bytes, Class<O> type, String mimeType) {
        Serializer serializer = DEFAULT._getSerializer(mimeType);
        return serializer.deserialize(bytes, type);
    }

    /**
     * Deserialize.
     *
     * @param <O>      the generic type
     * @param bytes    the bytes
     * @param type     the type
     * @param mimeType the mime type
     * @return the o
     */
    public static <O> O deserialize(byte[] bytes, Class<O> type, MimeType mimeType) {
        return deserialize(bytes, type, mimeType.getBaseType());
    }

    /**
     * Serialize.
     *
     * @param <O> the generic type
     * @param obj the obj
     * @return the byte[]
     */
    public static <O> byte[] serialize(O obj) {
        return DEFAULT_SERIALIZER.serialize(obj);
    }

    /**
     * Deserialize.
     *
     * @param <O>   the generic type
     * @param bytes the bytes
     * @param type  the type
     * @return the o
     */
    public static <O> O deserialize(byte[] bytes, Class<O> type) {
        return DEFAULT_SERIALIZER.deserialize(bytes, type);
    }

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws MimeTypeParseException the mime type parse exception
     */
    public static void main(String[] args) throws MimeTypeParseException {
        MimeType mimeType = new MimeType("application/json; charset=utf-8");
        mimeType.setParameter("charset", "utf-8");
        System.out.println(mimeType);
        System.out.println(mimeType.getBaseType());
        System.out.println(mimeType.getPrimaryType());
        System.out.println(mimeType.getSubType());
        System.out.println(mimeType.getParameters().get("charset"));

    }
}
