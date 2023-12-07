package com.zikrcode.counter.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zikrcode.counter.domain.model.Counter

@Database(entities = [Counter::class], version = 1)
abstract class CounterDatabase : RoomDatabase() {

    abstract val counterDao: CounterDao

    companion object {
        const val DATABASE_NAME = "counter_database"
    }
}