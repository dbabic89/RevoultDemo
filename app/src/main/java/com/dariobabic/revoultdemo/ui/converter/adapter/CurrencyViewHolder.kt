package com.dariobabic.revoultdemo.ui.converter.adapter

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_rate.view.*

class CurrencyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val imageCurrencyFlag: AppCompatImageView = view.imageCurrencyFlag
    val textCurrencyCode: TextView = view.textCurrencyCode
    val textCurrencyName: TextView = view.textCurrencyName
    val currencyValue: EditText = view.currencyValue
}