package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.domain.utils.CounterOrder
import com.zikrcode.counter.domain.utils.OrderType
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class AllCountersUseCaseTest {

    private val countersByNameAscending = testCounters.sortedBy { it.counterName }
    private val countersByNameDescending = testCounters.sortedByDescending { it.counterName }
    private val countersByDateAscending = testCounters.sortedBy { it.counterDate }
    private val countersByDateDescending = testCounters.sortedByDescending { it.counterDate }
    private lateinit var counterRepository: CounterRepository
    private lateinit var allCountersUseCase: AllCountersUseCase

    @Before
    fun setup() {
        counterRepository = mock {
            on { allCounters() } doReturn flowOf(testCounters)
        }
        allCountersUseCase = AllCountersUseCase(counterRepository)
    }

    @Test
    fun testAllCounters_returnsCountersInDefaultOrder() = runTest {
        val defaultCounters = countersByDateDescending
        val retrievedCounters = allCountersUseCase.invoke().first()

        verify(counterRepository).allCounters()
        assertEquals(defaultCounters, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByNameAscendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Name(OrderType.ASCENDING)
        ).first()

        verify(counterRepository).allCounters()
        assertEquals(countersByNameAscending, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByNameDescendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Name(OrderType.DESCENDING)
        ).first()

        verify(counterRepository).allCounters()
        assertEquals(countersByNameDescending, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByDateAscendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Date(OrderType.ASCENDING)
        ).first()

        verify(counterRepository).allCounters()
        assertEquals(countersByDateAscending, retrievedCounters)
    }

    @Test
    fun testAllCounters_returnsCountersByDateDescendingOrder() = runTest {
        val retrievedCounters = allCountersUseCase.invoke(
            CounterOrder.Date(OrderType.DESCENDING)
        ).first()

        verify(counterRepository).allCounters()
        assertEquals(countersByDateDescending, retrievedCounters)
    }
}