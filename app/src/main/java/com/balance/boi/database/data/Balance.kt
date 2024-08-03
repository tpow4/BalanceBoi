package com.balance.boi.database.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Date

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["accountId"])]
)
@TypeConverters(Converters::class)
data class Balance(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accountId: Int,
    val date: Date,
    val balance: Double
)