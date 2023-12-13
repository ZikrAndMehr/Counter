package com.zikrcode.counter.domain.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface UserPreferencesRepository {

    fun getDataStore(): DataStore<Preferences>
}