package com.balance.boi.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.balance.boi.database.data.Institution
import kotlinx.coroutines.flow.Flow

@Dao
interface InstitutionDao {
    @Insert
    suspend fun insert(institution: Institution)

    @Delete
    suspend fun deleteInstitution(institution: Institution)

    @Query("SELECT * FROM Institution")
    fun getAll(): Flow<List<Institution>>
}