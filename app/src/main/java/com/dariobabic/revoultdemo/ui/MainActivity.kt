package com.dariobabic.revoultdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dariobabic.revoultdemo.R
import com.dariobabic.revoultdemo.di.components.ActivityComponent
import com.dariobabic.revoultdemo.di.components.DaggerActivityComponent


class MainActivity : AppCompatActivity() {

    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        component = DaggerActivityComponent.builder().build()
        component.injectMainActivity(this)
    }

    fun getActivityComponent(): ActivityComponent {
        return component
    }
}
