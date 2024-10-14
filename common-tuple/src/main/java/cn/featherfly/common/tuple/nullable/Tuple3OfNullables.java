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
import cn.featherfly.common.tuple.getter.TupleGetter1;
import cn.featherfly.common.tuple.getter.TupleGetter2;

/**
 * This interface defines a generic {@link TupleOfNullables} of degree3 that can
 * hold null values. A TupleOfNullable is type safe, immutable and thread safe.
 * For pure non-null value elements see {@link Tuple}. For mutable tuples see
 * {@link MutableTuple}
 * This {@link TupleOfNullables} has a degree of 3
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TuplePattern
 *
 * @param <T0> type of element 0
 * @param <T1> type of element 1
 * @param <T2> type of element 2
 * @author Per Minborg
 * @see Tuple
 * @see TupleOfNullables
 * @see MutableTuple
 */
public interface Tuple3OfNullables<T0, T1, T2> extends TupleOfNullables {

    /**
     * Returns the 0th element from this tuple.
     *
     * @return the 0th element from this tuple.
     */
    Optional<T0> get0();

    /**
     * Returns the 1st element from this tuple.
     *
     * @return the 1st element from this tuple.
     */
    Optional<T1> get1();

    /**
     * Returns the 2nd element from this tuple.
     *
     * @return the 2nd element from this tuple.
     */
    Optional<T2> get2();

    @Override
    default int degree() {
        return 3;
    }

    @Override
    @SuppressWarnings("unchecked")
    default Optional<Object> get(int index) {
        switch (index) {
            case 0:
                return (Optional<Object>) get0();
            case 1:
                return (Optional<Object>) get1();
            case 2:
                return (Optional<Object>) get2();
            default:
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
     * @param <T1> the 1st element type
     * @param <T2> the 2nd element type
     */
    static <T0, T1, T2> TupleGetter0<Tuple3OfNullables<T0, T1, T2>, Optional<T0>> getter0() {
        return Tuple3OfNullables::get0;
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
     * @param <T1> the 1st element type
     * @param <T2> the 2nd element type
     */
    static <T0, T1, T2> TupleGetter0<Tuple3OfNullables<T0, T1, T2>, T0> getterOrNull0() {
        return Tuple3OfNullables::getOrNull0;
    }

    /**
     * Returns a {@link TupleGetter getter} for the 1st element in the {@code
     * Tuple}.
     *
     * @return the element at the 1st position
     * @param <T0> the 0th element type
     * @param <T1> the 1st element type
     * @param <T2> the 2nd element type
     */
    static <T0, T1, T2> TupleGetter1<Tuple3OfNullables<T0, T1, T2>, Optional<T1>> getter1() {
        return Tuple3OfNullables::get1;
    }

    /**
     * Returns the 1st element from this tuple or {@code null} if no such
     * element is present.
     *
     * @return the 1st element from this tuple or {@code null} if no such
     *         element is present.
     */
    default T1 getOrNull1() {
        return get1().orElse(null);
    }

    /**
     * Returns a {@link TupleGetter getter} for the 1st element in the {@code
     * Tuple}.
     *
     * @return the element at the 1st position
     * @param <T0> the 0th element type
     * @param <T1> the 1st element type
     * @param <T2> the 2nd element type
     */
    static <T0, T1, T2> TupleGetter1<Tuple3OfNullables<T0, T1, T2>, T1> getterOrNull1() {
        return Tuple3OfNullables::getOrNull1;
    }

    /**
     * Returns a {@link TupleGetter getter} for the 2nd element in the {@code
     * Tuple}.
     *
     * @return the element at the 2nd position
     * @param <T0> the 0th element type
     * @param <T1> the 1st element type
     * @param <T2> the 2nd element type
     */
    static <T0, T1, T2> TupleGetter2<Tuple3OfNullables<T0, T1, T2>, Optional<T2>> getter2() {
        return Tuple3OfNullables::get2;
    }

    /**
     * Returns the 2nd element from this tuple or {@code null} if no such
     * element is present.
     *
     * @return the 2nd element from this tuple or {@code null} if no such
     *         element is present.
     */
    default T2 getOrNull2() {
        return get2().orElse(null);
    }

    /**
     * Returns a {@link TupleGetter getter} for the 2nd element in the {@code
     * Tuple}.
     *
     * @return the element at the 2nd position
     * @param <T0> the 0th element type
     * @param <T1> the 1st element type
     * @param <T2> the 2nd element type
     */
    static <T0, T1, T2> TupleGetter2<Tuple3OfNullables<T0, T1, T2>, T2> getterOrNull2() {
        return Tuple3OfNullables::getOrNull2;
    }
}