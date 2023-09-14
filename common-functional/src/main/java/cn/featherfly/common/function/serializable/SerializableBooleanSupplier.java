
package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.BooleanSupplier;

/**
 * The Interface SerializableBooleanSupplier.
 *
 * @author zhongj
 * @see java.util.function.BooleanSupplier
 */
@FunctionalInterface
public interface SerializableBooleanSupplier extends Serializable, BooleanSupplier {

}
