package com.dariobabic.revoultdemo.domain.model

data class LatestCurrencies(
    val baseCurrencySymbol: String,
    val currencyRates: ArrayList<Currency>
)