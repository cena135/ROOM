package uts.c14220057.room.helper

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

object DateHelper {
    fun getCurrentDate() : String {
        val dateFormat = SimpleDateFormat(
            "yyyy/MM/dd HH:mm:ss",
        Locale.getDefault()
        )
        val date = Date()
        return dateFormat.format(date)
    }

}