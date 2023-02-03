package com.example.hypergol.model.common

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("rocket")
data class Rocket(
    @ColumnInfo(name = "rocket_id")
    val id: Int = 0,
    @Embedded
    val configuration: Configuration? = Configuration()
)

@Serializable
@SerialName("configuration")
data class Configuration(
    @ColumnInfo(name = "rocket_config_id")
    val id: Int = 0,
    @ColumnInfo(name = "rocket_name")
    val name: String? = "",
    val family: String? = "",
    val full_name: String? = "",
    val variant: String? = "",
    @ColumnInfo(name = "rocket_description")
    val description: String? = "",
    @Embedded
    val manufacturer: Manufacturer? = Manufacturer(),
    val length: Double? = 0.0,
    val diameter: Double? = 0.0,
    val launch_cost: String? = "",
    val leo_capacity: Int? = 0,
    val geo_capacity: Int? = 0,
    @ColumnInfo(name = "rocket_image")
    val image_url: String? = "",
    @ColumnInfo(name = "rocket_count")
    val total_launch_count: Int? = 0
)

@Serializable
@SerialName("manufacturer")
data class Manufacturer(
    @ColumnInfo(name = "manufacturer_name")
    val name: String? = ""
)