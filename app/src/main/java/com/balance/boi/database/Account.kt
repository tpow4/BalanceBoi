package com.balance.boi.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Institution::class,
            parentColumns = ["id"],
            childColumns = ["institutionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Account(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val institutionId: Int, // Foreign key to FinancialInstitution
val name: String,
val taxType: String // New field for tax type
)