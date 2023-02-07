package com.example.hypergol.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.hypergol.model.common.Agency
import kotlinx.coroutines.flow.Flow

@Dao
interface AgencyDao {
    @Query("SELECT * FROM launch_service_provider")
    fun getAgencies(): PagingSource<Int, Agency>

    @Query("SELECT * FROM launch_service_provider WHERE launch_provider_id = :id")
    fun getAgencyDetail(id: Int): Flow<Agency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAgencies(agencies: List<Agency>)

    @Query("DELETE FROM launch_service_provider")
    suspend fun deleteAllAgencies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detail: Agency)

    @Update
    suspend fun update(detail: Agency)

    @Delete
    suspend fun delete(detail: Agency)
}