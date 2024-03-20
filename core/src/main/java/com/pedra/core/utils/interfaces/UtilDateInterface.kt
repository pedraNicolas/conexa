package com.pedra.core.utils.interfaces

import java.util.Date
import java.util.Locale

interface UtilDateInterface {

    fun getCurrentDate(): Date

    fun dateToString(date: Date?, pattern: String, locale: Locale?): String
}