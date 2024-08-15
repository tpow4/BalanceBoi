package com.balance.boi.database.data

import androidx.room.TypeConverter
import java.math.BigDecimal
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

    @TypeConverter
    fun bigDecimalToString(input: BigDecimal): String {
        return input.toPlainString() ?: ""
    }

    @TypeConverter
    fun stringToBigDecimal(input: String): BigDecimal {
        if (input.isBlank()) return BigDecimal.valueOf(0.0)
        return input.toBigDecimalOrNull() ?: BigDecimal.valueOf(0.0)
    }
}