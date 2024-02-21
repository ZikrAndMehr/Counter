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

package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.data.testdoubles.FakeCounterRepository
import com.zikrcode.counter.domain.utils.CounterOrder
import com.zikrcode.counter.domain.utils.OrderType
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AllCountersUseCaseTest {

    private val countersByNameAscending = testCounters.sortedBy { it.counterName }
    private val countersByNameDescending = testCounters.sortedByDescending { it.counterName }
    private val countersByDateAscending = testCounters.sortedBy { it.counterDate }
    private val countersByDateDescending = testCounters.sortedByDescending { it.counterDate }

    private val counterRepository = FakeCounterRepository()
    private val allCountersUseCase = AllCountersUseCase(counterRepository)

    @Before
    fun setup() {
        counterRepository.data = testCounters
    }

    @Test
    fun testAllCounters_returnsCountersInDefaultOrder() = runTest {
        val defaultCounters = countersByDateDescending
        val retrievedCounters = allCountersUseCase.invoke().first()

        assertEquals(defaultCounters, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByNameAscendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Name(OrderType.ASCENDING)
        ).first()

        assertEquals(countersByNameAscending, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByNameDescendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Name(OrderType.DESCENDING)
        ).first()

        assertEquals(countersByNameDescending, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByDateAscendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Date(OrderType.ASCENDING)
        ).first()

        assertEquals(countersByDateAscending, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByDateDescendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Date(OrderType.DESCENDING)
        ).first()

        assertEquals(countersByDateDescending, retrievedCounters)
    }
}