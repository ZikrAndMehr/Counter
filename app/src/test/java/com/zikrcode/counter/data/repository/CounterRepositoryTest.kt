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