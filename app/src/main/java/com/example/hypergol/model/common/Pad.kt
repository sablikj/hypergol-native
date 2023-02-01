package com.example.hypergol.model.common

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
@SerialName("pad")
data class Pad (
    @ColumnInfo(name = "pad_id")
    val id: Int,
    @ColumnInfo(name = "pad_url")
    val url: String?,
    val agency_id: Int?,
    @ColumnInfo(name = "pad_name")
    val name: String?,
    val map_image: String?,
    @ColumnInfo(name = "pad_launch_count")
    val total_launch_count: Int?,
    @Embedded
    val location: Location?
)

@Serializable
@SerialName("location")
data class Location (
    @ColumnInfo(name = "pad_location_name")
    val name: String?,
    val country_code: String?
)