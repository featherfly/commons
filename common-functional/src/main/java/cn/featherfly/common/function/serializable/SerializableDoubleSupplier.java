
package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.DoubleSupplier;

/**
 * The Interface SerializableDoubleSupplier.
 *
 * @author zhongj
 */
@FunctionalInterface
public interface SerializableDoubleSupplier extends Serializable, DoubleSupplier {

    /**
     * Gets a result.
     *
     * @return a result
     */
    default double get() {
        return getAsDouble();
    }
}
