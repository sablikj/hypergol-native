package com.example.hypergol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hypergol.model.launch.UpcomingLaunchRemoteKeys

@Dao
interface UpcomingLaunchRemoteKeysDao {

    @Query("SELECT * FROM upcoming_launch_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): UpcomingLaunchRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UpcomingLaunchRemoteKeys>)

    @Query("DELETE FROM upcoming_launch_remote_keys")
    suspend fun deleteAllRemoteKeys()
}