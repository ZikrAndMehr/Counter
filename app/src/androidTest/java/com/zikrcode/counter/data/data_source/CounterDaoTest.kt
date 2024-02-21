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

package com.zikrcode.counter.data.data_source

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CounterDaoTest {

    private lateinit var counterDatabase: CounterDatabase
    private lateinit var counterDao: CounterDao

    @Before
    fun setup() {
        counterDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CounterDatabase::class.java
        ).build()
        counterDao = counterDatabase.counterDao
    }

    @After
    fun teardown() {
        counterDatabase.close()
    }

    @Test
    fun testCounterById() = runTest {
        testCounters.forEach {
            counterDao.insertCounter(it)
        }

        val retrievedCounter = counterDao.counterById(testCounters[1].id!!).first()
        assertEquals(testCounters[1], retrievedCounter)
    }

    @Test
    fun testInsertCounter_insertsCounter() = runTest {
        counterDao.insertCounter(testCounters[0])

        val retrievedCounter = counterDao.counterById(testCounters[0].id!!).first()
        assertEquals(testCounters[0], retrievedCounter)
    }

    @Test
    fun testInsertCounter_overwritesCounterWhenCounterExists() = runTest {
        val counter = testCounters[0]
        counterDao.insertCounter(counter)

        val overwroteCounter = counter.copy(counterSavedValue = 10)
        counterDao.insertCounter(overwroteCounter)

        val retrievedCounter = counterDao.counterById(testCounters[0].id!!).first()
        assertEquals(overwroteCounter, retrievedCounter)
    }

    @Test
    fun testAllCounters_returnsEmpty() = runTest {
        val retrievedCounters = counterDao.allCounters().first()
        assertTrue(retrievedCounters.isEmpty())
    }

    @Test
    fun testAllCounters_returnsCounters() = runTest {
        testCounters.forEach {
            counterDao.insertCounter(it)
        }

        val retrievedCounters = counterDao.allCounters().first()
        assertEquals(testCounters, retrievedCounters)
    }

    @Test
    fun testDeleteCounter() = runTest {
        testCounters.forEach {
            counterDao.insertCounter(it)
        }

        counterDao.deleteCounter(testCounters[2])

        val retrievedCounters = counterDao.allCounters().first()
        assertFalse(retrievedCounters.contains(testCounters[2]))
    }
}