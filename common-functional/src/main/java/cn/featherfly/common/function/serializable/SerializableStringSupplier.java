package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.StringSupplier;

/**
 * The Interface StringSupplier.
 *
 * @author zhongj
 * @see SerializableSupplier
 * @see StringSupplier
 */
@FunctionalInterface
public interface SerializableStringSupplier extends SerializableSupplier<String>, StringSupplier {

}