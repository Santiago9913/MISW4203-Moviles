package com.grupo3.vinilos.utils

import java.util.Date

fun parseDateToDDMMYYYY(date: Date):String{
    return "${date.day + 1}/${date.month + 1}/${date.year}"
}