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
package cn.featherfly.common.tuple;

import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import cn.featherfly.common.tuple.getter.TupleGetter;

/**
 * This interface defines a generic Tuple of any order that can hold null
 * values. A Tuple is type safe, immutable and thread safe. For pure non-null
 * value elements see {@link Tuple}
 *
 * @author pemi
 * @author zhongj
 * @see Tuple
 */
public interface MutableTuple extends BasicTuple<Optional<Object>> {

    /**
     * Returns a {@link Stream} of all values for this Tuple. If sequential, the
     * Stream will start with the 0:th tuple and progress upwards.
     *
     * @return a {@link Stream} of all values for this Tuple
     */
    default Stream<Optional<Object>> stream() {
        return IntStream.range(0, degree()).mapToObj(this::get);
    }

    @Override
    default <T> Stream<T> streamOf(Class<T> clazz) {
        return stream().filter(Optional::isPresent).map(Optional::get).filter(clazz::isInstance).map(clazz::cast);
    }

    /**
     * Sets the tuple element at the given index. For example, set(0, "value0") will
     * set the first element and set1("value1") will set the second etc.
     *
     * @param index set index
     * @param object set object
     * @throws IndexOutOfBoundsException if
     *         {@code index < 0 || index >= length()}
     */
    void set(int index, Object object);

    /**
     * Returns a getter method for the specified ordinal element.
     *
     * @param index the position of the element to return
     * @param <TUPLE> the type of the tuple
     * @param <R> the type of the returned element
     * @return the created getter
     */
    static <TUPLE extends MutableTuple, R> TupleGetter<TUPLE, R> getter(int index) {
        return new TupleGetter<TUPLE, R>() {
            @Override
            public int index() {
                return index;
            }

            @Override
            @SuppressWarnings("unchecked")
            public R apply(TUPLE tuple) {
                return (R) tuple.get(index);
            }
        };
    }

}
