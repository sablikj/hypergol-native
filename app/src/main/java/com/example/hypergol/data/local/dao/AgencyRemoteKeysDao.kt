package com.example.hypergol.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hypergol.model.agency.AgencyRemoteKeys

@Dao
interface AgencyRemoteKeysDao {

    @Query("SELECT * FROM agency_remote_keys WHERE id =:id")
    suspend fun getRemoteKeys(id: Int): AgencyRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<AgencyRemoteKeys>)

    @Query("DELETE FROM agency_remote_keys")
    suspend fun deleteAllRemoteKeys()
}