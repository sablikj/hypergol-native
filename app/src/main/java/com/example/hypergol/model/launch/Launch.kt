package com.example.hypergol.model.launch

import androidx.room.*
import com.example.hypergol.model.common.*
import com.example.hypergol.model.common.LaunchRocket
import com.example.hypergol.util.Constants.LAUNCH_TABLE
import com.example.hypergol.util.formatDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable // Used for JSON to obj conversion
@SerialName("results") // JSON name
@Entity(tableName = LAUNCH_TABLE)
data class Launch(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "upcoming_launch_id")
    val id: String,
    @ColumnInfo(name = "upcoming_launch_name")
    val name: String,
    @Embedded
    val status: Status?,
    val net: String?,
    val formattedNet: String? = formatDate(net, false),
    @Embedded
    val launch_service_provider: Agency?,
    @Embedded
    val rocket: LaunchRocket?,
    @Embedded
    val mission: Mission?,
    @Embedded
    val pad: Pad?,
    val image: String = "app/src/main/res/drawable/ic_placeholder.xml"
)

@Serializable
data class LaunchResponse(val results: List<Launch>, val count: Int = 0) {
    fun distinct(): List<Launch> {
        return results.take(count)
    }
}