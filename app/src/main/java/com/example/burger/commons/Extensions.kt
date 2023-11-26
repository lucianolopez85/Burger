package com.example.burger.commons

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun Double.formattedCurrency(): String? {
    val nf: NumberFormat = DecimalFormat(
        "R$ #,##0.00", DecimalFormatSymbols(
            Locale("pt", "BR")
        )
    )
    return nf.format(this)
}