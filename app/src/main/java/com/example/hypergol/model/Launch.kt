package com.example.hypergol.model

import androidx.room.*
import com.example.hypergol.model.common.*
import com.example.hypergol.util.Constants.UPCOMING_LAUNCHES_TABLE
import com.example.hypergol.util.formatDate
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
    @Embedded
    val status: Status?,
    val net: String?,
    val formattedNet: String? = formatDate(net),
    @Embedded
    val launch_service_provider: LaunchProvider?,
    @Embedded
    val rocket: Rocket?,
    @Embedded
    val mission: Mission?,
    @Embedded
    val pad: Pad?,
    val image: String = "app/src/main/res/drawable/ic_placeholder.xml"
)
