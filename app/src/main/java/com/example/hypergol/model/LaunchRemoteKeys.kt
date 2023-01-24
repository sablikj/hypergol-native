package com.example.hypergol.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.util.Constants.LAUNCH_REMOTE_KEYS

// TODO: Use api prev/next fields instead
@Entity(tableName = LAUNCH_REMOTE_KEYS)
data class LaunchRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)