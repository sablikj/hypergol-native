package com.example.hypergol.util

import kotlinx.datetime.Clock
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.toJavaDuration

fun formatDate(dateUTC: String?, isReadable: Boolean) : String {
    if (dateUTC.isNullOrEmpty()) return "No date"

    if(isReadable){
        val date = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(dateUTC)
        val newFormat = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
        return date?.let { newFormat.format(it) }.orEmpty()
    }
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(dateUTC)
    val newFormat = SimpleDateFormat("dd/MM/yyy HH:mm", Locale.getDefault())
    return date?.let { newFormat.format(it) }.orEmpty()
}

fun isUpcoming(launch: String): Boolean{
    return launch.toInstant() > Clock.System.now()
}

fun getRemainingTime(date: String, longFormat: Boolean) : String {
    if(!date.isNullOrEmpty()){
        val net = date.toInstant()
        val remaining = net.minus(Clock.System.now()).toJavaDuration()

        val days = remaining.toDays()
        val hours = remaining.toHours()
        val minutes = remaining.toMinutes()

        if(!longFormat){
            if(days > 0){
                return "$days Days"
            }
            if(hours > 0){
                return "$hours Hours"
            }else{
                return "$minutes Minutes"
            }
        }else{
            if(days > 0){
                return "T - $days Days ${remaining.minusDays(days).toHours()} Hours " +
                        "${remaining.minusHours(hours).toMinutes()} Minutes \n" +
                        "${remaining.minusMinutes(minutes).seconds} Seconds"
            }else{
                return "T - $hours Hours ${remaining.minusHours(hours).toMinutes()} Minutes " +
                        "${remaining.minusMinutes(minutes).seconds} Seconds"
            }
        }
    }else{
        return "No data"
    }

}