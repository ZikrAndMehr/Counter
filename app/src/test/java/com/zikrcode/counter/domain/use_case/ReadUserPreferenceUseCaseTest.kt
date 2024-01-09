package com.zikrcode.counter.domain.use_case

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zikrcode.counter.domain.repository.UserPreferencesRepository
import com.zikrcode.counter.utils.testPreferencesBoolean
import com.zikrcode.counter.utils.testPreferencesInt
import com.zikrcode.counter.utils.testPreferencesString
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ReadUserPreferenceUseCaseTest {

    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private lateinit var readUserPreferenceUseCase: ReadUserPreferenceUseCase

    @Before
    fun setup() {
        userPreferencesRepository = mock()
        readUserPreferenceUseCase = ReadUserPreferenceUseCase(userPreferencesRepository)
    }

    @Test
    fun testReadUserPreference_readsStringPreference() = runTest {
        val key = testPreferencesString.keys.first()
        val value = testPreferencesString[key]
        val preferenceKey = stringPreferencesKey(key)

        whenever(userPreferencesRepository.readUserPreference(preferenceKey))
            .thenReturn(flowOf(value))

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()

        verify(userPreferencesRepository).readUserPreference(preferenceKey)
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testReadUserPreference_readsIntPreference() = runTest {
        val key = testPreferencesInt.keys.first()
        val value = testPreferencesInt[key]
        val preferenceKey = intPreferencesKey(key)

        whenever(userPreferencesRepository.readUserPreference(preferenceKey))
            .thenReturn(flowOf(value))

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()

        verify(userPreferencesRepository).readUserPreference(preferenceKey)
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testReadUserPreference_readsBooleanPreference() = runTest {
        val key = testPreferencesBoolean.keys.first()
        val value = testPreferencesBoolean[key]
        val preferenceKey = booleanPreferencesKey(key)

        whenever(userPreferencesRepository.readUserPreference(preferenceKey))
            .thenReturn(flowOf(value))

        val retrievedValue = readUserPreferenceUseCase.invoke(preferenceKey).first()

        verify(userPreferencesRepository).readUserPreference(preferenceKey)
        assertEquals(value, retrievedValue)
    }
}