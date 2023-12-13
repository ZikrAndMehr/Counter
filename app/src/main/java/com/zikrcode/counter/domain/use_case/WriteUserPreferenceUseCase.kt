package com.zikrcode.counter.domain.use_case

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.zikrcode.counter.domain.repository.UserPreferencesRepository

class WriteUserPreferenceUseCase(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend operator fun <T> invoke(key: Preferences.Key<T>, value: T) {
        userPreferencesRepository.getDataStore().edit { preferences ->
            preferences[key] = value
        }
    }
}