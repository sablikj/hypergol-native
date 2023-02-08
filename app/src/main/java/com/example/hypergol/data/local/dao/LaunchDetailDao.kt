package com.example.hypergol.data.local.dao

import androidx.room.*
import com.example.hypergol.model.launch.LaunchDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDetailDao {

    @Query("SELECT * FROM launch_detail_table WHERE launch_detail_id = :id")
    fun getLaunchDetail(id: String): Flow<LaunchDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detail: LaunchDetail)

    @Update
    suspend fun update(detail: LaunchDetail)

    @Delete
    suspend fun delete(detail: LaunchDetail)

    @Query("DELETE FROM launch_detail_table")
    suspend fun deleteAllLaunchDetails()
}