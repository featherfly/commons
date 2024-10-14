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
package cn.featherfly.common.tuple.mutable;

import java.util.Optional;
import java.util.function.BiConsumer;

import cn.featherfly.common.tuple.MutableTuple;
import cn.featherfly.common.tuple.Tuple;
import cn.featherfly.common.tuple.TupleOfNullables;
import cn.featherfly.common.tuple.getter.TupleGetter;
import cn.featherfly.common.tuple.getter.TupleGetter0;
import cn.featherfly.common.tuple.getter.TupleGetter1;

/**
 * This interface defines a generic {@link MutableTuple} of any order that can
 * hold null values. A MutableTuple is type safe but <em>NOT</em> thread safe.
 * For pure non-null value elements see {@link Tuple}
 * This {@link MutableTuple} has a degree of 2
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TuplePattern
 *
 * @param <T0> type of element 0
 * @param <T1> type of element 1
 * @author Per Minborg
 * @author zhongj
 * @see Tuple
 * @see TupleOfNullables
 * @see MutableTuple
 */
public interface MutableTuple2<T0, T1> extends MutableTuple {

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
     * Sets the 0th element in this tuple.
     *
     * @param t0 the new value for the 0th element
     */
    void set0(T0 t0);

    /**
     * Sets the 1st element in this tuple.
     *
     * @param t1 the new value for the 1st element
     */
    void set1(T1 t1);

    @Override
    default int degree() {
        return 2;
    }

    @Override
    @SuppressWarnings("unchecked")
    default Optional<Object> get(int index) {
        switch (index) {
            case 0:
                return (Optional<Object>) get0();
            case 1:
                return (Optional<Object>) get1();
            default:
                throw new IndexOutOfBoundsException(
                    String.format("Index %d is outside bounds of tuple of degree %s", index, degree()));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    default void set(int index, Object object) {
        switch (index) {
            case 0:
                set0((T0) object);
                return;
            case 1:
                set1((T1) object);
                return;
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
     */
    static <T0, T1> TupleGetter0<MutableTuple2<T0, T1>, Optional<T0>> getter0() {
        return MutableTuple2::get0;
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
     */
    static <T0, T1> TupleGetter0<MutableTuple2<T0, T1>, T0> getterOrNull0() {
        return MutableTuple2::getOrNull0;
    }

    /**
     * Returns a setter for the 0th element in the {@code MutableTuple}.
     *
     * @return the element at the 0th position
     * @param <T0> the 0th element type
     * @param <T1> the 1st element type
     */
    static <T0, T1> BiConsumer<MutableTuple2<T0, T1>, T0> setter0() {
        return MutableTuple2::set0;
    }

    /**
     * Returns a {@link TupleGetter getter} for the 1st element in the {@code
     * Tuple}.
     *
     * @return the element at the 1st position
     * @param <T0> the 0th element type
     * @param <T1> the 1st element type
     */
    static <T0, T1> TupleGetter1<MutableTuple2<T0, T1>, Optional<T1>> getter1() {
        return MutableTuple2::get1;
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
     */
    static <T0, T1> TupleGetter1<MutableTuple2<T0, T1>, T1> getterOrNull1() {
        return MutableTuple2::getOrNull1;
    }

    /**
     * Returns a setter for the 1st element in the {@code MutableTuple}.
     *
     * @return the element at the 1st position
     * @param <T0> the 0th element type
     * @param <T1> the 1st element type
     */
    static <T0, T1> BiConsumer<MutableTuple2<T0, T1>, T1> setter1() {
        return MutableTuple2::set1;
    }
}