package com.example.hypergol.model.agency

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.util.Constants

@Entity(tableName = Constants.AGENCY_REMOTE_KEYS_TABLE)
data class AgencyRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevOffset: Int?,
    val nextOffset: Int?
)