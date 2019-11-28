package com.dariobabic.revoultdemo.domain.use_cases

import com.dariobabic.revoultdemo.data.RevolutBaseResponse
import com.dariobabic.revoultdemo.domain.RevolutRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetLatestCurrencyRates @Inject constructor(
    private val repository: RevolutRepository
) : ObservableUseCase<RevolutBaseResponse, String>() {

    override fun buildUseCaseObservable(params: String?): Observable<RevolutBaseResponse> {
        val baseCurrency = params ?: return Observable.just(null)
        return repository.getLatestCurrencyRates(baseCurrency)
    }
}