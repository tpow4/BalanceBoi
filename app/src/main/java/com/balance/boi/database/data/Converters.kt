package com.balance.boi.database.data

import androidx.room.TypeConverter
import java.sql.Date

class Converters {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(value: Long): Date {
        return Date(value)
    }
}