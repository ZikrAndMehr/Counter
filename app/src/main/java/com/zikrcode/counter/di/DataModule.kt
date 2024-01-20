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

package com.zikrcode.counter.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.zikrcode.counter.data.data_source.CounterDao
import com.zikrcode.counter.data.data_source.CounterDatabase
import com.zikrcode.counter.data.repository.CounterRepositoryImpl
import com.zikrcode.counter.data.repository.UserPreferencesRepositoryImpl
import com.zikrcode.counter.data.repository.CounterRepository
import com.zikrcode.counter.data.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_preferences"

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideCounterDatabase(application: Application): CounterDatabase {
        return Room.databaseBuilder(
            application,
            CounterDatabase::class.java,
            CounterDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideCounterDao(counterDatabase: CounterDatabase): CounterDao {
        return counterDatabase.counterDao
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(application: Application): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            application.preferencesDataStoreFile(USER_PREFERENCES)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCounterRepository(
        counterRepositoryImpl: CounterRepositoryImpl
    ): CounterRepository

    @Singleton
    @Binds
    abstract fun bindUserPreferencesRepository(
        userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
    ): UserPreferencesRepository
}