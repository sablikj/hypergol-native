package com.example.hypergol.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hypergol.model.common.*
import com.example.hypergol.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.LAUNCH_DETAIL_TABLE)
data class LaunchDetail(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "launch_detail_id")
    val id: String,
    val url: String?,
    @ColumnInfo(name = "launch_detail_name")
    val name: String?,
    @Embedded
    val status: Status?,
    @Embedded
    val updates: List<Update>?,
    val net: String?,
    val window_end: String?,
    val window_start: String?,
    @Embedded
    val launch_service_provider: LaunchProvider?,
    @Embedded
    val rocket: Rocket?,
    @Embedded
    val mission: Mission?,
    @Embedded
    val pad: Pad?,
    @SerialName("image")
    val image_url: String?
    )

@Serializable
data class Update(
    val profile_image: String?,
    val comment: String?,
    val info_url: String?,
    val created_by: String?,
    val created_on: String?
)