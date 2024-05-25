package com.grupo3.vinilos.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date


fun parseDateToDDMMYYYY(date: Date):String{
    return "${date.day + 1}/${date.month + 1}/${date.year}"
}

fun convertMillisToLocalDateString(millis: Long?) : String {
    val europeanDatePattern = "dd/MM/yyyy"
    val europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern)
    return if (millis != null)
        europeanDateFormatter.format(Instant
            .ofEpochMilli(millis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate().plusDays(1)).toString()
    else ""
}