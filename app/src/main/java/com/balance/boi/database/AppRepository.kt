package com.balance.boi.database

import com.balance.boi.database.data.Account
import com.balance.boi.database.data.Balance
import com.balance.boi.database.data.Institution
import javax.inject.Inject

class AppRepository @Inject constructor(private val db: AppDatabase)
{
    fun getAllAccounts() = db.accountDao().getAccounts()
    suspend fun insertAccount(account: Account) = db.accountDao().insert(account)
    suspend fun deleteAccount(account: Account) = db.accountDao().delete(account)


    fun getAllInstitutions() = db.institutionDao().getAll()
    suspend fun insertInstitution(institution: Institution) = db.institutionDao().insert(institution)
    suspend fun deleteInstitution(institution: Institution) = db.institutionDao().deleteInstitution(institution)

    suspend fun insertBalance(balance: Balance) = db.balanceDao().insert(balance)
    suspend fun deleteBalance(balance: Balance) = db.balanceDao().delete(balance)

}