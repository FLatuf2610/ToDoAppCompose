package com.example.todocompose.common

import java.text.SimpleDateFormat
import java.util.Locale

object Constants {


    const val SPLASH_SCREEN = "splashScreen"
    const val ON_BOARD_SCREEN = "onBoard"
    const val TO_DO_SCREEN = "toDo"
    const val ADD_SCREEN = "addScreen"

    const val USER_NAME = "user_name"
    const val IS_FIRST_TIME = "is_first_time"

    fun convertHour(hour: String): String {
        val format24 = SimpleDateFormat("HH:mm", Locale.getDefault())
        val format12 = SimpleDateFormat("hh:mm a", Locale.getDefault())

        val hora24Date = format24.parse(hour)
        return format12.format(hora24Date!!)
    }

}