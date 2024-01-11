package com.zikrcode.counter.data.repository

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zikrcode.counter.utils.TEST_DATASTORE_NAME
import com.zikrcode.counter.utils.testPreferencesBoolean
import com.zikrcode.counter.utils.testPreferencesInt
import com.zikrcode.counter.utils.testPreferencesString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

@OptIn(ExperimentalCoroutinesApi::class)
class UserPreferencesRepositoryTest {

    @get:Rule
    val temporaryFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private val testScope = TestScope(UnconfinedTestDispatcher())
    private val dataStore = PreferenceDataStoreFactory.create(scope = testScope) {
        temporaryFolder.newFile(TEST_DATASTORE_NAME)
    }
    private val userPreferencesRepository = UserPreferencesRepositoryImpl(dataStore)

    @Test
    fun testReadUserPreference_readsStringPreference() = runTest {
        testPreferencesString.forEach {
            userPreferencesRepository.writeUserPreference(
                stringPreferencesKey(it.key), it.value
            )
        }

        val key = testPreferencesString.keys.first()
        val value = testPreferencesString[key]
        val retrievedValue = userPreferencesRepository
            .readUserPreference(stringPreferencesKey(key)).first()
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testReadUserPreference_readsIntPreference() = runTest {
        testPreferencesInt.forEach {
            userPreferencesRepository.writeUserPreference(
                intPreferencesKey(it.key), it.value
            )
        }

        val key = testPreferencesInt.keys.first()
        val value = testPreferencesInt[key]
        val retrievedValue = userPreferencesRepository
            .readUserPreference(intPreferencesKey(key)).first()
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testReadUserPreference_readsBooleanPreference() = runTest {
        testPreferencesBoolean.forEach {
            userPreferencesRepository.writeUserPreference(
                booleanPreferencesKey(it.key), it.value
            )
        }

        val key = testPreferencesBoolean.keys.first()
        val value = testPreferencesBoolean[key]
        val retrievedValue = userPreferencesRepository
            .readUserPreference(booleanPreferencesKey(key)).first()
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testWriteUserPreference_writesStringPreference() = runTest {
        val key = testPreferencesString.keys.first()
        val value = testPreferencesString[key]!!

        userPreferencesRepository.writeUserPreference(stringPreferencesKey(key), value)
        val retrievedValue = userPreferencesRepository
            .readUserPreference(stringPreferencesKey(key)).first()
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testWriteUserPreference_writesIntPreference() = runTest {
        val key = testPreferencesInt.keys.first()
        val value = testPreferencesInt[key]!!

        userPreferencesRepository.writeUserPreference(intPreferencesKey(key), value)
        val retrievedValue = userPreferencesRepository
            .readUserPreference(intPreferencesKey(key)).first()
        assertEquals(value, retrievedValue)
    }

    @Test
    fun testWriteUserPreference_writesBooleanPreference() = runTest {
        val key = testPreferencesBoolean.keys.first()
        val value = testPreferencesBoolean[key]!!

        userPreferencesRepository.writeUserPreference(booleanPreferencesKey(key), value)
        val retrievedValue = userPreferencesRepository
            .readUserPreference(booleanPreferencesKey(key)).first()
        assertEquals(value, retrievedValue)
    }
}