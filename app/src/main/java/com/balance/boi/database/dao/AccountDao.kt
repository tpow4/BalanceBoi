package com.balance.boi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.balance.boi.database.data.Account
import com.balance.boi.database.data.AccountWithBalances
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert
    suspend fun insert(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Transaction
    @Query("SELECT * FROM Account")
    fun getAccounts(): Flow<List<AccountWithBalances>>

    @Transaction
    @Query("SELECT * FROM Account WHERE accountInstitutionId =:institutionId")
    fun getAccountsForInstitution(institutionId: Int): Flow<List<AccountWithBalances>>
}