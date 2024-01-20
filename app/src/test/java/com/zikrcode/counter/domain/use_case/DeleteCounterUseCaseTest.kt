package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.data.testdoubles.FakeCounterRepository
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class DeleteCounterUseCaseTest {

    @Test
    fun testDeleteCounter() = runTest {
        val counterRepository = FakeCounterRepository()
        val deleteCounterUseCase = DeleteCounterUseCase(counterRepository)

        counterRepository.data = testCounters

        val counter = testCounters.first()
        deleteCounterUseCase.invoke(counter)

        assertFalse(counterRepository.data.contains(counter))
    }
}