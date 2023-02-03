package com.example.hypergol.model.common

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("mission")
data class Mission(
    @ColumnInfo(name = "mission_id")
    val id: Int = 0,
    @ColumnInfo(name = "mission_name")
    val name: String? = "",
    @ColumnInfo(name = "mission_description")
    val description: String? = "",
    @ColumnInfo(name = "mission_designator")
    val launch_designator: String? = "",
    @ColumnInfo(name = "mission_type")
    val type: String? = "",
    @Embedded
    val orbit: Orbit? = Orbit()
)

@Serializable
@SerialName("orbit")
data class Orbit(
    @ColumnInfo(name = "orbit_name")
    val name: String? = ""
)