package com.dariobabic.revoultdemo.di.modules

import android.util.Log
import com.dariobabic.revoultdemo.utils.Constants.REVOLUT_APPLICATION
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i(REVOLUT_APPLICATION, message)
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}