package com.zikrcode.counter.di

import android.app.Application
import androidx.room.Room
import com.zikrcode.counter.data.data_source.CounterDatabase
import com.zikrcode.counter.data.repository.CounterRepositoryImpl
import com.zikrcode.counter.domain.repository.CounterRepository
import com.zikrcode.counter.domain.use_case.AllCountersUseCase
import com.zikrcode.counter.domain.use_case.CounterByNameUseCase
import com.zikrcode.counter.domain.use_case.CounterUseCases
import com.zikrcode.counter.domain.use_case.DeleteCounterUseCase
import com.zikrcode.counter.domain.use_case.InsertCounterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
    fun provideCounterRepository(counterDatabase: CounterDatabase): CounterRepository {
        return CounterRepositoryImpl(counterDatabase.counterDao)
    }

    @Provides
    @Singleton
    fun provideCounterUseCases(counterRepository: CounterRepository): CounterUseCases {
        return CounterUseCases(
            CounterByNameUseCase(counterRepository),
            AllCountersUseCase(counterRepository),
            InsertCounterUseCase(counterRepository),
            DeleteCounterUseCase(counterRepository)
        )
    }
}