package com.zikrcode.counter.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.zikrcode.counter.data.data_source.CounterDatabase
import com.zikrcode.counter.data.repository.CounterRepositoryImpl
import com.zikrcode.counter.data.repository.UserPreferencesRepositoryImpl
import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.domain.repository.UserPreferencesRepository
import com.zikrcode.counter.domain.use_case.AllCountersUseCase
import com.zikrcode.counter.domain.use_case.CounterByIdUseCase
import com.zikrcode.counter.domain.use_case.CounterUseCases
import com.zikrcode.counter.domain.use_case.DeleteCounterUseCase
import com.zikrcode.counter.domain.use_case.InsertCounterUseCase
import com.zikrcode.counter.domain.use_case.ReadUserPreferenceUseCase
import com.zikrcode.counter.domain.use_case.WriteUserPreferenceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val USER_PREFERENCES = "user_preferences"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCounterDatabase(application: Application): CounterDatabase {
        return Room.databaseBuilder(
            application,
            CounterDatabase::class.java,
            CounterDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(application: Application): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            application.preferencesDataStoreFile(USER_PREFERENCES)
        }
    }

    @Provides
    @Singleton
    fun provideCounterRepository(counterDatabase: CounterDatabase): CounterRepository {
        return CounterRepositoryImpl(counterDatabase.counterDao)
    }

    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        dataStore: DataStore<Preferences>
    ): UserPreferencesRepository {
        return UserPreferencesRepositoryImpl(dataStore)
    }

    @Provides
    @Singleton
    fun provideCounterUseCases(
        counterRepository: CounterRepository,
        userPreferencesRepository: UserPreferencesRepository
    ): CounterUseCases {
        return CounterUseCases(
            CounterByIdUseCase(counterRepository),
            AllCountersUseCase(counterRepository),
            InsertCounterUseCase(counterRepository),
            DeleteCounterUseCase(counterRepository),
            ReadUserPreferenceUseCase(userPreferencesRepository),
            WriteUserPreferenceUseCase(userPreferencesRepository)
        )
    }
}