package com.zikrcode.counter.domain.use_case

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zikrcode.counter.data.testdoubles.FakeUserPreferencesRepository
import com.zikrcode.counter.utils.testPreferencesBoolean
import com.zikrcode.counter.utils.testPreferencesInt
import com.zikrcode.counter.utils.testPreferencesString
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class WriteUserPreferenceUseCaseTest {

    private val userPreferencesRepository = FakeUserPreferencesRepository()
    private val writeUserPreferenceUseCase = WriteUserPreferenceUseCase(userPreferencesRepository)

    @Test
    fun testWriteUserPreference_savesStringPreferenceKey() = runTest {
        val key = testPreferencesString.keys.first()
        val value = testPreferencesString[key]!!
        val preferenceKey = stringPreferencesKey(key)

        writeUserPreferenceUseCase.invoke(preferenceKey, value)
        val savedValue = userPreferencesRepository.getTestPreferences()[preferenceKey]
        assertEquals(value, savedValue)
    }

    @Test
    fun testWriteUserPreference_savesIntPreferenceKey() = runTest {
        val key = testPreferencesInt.keys.first()
        val value = testPreferencesInt[key]!!
        val preferenceKey = intPreferencesKey(key)

        writeUserPreferenceUseCase.invoke(preferenceKey, value)
        val savedValue = userPreferencesRepository.getTestPreferences()[preferenceKey]
        assertEquals(value, savedValue)
    }

    @Test
    fun testWriteUserPreference_savesBooleanPreferenceKey() = runTest {
        val key = testPreferencesBoolean.keys.first()
        val value = testPreferencesBoolean[key]!!
        val preferenceKey = booleanPreferencesKey(key)

        writeUserPreferenceUseCase.invoke(preferenceKey, value)
        val savedValue = userPreferencesRepository.getTestPreferences()[preferenceKey]
        assertEquals(value, savedValue)
    }
}