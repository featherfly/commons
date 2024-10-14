/*
 *
 * Copyright (c) 2006-2020, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package cn.featherfly.common.tuple.internal.nullable.mapper;

import java.util.function.Function;

import cn.featherfly.common.tuple.Tuple2;
import cn.featherfly.common.tuple.TupleMapper;
import cn.featherfly.common.tuple.TuplesOfNullables;
import cn.featherfly.common.tuple.internal.AbstractTupleMapper;
import cn.featherfly.common.tuple.nullable.Tuple2OfNullables;

/**
 * An implementation class of a {@link TupleMapper } of degree 2
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TupleMapperImplPattern
 * 
 * @param <T>  Type of the original object for the mapper to use when creating a
 *             {@code Tuple }
 * @param <T0> type of element 0
 * @param <T1> type of element 1
 * 
 * @author Per Minborg
 */
public final class Tuple2OfNullablesMapperImpl<T, T0, T1> 
extends AbstractTupleMapper<T, Tuple2OfNullables<T0, T1>> 
implements TupleMapper<T, Tuple2OfNullables<T0, T1>> {
    
    /**
     * Constructs a {@link TupleMapper } that can create {@link Tuple2 }.
     * 
     * @param m0 mapper to apply for element 0
     * @param m1 mapper to apply for element 1
     */
    public Tuple2OfNullablesMapperImpl(Function<T, T0> m0, Function<T, T1> m1) {
        super(2);
        set(0, m0);
        set(1, m1);
    }
    
    @Override
    public Tuple2OfNullables<T0, T1> apply(T t) {
        return TuplesOfNullables.ofNullables(
            get0().apply(t),
            get1().apply(t)
        );
    }
    
    public Function<T, T0> get0() {
        return getAndCast(0);
    }
    
    public Function<T, T1> get1() {
        return getAndCast(1);
    }
}