package com.example.hypergol.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hypergol.model.launch.Launch

@Dao
interface LaunchDao {

    @Query("SELECT * FROM launch_table WHERE net > datetime('now') ORDER BY net")
    fun getAllUpcomingLaunches(): PagingSource<Int, Launch>

    @Query("SELECT * FROM launch_table")
    fun getLaunches(): PagingSource<Int, Launch>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLaunches(launches: List<Launch>)

    @Query("DELETE FROM launch_table WHERE net > datetime('now')")
    suspend fun deleteAllUpcomingLaunches()

    @Query("DELETE FROM launch_table")
    suspend fun deleteAllLaunches()
}