package com.dariobabic.revoultdemo.di.components

import com.dariobabic.revoultdemo.RevolutDemoApplication
import com.dariobabic.revoultdemo.di.modules.ApplicationModule
import com.dariobabic.revoultdemo.di.scopes.PerApplication
import dagger.Component


@PerApplication
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun injectRevolutApplication(revolutDemoApplication: RevolutDemoApplication)
}