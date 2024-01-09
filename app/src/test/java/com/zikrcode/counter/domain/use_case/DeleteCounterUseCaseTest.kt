package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class DeleteCounterUseCaseTest {

    @Test
    fun testDeleteCounter() = runTest {
        val counterRepository = mock<CounterRepository>()
        val deleteCounterUseCase = DeleteCounterUseCase(counterRepository)

        val counter = testCounters[0]
        deleteCounterUseCase.invoke(counter)

        verify(counterRepository).deleteCounter(counter)
    }
}