package com.arlib.compose.domain.usecases

import android.os.Build
import java.time.LocalTime
import java.util.Calendar
import javax.inject.Inject

class ArLibGreetingsUseCase @Inject constructor() {
    operator fun invoke(): String {
        val currentHour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now().hour
        } else {
            Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        }

        return when (currentHour) {
            in 5..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..18 -> "Good Evening"
            in 19..23, in 0..4 -> "Good Night"
            else -> "Hello"  // This case shouldn't occur, but keeping a friendly default
        }
    }
}