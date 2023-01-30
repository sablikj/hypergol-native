package com.example.hypergol.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.util.Constants.LAUNCH_REMOTE_KEYS_TABLE

// TODO: Use api prev/next fields instead
@Entity(tableName = LAUNCH_REMOTE_KEYS_TABLE)
data class LaunchRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevOffset: Int?,
    val nextOffset: Int?
)