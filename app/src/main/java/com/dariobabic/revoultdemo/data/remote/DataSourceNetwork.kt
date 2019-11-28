package com.dariobabic.revoultdemo.data.remote

import com.dariobabic.revoultdemo.data.DataSource
import com.dariobabic.revoultdemo.data.RevolutBaseResponse
import io.reactivex.Observable
import javax.inject.Inject

class DataSourceNetwork @Inject constructor(private val revolutService: RevolutService) : DataSource {

    override fun getLatestCurrencyRates(base: String): Observable<RevolutBaseResponse> {
        return revolutService.getLatestCurrencyRates(base)
    }
}