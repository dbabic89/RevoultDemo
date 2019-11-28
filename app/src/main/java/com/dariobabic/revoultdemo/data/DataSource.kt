package com.dariobabic.revoultdemo.data

import io.reactivex.Observable

interface DataSource {

    fun getLatestCurrencyRates(base: String): Observable<RevolutBaseResponse>
}