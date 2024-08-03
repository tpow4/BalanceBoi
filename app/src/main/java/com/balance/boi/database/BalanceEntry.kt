package com.balance.boi.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BalanceEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val accountId: Int, // Foreign key to Account
    val date: Date,
    val balance: Double
)