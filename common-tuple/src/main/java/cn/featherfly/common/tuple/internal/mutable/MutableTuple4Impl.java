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
package cn.featherfly.common.tuple.internal.mutable;

import java.util.Optional;

import cn.featherfly.common.tuple.MutableTuple;
import cn.featherfly.common.tuple.internal.AbstractMutableTuple;
import cn.featherfly.common.tuple.mutable.MutableTuple4;

/**
 * An implementation class of a {@link MutableTuple4}.
 * <p>
 * Generated by com.speedment.sources.pattern.tuple.TupleImplPattern
 * 
 * @param <T0> type of element 0
 * @param <T1> type of element 1
 * @param <T2> type of element 2
 * @param <T3> type of element 3
 * 
 * @author Per Minborg
 */
public final class MutableTuple4Impl<T0, T1, T2, T3> 
extends AbstractMutableTuple 
implements MutableTuple4<T0, T1, T2, T3> {
    
    /**
     * Constructs a {@link MutableTuple} of type {@link MutableTuple4}.
     */
    public MutableTuple4Impl() {
        super(MutableTuple4Impl.class, 4);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Optional<T0> get0() {
        return Optional.ofNullable((T0) values[0]);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Optional<T1> get1() {
        return Optional.ofNullable((T1) values[1]);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Optional<T2> get2() {
        return Optional.ofNullable((T2) values[2]);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Optional<T3> get3() {
        return Optional.ofNullable((T3) values[3]);
    }
    
    @Override
    public void set0(T0 t0) {
        values[0] = t0;
    }
    
    @Override
    public void set1(T1 t1) {
        values[1] = t1;
    }
    
    @Override
    public void set2(T2 t2) {
        values[2] = t2;
    }
    
    @Override
    public void set3(T3 t3) {
        values[3] = t3;
    }
}