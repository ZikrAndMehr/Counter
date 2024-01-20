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

package com.zikrcode.counter.data.testdoubles

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zikrcode.counter.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserPreferencesRepository : UserPreferencesRepository {

    private var data = mutableMapOf<Preferences.Key<*>, Any>()

    fun setTestPreferences(testPreferences: Map<String, Any>) {
        testPreferences.forEach { (key, value) ->
            when (value) {
                is String -> {
                    data[stringPreferencesKey(key)] = value
                }
                is Int -> {
                    data[intPreferencesKey(key)] = value
                }
                is Boolean -> {
                    data[booleanPreferencesKey(key)] = value
                }
            }
        }
    }

    fun getTestPreferences() = data.toMap()

    @Suppress("UNCHECKED_CAST")
    override fun <T> readUserPreference(key: Preferences.Key<T>): Flow<T?> {
        return flowOf(data[key] as? T?)
    }

    override suspend fun <T> writeUserPreference(key: Preferences.Key<T>, value: T) {
        data[key] = value!!
    }
}