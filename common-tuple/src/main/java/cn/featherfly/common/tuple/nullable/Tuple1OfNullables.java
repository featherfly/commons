/*
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package cn.featherfly.common.tuple.nullable;

import java.util.Optional;

import cn.featherfly.common.tuple.MutableTuple;
import cn.featherfly.common.tuple.Tuple;
import cn.featherfly.common.tuple.TupleOfNullables;
import cn.featherfly.common.tuple.getter.TupleGetter;
import cn.featherfly.common.tuple.getter.TupleGetter0;

/**
 * This interface defines a generic {@link TupleOfNullables} of degree1 that can
 * hold null values. A TupleOfNullable is type safe, immutable and thread safe.
 * For pure non-null value elements see {@link Tuple}. For mutable tuples see
 * {@link MutableTuple}
 * This {@link TupleOfNullables} has a degree of 1
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TuplePattern
 *
 * @param <T0> type of element 0
 * @author Per Minborg
 * @see Tuple
 * @see TupleOfNullables
 * @see MutableTuple
 */
public interface Tuple1OfNullables<T0> extends TupleOfNullables {

    /**
     * Returns the 0th element from this tuple.
     *
     * @return the 0th element from this tuple.
     */
    Optional<T0> get0();

    @Override
    default int degree() {
        return 1;
    }

    @Override
    @SuppressWarnings("unchecked")
    default Optional<Object> get(int index) {
        if (index == 0) {
            return (Optional<Object>) get0();
        } else {
            throw new IndexOutOfBoundsException(
                String.format("Index %d is outside bounds of tuple of degree %s", index, degree()));
        }
    }

    /**
     * Returns a {@link TupleGetter getter} for the 0th element in the {@code
     * Tuple}.
     *
     * @return the element at the 0th position
     * @param <T0> the 0th element type
     */
    static <T0> TupleGetter0<Tuple1OfNullables<T0>, Optional<T0>> getter0() {
        return Tuple1OfNullables::get0;
    }

    /**
     * Returns the 0th element from this tuple or {@code null} if no such
     * element is present.
     *
     * @return the 0th element from this tuple or {@code null} if no such
     *         element is present.
     */
    default T0 getOrNull0() {
        return get0().orElse(null);
    }

    /**
     * Returns a {@link TupleGetter getter} for the 0th element in the {@code
     * Tuple}.
     *
     * @return the element at the 0th position
     * @param <T0> the 0th element type
     */
    static <T0> TupleGetter0<Tuple1OfNullables<T0>, T0> getterOrNull0() {
        return Tuple1OfNullables::getOrNull0;
    }
}