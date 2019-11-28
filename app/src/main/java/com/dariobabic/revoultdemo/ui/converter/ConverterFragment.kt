package com.dariobabic.revoultdemo.ui.converter

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dariobabic.revoultdemo.R
import com.dariobabic.revoultdemo.RevolutDemoApplication
import com.dariobabic.revoultdemo.data.remote.RevolutService
import com.dariobabic.revoultdemo.di.components.DaggerFragmentComponent
import com.dariobabic.revoultdemo.domain.model.Currency
import com.dariobabic.revoultdemo.ui.converter.adapter.ConverterAdapter
import com.dariobabic.revoultdemo.ui.converter.adapter.ConverterListener
import kotlinx.android.synthetic.main.fragment_converter.*
import javax.inject.Inject

class ConverterFragment : Fragment(), ConverterContract.View {

    @Inject
    lateinit var revolutService: RevolutService
    @Inject
    lateinit var converterPresenter: ConverterPresenter
    lateinit var adapter: ConverterAdapter

    private val listener = object :
        ConverterListener {

        override fun onSymbolChanged(currency: Currency) {
            converterPresenter.currentBaseCurrency = currency.symbol
            recyclerView.scrollToPosition(0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        injectFragment()
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        converterPresenter.detachView()
    }

    override fun updateValues(value: ArrayList<Currency>) {
        adapter.updateItems(value)
    }

    private fun injectFragment() {
        val component = DaggerFragmentComponent
            .builder()
            .applicationComponent(
                RevolutDemoApplication
                    .getApplication(activity as Activity)
                    .getApplicationComponent()
            )
            .build()
        component.injectConverterFragment(this)
    }

    private fun setAdapter() {
        adapter = ConverterAdapter(listener)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun setPresenter() {
        converterPresenter.attachView(this)
        converterPresenter.getLatestCurrencyRates()
    }
}
