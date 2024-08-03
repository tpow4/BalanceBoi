package com.balance.boi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Institution(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)