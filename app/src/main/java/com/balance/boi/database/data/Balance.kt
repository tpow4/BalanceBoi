package com.balance.boi.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.math.BigDecimal
import java.sql.Date

@Entity
@TypeConverters(Converters::class)
data class Balance(
    @PrimaryKey(autoGenerate = true) val balanceId: Int = 0,
    val balanceAccountId: Int,
    val balanceDate: Date,
    val balanceAmount: BigDecimal
)