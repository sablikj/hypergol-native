package com.example.hypergol.model.rocket

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.util.Constants

@Entity(tableName = Constants.ROCKET_REMOTE_KEYS_TABLE)
data class RocketRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevOffset: Int?,
    val nextOffset: Int?
)