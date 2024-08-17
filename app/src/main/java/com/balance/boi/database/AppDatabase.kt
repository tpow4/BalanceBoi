package com.balance.boi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.balance.boi.database.dao.AccountDao
import com.balance.boi.database.dao.BalanceDao
import com.balance.boi.database.dao.InstitutionDao
import com.balance.boi.database.data.Account
import com.balance.boi.database.data.Balance
import com.balance.boi.database.data.Converters
import com.balance.boi.database.data.Institution

@Database(
    entities = [Institution::class, Account::class, Balance::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun institutionDao(): InstitutionDao
    abstract fun accountDao(): AccountDao
    abstract fun balanceDao(): BalanceDao
}