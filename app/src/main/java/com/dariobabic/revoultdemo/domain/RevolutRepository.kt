package com.dariobabic.revoultdemo.domain

import com.dariobabic.revoultdemo.data.RevolutBaseResponse
import com.dariobabic.revoultdemo.data.remote.DataSourceNetwork
import io.reactivex.Observable
import javax.inject.Inject

class RevolutRepository @Inject constructor(private val dataSourceNetwork: DataSourceNetwork) {

    fun getLatestCurrencyRates(baseRate: String): Observable<RevolutBaseResponse> {
        return dataSourceNetwork.getLatestCurrencyRates(baseRate)
    }
}