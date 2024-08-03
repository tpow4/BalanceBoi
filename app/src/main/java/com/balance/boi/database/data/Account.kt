package com.balance.boi.database.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Institution::class,
            parentColumns = ["id"],
            childColumns = ["institutionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["institutionId"])]
)
data class Account(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
val institutionId: Int,
val name: String,
val taxType: String
)