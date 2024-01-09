package com.zikrcode.counter.data.repository

import com.zikrcode.counter.data.data_source.CounterDao
import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CounterRepositoryTest {

    private lateinit var counterDao: CounterDao
    private lateinit var counterRepository: CounterRepository

    @Before
    fun setup() {
        counterDao = mock()
        counterRepository = CounterRepositoryImpl(counterDao)
    }

    @Test
    fun testCounterById() = runTest {
        val counter = testCounters[0]
        val id = counter.id!!

        whenever(counterDao.counterById(id))
            .thenReturn(flowOf(counter))

        val retrievedCounter = counterRepository.counterById(id).first()
        verify(counterDao).counterById(id)
        assertEquals(counter, retrievedCounter)
    }

    @Test
    fun testAllCounters() = runTest {
        whenever(counterDao.allCounters())
            .thenReturn(flowOf(testCounters))

        val retrievedCounters = counterRepository.allCounters().first()
        verify(counterDao).allCounters()
        assertEquals(testCounters, retrievedCounters)
    }

    @Test
    fun testInsertCounter() = runTest {
        val counter = testCounters[0]
        counterRepository.insertCounter(counter)
        verify(counterDao).insertCounter(counter)
    }

    @Test
    fun testDeleteCounter() = runTest {
        val counter = testCounters[0]
        counterRepository.deleteCounter(counter)
        verify(counterDao).deleteCounter(counter)
    }
}