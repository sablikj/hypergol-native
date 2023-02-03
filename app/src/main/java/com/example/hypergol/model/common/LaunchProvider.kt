package com.example.hypergol.model.common

import androidx.room.ColumnInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("launch_service_provider")
data class LaunchProvider(
    @ColumnInfo(name = "launch_provider_id")
    val id: Int = 0,
    @ColumnInfo(name = "launch_provider_name")
    val name: String? = "",
    @ColumnInfo(name = "launch_provider_type")
    val type: String? = "",
    @ColumnInfo(name = "launch_provider_description")
    val description: String? = "",
    @ColumnInfo(name = "launch_provider_administrator")
    val administrator: String? = "",
    val founding_year: Int? = 0,
    @ColumnInfo(name = "launch_provider_count")
    val total_launch_count: Int? = 0,
    val successful_launches: Int? = 0,
    val failed_launches: Int? = 0,
    @ColumnInfo(name = "launch_provider_image")
    val image_url: String? = ""
)