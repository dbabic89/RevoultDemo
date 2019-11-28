package com.dariobabic.revoultdemo.di.components

import com.dariobabic.revoultdemo.di.modules.NetworkModule
import com.dariobabic.revoultdemo.di.modules.RetrofitModule
import com.dariobabic.revoultdemo.ui.converter.ConverterFragment
import dagger.Component

@Component(
    modules = [RetrofitModule::class, NetworkModule::class],
    dependencies = [ApplicationComponent::class]
)
interface FragmentComponent {

    fun injectConverterFragment(converterFragment: ConverterFragment)
}