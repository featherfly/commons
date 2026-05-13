
/*
 * All rights Reserved, Designed By zhongj
 * @Description:
 * @author: zhongj
 * @date: 2026-05-12 17:04:12
 * @Copyright: 2026 www.featherfly.cn Inc. All rights reserved.
 */
package cn.featherfly.common.serialization;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.activation.MimeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.featherfly.common.lang.AssertIllegalArgument;
import cn.featherfly.common.lang.Lang;

/**
 * SerializableStrategy.
 *
 * @author zhongj
 */
public class SerializableStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(SerializableStrategy.class);

    private final Serializer defaultSerializer;

    private final Map<String, Serializer> serializers = new HashMap<>();

    private Charset charset;

    /**
     * Instantiates a new serializable strategy.
     *
     * @param serializers the serializers
     * @param defaultSerializer the default serializer
     * @param charset the charset
     */
    public SerializableStrategy(Serializer defaultSerializer) {
        this(defaultSerializer, null, StandardCharsets.UTF_8);
    }

    /**
     * Instantiates a new serializable strategy.
     *
     * @param serializers the serializers
     * @param defaultSerializer the default serializer
     * @param charset the charset
     */
    public SerializableStrategy(Serializer defaultSerializer, Map<String, Serializer> serializers) {
        this(defaultSerializer, serializers, StandardCharsets.UTF_8);
    }

    /**
     * Instantiates a new serializable strategy.
     *
     * @param serializers the serializers
     * @param defaultSerializer the default serializer
     * @param charset the charset
     */
    public SerializableStrategy(Serializer defaultSerializer, Map<String, Serializer> serializers, Charset charset) {
        super();
        AssertIllegalArgument.isNotNull(defaultSerializer, "defaultSerializer");
        this.defaultSerializer = defaultSerializer;
        if (Lang.isNotEmpty(serializers)) {
            this.serializers.putAll(serializers);
        }
        // setCharset method handle serializer charset
        setCharset(charset);
    }

    private Serializer getExistsSerializer(String mimeType) {
        Serializer serializer = serializers.get(mimeType);
        if (serializer == null) {
            throw new SerializationException(SerializationExceptionCode.createNoSerializerForMimeTypeCode(mimeType));
        }
        return serializer;
    }

    protected void addSerializer(String mimeType, Serializer serializer) {
        serializers.put(mimeType, serializer);
        if (serializer instanceof AbstractSerializer) {
            ((AbstractSerializer) serializer).setCharset(charset);
        }
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
     * get charset value
     *
     * @return charset
     */
    public Charset getCharset() {
        return charset;
    }

    /**
     * set charset value
     *
     * @param charset charset
     */
    public void setCharset(Charset charset) {
        AssertIllegalArgument.isNotNull(charset, "charset");
        this.charset = charset;
        // handle serializer charset
        for (Serializer serializer : serializers.values()) {
            if (serializer instanceof AbstractSerializer) {
                ((AbstractSerializer) serializer).setCharset(charset);
            }
        }
        // handle serializer charset
        if (defaultSerializer instanceof AbstractSerializer) {
            ((AbstractSerializer) defaultSerializer).setCharset(this.charset);
        }
    }

    /**
     * Serialize.
     *
     * @param <O> the generic type
     * @param obj the obj
     * @param mimeType the mime type
     * @return the byte[]
     */
    public <O> byte[] serialize(O obj, MimeType mimeType) {
        return serialize(obj, mimeType.getBaseType());
    }

    /**
     * Serialize.
     *
     * @param <O> the generic type
     * @param obj the obj
     * @param mimeType the mime type
     * @return the byte[]
     */
    public <O> byte[] serialize(O obj, String mimeType) {
        Serializer serializer = getExistsSerializer(mimeType);
        LOG.debug("serialize with {} for mimeType {}", serializer.getClass().getName(), mimeType);
        return serializer.serialize(obj);
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
    public <O> O deserialize(byte[] bytes, Class<O> type, String mimeType) {
        Serializer serializer = getExistsSerializer(mimeType);
        LOG.debug("deserialize with {} for mimeType {} ", serializer.getClass().getName(), mimeType);
        return serializer.deserialize(bytes, type);
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
    public <O> O deserialize(byte[] bytes, Class<O> type, MimeType mimeType) {
        return deserialize(bytes, type, mimeType.getBaseType());
    }

    /**
     * Serialize.
     *
     * @param <O> the generic type
     * @param obj the obj
     * @return the byte[]
     */
    public <O> byte[] serialize(O obj) {
        LOG.debug("serialize with {}", defaultSerializer.getClass().getName());
        return defaultSerializer.serialize(obj);
    }

    /**
     * Deserialize.
     *
     * @param <O> the generic type
     * @param bytes the bytes
     * @param type the type
     * @return the o
     */
    public <O> O deserialize(byte[] bytes, Class<O> type) {
        LOG.debug("deserialize with {}", defaultSerializer.getClass().getName());
        return defaultSerializer.deserialize(bytes, type);
    }

}
