package com.dariobabic.revoultdemo.di.components

import com.dariobabic.revoultdemo.di.modules.ActivityModule
import com.dariobabic.revoultdemo.ui.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun injectMainActivity(mainActivity: MainActivity)
}