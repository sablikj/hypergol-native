package com.example.hypergol.model.launch

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.util.Constants.LAUNCH_REMOTE_KEYS_TABLE

@Entity(tableName = LAUNCH_REMOTE_KEYS_TABLE)
data class LaunchRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevOffset: Int?,
    val nextOffset: Int?
)