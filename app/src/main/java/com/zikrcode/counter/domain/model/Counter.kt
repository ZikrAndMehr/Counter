package com.zikrcode.counter.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class Counter(
    @PrimaryKey @ColumnInfo(name = "counter_name") val counterName: String,
    @ColumnInfo(name = "counter_description") val counterDescription: String,
    @ColumnInfo(name = "counter_date") val counterDate: Long,
    @ColumnInfo(name = "counter_saved_value") val counterSavedValue: Int
)