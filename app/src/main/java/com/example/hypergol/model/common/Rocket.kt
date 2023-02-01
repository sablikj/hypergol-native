package com.example.hypergol.model.common

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("rocket")
data class Rocket(
    @ColumnInfo(name = "rocket_id")
    val id: Int,
    @Embedded
    val configuration: Configuration?
)

@Serializable
@SerialName("configuration")
data class Configuration(
    @ColumnInfo(name = "rocket_config_id")
    val id: Int,
    @ColumnInfo(name = "rocket_name")
    val name: String?,
    val family: String?,
    val full_name: String?,
    val variant: String?,
    @ColumnInfo(name = "rocket_description")
    val description: String?,
    @Embedded
    val manufacturer: Manufacturer?,
    val length: Float?,
    val diameter: Float?,
    val launch_cost: String?,
    val leo_capacity: Int?,
    val geo_capacity: Int?,
    @ColumnInfo(name = "rocket_image")
    val image_url: String?,
    @ColumnInfo(name = "rocket_count")
    val total_launch_count: Int?
)

@Serializable
@SerialName("manufacturer")
data class Manufacturer(
    @ColumnInfo(name = "manufacturer_name")
    val name: String?
)