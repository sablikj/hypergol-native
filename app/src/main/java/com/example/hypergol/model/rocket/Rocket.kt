package com.example.hypergol.model.rocket

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.model.common.Agency
import com.example.hypergol.util.Constants.ROCKET_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = ROCKET_TABLE)
data class Rocket (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val name: String? = "",
    val active: Boolean = false,
    val reusable: Boolean = false,
    val description: String? = "",
    val family: String? = "",
    val full_name: String? = "",
    @Embedded
    val manufacturer: Agency? = Agency(),
    val length: Double? = 0.0,
    val diameter: Double? = 0.0,
    val launch_cost: String? = "",
    val leo_capacity: Int? = 0,
    val gto_capacity: Int? = 0,
    val maiden_flight: String? = "",
    @ColumnInfo(name = "rocket_image")
    val image_url: String? = "",
    @ColumnInfo(name = "rocket_detail_launches_success")
    val successful_launches: Int? = 0,
    @ColumnInfo(name = "rocket_detail_launches_fail")
    val failed_launches: Int? = 0,
)

@Serializable
data class RocketResponse(val results: List<Rocket>)
