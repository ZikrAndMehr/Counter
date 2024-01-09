package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class CounterByIdUseCaseTest {

    @Test
    fun testCounterById() = runTest {
        val counter = testCounters[0]
        val id = counter.id!!

        val counterRepository = mock<CounterRepository> {
            on { counterById(eq(id)) } doReturn flowOf(counter)
        }
        val counterByIdUseCase = CounterByIdUseCase(counterRepository)

        val retrievedCounter = counterByIdUseCase.invoke(id).first()

        verify(counterRepository).counterById(eq(id))
        assertEquals(counter, retrievedCounter)
    }
}