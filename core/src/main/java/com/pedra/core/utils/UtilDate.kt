package com.pedra.core.utils

import com.pedra.core.utils.interfaces.UtilDateInterface
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class UtilDate : UtilDateInterface {

    companion object {
        val UI_DATE_PATTERN_SHORT = "dd/MM/yy"
        val UI_DATE_PATTERN = "dd-MM-yyyy"
        val COMPLETE_DATE_PATTERN = "dd 'de' MMMM 'de' yyyy"


        private var INSTANCE: UtilDateInterface? = null

        fun getInstance(): UtilDateInterface {
            if (INSTANCE == null) createInstance()
            return INSTANCE!!
        }


        private fun createInstance() {
            if (INSTANCE == null) {
                INSTANCE = UtilDate()
            }
        }
    }

    override fun getCurrentDate(): Date {
        return Calendar.getInstance().time
    }

    override fun dateToString(date: Date?, pattern: String, locale: Locale?): String {
        if (date == null) return ""
        var sdfYear: SimpleDateFormat = if (locale != null) SimpleDateFormat(pattern, locale)
        else SimpleDateFormat(pattern)
        return sdfYear.format(date)
    }


}