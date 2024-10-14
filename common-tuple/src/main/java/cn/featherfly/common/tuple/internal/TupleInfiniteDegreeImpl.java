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
package cn.featherfly.common.tuple.internal;

import cn.featherfly.common.tuple.Tuple;

/**
 * An implementation class of a {@link Tuple } with infinite degree. Sadly,
 * types are lost for this implementation.
 *
 * @author Per Minborg
 */
public final class TupleInfiniteDegreeImpl extends AbstractTuple implements Tuple {

    public TupleInfiniteDegreeImpl(Object... elements) {
        super(TupleInfiniteDegreeImpl.class, elements);
    }

    @Override
    public int degree() {
        return values.length;
    }

}
