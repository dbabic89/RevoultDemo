package com.dariobabic.revoultdemo.ui.converter.adapter

import com.dariobabic.revoultdemo.domain.model.Currency

interface ConverterListener {

    fun onSymbolChanged(currency: Currency)
}