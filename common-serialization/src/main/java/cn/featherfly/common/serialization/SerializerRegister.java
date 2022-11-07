
package cn.featherfly.common.serialization;

import java.util.HashMap;
import java.util.Map;

/**
 * SerializerRegister.
 *
 * @author zhongj
 */
public class SerializerRegister {

    private Map<Byte, Serializer> serializers = new HashMap<>();

    public SerializerRegister register(Serializers serializers) {
        return register(serializers.getSerializer(), serializers.getKey());
    }

    public SerializerRegister register(Serializer serializer, byte key) {
        if (serializers.containsKey(key)) {
            //            throw new CodecException(CodecExceptionCode.createKeyAlreadyRegisteredForSerializerCode(key,
            //                    serializer.getClass().getName()));
        }
        serializers.put(key, serializer);
        return this;
    }

    public SerializerRegister unregister(Serializer serializer) {
        if (serializers.containsValue(serializer)) {
            Byte key = null;
            for (Map.Entry<Byte, Serializer> entry : serializers.entrySet()) {
                Serializer value = entry.getValue();
                if (value == serializer) {
                    key = entry.getKey();
                    break;
                }
            }
            serializers.remove(key);
        }
        return this;
    }

    public Serializer getSerializer(byte key) {
        return serializers.get(key);
    }

}
