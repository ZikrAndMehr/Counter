/*
 * Copyright (C) 2023 Zokirjon Mamadjonov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zikrcode.counter.data.testdoubles

import com.zikrcode.counter.data.model.Counter
import com.zikrcode.counter.data.repository.CounterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCounterRepository : CounterRepository {

    private var _data: MutableMap<Int, Counter> = mutableMapOf()
    var data: List<Counter>
        get() = _data.values.toList()
        set(newData) {
            _data = newData.associateBy { it.id!! }.toMutableMap()
        }
    override fun counterById(id: Int): Flow<Counter> {
        return flowOf(_data[id]!!)
    }

    override fun allCounters(): Flow<List<Counter>> {
        return flowOf(data)
    }

    override suspend fun insertCounter(counter: Counter) {
        _data[counter.id!!] = counter
    }

    override suspend fun deleteCounter(counter: Counter) {
        _data.remove(counter.id)
    }
}