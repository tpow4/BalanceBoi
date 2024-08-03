package com.balance.boi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.balance.boi.database.dao.AccountDao
import com.balance.boi.database.dao.BalanceDao
import com.balance.boi.database.dao.InstitutionDao
import com.balance.boi.database.data.Account
import com.balance.boi.database.data.Balance
import com.balance.boi.database.data.Institution
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Database(
    entities = [Institution::class, Account::class, Balance::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
@InstallIn(SingletonComponent::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun institutionDao(): InstitutionDao
    abstract fun accountDao(): AccountDao
    abstract fun balanceDao(): BalanceDao
}