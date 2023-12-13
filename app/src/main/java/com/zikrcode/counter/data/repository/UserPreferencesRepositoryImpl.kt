package com.zikrcode.counter.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.zikrcode.counter.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    override fun getDataStore(): DataStore<Preferences> {
        return dataStore
    }
}