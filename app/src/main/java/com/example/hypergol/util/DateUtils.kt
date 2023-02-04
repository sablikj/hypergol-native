package com.example.hypergol.util

import android.annotation.SuppressLint
import kotlinx.datetime.Clock
import kotlinx.datetime.toInstant
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.toJavaDuration

fun formatDate(dateUTC: String?) : String {
    if (dateUTC.isNullOrEmpty()) return "No date"

    val date = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(dateUTC)
    val newFormat = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
    return date?.let { newFormat.format(it) }.orEmpty()
}

fun getRemainingTime(date: String, longFormat: Boolean) : String {

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
            return "$days Days ${remaining.minusDays(days).toHours()} Hours " +
                    "${remaining.minusHours(hours).toMinutes()} Minutes \n" +
                    "${remaining.minusMinutes(minutes).seconds} Seconds"
        }else{
            return "$hours Hours ${remaining.minusHours(hours).toMinutes()} Minutes " +
                    "${remaining.minusMinutes(minutes).seconds} Seconds"
        }
    }
}