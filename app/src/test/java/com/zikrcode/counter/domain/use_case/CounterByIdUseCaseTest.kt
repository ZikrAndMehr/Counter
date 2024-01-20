package com.zikrcode.counter.domain.use_case

import com.zikrcode.counter.data.testdoubles.FakeCounterRepository
import com.zikrcode.counter.utils.testCounters
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class CounterByIdUseCaseTest {

    @Test
    fun testCounterById() = runTest {
        val counterRepository = FakeCounterRepository()
        val counterByIdUseCase = CounterByIdUseCase(counterRepository)

        counterRepository.data = testCounters

        val counter = testCounters.last()
        val id = counter.id!!

        val retrievedCounter = counterByIdUseCase.invoke(id).first()
        assertEquals(counter, retrievedCounter)
    }
}