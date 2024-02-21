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

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zikrcode.counter.data.testdoubles.FakeUserPreferencesRepository
import com.zikrcode.counter.utils.testPreferencesBoolean
import com.zikrcode.counter.utils.testPreferencesInt
import com.zikrcode.counter.utils.testPreferencesString
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class ReadUserPreferenceUseCaseTest {

    private val userPreferencesRepository = FakeUserPreferencesRepository()
    private val readUserPreferenceUseCase = ReadUserPreferenceUseCase(userPreferencesRepository)

    @Test
    fun testReadUserPreference_returnsNullForNonExistentStringPreferenceKey() = runTest {
        val key = testPreferencesString.keys.first()
        val preferenceKey = stringPreferencesKey(key)

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()
        assertNull(retrievedValue)
    }

    @Test
    fun testReadUserPreference_returnsValueOfStringPreferenceKey() = runTest {
        userPreferencesRepository.setTestPreferences(testPreferencesString)

        val key = testPreferencesString.keys.last()
        val value = testPreferencesString[key]
        val preferenceKey = stringPreferencesKey(key)

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testReadUserPreference_returnsNullForNonExistentIntPreferenceKey() = runTest {
        val key = testPreferencesInt.keys.first()
        val preferenceKey = intPreferencesKey(key)

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()
        assertNull(retrievedValue)
    }
    @Test
    fun testReadUserPreference_returnsValueOfIntPreferenceKey() = runTest {
        userPreferencesRepository.setTestPreferences(testPreferencesInt)

        val key = testPreferencesInt.keys.last()
        val value = testPreferencesInt[key]
        val preferenceKey = intPreferencesKey(key)

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testReadUserPreference_returnsNullForNonExistentBooleanPreferenceKey() = runTest {
        val key = testPreferencesBoolean.keys.first()
        val preferenceKey = booleanPreferencesKey(key)

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()
        assertNull(retrievedValue)
    }

    @Test
    fun testReadUserPreference_returnsValueOfBooleanPreferenceKey() = runTest {
        userPreferencesRepository.setTestPreferences(testPreferencesBoolean)

        val key = testPreferencesBoolean.keys.last()
        val value = testPreferencesBoolean[key]
        val preferenceKey = booleanPreferencesKey(key)

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()
        assertEquals(value, retrievedValue)
    }
}