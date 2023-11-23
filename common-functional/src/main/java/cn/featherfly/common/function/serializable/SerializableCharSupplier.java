
package cn.featherfly.common.function.serializable;

import java.io.Serializable;

import cn.featherfly.common.function.CharSupplier;

/**
 * The Interface SerializableCharSupplier.
 *
 * @author zhongj
 * @see CharSupplier
 */
@FunctionalInterface
public interface SerializableCharSupplier extends Serializable, CharSupplier {

}
