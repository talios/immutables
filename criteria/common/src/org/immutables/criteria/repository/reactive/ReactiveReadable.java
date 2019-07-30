/*
 * Copyright 2019 Immutables Authors and Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.immutables.criteria.repository.reactive;

import org.immutables.criteria.Criterias;
import org.immutables.criteria.Criterion;
import org.immutables.criteria.adapter.Backend;
import org.immutables.criteria.expression.Query;

import java.util.Objects;

public class ReactiveReadable<T> implements ReactiveRepository.Readable<T> {

  private final Query query;
  private final Backend backend;

  public ReactiveReadable(Class<?> entity, Backend backend) {
    Objects.requireNonNull(entity, "entity");
    this.query = Query.of(entity);
    this.backend = backend;
  }

  @Override
  public ReactiveReader<T> find(Criterion<T> criteria) {
    return new ReactiveReader<>(Criterias.toQuery(criteria), backend);
  }

  @Override
  public ReactiveReader<T> findAll() {
    return new ReactiveReader<>(query, backend);
  }

}
