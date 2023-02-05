package com.example.hypergol.model.launch

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.util.Constants.UPCOMING_LAUNCH_REMOTE_KEYS_TABLE

@Entity(tableName = UPCOMING_LAUNCH_REMOTE_KEYS_TABLE)
data class UpcomingLaunchRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevOffset: Int?,
    val nextOffset: Int?
)