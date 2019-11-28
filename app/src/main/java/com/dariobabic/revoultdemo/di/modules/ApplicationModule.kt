package com.dariobabic.revoultdemo.di.modules

import com.dariobabic.revoultdemo.RevolutDemoApplication
import com.dariobabic.revoultdemo.di.scopes.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val revolutDemoApplication: RevolutDemoApplication) {

    @Provides
    @PerApplication
    fun providesRevolutDemoApplication(): RevolutDemoApplication = revolutDemoApplication
}