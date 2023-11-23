
package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.BoolSupplier;

/**
 * The Interface SerializableBoolSupplier.
 *
 * @author zhongj
 * @see BoolSupplier
 */
@FunctionalInterface
public interface SerializableBoolSupplier extends SerializableSupplier<Boolean>, BoolSupplier {

}
