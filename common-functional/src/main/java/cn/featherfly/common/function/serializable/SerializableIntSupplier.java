
package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.IntSupplier;

/**
 * The Interface SerializableIntSupplier.
 *
 * @author zhongj
 * @see java.util.function.IntSupplier
 */
@FunctionalInterface
public interface SerializableIntSupplier extends Serializable, IntSupplier {
    /**
     * Gets a result.
     *
     * @return a result
     */
    default int get() {
        return getAsInt();
    }
}
