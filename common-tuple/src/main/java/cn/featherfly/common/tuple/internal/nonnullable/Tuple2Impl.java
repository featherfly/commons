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
package cn.featherfly.common.tuple.internal.nonnullable;

import cn.featherfly.common.tuple.Tuple;
import cn.featherfly.common.tuple.Tuple2;
import cn.featherfly.common.tuple.internal.AbstractTuple;

/**
 * An implementation class of a {@link Tuple2}.
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TupleImplPattern
 * 
 * @param <T0> type of element 0
 * @param <T1> type of element 1
 * 
 * @author Per Minborg
 */
public final class Tuple2Impl<T0, T1> 
extends AbstractTuple 
implements Tuple2<T0, T1> {
    
    /**
     * Constructs a {@link Tuple} of type {@link Tuple2}.
     * 
     * @param e0 element 0
     * @param e1 element 1
     */
    public Tuple2Impl(T0 e0, T1 e1) {
        super(Tuple2Impl.class, e0, e1);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T0 get0() {
        return ((T0) values[0]);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public T1 get1() {
        return ((T1) values[1]);
    }
}