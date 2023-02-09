package com.example.hypergol.model.launch

import androidx.room.*
import com.example.hypergol.model.common.*
import com.example.hypergol.model.common.Agency
import com.example.hypergol.model.common.LaunchRocket
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
    val net: String? = "",
    val window_end: String? = "",
    val window_start: String? = "",
    @Embedded
    val launch_service_provider: Agency? = Agency(),
    @Embedded
    val rocket: LaunchRocket? = LaunchRocket(),
    @Embedded
    val mission: Mission? = Mission(),
    @Embedded
    val pad: Pad? = Pad(),
    @SerialName("image")
    val image_url: String? = ""
    )