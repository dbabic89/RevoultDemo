package com.dariobabic.revoultdemo.domain.use_cases

import io.reactivex.Observable

abstract class ObservableUseCase<T, in Params> {

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    fun getUseCaseObservable(params: Params? = null) = this.buildUseCaseObservable(params)
}