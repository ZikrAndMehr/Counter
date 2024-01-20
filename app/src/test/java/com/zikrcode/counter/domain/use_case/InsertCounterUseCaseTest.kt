package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.data.testdoubles.FakeCounterRepository
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class InsertCounterUseCaseTest {

    private val counterRepository = FakeCounterRepository()
    private val insertCounterUseCase = InsertCounterUseCase(counterRepository)

    @Test
    fun testInsertCounter_withBlankCounterName() = runTest {
        val counter = testCounters.first().copy(counterName = "")
        val counterValidationResult = insertCounterUseCase.invoke(counter)

        assertFalse(counterRepository.data.contains(counter))
        assertFalse(counterValidationResult.successful)
        assertNotNull(counterValidationResult.errorMessage)
    }

    @Test
    fun testInsertCounter_withValidCounter() = runTest {
        val counter = testCounters.last()
        val counterValidationResult = insertCounterUseCase.invoke(counter)

        assertTrue(counterRepository.data.contains(counter))
        assertTrue(counterValidationResult.successful)
        assertNull(counterValidationResult.errorMessage)
    }
}