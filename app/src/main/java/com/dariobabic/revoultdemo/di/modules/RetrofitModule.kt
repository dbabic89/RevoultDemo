package com.dariobabic.revoultdemo.di.modules

import com.dariobabic.revoultdemo.data.remote.RevolutService
import com.dariobabic.revoultdemo.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object RetrofitModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun gSon() = Gson()

    @Provides
    @Reusable
    @JvmStatic
    internal fun retrofit(okHttpClient: OkHttpClient, gson: Gson) =
        Retrofit.Builder().baseUrl(Constants.REVOLUT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient).build()


    @Provides
    @Reusable
    @JvmStatic
    internal fun revolutService(retrofit: Retrofit) =
        retrofit.create(RevolutService::class.java)
}