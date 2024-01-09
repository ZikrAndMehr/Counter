package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class InsertCounterUseCaseTest {

    @Test
    fun testInsertCounter_withBlankCounterName() = runTest {
        val counterRepository = mock<CounterRepository>()
        val insertCounterUseCase = InsertCounterUseCase(counterRepository)

        val counter = testCounters[0].copy(counterName = "")
        val counterValidationResult = insertCounterUseCase.invoke(counter)

        verify(counterRepository, never()).insertCounter(counter)
        assertFalse(counterValidationResult.successful)
        assertNotNull(counterValidationResult.errorMessage)
    }

    @Test
    fun testInsertCounter_withValidCounter() = runTest {
        val counterRepository = mock<CounterRepository>()
        val insertCounterUseCase = InsertCounterUseCase(counterRepository)

        val counter = testCounters[0]
        val counterValidationResult = insertCounterUseCase.invoke(counter)

        verify(counterRepository).insertCounter(counter)
        assertTrue(counterValidationResult.successful)
        assertNull(counterValidationResult.errorMessage)
    }
}