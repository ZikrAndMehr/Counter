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