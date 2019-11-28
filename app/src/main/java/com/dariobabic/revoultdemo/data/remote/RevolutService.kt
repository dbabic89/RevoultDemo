package com.dariobabic.revoultdemo.data.remote

import com.dariobabic.revoultdemo.data.RevolutBaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RevolutService {
    @GET("latest")
    fun getLatestCurrencyRates(@Query("base") baseCurrencyCode: String): Observable<RevolutBaseResponse>
}