
package cn.featherfly.common.function.serializable;

import cn.featherfly.common.function.CharacterSupplier;

/**
 * The Interface SerializableCharSupplier.
 *
 * @author zhongj
 * @see CharacterSupplier
 */
@FunctionalInterface
public interface SerializableCharacterSupplier extends SerializableSupplier<Character>, CharacterSupplier {

}
