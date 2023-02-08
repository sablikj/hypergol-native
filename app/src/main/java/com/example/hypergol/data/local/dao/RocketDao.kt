package com.example.hypergol.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.hypergol.model.rocket.Rocket
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {
    @Query("SELECT * FROM rocket_detail_table")
    fun getRockets(): PagingSource<Int, Rocket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRockets(rockets: List<Rocket>)

    @Query("DELETE FROM rocket_detail_table")
    suspend fun deleteAllRockets()

    @Query("SELECT * FROM rocket_detail_table WHERE id = :id")
    fun getRocketDetail(id: Int): Flow<Rocket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(detail: Rocket)

    @Update
    suspend fun update(detail: Rocket)

    @Delete
    suspend fun delete(detail: Rocket)

    @Query("DELETE FROM rocket_detail_table")
    suspend fun deleteAllRocketDetails()
}