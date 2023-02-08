package com.example.hypergol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hypergol.model.launch.LaunchRemoteKeys
import com.example.hypergol.model.rocket.RocketRemoteKeys

@Dao
interface RocketRemoteKeysDao {

    @Query("SELECT * FROM rocket_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): RocketRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<RocketRemoteKeys>)

    @Query("DELETE FROM rocket_remote_keys")
    suspend fun deleteAllRemoteKeys()
}