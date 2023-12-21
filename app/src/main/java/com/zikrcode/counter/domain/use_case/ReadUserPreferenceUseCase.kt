package com.zikrcode.counter.domain.use_case

import androidx.datastore.preferences.core.Preferences
import com.zikrcode.counter.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadUserPreferenceUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    operator fun <T> invoke(key: Preferences.Key<T>): Flow<T?> {
        return userPreferencesRepository.getDataStore().data.map { preferences ->
            preferences[key]
        }
    }
}