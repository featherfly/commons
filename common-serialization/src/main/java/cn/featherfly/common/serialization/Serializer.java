
package cn.featherfly.common.serialization;

/**
 * <p>
 * Serializer
 * </p>
 * 
 * @author zhongj
 */
public interface Serializer {

    <O> byte[] serialize(O obj);

    <O> O deserialize(byte[] bytes, Class<O> type);

}
