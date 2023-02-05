package com.example.hypergol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hypergol.model.launch.LaunchRemoteKeys

@Dao
interface LaunchRemoteKeysDao {

    @Query("SELECT * FROM launch_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: String): LaunchRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<LaunchRemoteKeys>)

    @Query("DELETE FROM launch_remote_keys")
    suspend fun deleteAllRemoteKeys()
}