package com.dariobabic.revoultdemo.ui.converter

import android.util.Log
import com.dariobabic.revoultdemo.domain.mapper.LatestCurrenciesMapper
import com.dariobabic.revoultdemo.domain.model.LatestCurrencies
import com.dariobabic.revoultdemo.domain.use_cases.GetLatestCurrencyRates
import com.dariobabic.revoultdemo.utils.Constants.BASE_CURRENCY_EURO
import com.dariobabic.revoultdemo.utils.Constants.REVOLUT_APPLICATION
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ConverterPresenter @Inject constructor(private val getLatestCurrencyRates: GetLatestCurrencyRates) :
    ConverterContract.Presenter {

    lateinit var view: ConverterContract.View
    private var compositeDisposable = CompositeDisposable()
    var currentBaseCurrency = BASE_CURRENCY_EURO

    fun getLatestCurrencyRates() {
        Observable.interval(1000, TimeUnit.MILLISECONDS)
            .flatMap {
                getLatestCurrencyRates.getUseCaseObservable(currentBaseCurrency)
            }.flatMap {
                Observable.just(LatestCurrenciesMapper().mapToEntity(it))
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<LatestCurrencies> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onNext(values: LatestCurrencies) {
                    view.updateValues(values.currencyRates)
                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    val localizedMessage = e.localizedMessage ?: return
                    Log.i(REVOLUT_APPLICATION, localizedMessage)
                }
            })
    }

    override fun attachView(view: ConverterContract.View) {
        this.view = view
    }

    override fun detachView() {
        compositeDisposable.clear()
    }
}