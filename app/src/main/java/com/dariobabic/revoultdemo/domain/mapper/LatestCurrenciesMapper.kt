package com.dariobabic.revoultdemo.domain.mapper

import com.dariobabic.revoultdemo.data.RevolutBaseResponse
import com.dariobabic.revoultdemo.domain.model.Currency
import com.dariobabic.revoultdemo.domain.model.LatestCurrencies
import com.dariobabic.revoultdemo.utils.Constants.BASE_CURRENCY_EURO

class LatestCurrenciesMapper : Mapper<RevolutBaseResponse, LatestCurrencies> {
    override fun mapToEntity(response: RevolutBaseResponse): LatestCurrencies {
        val currencyRates = ArrayList<Currency>()

        val base = response.base ?: BASE_CURRENCY_EURO
        val rates = response.rates
        rates?.forEach {
            currencyRates.add(Currency(it.key, it.value))
        }
        return LatestCurrencies(base, currencyRates)
    }
}