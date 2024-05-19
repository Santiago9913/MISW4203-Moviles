package com.grupo3.vinilos.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.util.Date

fun parseDateToDDMMYYYY(date: Date):String{
    return "${date.day + 1}/${date.month + 1}/${date.year}"
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToLocalDateString(millis: Long?) : String {
    return if (millis != null)
        Instant
            .ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate().toString()
    else ""
}