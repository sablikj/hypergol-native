package com.example.hypergol.model

import androidx.room.*
import com.example.hypergol.util.Constants.UPCOMING_LAUNCHES_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable // Used for JSON to obj conversion
@SerialName("results") // JSON name
@Entity(tableName = UPCOMING_LAUNCHES_TABLE)
data class Launch(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "upcoming_launch_id")
    val id: String,
    @ColumnInfo(name = "upcoming_launch_name")
    val name: String,
    val net: String?,
    @Embedded
    val launch_service_provider: LaunchProvider,
    @Embedded
    val rocket: Rocket?,
    @Embedded
    val mission: Mission?,
    @Embedded
    val pad: Pad?,
    val image: String = "app/src/main/res/drawable/ic_placeholder.xml"
)

@Serializable
@SerialName("launch_service_provider")
data class LaunchProvider(
    @ColumnInfo(name = "launch_provider_id")
    val id: Int,
    @ColumnInfo(name = "launch_provider_name")
    val name: String?,
    val type: String?
)
//TODO: wrong model structure
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
    val variant: String?
)

@Serializable
@SerialName("mission")
data class Mission(
    @ColumnInfo(name = "mission_id")
    val id: Int,
    @ColumnInfo(name = "mission_name")
    val name: String?,
    @ColumnInfo(name = "mission_type")
    val type: String?,
    @Embedded
    val orbit: Orbit?
)

@Serializable
@SerialName("orbit")
data class Orbit(
    @ColumnInfo(name = "orbit_id")
    val id: Int,
    @ColumnInfo(name = "orbit_name")
    val name: String?
)

@Serializable
@SerialName("pad")
data class Pad(
    @ColumnInfo(name = "pad_id")
    val id: Int,
    @ColumnInfo(name = "pad_name")
    val name: String?,
)