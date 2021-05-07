package com.berteodosio.seriemesmo.presentation.episodeDetails.formatter

import com.berteodosio.seriemesmo.presentation.custom.TAG
import com.berteodosio.seriemesmo.presentation.custom.logger.AppLogger
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DefaultDateFormatter(
    private val userDateFormat: DateFormat
) : DateFormatter {

    override fun format(date: String): String = try {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateObject: Date = oldFormat.parse(date)!!      // in case of null, we force an exception that is handled
        userDateFormat.format(dateObject)
    } catch (e: Exception) {
        AppLogger.e(TAG, "Error formatting date", e)
        date
    }
}