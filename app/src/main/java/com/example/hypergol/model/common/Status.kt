package com.example.hypergol.model.common

import androidx.room.ColumnInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("status")
data class Status (
    @ColumnInfo(name = "status_name")
    val name: String?,
    @ColumnInfo(name = "status_description")
    val description: String?
)