package com.example.hypergol.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable // Used for JSON to obj conversion
@SerialName("results") // JSON name
data class UpcomingLaunch(
    val id: Int,
    val url: String,
    val name: String,
    val net: LocalDateTime, // Serializable Date datatype
    val launch_service_provider: LaunchProvider,
    val rocket: Rocket,
    val mission: Mission,
    val pad: Pad,
    val image_url: String,
)

@Serializable
@SerialName("launch_service_provider")
data class LaunchProvider(
    val id: Int,
    val name: String,
    val type: String
)

@Serializable
@SerialName("rocket")
data class Rocket(
    val id: Int,
    val name: String,
    val family: String,
    val full_name: String,
    val variant: String
)

@Serializable
@SerialName("mission")
data class Mission(
    val id: Int,
    val name: String,
    val description: String,
    val launch_designator: String,
    val type: String,
)

@Serializable
@SerialName("pad")
data class Pad(
    val id: Int,
    val name: String,
)