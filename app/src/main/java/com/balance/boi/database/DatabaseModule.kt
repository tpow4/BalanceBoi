package com.balance.boi.database

import android.content.Context
import androidx.room.Room
import com.balance.boi.database.dao.AccountDao
import com.balance.boi.database.dao.BalanceDao
import com.balance.boi.database.dao.InstitutionDao
import com.balance.boi.utils.CipherUtils.getFactoryForPassphrase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app-database"
        )
            .openHelperFactory(getFactoryForPassphrase())
            .build()
    }

    @Provides
    @Singleton
    fun provideAccountDao(appDatabase: AppDatabase): AccountDao {
        return appDatabase.accountDao()
    }

    @Provides
    @Singleton
    fun provideBalanceDao(appDatabase: AppDatabase): BalanceDao {
        return appDatabase.balanceDao()
    }

    @Provides
    @Singleton
    fun provideInstitutionDao(appDatabase: AppDatabase): InstitutionDao {
        return appDatabase.institutionDao()
    }
}