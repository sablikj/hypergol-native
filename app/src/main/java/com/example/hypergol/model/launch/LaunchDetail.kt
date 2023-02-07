package com.example.hypergol.model

import androidx.room.*
import com.example.hypergol.model.common.*
import com.example.hypergol.model.common.Agency
import com.example.hypergol.model.common.Rocket
import com.example.hypergol.util.Constants
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = Constants.LAUNCH_DETAIL_TABLE)
data class LaunchDetail(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "launch_detail_id")
    val id: String = "",
    val url: String? = "",
    @ColumnInfo(name = "launch_detail_name")
    val name: String? = "",
    @Embedded
    val status: Status? = Status(),
    //@Embedded
    //@TypeConverters(Converters::class)
    //val updates: MutableList<Update>,
    val net: String? = "",
    val window_end: String? = "",
    val window_start: String? = "",
    @Embedded
    val launch_service_provider: Agency? = Agency(),
    @Embedded
    val rocket: Rocket? = Rocket(),
    @Embedded
    val mission: Mission? = Mission(),
    @Embedded
    val pad: Pad? = Pad(),
    @SerialName("image")
    val image_url: String? = ""
    )

@Serializable
data class Update(
    val profile_image: String?,
    val comment: String?,
    val info_url: String?,
    val created_by: String?,
    val created_on: String?
)