package com.example.hypergol.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hypergol.model.UpcomingLaunch

@Dao
interface UpcomingLaunchDao {

    @Query("SELECT * FROM upcoming_launches_table")
    fun getAllUpcomingLaunches(): PagingSource<Int, UpcomingLaunch>

    // Paginating through API as well as db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpcomingLaunches(launches: List<UpcomingLaunch>)

    @Query("DELETE FROM upcoming_launches_table")
    suspend fun deleteAllUpcomingLaunches()
}