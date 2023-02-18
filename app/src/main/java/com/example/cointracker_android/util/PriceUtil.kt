package com.example.cointracker_android.util

import java.text.DecimalFormat

object PriceUtil {
    fun formatDoubleToCurrency(amount: Double) : String {
        return formatNumber(amount) + " " + Constants.Defaults.CURRENCY_SYMBOL
    }

    private fun formatNumber(amount: Double) : String {
        val decimalFormat = DecimalFormat("###,###.##")
        decimalFormat.isDecimalSeparatorAlwaysShown = true
        decimalFormat.minimumFractionDigits = 2
        return decimalFormat.format(amount)
    }

    fun formatPercentage(amount: Double) : String {
        return "${String.format("%.2f", amount)} %"
    }
}