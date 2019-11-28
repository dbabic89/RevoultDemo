package com.dariobabic.revoultdemo

import android.app.Activity
import android.app.Application
import com.akaita.java.rxjava2debug.RxJava2Debug
import com.dariobabic.revoultdemo.di.components.ApplicationComponent
import com.dariobabic.revoultdemo.di.components.DaggerApplicationComponent

class RevolutDemoApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        RxJava2Debug.enableRxJava2AssemblyTracking()

        component = DaggerApplicationComponent.builder().build()
        component.injectRevolutApplication(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        fun getApplication(activity: Activity) = activity.application as RevolutDemoApplication
    }
}