
package cn.featherfly.common.function.serializable;

import java.io.Serializable;
import java.util.function.LongSupplier;

/**
 * The Interface SerializableLongSupplier.
 *
 * @author zhongj
 * @see java.util.function.LongSupplier
 */
@FunctionalInterface
public interface SerializableLongSupplier extends Serializable, LongSupplier {

}