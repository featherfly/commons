
package cn.featherfly.common.function.serializable;

import java.io.Serializable;

import cn.featherfly.common.function.ByteSupplier;

/**
 * The Interface SerializableByteSupplier.
 *
 * @author zhongj
 * @see ByteSupplier
 */
@FunctionalInterface
public interface SerializableShortSupplier extends Serializable, ByteSupplier {

}
