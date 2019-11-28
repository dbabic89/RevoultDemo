package com.dariobabic.revoultdemo.ui.converter

import com.dariobabic.revoultdemo.domain.model.Currency

interface ConverterContract {

    interface View {

        fun updateValues(value: ArrayList<Currency>)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()
    }
}