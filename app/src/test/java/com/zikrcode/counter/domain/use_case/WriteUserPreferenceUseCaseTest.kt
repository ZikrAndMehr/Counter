package com.zikrcode.counter.domain.use_case

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zikrcode.counter.domain.repository.UserPreferencesRepository
import com.zikrcode.counter.utils.testPreferencesBoolean
import com.zikrcode.counter.utils.testPreferencesInt
import com.zikrcode.counter.utils.testPreferencesString
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class WriteUserPreferenceUseCaseTest {

    private lateinit var userPreferencesRepository: UserPreferencesRepository
    private lateinit var writeUserPreferenceUseCase: WriteUserPreferenceUseCase

    @Before
    fun setup() {
        userPreferencesRepository = mock()
        writeUserPreferenceUseCase = WriteUserPreferenceUseCase(userPreferencesRepository)
    }

    @Test
    fun testReadUserPreference_readsStringPreference() = runTest {
        val key = testPreferencesString.keys.first()
        val value = testPreferencesString[key]!!
        val preferenceKey = stringPreferencesKey(key)

        writeUserPreferenceUseCase.invoke(preferenceKey, value)
        verify(userPreferencesRepository).writeUserPreference(preferenceKey, value)
    }

    @Test
    fun testReadUserPreference_readsIntPreference() = runTest {
        val key = testPreferencesInt.keys.first()
        val value = testPreferencesInt[key]!!
        val preferenceKey = intPreferencesKey(key)

        writeUserPreferenceUseCase.invoke(preferenceKey, value)
        verify(userPreferencesRepository).writeUserPreference(preferenceKey, value)
    }

    @Test
    fun testReadUserPreference_readsBooleanPreference() = runTest {
        val key = testPreferencesBoolean.keys.first()
        val value = testPreferencesBoolean[key]!!
        val preferenceKey = booleanPreferencesKey(key)

        writeUserPreferenceUseCase.invoke(preferenceKey, value)
        verify(userPreferencesRepository).writeUserPreference(preferenceKey, value)
    }
}