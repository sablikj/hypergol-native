package com.example.hypergol.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.util.Constants.UPCOMING_LAUNCHES_TABLE
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable // Used for JSON to obj conversion
@SerialName("results") // JSON name
@Entity(tableName = UPCOMING_LAUNCHES_TABLE)
data class UpcomingLaunch(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val url: String,
    val name: String,
    val net: LocalDateTime, // Serializable Date datatype
    @Embedded
    val launch_service_provider: LaunchProvider,
    @Embedded
    val rocket: Rocket,
    @Embedded
    val mission: Mission,
    @Embedded
    val pad: Pad,
    val image_url: String,
)

@Serializable
@SerialName("launch_service_provider")
data class LaunchProvider(
    val id: String,
    val name: String,
    val type: String
)

@Serializable
@SerialName("rocket")
data class Rocket(
    val id: String,
    val name: String,
    val family: String,
    val full_name: String,
    val variant: String
)

@Serializable
@SerialName("mission")
data class Mission(
    val id: String,
    val name: String,
    val description: String,
    val launch_designator: String,
    val type: String,
)

@Serializable
@SerialName("pad")
data class Pad(
    val id: String,
    val name: String,
)