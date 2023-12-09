package com.zikrcode.counter.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class Counter(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "counter_name") val counterName: String,
    @ColumnInfo(name = "counter_description") val counterDescription: String,
    @ColumnInfo(name = "counter_date") val counterDate: Long,
    @ColumnInfo(name = "counter_saved_value") val counterSavedValue: Int
) {
    companion object {
        fun instance() = Counter(
            counterName = "Counter",
            counterDescription = "A counter is a device which stores the number of times a" +
                    " particular event or process has occurred",
            counterDate = 0L,
            counterSavedValue = 0
        )
    }
}