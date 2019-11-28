package com.dariobabic.revoultdemo.ui.converter.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dariobabic.revoultdemo.R
import com.dariobabic.revoultdemo.domain.model.Currency
import com.dariobabic.revoultdemo.utils.Constants.BASE_CURRENCY_EURO
import com.dariobabic.revoultdemo.utils.ResourcesUtils

class ConverterAdapter(private val listener: ConverterListener) :
    RecyclerView.Adapter<CurrencyViewHolder>() {

    enum class PayLoad {
        VALUE
    }

    private var items: ArrayList<Currency>? = null
    var currentBaseSymbol = BASE_CURRENCY_EURO
    var currentBaseValue = 100.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_rate, parent, false)
        return CurrencyViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = items?.get(position) ?: return
        val symbol = currency.symbol
        holder.imageCurrencyFlag.setImageResource(
            ResourcesUtils.getCurrencyFlagId(
                holder.currencyValue.context,
                symbol
            )
        )
        holder.textCurrencyCode.text = symbol
        holder.textCurrencyName.setText(
            ResourcesUtils.getCurrencyNameId(
                holder.currencyValue.context,
                symbol
            )
        )

        holder.currencyValue.setText(String.format("%.2f", currency.value))

        holder.view.setOnClickListener {
            holder.currencyValue.requestFocus()
        }

        holder.currencyValue.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) return@setOnFocusChangeListener
            moveItem(position)
        }

        if (position == 0) {
            currentBaseSymbol = currency.symbol
            currentBaseValue = currency.value
            holder.setIsRecyclable(false)
        } else {
            holder.setIsRecyclable(true)
        }

        holder.currencyValue.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.isNullOrEmpty() || (currency.symbol != currentBaseSymbol) || position != 0 || !holder.currencyValue.isFocused) return
                currentBaseValue = p0.toString().toDouble()
            }
        })
    }

    fun updateItems(list: ArrayList<Currency>) {
        if (items.isNullOrEmpty()) {
            items = list
            items?.add(0, Currency(currentBaseSymbol, currentBaseValue))
        } else {
            items?.forEach { currency ->
                val symbol = currency.symbol
                var value = currentBaseValue
                list.forEach {
                    if (symbol == it.symbol) {
                        value = it.value * currentBaseValue
                    }
                }
                currency.value = value
            }
        }
        notifyItemRangeChanged(1, itemCount - 1, PayLoad.VALUE)
    }

    private fun moveItem(fromPosition: Int) {
        if (fromPosition == 0) return

        items?.removeAt(fromPosition).also {
            val currency = it ?: return@also
            items?.add(0, currency)
            listener.onSymbolChanged(currency)
        }

        notifyItemMoved(fromPosition, 0)
        notifyItemChanged(0)
    }
}