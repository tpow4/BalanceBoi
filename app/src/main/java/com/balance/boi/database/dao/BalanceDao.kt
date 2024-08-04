package com.balance.boi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.balance.boi.database.data.Balance
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {
    @Insert
    suspend fun insert(balance: Balance)

    @Delete
    suspend fun delete(balance: Balance)
}