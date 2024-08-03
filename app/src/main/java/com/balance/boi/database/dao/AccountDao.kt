package com.balance.boi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.balance.boi.database.data.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert
    suspend fun insert(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Query("SELECT * FROM Account")
    fun getAccounts(): Flow<List<Account>>

    @Query("SELECT * FROM Account WHERE institutionId =:institutionId")
    fun getAccountsForInstitution(institutionId: Int): Flow<List<Account>>
}