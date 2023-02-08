package com.example.hypergol.model.common

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.model.launch.Launch
import com.example.hypergol.util.Constants.AGENCY_TABLE
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = AGENCY_TABLE)
data class Agency(
    @ColumnInfo(name = "launch_provider_id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @ColumnInfo(name = "launch_provider_name")
    val name: String? = "",
    @ColumnInfo(name = "launch_provider_type")
    val type: String? = "",
    @ColumnInfo(name = "launch_provider_country")
    val country_code: String? = "",
    @ColumnInfo(name = "launch_provider_description")
    val description: String? = "",
    @ColumnInfo(name = "launch_provider_administrator")
    val administrator: String? = "",
    val founding_year: Int? = 0,
    @ColumnInfo(name = "launch_provider_launchers")
    val launchers: String? = "",
    @ColumnInfo(name = "launch_provider_spacecraft")
    val spacecraft: String? = "",
    @ColumnInfo(name = "launch_provider_count")
    val total_launch_count: Int? = 0,
    @ColumnInfo(name = "launch_provider_launches_success")
    val successful_launches: Int? = 0,
    @ColumnInfo(name = "launch_provider_launches_fail")
    val failed_launches: Int? = 0,
    @ColumnInfo(name = "launch_provider_landing_success")
    val successful_landings: Int? = 0,
    @ColumnInfo(name = "launch_provider_landing_fail")
    val failed_landings: Int? = 0,
    @ColumnInfo(name = "launch_provider_image")
    val image_url: String? = "",
    @ColumnInfo(name = "launch_provider_logo")
    val logo_url: String? = "",
    @ColumnInfo(name = "launch_provider_info")
    val info_url: String? = "",
    // TODO: Add GSON converter
    //val launcher_list: []
)

@Serializable
data class AgencyResponse(val results: List<Agency>, val count: Int = 0) {
    fun distinct(): List<Agency> {
        return results.take(count)
    }
}