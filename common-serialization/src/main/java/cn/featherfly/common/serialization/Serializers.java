
package cn.featherfly.common.serialization;

/**
 * <p>
 * SerializationType
 * </p>
 *
 * @author zhongj
 */
public enum Serializers {

    JSON(new JacksonSerializer(), (byte) 0), PROTOBUF(
            new ProtostuffSerializer(),
            (byte) 1), KRYO(new KryoSerializer(), (byte) 2);

    private Serializers(Serializer serializer, byte key) {
        this.key = key;
        this.serializer = serializer;
    }

    private byte key;

    private Serializer serializer;

    /**
     * 返回key
     * 
     * @return key
     */
    public byte getKey() {
        return key;
    }

    /**
     * 返回serializer
     * 
     * @return serializer
     */
    public Serializer getSerializer() {
        return serializer;
    }
}
