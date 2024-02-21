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

package com.zikrcode.counter.data.repository

import com.zikrcode.counter.data.testdoubles.FakeCounterDao
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CounterRepositoryTest {

    private var counterDao = FakeCounterDao()
    private var counterRepository = CounterRepositoryImpl(counterDao)

    @Test
    fun testCounterById() = runTest {
        counterDao.counters = testCounters

        val counter = testCounters.first()
        val id = counter.id!!

        val retrievedCounter = counterRepository.counterById(id).first()
        assertEquals(counter, retrievedCounter)
    }

    @Test
    fun testAllCounters() = runTest {
        counterDao.counters = testCounters

        val retrievedCounters = counterRepository.allCounters().first()
        assertEquals(testCounters, retrievedCounters)
    }

    @Test
    fun testInsertCounter() = runTest {
        val counter = testCounters.last()
        counterRepository.insertCounter(counter)

        assertTrue(counterDao.counters.contains(counter))
    }

    @Test
    fun testDeleteCounter() = runTest {
        counterDao.counters = testCounters

        val counter = testCounters.last()
        counterRepository.deleteCounter(counter)

        assertFalse(counterDao.counters.contains(counter))
    }
}