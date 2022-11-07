
package cn.featherfly.common.serialization;

/**
 * Serializer.
 *
 * @author zhongj
 */
public interface Serializer {

    <O> byte[] serialize(O obj);

    <O> O deserialize(byte[] bytes, Class<O> type);
}
